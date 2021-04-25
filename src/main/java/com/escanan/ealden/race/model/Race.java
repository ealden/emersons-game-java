package com.escanan.ealden.race.model;

import java.util.*;

public class Race {
    static final String ID = "id";
    static final String RACERS = "racers";
    static final String CURRENT_RACER = "currentRacer";
    static final String FINISH_LINE = "finishLine";
    static final String OVER = "over";
    static final String ALL_CRASHED = "allCrashed";
    static final String MESSAGE = "message";

    private final Long id;
    private final List<Racer> racers;
    private Racer currentRacer;
    private final int finishLine;
    private final boolean allCrashed;
    private final String message;
    private Roll lastRoll;

    public Race() {
        id = 1L;
        racers = new ArrayList<>();
        finishLine = 10;
        allCrashed = false;
        message = "Time to RACE!  Alice rolls first!";
    }

    public Race addRacer(Racer racer) {
        racers.add(racer);

        racer.setRace(this);

        currentRacer = racer;

        return this;
    }

    public void roll(int number, SpeedType speedType) {
        int move = speedType.move(number, currentRacer.getDamage());

        currentRacer.roll(number, speedType);

        lastRoll = new Roll();
        lastRoll.setRacer(currentRacer);
        lastRoll.setSpeedType(speedType);
        lastRoll.setNumber(number);
        lastRoll.setMove(move);
        lastRoll.setNewPosition(currentRacer.getPosition());
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

        return false;
    }

    public boolean isAllCrashed() {
        return allCrashed;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> asJSON() {
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
            racers.add(racer.asJSON());
        }

        return racers;
    }

    private Map<String, Object> currentRacerJSON() {
        Racer currentRacer = getCurrentRacer();

        if (currentRacer != null) {
            return currentRacer.asJSON();
        } else {
            return null;
        }
    }
}
