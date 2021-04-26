package com.escanan.ealden.race;

import com.escanan.ealden.race.service.RaceService;
import com.escanan.ealden.race.service.impl.RaceServiceImpl;

public final class Configurations {
    private static boolean testMode = false;

    private static RaceService raceService;

    private Configurations() {
    }

    public static boolean isTestMode() {
        return testMode;
    }

    public static RaceService raceService() {
        if (raceService == null) {
            raceService = new RaceServiceImpl();
        }

        return raceService;
    }

    public static void setTestMode(boolean flag) {
        testMode = flag;
    }
}
