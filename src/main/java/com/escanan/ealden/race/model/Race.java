package com.escanan.ealden.race.model;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private final Long id;
    private final List<Racer> racers;
    private final Racer currentRacer;
    private final int finishLine;
    private final boolean over;
    private final boolean allCrashed;
    private final String message;

    public Race() {
        id = 1L;
        racers = new ArrayList<>();
        currentRacer = new Racer();
        finishLine = 10;
        over = false;
        allCrashed = false;
        message = "Time to RACE!  Alice rolls first!";
    }

    public Race addRacer(Racer racer) {
        racers.add(racer);

        return this;
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
        return over;
    }

    public boolean isAllCrashed() {
        return allCrashed;
    }

    public String getMessage() {
        return message;
    }
}