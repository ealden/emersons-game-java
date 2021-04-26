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

        this.currentRacer = race.getCurrentRacer();
        this.lastRoll = race.getLastRoll();
    }

    public String build() {
        if (race.isReady()) {
            return format("Time to RACE!  %s rolls first!", currentRacer.getName());
        } else if (race.isAllCrashed()) {
            return "All racers CRASHED!!!  This race is over!";
        } else if (race.isOver()) {
            return format("%s wins the race!  Congratulations!!!", race.getLastRoll().getRacer().getName());
        } else if (race.isStarted() && lastRoll.getRacer().isCrashed()) {
            return format("%s chose %s speed, and rolled %d and moved %d.  %s CRASHED!!!  %s rolls next!",
                    lastRoll.getRacer().getName(),
                    lastRoll.getSpeedType().toString().toUpperCase(),
                    lastRoll.getNumber(),
                    lastRoll.getMove(),
                    lastRoll.getRacer().getName(),
                    currentRacer.getName());
        } else if (race.isStarted() && lastRoll.getRacer().isDamaged()) {
            Object[] args = {
                    lastRoll.getRacer().getName(),
                    lastRoll.getSpeedType().toString().toUpperCase(),
                    lastRoll.getNumber(),
                    lastRoll.getMove(),
                    lastRoll.getRacer().getName(),
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
        } else if (race.isStarted()) {
            return format("%s chose %s speed, and rolled %d and moved %d.  %s rolls next!",
                    lastRoll.getRacer().getName(),
                    lastRoll.getSpeedType().toString().toUpperCase(),
                    lastRoll.getNumber(),
                    lastRoll.getMove(),
                    currentRacer.getName());
        } else {
            return null;
        }
    }
}
