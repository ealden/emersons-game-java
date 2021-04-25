package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RacerTest {
    @Test
    public void rollMustSetPositionTo1WithNoDamageIfRollIs1AndSpeedTypeNormal() {
        Racer racer = new Racer();
        racer.roll(1, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));
    }
}
