package com.escanan.ealden.race.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Racer {
    public static final int MAX_DAMAGE = 6;
    public static final int NO_DAMAGE = 0;

    static final String ID = "id";
    static final String NAME = "name";
    static final String POSITION = "position";
    static final String DAMAGE = "damage";
    static final String RANK = "rank";
    static final String CRASHED = "crashed";
    static final String DAMAGED = "damaged";
    static final String WINNER = "winner";

    private Long id;
    private String name;
    private Race race;
    private int position;
    private int damage;
    private int rank;

    public Racer() {
    }

    public Racer(String name) {
        this.name = name;
    }

    public void roll(int number, SpeedType speedType) {
        position += speedType.move(number, damage);
        damage += speedType.getDamage();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public int getPosition() {
        if (isWinner()) {
            return race.getFinishLine();
        } else {
            return position;
        }
    }

    public int getDamage() {
        return damage;
    }

    public int getRank() {
        return rank;
    }

    public boolean isCrashed() {
        return (damage >= MAX_DAMAGE);
    }

    public boolean isDamaged() {
        return damage > NO_DAMAGE;
    }

    public boolean isWinner() {
        return (position >= race.getFinishLine());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Map<String, Object> asJson() {
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
