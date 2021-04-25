package com.escanan.ealden.race.model;

public class Racer {
    private final Long id;
    private final String name;
    private int position;
    private int damage;
    private final int rank;
    private final boolean crashed;
    private final boolean damaged;
    private final boolean winner;

    public Racer() {
        id = 1L;
        name = "Alice";
        position = 0;
        damage = 0;
        rank = 1;
        crashed = false;
        damaged = false;
        winner = false;
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

    public int getPosition() {
        return position;
    }

    public int getDamage() {
        return damage;
    }

    public int getRank() {
        return rank;
    }

    public boolean isCrashed() {
        return crashed;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public boolean isWinner() {
        return winner;
    }
}
