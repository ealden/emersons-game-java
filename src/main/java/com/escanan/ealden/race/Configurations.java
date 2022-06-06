package com.escanan.ealden.race;

import com.escanan.ealden.race.controller.api.RacesController;
import com.escanan.ealden.race.controller.api.SettingsController;
import com.escanan.ealden.race.service.RaceService;
import com.escanan.ealden.race.service.fake.FakeRaceService;

public final class Configurations {
    private static RacesController racesController;
    private static SettingsController settingsController;

    private static RaceService raceService;

    private static boolean testMode = false;

    private Configurations() {
    }

    public static RaceService raceService() {
        if (raceService == null) {
            raceService = new FakeRaceService();
        }

        return raceService;
    }

    public static RacesController racesController() {
        if (racesController == null) {
            racesController = new RacesController();
            racesController.setTestMode(testMode);
            racesController.setRaceService(raceService());
        }

        return racesController;
    }

    public static SettingsController settingsController() {
        if (settingsController == null) {
            settingsController = new SettingsController();
            settingsController.setTestMode(testMode);
        }

        return settingsController;
    }

    public static void setTestMode(boolean flag) {
        testMode = flag;

        racesController = null;
        settingsController = null;
    }

    public static void setRaceService(RaceService service) {
        raceService = service;
    }

    public static void setRacesController(RacesController controller) {
        racesController = controller;
    }

    public static void setSettingsController(SettingsController controller) {
        settingsController = controller;
    }
}
