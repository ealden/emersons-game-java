package com.escanan.ealden.race.controller.api.model;

import com.escanan.ealden.race.model.SpeedType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.escanan.ealden.race.controller.api.model.Roll.ROLL_PARAM;
import static com.escanan.ealden.race.controller.api.model.Roll.SPEED_TYPE_PARAM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RollTest {
    @Test
    public void fromParametersMustReturnRollModel() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ROLL_PARAM, "1");
        parameters.put(SPEED_TYPE_PARAM, "NORMAL");

        Roll roll = Roll.fromParameters(parameters);

        assertThat(roll.getNumber(), is(equalTo(1)));
        assertThat(roll.getSpeedType(), is(equalTo(SpeedType.NORMAL)));
    }

    @Test
    public void fromParametersMustReturnRollWithNullNumberIfParameterIsNotPresent() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(SPEED_TYPE_PARAM, "NORMAL");

        Roll roll = Roll.fromParameters(parameters);

        assertThat(roll.getNumber(), is(nullValue()));
        assertThat(roll.getSpeedType(), is(equalTo(SpeedType.NORMAL)));
    }
}
