package com.escanan.ealden.race.model;

import static java.lang.String.format;

public class MessageBuilder {
    private Race race;

    private Racer currentRacer;
    private Roll lastRoll;

    public MessageBuilder(Race race) {
        this.race = race;

        this.currentRacer = race.getCurrentRacer();
        this.lastRoll = race.getLastRoll();
    }

    public String build() {
        if (isTimeToRace()) {
            return format("Time to RACE!  %s rolls first!", currentRacer.getName());
        } else if (race.isAllCrashed()) {
            return "All racers CRASHED!!!  This race is over!";
        } else if (race.isOver()) {
            return format("%s wins the race!  Congratulations!!!", race.getLastRoll().getRacer().getName());
        } else if (isRacing()) {
            return getMessageWhenRacing();
        } else {
            return null;
        }
    }

    private String getMessageWhenRacing() {
        if (lastRoll.getRacer().isCrashed()) {
            return format("%s chose %s speed, and rolled %d and moved %d.  %s CRASHED!!!  %s rolls next!",
                    lastRoll.getRacer().getName(),
                    lastRoll.getSpeedType().toString().toUpperCase(),
                    lastRoll.getNumber(),
                    lastRoll.getMove(),
                    lastRoll.getRacer().getName(),
                    currentRacer.getName());
        } else if (lastRoll.getRacer().isDamaged()) {
            return format(racerDamagedMessage(),
                    lastRoll.getRacer().getName(),
                    lastRoll.getSpeedType().toString().toUpperCase(),
                    lastRoll.getNumber(),
                    lastRoll.getMove(),
                    lastRoll.getRacer().getName(),
                    lastRoll.getNewDamage(),
                    currentRacer.getName());
        } else {
            return format("%s chose %s speed, and rolled %d and moved %d.  %s rolls next!",
                    lastRoll.getRacer().getName(),
                    lastRoll.getSpeedType().toString().toUpperCase(),
                    lastRoll.getNumber(),
                    lastRoll.getMove(),
                    currentRacer.getName());
        }
    }

    private String racerDamagedMessage() {
        switch (lastRoll.getSpeedType()) {
            case NORMAL:
                return "%s chose %s speed, and rolled %d and moved %d.  %s has %d damage.  %s rolls next!";
            case SUPER:
                return "%s chose %s speed, and rolled %d and moved %d.  %s now has %d damage.  %s rolls next!";
        }

        return null;
    }

    private boolean isTimeToRace() {
        return ((currentRacer != null) && (lastRoll == null));
    }

    private boolean isRacing() {
        return ((currentRacer != null) && (lastRoll != null));
    }
}
