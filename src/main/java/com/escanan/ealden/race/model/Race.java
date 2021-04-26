package com.escanan.ealden.race.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.escanan.ealden.race.model.Roll.createRoll;
import static java.util.stream.Collectors.toList;

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

    public boolean isStarted() {
        return lastRoll != null;
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
        return new MessageBuilder(this).build();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Object> asJson() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put(ID, getId());
        parameters.put(RACERS, racers.stream().map(racer -> racer.asJson()).collect(toList()));

        if (currentRacer != null) {
            parameters.put(CURRENT_RACER, currentRacer.asJson());
        } else {
            parameters.put(CURRENT_RACER, null);
        }

        parameters.put(FINISH_LINE, getFinishLine());
        parameters.put(OVER, isOver());
        parameters.put(ALL_CRASHED, isAllCrashed());
        parameters.put(MESSAGE, getMessage());

        return parameters;
    }
}
