package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MessageBuilderTest {
    @Test
    public void getMessageWhenNoRacersJoined() {
        Race race = new Race();

        String message = new MessageBuilder(race).build();

        assertThat(message, is(nullValue()));
    }

    @Test
    public void getMessageWhenRacersJoined() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));

        String message = new MessageBuilder(race).build();

        assertThat(message, is(equalTo("Time to RACE!  Alice rolls first!")));
    }

    @Test
    public void getMessageAfterRacerRolls() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));
        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        String message = new MessageBuilder(race).build();

        assertThat(message, is(equalTo("Alice chose NORMAL speed, and rolled 1 and moved 1.  Bob rolls next!")));
    }

    @Test
    public void getMessageWhenAllRacersCrashed() {
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

        assertThat(message, is(equalTo("All racers CRASHED!!!  This race is over!")));
    }

    @Test
    public void getMessageWhenARacerWins() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setPosition(race.getFinishLine());
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        String message = new MessageBuilder(race).build();

        assertThat(message, is(equalTo("Alice wins the race!  Congratulations!!!")));
    }

    @Test
    public void getMessageWhenLastRacerCrashed() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        String message = new MessageBuilder(race).build();

        assertThat(message, is(equalTo("Alice chose NORMAL speed, and rolled 1 and moved 0.  Alice CRASHED!!!  Bob rolls next!")));
    }

    @Test
    public void getMessageWhenLastRacerDamagedAndRollWithNormalSpeed() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(1);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        String message = new MessageBuilder(race).build();

        assertThat(message, is(equalTo("Alice chose NORMAL speed, and rolled 1 and moved 0.  Alice has 1 damage.  Bob rolls next!")));
    }

    @Test
    public void getMessageWhenLastRacerDamagedAndRollWithSuperSpeed() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(1);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, SUPER);

        String message = new MessageBuilder(race).build();

        assertThat(message, is(equalTo("Alice chose SUPER speed, and rolled 1 and moved 0.  Alice now has 3 damage.  Bob rolls next!")));
    }
}
