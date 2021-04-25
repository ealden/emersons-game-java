package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
}