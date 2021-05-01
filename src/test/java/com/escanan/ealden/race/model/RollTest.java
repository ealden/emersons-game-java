package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class RollTest {
    @Test
    void createRoll() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setRace(race);
        racer.setPosition(1);
        racer.setDamage(0);

        Roll roll = Roll.createRoll(racer, 0, 0, 1, NORMAL);

        assertThat(roll, notNullValue());
        assertThat(roll.getRacer(), sameInstance(racer));
        assertThat(roll.getPosition(), equalTo(0));
        assertThat(roll.getDamage(), equalTo(0));
        assertThat(roll.getSpeedType(), equalTo(NORMAL));
        assertThat(roll.getNumber(), equalTo(1));
        assertThat(roll.getMove(), equalTo(1));
        assertThat(roll.getNewPosition(), equalTo(1));
        assertThat(roll.getNewDamage(), equalTo(0));
        assertThat(roll.isCrashed(), equalTo(racer.isCrashed()));
        assertThat(roll.isWin(), equalTo(racer.isWinner()));
    }

    @Test
    void getSpeedMustReturnNullIfSpeedTypeIsNull() {
        Roll roll = new Roll();

        assertThat(roll.getSpeed(), nullValue());
    }

    @Test
    void getSpeedMustReturnSpeedTypeInAllCaps() {
        Roll roll = new Roll();

        roll.setSpeedType(NORMAL);
        assertThat(roll.getSpeed(), equalTo("NORMAL"));

        roll.setSpeedType(SUPER);
        assertThat(roll.getSpeed(), equalTo("SUPER"));
    }

    @Test
    void getRacerNameMustReturnName() {
        Roll roll = new Roll();
        roll.setRacer(new Racer("Alice"));

        assertThat(roll.getRacerName(), equalTo("Alice"));
    }

    @Test
    void getRacerNameMustReturnNullIfRacerIsNull() {
        Roll roll = new Roll();
        roll.setRacer(null);

        assertThat(roll.getRacerName(), nullValue());
    }
}
