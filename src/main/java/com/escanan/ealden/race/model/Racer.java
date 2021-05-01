package com.escanan.ealden.race.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Racer implements JsonAware {
    public static final int MAX_DAMAGE = 6;
    public static final int NO_DAMAGE = 0;

    static final String ID_PARAM = "id";
    static final String NAME_PARAM = "name";
    static final String POSITION_PARAM = "position";
    static final String DAMAGE_PARAM = "damage";
    static final String RANK_PARAM = "rank";
    static final String CRASHED_PARAM = "crashed";
    static final String DAMAGED_PARAM = "damaged";
    static final String WINNER_PARAM = "winner";

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
        return damage >= MAX_DAMAGE;
    }

    public boolean isDamaged() {
        return damage > NO_DAMAGE;
    }

    public boolean isWinner() {
        return position >= race.getFinishLine();
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
        Map<String, Object> json = new LinkedHashMap<>();
        json.put(ID_PARAM, getId());
        json.put(NAME_PARAM, getName());
        json.put(POSITION_PARAM, getPosition());
        json.put(DAMAGE_PARAM, getDamage());
        json.put(RANK_PARAM, getRank());
        json.put(CRASHED_PARAM, isCrashed());
        json.put(DAMAGED_PARAM, isDamaged());
        json.put(WINNER_PARAM, isWinner());

        return json;
    }
}
