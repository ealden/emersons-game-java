package com.escanan.ealden.race.model;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static java.lang.String.format;

public class MessageBuilder {
    private static final String ALL_RACERS_CRASHED = "All racers CRASHED!!!  This race is over!";

    private final Race race;

    private final Racer currentRacer;
    private final Roll lastRoll;

    public MessageBuilder(Race race) {
        this.race = race;

        currentRacer = race.getCurrentRacer();
        lastRoll = race.getLastRoll();
    }

    public String build() {
        if (race.isReady()) {
            return raceReady();
        } else if (race.isAllCrashed()) {
            return ALL_RACERS_CRASHED;
        } else if (race.isOver()) {
            return raceOver();
        } else if (didPreviousRacerCrash()) {
            return racerCrashed();
        } else if (isPreviousRacerDamaged()) {
            return racerDamaged();
        } else if (race.isStarted()) {
            return raceStarted();
        } else {
            return null;
        }
    }

    private boolean didPreviousRacerCrash() {
        return race.isStarted() && lastRoll.getRacer().isCrashed();
    }

    private boolean isPreviousRacerDamaged() {
        return race.isStarted() && lastRoll.getRacer().isDamaged();
    }

    private String raceReady() {
        return format("Time to RACE!  %s rolls first!", currentRacer.getName());
    }

    private String raceOver() {
        return format("%s wins the race!  Congratulations!!!", lastRoll.getRacerName());
    }

    private String racerCrashed() {
        return format("%s chose %s speed, and rolled %d and moved %d.  %s CRASHED!!!  %s rolls next!",
                lastRoll.getRacerName(),
                lastRoll.getSpeed(),
                lastRoll.getNumber(),
                lastRoll.getMove(),
                lastRoll.getRacerName(),
                currentRacer.getName());
    }

    private String racerDamaged() {
        Object[] args = {
                lastRoll.getRacerName(),
                lastRoll.getSpeed(),
                lastRoll.getNumber(),
                lastRoll.getMove(),
                lastRoll.getRacerName(),
                lastRoll.getNewDamage(),
                currentRacer.getName()
        };

        if (NORMAL == lastRoll.getSpeedType()) {
            return format("%s chose %s speed, and rolled %d and moved %d.  %s has %d damage.  %s rolls next!", args);
        } else {
            return format("%s chose %s speed, and rolled %d and moved %d.  %s now has %d damage.  %s rolls next!", args);
        }
    }

    private String raceStarted() {
        return format("%s chose %s speed, and rolled %d and moved %d.  %s rolls next!",
                lastRoll.getRacerName(),
                lastRoll.getSpeed(),
                lastRoll.getNumber(),
                lastRoll.getMove(),
                currentRacer.getName());
    }
}
