package com.escanan.ealden.race.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.escanan.ealden.race.model.Roll.createRoll;
import static java.lang.String.format;

public class Race {
    static final String ID = "id";
    static final String RACERS = "racers";
    static final String CURRENT_RACER = "currentRacer";
    static final String FINISH_LINE = "finishLine";
    static final String OVER = "over";
    static final String ALL_CRASHED = "allCrashed";
    static final String MESSAGE = "message";

    private static final int DEFAULT_FINISH_LINE = 10;
    private static final int MAX_ROLL = 6;

    private final List<Racer> racers = new ArrayList<>();
    private final int finishLine = DEFAULT_FINISH_LINE;
    private Long id;
    private Racer currentRacer;
    private Roll lastRoll;

    public Race addRacer(Racer racer) {
        racers.add(racer);

        racer.setId(Long.valueOf(racers.size()));
        racer.setRace(this);
        racer.setRank(racers.size());

        if (currentRacer == null) {
            currentRacer = racer;
        }

        return this;
    }

    public void roll(SpeedType speedType) {
        int roll = new SecureRandom().nextInt(MAX_ROLL) + 1;

        roll(roll, speedType);
    }

    public void roll(int number, SpeedType speedType) {
        int oldPosition = currentRacer.getPosition();
        int oldDamage = currentRacer.getDamage();

        currentRacer.roll(number, speedType);

        lastRoll = createRoll(currentRacer, oldPosition, oldDamage, number, speedType);

        currentRacer = nextRacer();
    }

    private Racer nextRacer() {
        int nextRank = ((currentRacer.getRank() % racers.size()) + 1);

        for (Racer racer : racers) {
            if (nextRank == racer.getRank()) {
                return racer;
            }
        }

        return currentRacer;
    }

    public Roll getLastRoll() {
        return lastRoll;
    }

    public Long getId() {
        return id;
    }

    public List<Racer> getRacers() {
        return racers;
    }

    public Racer getCurrentRacer() {
        return currentRacer;
    }

    public int getFinishLine() {
        return finishLine;
    }

    public boolean isOver() {
        for (Racer racer : getRacers()) {
            if (racer.isWinner()) {
                return true;
            }
        }

        return isAllCrashed();
    }

    public boolean isAllCrashed() {
        boolean allCrashed = !racers.isEmpty();

        for (Racer racer : racers) {
            allCrashed = (allCrashed && racer.isCrashed());
        }

        return allCrashed;
    }

    public String getMessage() {
        if (isTimeToRace()) {
            return format("Time to RACE!  %s rolls first!", currentRacer.getName());
        } else if (isAllCrashed()) {
            return "All racers CRASHED!!!  This race is over!";
        } else if (isOver()) {
            return format("%s wins the race!  Congratulations!!!", lastRoll.getRacer().getName());
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

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Object> asJson() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put(ID, getId());
        parameters.put(RACERS, racersJSON());
        parameters.put(CURRENT_RACER, currentRacerJSON());
        parameters.put(FINISH_LINE, getFinishLine());
        parameters.put(OVER, isOver());
        parameters.put(ALL_CRASHED, isAllCrashed());
        parameters.put(MESSAGE, getMessage());

        return parameters;
    }

    private List<Map<String, Object>> racersJSON() {
        List<Map<String, Object>> racers = new ArrayList<>();

        for (Racer racer : getRacers()) {
            racers.add(racer.asJson());
        }

        return racers;
    }

    private Map<String, Object> currentRacerJSON() {
        Racer currentRacer = getCurrentRacer();

        if (currentRacer != null) {
            return currentRacer.asJson();
        } else {
            return null;
        }
    }
}
