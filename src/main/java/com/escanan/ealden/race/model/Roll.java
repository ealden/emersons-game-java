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

    public static Roll createRoll(Racer racer, int oldPosition, int oldDamage, int number, SpeedType speedType) {
        Roll roll = new Roll();
        roll.setRacer(racer);
        roll.setPosition(oldPosition);
        roll.setDamage(oldDamage);
        roll.setSpeedType(speedType);
        roll.setNumber(number);
        roll.setMove(speedType.move(number, oldDamage));
        roll.setNewPosition(racer.getPosition());
        roll.setNewDamage(racer.getDamage());
        roll.setCrashed(racer.isCrashed());
        roll.setWin(racer.isWinner());

        return roll;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public int getPosition() {
        return position;
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

    public SpeedType getSpeedType() {
        return speedType;
    }

    public void setSpeedType(SpeedType speedType) {
        this.speedType = speedType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
    }

    public int getNewDamage() {
        return newDamage;
    }

    public void setNewDamage(int newDamage) {
        this.newDamage = newDamage;
    }

    public boolean isCrashed() {
        return crashed;
    }

    public void setCrashed(boolean crashed) {
        this.crashed = crashed;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}