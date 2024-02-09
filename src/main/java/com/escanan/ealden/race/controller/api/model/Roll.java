package com.escanan.ealden.race.controller.api.model;

import com.escanan.ealden.race.controller.api.JsonException;
import com.escanan.ealden.race.controller.api.util.RequestParameters;
import com.escanan.ealden.race.model.SpeedType;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class Roll {
    static final String ROLL_PARAM = "roll";
    static final String SPEED_TYPE_PARAM = "speedType";

    private Integer number;
    private SpeedType speedType;

    public static Roll fromRequest(HttpServletRequest request) {
        try {
            return fromParameters(RequestParameters.fromRequest(request));
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    static Roll fromParameters(Map<String, String> parameters) {
        Roll roll = new Roll();

        if (parameters.containsKey(ROLL_PARAM)) {
            roll.setNumber(Integer.parseInt(parameters.get(ROLL_PARAM)));
        }

        if (parameters.containsKey(SPEED_TYPE_PARAM)) {
            roll.setSpeedType(SpeedType.valueOf(parameters.get(SPEED_TYPE_PARAM)));
        }

        return roll;
    }

    public Integer getNumber() {
        return number;
    }

    public SpeedType getSpeedType() {
        return speedType;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setSpeedType(SpeedType speedType) {
        this.speedType = speedType;
    }
}
