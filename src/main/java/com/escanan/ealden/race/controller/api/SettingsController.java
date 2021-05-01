package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.Configurations;

import java.util.HashMap;
import java.util.Map;

public class SettingsController {
    static final String TEST_MODE_PARAM = "testMode";

    public Map<String, Boolean> index() {
        Map<String, Boolean> settings = new HashMap<>();
        settings.put(TEST_MODE_PARAM, Configurations.isTestMode());

        return settings;
    }
}
