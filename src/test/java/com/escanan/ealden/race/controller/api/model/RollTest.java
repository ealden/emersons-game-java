package com.escanan.ealden.race.controller.api.model;

import com.escanan.ealden.race.controller.api.JsonException;
import com.escanan.ealden.race.model.SpeedType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.escanan.ealden.race.controller.api.model.Roll.ROLL_PARAM;
import static com.escanan.ealden.race.controller.api.model.Roll.SPEED_TYPE_PARAM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RollTest {
    @Mock
    private HttpServletRequest request;

    @Test
    void fromParametersMustReturnRollModel() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ROLL_PARAM, "1");
        parameters.put(SPEED_TYPE_PARAM, "NORMAL");

        Roll roll = Roll.fromParameters(parameters);

        assertThat(roll.getNumber(), equalTo(1));
        assertThat(roll.getSpeedType(), equalTo(SpeedType.NORMAL));
    }

    @Test
    void fromParametersMustReturnRollWithNullNumberIfParameterIsNotPresent() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(SPEED_TYPE_PARAM, "NORMAL");

        Roll roll = Roll.fromParameters(parameters);

        assertThat(roll.getNumber(), nullValue());
        assertThat(roll.getSpeedType(), equalTo(SpeedType.NORMAL));
    }

    @Test
    void fromParametersWithNoParameters() {
        Roll roll = Roll.fromParameters(new HashMap<>());

        assertThat(roll.getNumber(), nullValue());
        assertThat(roll.getSpeedType(), nullValue());
    }

    @Test
    void fromRequestWithException() throws IOException {
        when(request.getReader()).thenThrow(new IOException());

        assertThrows(JsonException.class, () -> Roll.fromRequest(request));
    }
}
