package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.escanan.ealden.race.model.Racer.*;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RacerTest {
    @Test
    public void rollMustSetPositionTo1WithNoDamageIfRollIsOddAndSpeedTypeNormal() {
        Racer racer = new Racer();
        racer.roll(1, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.roll(3, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.roll(5, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));
    }

    @Test
    public void rollMustSetPositionTo2WithNoDamageIfRollIsEvenAndSpeedTypeNormal() {
        Racer racer = new Racer();
        racer.roll(2, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.roll(4, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.roll(6, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));
    }

    @Test
    public void rollMustSetPositionToRollWithDamageIfSpeedTypeSuper() {
        Racer racer = new Racer();
        racer.roll(1, SUPER);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(2));

        racer = new Racer();
        racer.roll(2, SUPER);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(2));

        racer = new Racer();
        racer.roll(3, SUPER);

        assertThat(racer.getPosition(), is(3));
        assertThat(racer.getDamage(), is(2));

        racer = new Racer();
        racer.roll(4, SUPER);

        assertThat(racer.getPosition(), is(4));
        assertThat(racer.getDamage(), is(2));

        racer = new Racer();
        racer.roll(5, SUPER);

        assertThat(racer.getPosition(), is(5));
        assertThat(racer.getDamage(), is(2));

        racer = new Racer();
        racer.roll(6, SUPER);

        assertThat(racer.getPosition(), is(6));
        assertThat(racer.getDamage(), is(2));
    }

    @Test
    public void getPositionMustReturnCurrentPositionIfNotYetOnFinishLine() {
        int position = FINISH_LINE - 1;

        Racer racer = new Racer();
        racer.setPosition(position);

        assertThat(racer.getPosition(), is(position));
    }

    @Test
    public void getPositionMustReturnFinishLineIfCrossedFinishLine() {
        int position = FINISH_LINE + 10;

        Racer racer = new Racer();
        racer.setPosition(position);

        assertThat(racer.getPosition(), is(FINISH_LINE));
    }

    @Test
    public void asJSON() {
        Racer racer = new Racer();

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
        int position = FINISH_LINE;

        Racer racer = new Racer();
        racer.setPosition(position);

        assertThat(racer.isWinner(), is(true));
    }

    @Test
    public void isWinnerMustReturnTrueIfCrossedFinishLine() {
        int position = FINISH_LINE + 1;

        Racer racer = new Racer();
        racer.setPosition(position);

        assertThat(racer.isWinner(), is(true));
    }

    @Test
    public void isWinnerMustReturnFalseIfNotYetOnFinishLine() {
        int position = FINISH_LINE - 1;

        Racer racer = new Racer();
        racer.setPosition(position);

        assertThat(racer.isWinner(), is(false));
    }
}
