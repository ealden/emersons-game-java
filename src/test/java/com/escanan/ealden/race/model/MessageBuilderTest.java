package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

class MessageBuilderTest {
    @Test
    void noRacers() {
        Race race = new Race();

        String message = new MessageBuilder(race).build();

        assertThat(message, nullValue());
    }

    @Test
    void racerJoined() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));

        String message = new MessageBuilder(race).build();

        assertThat(message, equalTo("Time to RACE!  Alice rolls first!"));
    }

    @Test
    void currentRacerRolls() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));
        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        String message = new MessageBuilder(race).build();

        assertThat(message, equalTo("Alice chose NORMAL speed, and rolled 1 and moved 1.  Bob rolls next!"));
    }

    @Test
    void allRacersCrashed() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));
        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        for (Racer racer : race.getRacers()) {
            racer.setDamage(MAX_DAMAGE);
        }

        race.roll(1, NORMAL);

        String message = new MessageBuilder(race).build();

        assertThat(message, equalTo("All racers CRASHED!!!  This race is over!"));
    }

    @Test
    void currentRacerWins() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setPosition(race.getFinishLine());
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        String message = new MessageBuilder(race).build();

        assertThat(message, equalTo("Alice wins the race!  Congratulations!!!"));
    }

    @Test
    void currentRacerCrashed() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        String message = new MessageBuilder(race).build();

        assertThat(message, equalTo("Alice chose NORMAL speed, and rolled 1 and moved 0.  Alice CRASHED!!!  Bob rolls next!"));
    }

    @Test
    void damagedCurrentRacerRollsNormal() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(1);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        String message = new MessageBuilder(race).build();

        assertThat(message, equalTo("Alice chose NORMAL speed, and rolled 1 and moved 0.  Alice has 1 damage.  Bob rolls next!"));
    }

    @Test
    void damagedCurrentRacerRollsSuper() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(1);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, SUPER);

        String message = new MessageBuilder(race).build();

        assertThat(message, equalTo("Alice chose SUPER speed, and rolled 1 and moved 0.  Alice now has 3 damage.  Bob rolls next!"));
    }
}
