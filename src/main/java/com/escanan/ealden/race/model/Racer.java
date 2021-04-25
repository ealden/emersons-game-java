package com.escanan.ealden.race.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Racer {
    static final int MAX_DAMAGE = 6;

    static final String ID = "id";
    static final String NAME = "name";
    static final String POSITION = "position";
    static final String DAMAGE = "damage";
    static final String RANK = "rank";
    static final String CRASHED = "crashed";
    static final String DAMAGED = "damaged";
    static final String WINNER = "winner";

    private final String name;
    private final int rank;
    private final boolean damaged;
    private Long id;
    private Race race;
    private int position;
    private int damage;

    public Racer() {
        this(null);
    }

    public Racer(String name) {
        this.name = name;

        position = 0;
        damage = 0;
        rank = 1;
        damaged = false;
    }

    public void roll(int number, SpeedType speedType) {
        position += speedType.move(number, damage);
        damage += speedType.getDamage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getPosition() {
        if (isWinner()) {
            return race.getFinishLine();
        } else {
            return position;
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRank() {
        return rank;
    }

    public boolean isCrashed() {
        return (damage >= MAX_DAMAGE);
    }

    public boolean isDamaged() {
        return damaged;
    }

    public boolean isWinner() {
        return (position >= race.getFinishLine());
    }

    public Map<String, Object> asJSON() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put(ID, getId());
        parameters.put(NAME, getName());
        parameters.put(POSITION, getPosition());
        parameters.put(DAMAGE, getDamage());
        parameters.put(RANK, getRank());
        parameters.put(CRASHED, isCrashed());
        parameters.put(DAMAGED, isDamaged());
        parameters.put(WINNER, isWinner());

        return parameters;
    }
}
