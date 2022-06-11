package com.escanan.ealden.race.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.escanan.ealden.race.model.Roll.createRoll;
import static java.util.stream.Collectors.toList;

public class Race implements JsonAware {
    static final int DEFAULT_FINISH_LINE = 10;

    private static final int MAX_ROLL = 6;

    private final List<Racer> racers = new ArrayList<>();
    private Long id;
    private Racer currentRacer;
    private Roll lastRoll;
    private int finishLine = DEFAULT_FINISH_LINE;

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

        Racer nextRacer = currentRacer;

        for (Racer racer : racers) {
            if (nextRank == racer.getRank()) {
                nextRacer = racer;
            }
        }

        return nextRacer;
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

    public Roll getLastRoll() {
        return lastRoll;
    }

    public int getFinishLine() {
        return finishLine;
    }

    public String getMessage() {
        return new MessageBuilder(this).build();
    }

    public boolean isReady() {
        return (currentRacer != null) && !isStarted() && !isOver();
    }

    public boolean isStarted() {
        return (lastRoll != null) && !isOver();
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFinishLine(int finishLine) {
        this.finishLine = finishLine;
    }

    public Map<String, Object> asJson() {
        Map<String, Object> json = new LinkedHashMap<>();
        json.put("id", getId());
        json.put("racers", racers.stream().map(Racer::asJson).collect(toList()));
        json.put("currentRacer", currentRacerJson());
        json.put("finishLine", getFinishLine());
        json.put("over", isOver());
        json.put("allCrashed", isAllCrashed());
        json.put("message", getMessage());

        return json;
    }

    private Map<String, Object> currentRacerJson() {
        Map<String, Object> json = null;

        if (currentRacer != null) {
            json = currentRacer.asJson();
        }

        return json;
    }
}
