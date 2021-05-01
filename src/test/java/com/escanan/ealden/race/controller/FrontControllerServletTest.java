package com.escanan.ealden.race.controller;

import com.escanan.ealden.race.Configurations;
import com.escanan.ealden.race.controller.api.RacesController;
import com.escanan.ealden.race.controller.api.SettingsController;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.escanan.ealden.race.Matchers.jsonResponseOf;
import static com.escanan.ealden.race.controller.FrontControllerServlet.*;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FrontControllerServletTest {
    private FrontControllerServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private BufferedReader reader;

    @Mock
    private PrintWriter writer;

    @Captor
    private ArgumentCaptor<String> jsonResponse;

    @Mock
    private RacesController racesController;

    @Mock
    private SettingsController settingsController;

    @BeforeEach
    void setUp() throws IOException {
        servlet = new FrontControllerServlet();

        Configurations.setRacesController(racesController);
        Configurations.setSettingsController(settingsController);
        Configurations.setTestMode(true);

        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    void getRacesUrl() throws IOException {
        when(request.getPathInfo()).thenReturn(RACES_URL);

        Race currentRace = new Race();
        currentRace.setId(1L);
        currentRace.addRacer(new Racer("Alice"));

        when(racesController.index()).thenReturn(currentRace);

        servlet.doGet(request, response);

        verify(writer).print(jsonResponse.capture());

        assertThat(jsonResponse.getValue(), jsonResponseOf("/api/races"));
    }

    @Test
    void getSettingsUrl() throws IOException {
        when(request.getPathInfo()).thenReturn(SETTINGS_URL);

        Map<String, Boolean> settings = new HashMap<>();
        settings.put("testMode", false);

        when(settingsController.index()).thenReturn(settings);

        servlet.doGet(request, response);

        verify(writer).print(jsonResponse.capture());

        assertThat(jsonResponse.getValue(), jsonResponseOf("/api/settings"));
    }

    @Test
    void postNewRaceUrl() throws IOException {
        when(request.getPathInfo()).thenReturn(NEW_RACE_URL);

        Race currentRace = new Race();
        currentRace.setId(1L);
        currentRace.addRacer(new Racer("Alice"));

        when(racesController.newRace()).thenReturn(currentRace);

        servlet.doPost(request, response);

        verify(writer).print(jsonResponse.capture());

        assertThat(jsonResponse.getValue(), jsonResponseOf("/api/races/new"));
    }

    @Test
    void postRollRaceUrl() throws IOException {
        when(request.getPathInfo()).thenReturn(ROLL_RACE_URL);
        when(request.getReader()).thenReturn(reader);

        String jsonRequest = "{\"roll\":1,\"speedType\":\"NORMAL\"}";

        when(reader.readLine()).thenReturn(jsonRequest).thenReturn(null);

        Race currentRace = new Race();
        currentRace.setId(1L);
        currentRace.addRacer(new Racer("Alice"));
        currentRace.addRacer(new Racer("Bob"));

        currentRace.roll(1, NORMAL);

        when(racesController.roll(any())).thenReturn(currentRace);

        servlet.doPost(request, response);

        verify(writer).print(jsonResponse.capture());

        assertThat(jsonResponse.getValue(), jsonResponseOf("/api/races/roll"));
    }
}
