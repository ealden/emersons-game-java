package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.controller.api.model.Roll;
import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import static com.escanan.ealden.race.Matchers.jsonResponseOf;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RollControllerTest {
    private RollController controller;

    @Mock
    private RollController shim;

    @Mock
    private RaceService raceService;

    @Mock
    private HttpServletRequest request;

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

        Roll roll = new Roll();
        roll.setNumber(1);
        roll.setSpeedType(NORMAL);

        when(shim.fromRequest(any())).thenReturn(roll);

        controller.setTestMode(true);
        controller.setShim(shim);
        controller.doPost(request, response);

        verify(writer).print(responseBody.capture());

        assertThat(responseBody.getValue(), jsonResponseOf("/api/races/roll"));
    }

    @Test
    public void doPostMustRandomizeRollIfNotInTestMode() throws IOException {
        Race currentRace = mock(Race.class);

        when(currentRace.asJson()).thenReturn(Collections.emptyMap());
        when(raceService.getCurrentRace()).thenReturn(currentRace);

        Roll roll = new Roll();
        roll.setNumber(1);
        roll.setSpeedType(NORMAL);

        when(shim.fromRequest(any())).thenReturn(roll);

        controller.setTestMode(false);
        controller.setShim(shim);
        controller.doPost(request, response);

        verify(writer).print(anyString());
        verify(currentRace).roll(NORMAL);
    }
}
