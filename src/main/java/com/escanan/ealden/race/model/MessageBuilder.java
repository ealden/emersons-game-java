package com.escanan.ealden.race.model;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static java.lang.String.format;

public class MessageBuilder {
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
            return format("Time to RACE!  %s rolls first!", currentRacer.getName());
        } else if (race.isAllCrashed()) {
            return "All racers CRASHED!!!  This race is over!";
        } else if (race.isOver()) {
            return format("%s wins the race!  Congratulations!!!", lastRoll.getRacerName());
        } else if (race.isStarted() && lastRoll.getRacer().isCrashed()) {
            return racerCrashed();
        } else if (race.isStarted() && lastRoll.getRacer().isDamaged()) {
            return racerDamaged();
        } else if (race.isStarted()) {
            return raceStarted();
        } else {
            return null;
        }
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
        } else if (SUPER == lastRoll.getSpeedType()) {
            return format("%s chose %s speed, and rolled %d and moved %d.  %s now has %d damage.  %s rolls next!", args);
        } else {
            return null;
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
