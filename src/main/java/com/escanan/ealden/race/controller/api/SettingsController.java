package com.escanan.ealden.race.controller.api;

import java.util.HashMap;
import java.util.Map;

public class SettingsController {
    static final String TEST_MODE_PARAM = "testMode";

    private boolean testMode;

    public Map<String, Boolean> index() {
        Map<String, Boolean> settings = new HashMap<>();
        settings.put(TEST_MODE_PARAM, testMode);

        return settings;
    }

    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }
}
