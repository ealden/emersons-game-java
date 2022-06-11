package com.escanan.ealden.race.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Racer implements JsonAware {
    public static final int MAX_DAMAGE = 6;
    public static final int NO_DAMAGE = 0;

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
        json.put("id", getId());
        json.put("name", getName());
        json.put("position", getPosition());
        json.put("damage", getDamage());
        json.put("rank", getRank());
        json.put("crashed", isCrashed());
        json.put("damaged", isDamaged());
        json.put("winner", isWinner());

        return json;
    }
}
