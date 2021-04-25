package com.escanan.ealden.race.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.escanan.ealden.race.model.Roll.createRoll;

public class Race {
    static final String ID = "id";
    static final String RACERS = "racers";
    static final String CURRENT_RACER = "currentRacer";
    static final String FINISH_LINE = "finishLine";
    static final String OVER = "over";
    static final String ALL_CRASHED = "allCrashed";
    static final String MESSAGE = "message";

    private static final int DEFAULT_FINISH_LINE = 10;

    private final Long id;
    private final List<Racer> racers;
    private final int finishLine = DEFAULT_FINISH_LINE;
    private final boolean allCrashed;
    private final String message;
    private Racer currentRacer;
    private Roll lastRoll;

    public Race() {
        id = 1L;
        racers = new ArrayList<>();
        allCrashed = false;
        message = "Time to RACE!  Alice rolls first!";
    }

    public Race addRacer(Racer racer) {
        racers.add(racer);

        racer.setId(Long.valueOf(racers.size()));
        racer.setRace(this);

        currentRacer = racer;

        return this;
    }

    public void roll(int number, SpeedType speedType) {
        int oldPosition = currentRacer.getPosition();
        int oldDamage = currentRacer.getDamage();

        currentRacer.roll(number, speedType);

        lastRoll = createRoll(currentRacer, oldPosition, oldDamage, number, speedType);
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
