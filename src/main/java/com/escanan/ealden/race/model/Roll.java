package com.escanan.ealden.race.model;

public class Roll {
    private Long id;
    private Racer racer;
    private int position;
    private int damage;
    private SpeedType speedType;
    private int number;
    private int move;
    private int newPosition;
    private int newDamage;
    private boolean crashed;
    private boolean win;

    public Long getId() {
        return id;
    }

    public Racer getRacer() {
        return racer;
    }

    public int getPosition() {
        return position;
    }

    public int getDamage() {
        return damage;
    }

    public SpeedType getSpeedType() {
        return speedType;
    }

    public int getNumber() {
        return number;
    }

    public int getMove() {
        return move;
    }

    public int getNewPosition() {
        return newPosition;
    }

    public int getNewDamage() {
        return newDamage;
    }

    public boolean isCrashed() {
        return crashed;
    }

    public boolean isWin() {
        return win;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeedType(SpeedType speedType) {
        this.speedType = speedType;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
    }

    public void setNewDamage(int newDamage) {
        this.newDamage = newDamage;
    }

    public void setCrashed(boolean crashed) {
        this.crashed = crashed;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}