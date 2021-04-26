package com.escanan.ealden.race;

public final class Configurations {
    private static boolean testMode = false;

    private Configurations() {
    }

    public static boolean isTestMode() {
        return testMode;
    }

    public static void setTestMode(boolean flag) {
        testMode = flag;
    }
}
