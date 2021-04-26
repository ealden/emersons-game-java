package com.escanan.ealden.race;

import com.escanan.ealden.race.service.RaceService;
import com.escanan.ealden.race.service.fake.FakeRaceService;

public final class Configurations {
    private static boolean testMode = false;

    private static final RaceService raceService;

    static {
        raceService = new FakeRaceService();
    }

    private Configurations() {
    }

    public static boolean isTestMode() {
        return testMode;
    }

    public static RaceService raceService() {
        return raceService;
    }

    public static void setTestMode(boolean flag) {
        testMode = flag;
    }
}
