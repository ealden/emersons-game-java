package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RollTest {
    @Test
    public void createRoll() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setRace(race);
        racer.setPosition(1);
        racer.setDamage(0);

        Roll roll = Roll.createRoll(racer, 0, 0, 1, NORMAL);

        assertThat(roll, is(not(nullValue())));
        assertThat(roll.getRacer(), is(sameInstance(racer)));
        assertThat(roll.getPosition(), is(equalTo(0)));
        assertThat(roll.getDamage(), is(equalTo(0)));
        assertThat(roll.getSpeedType(), is(equalTo(NORMAL)));
        assertThat(roll.getNumber(), is(equalTo(1)));
        assertThat(roll.getMove(), is(equalTo(1)));
        assertThat(roll.getNewPosition(), is(equalTo(1)));
        assertThat(roll.getNewDamage(), is(equalTo(0)));
        assertThat(roll.isCrashed(), is(equalTo(racer.isCrashed())));
        assertThat(roll.isWin(), is(equalTo(racer.isWinner())));
    }
}
