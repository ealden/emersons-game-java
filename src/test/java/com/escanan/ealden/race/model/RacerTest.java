package com.escanan.ealden.race.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.escanan.ealden.race.model.Racer.*;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

public class RacerTest {
    private Race currentRace;

    @BeforeEach
    public void setUp() {
        currentRace = new Race();
    }

    @Test
    public void rollMustSetPositionTo1WithNoDamageIfRollIsOddAndSpeedTypeNormal() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(1, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(3, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(5, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));
    }

    @Test
    public void rollMustSetPositionTo2WithNoDamageIfRollIsEvenAndSpeedTypeNormal() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(2, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(4, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(6, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));
    }

    @Test
    public void rollMustSetPositionToRollWithDamageIfSpeedTypeSuper() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(1, SUPER);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(2));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(2, SUPER);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(2));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(3, SUPER);

        assertThat(racer.getPosition(), is(3));
        assertThat(racer.getDamage(), is(2));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(4, SUPER);

        assertThat(racer.getPosition(), is(4));
        assertThat(racer.getDamage(), is(2));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(5, SUPER);

        assertThat(racer.getPosition(), is(5));
        assertThat(racer.getDamage(), is(2));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(6, SUPER);

        assertThat(racer.getPosition(), is(6));
        assertThat(racer.getDamage(), is(2));
    }

    @Test
    public void getPositionMustReturnCurrentPositionIfNotYetOnFinishLine() {
        int position = currentRace.getFinishLine() - 1;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.getPosition(), is(position));
    }

    @Test
    public void getPositionMustReturnFinishLineIfCrossedFinishLine() {
        int position = currentRace.getFinishLine() + 10;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.getPosition(), is(currentRace.getFinishLine()));
    }

    @Test
    public void asJSON() {
        Racer racer = new Racer();
        racer.setRace(currentRace);

        Map<String, Object> json = racer.asJSON();

        assertThat(json, hasEntry(ID, 1L));
        assertThat(json, hasEntry(NAME, "Alice"));
        assertThat(json, hasEntry(POSITION, 0));
        assertThat(json, hasEntry(DAMAGE, 0));
        assertThat(json, hasEntry(RANK, 1));
        assertThat(json, hasEntry(CRASHED, false));
        assertThat(json, hasEntry(DAMAGED, false));
        assertThat(json, hasEntry(WINNER, false));
    }

    @Test
    public void isWinnerMustReturnTrueIfOnFinishLine() {
        int position = currentRace.getFinishLine();

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.isWinner(), is(true));
    }

    @Test
    public void isWinnerMustReturnTrueIfCrossedFinishLine() {
        int position = currentRace.getFinishLine() + 1;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.isWinner(), is(true));
    }

    @Test
    public void isWinnerMustReturnFalseIfNotYetOnFinishLine() {
        int position = currentRace.getFinishLine() - 1;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.isWinner(), is(false));
    }

    @Test
    public void isCrashedMustReturnFalseIfDamageLessThanMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE - 1);

        assertThat(racer.isCrashed(), is(false));
    }

    @Test
    public void isCrashedMustReturnTrueIfDamageEqualToMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);

        assertThat(racer.isCrashed(), is(true));
    }

    @Test
    public void isCrashedMustReturnTrueIfDamageMoreThanMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE + 1);

        assertThat(racer.isCrashed(), is(true));
    }
}
