package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;
import com.google.gson.Gson;
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
import static com.escanan.ealden.race.controller.api.model.Roll.ROLL_PARAM;
import static com.escanan.ealden.race.controller.api.model.Roll.SPEED_TYPE_PARAM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RollControllerTest {
    private RollController controller;

    @Mock
    private RaceService raceService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private BufferedReader reader;

    @Mock
    private HttpServletResponse response;

    @Mock
    private PrintWriter writer;

    @Captor
    private ArgumentCaptor<String> responseBody;

    @BeforeEach
    public void setUp() throws IOException {
        controller = new RollController();
        controller.setRaceService(raceService);

        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void doPostMustReturnJsonResponse() throws IOException {
        Race currentRace = new Race();
        currentRace.setId(1L);
        currentRace.addRacer(new Racer("Alice"));
        currentRace.addRacer(new Racer("Bob"));

        when(raceService.getCurrentRace()).thenReturn(currentRace);

        Map<String, String> parameters = new HashMap<>();
        parameters.put(SPEED_TYPE_PARAM, "NORMAL");
        parameters.put(ROLL_PARAM, "1");

        String requestBody = new Gson().toJson(parameters);

        when(request.getReader()).thenReturn(reader);
        when(reader.readLine()).thenReturn(requestBody).thenReturn(null);

        controller.doPost(request, response);

        verify(writer).print(responseBody.capture());

        assertThat(responseBody.getValue(), is(jsonResponseOf("/api/races/roll")));
    }
}
