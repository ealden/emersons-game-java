package com.escanan.ealden.race.controller.api.model;

import com.escanan.ealden.race.model.SpeedType;

import java.util.Map;

public class Roll {
    public static final String ROLL_PARAM = "roll";
    public static final String SPEED_TYPE_PARAM = "speedType";

    private Integer roll;
    private SpeedType speedType;

    public static Roll fromParameters(Map<String, String> parameters) {
        Integer number = null;

        if (parameters.containsKey(ROLL_PARAM)) {
            number = Integer.parseInt(parameters.get(ROLL_PARAM));
        }

        SpeedType speedType = SpeedType.valueOf(parameters.get(SPEED_TYPE_PARAM));

        Roll roll = new Roll();
        roll.setRoll(number);
        roll.setSpeedType(speedType);

        return roll;
    }

    public Integer getRoll() {
        return roll;
    }

    public SpeedType getSpeedType() {
        return speedType;
    }

    public void setRoll(Integer roll) {
        this.roll = roll;
    }

    public void setSpeedType(SpeedType speedType) {
        this.speedType = speedType;
    }
}
