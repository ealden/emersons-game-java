package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.escanan.ealden.race.model.Race.*;
import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.Racer.NO_DAMAGE;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RaceTest {
    @Test
    public void currentRacerMustBeNullIfNoRaceHasNoRacers() {
        Race race = new Race();

        assertThat(race.getCurrentRacer(), is(nullValue()));
    }

    @Test
    public void addRacerMustSetCurrentRacerToRacerAdded() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(race.getCurrentRacer(), is(sameInstance(racer)));
    }

    @Test
    public void addRacerMustSetRaceToSameRace() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(racer.getRace(), is(sameInstance(race)));
    }

    @Test
    public void addRacerMustSetRankToRacersCount() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(racer.getRank(), is(equalTo(1)));
    }

    @Test
    public void addRacerMustOnlySetFirstRacerAsCurrentRacer() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        race.addRacer(new Racer());

        assertThat(race.getCurrentRacer(), is(sameInstance(racer)));
    }

    @Test
    public void asJSON() {
        Race race = new Race();
        race.setId(1L);
        race.addRacer(new Racer("Alice"));

        Map<String, Object> json = race.asJSON();

        assertThat(json, hasEntry(ID, 1L));
        assertThat(json, hasKey(RACERS));
        assertThat(json, hasKey(CURRENT_RACER));
        assertThat(json, hasEntry(FINISH_LINE, 10));
        assertThat(json, hasEntry(OVER, false));
        assertThat(json, hasEntry(ALL_CRASHED, false));
        assertThat(json, hasEntry(MESSAGE, "Time to RACE!  Alice rolls first!"));
    }

    @Test
    public void asJSONMustReturnJSONWhenNoRacers() {
        Race race = new Race();
        race.setId(1L);

        Map<String, Object> json = race.asJSON();

        assertThat(json, hasEntry(ID, 1L));
        assertThat(json, hasKey(RACERS));
        assertThat(json, hasEntry(CURRENT_RACER, null));
        assertThat(json, hasEntry(FINISH_LINE, 10));
        assertThat(json, hasEntry(OVER, false));
        assertThat(json, hasEntry(ALL_CRASHED, false));
        assertThat(json, hasEntry(MESSAGE, null));
    }

    @Test
    public void isOverMustReturnFalseIfNoRacersHaveWon() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        race.addRacer(racer3);

        assertThat(race.isOver(), is(false));
    }

    @Test
    public void isOverMustReturnTrueIfARacerHasWon() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setPosition(race.getFinishLine());
        race.addRacer(racer3);

        assertThat(race.isOver(), is(true));
    }

    @Test
    public void rollMustCreateNewRollEntry() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        race.roll(1, NORMAL);

        assertThat(race.getLastRoll(), is(notNullValue()));
        assertThat(race.getLastRoll().getRacer(), is(sameInstance(racer)));
    }

    @Test
    public void rollMustSetCurrentRacerToNextRacer() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        race.roll(1, NORMAL);

        assertThat(race.getCurrentRacer(), is(sameInstance(racer2)));
    }

    @Test
    public void isAllCrashedMustReturnTrueIfAllRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        Racer racer2 = new Racer();
        racer2.setDamage(MAX_DAMAGE);
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setDamage(MAX_DAMAGE);
        race.addRacer(racer3);

        assertThat(race.isAllCrashed(), is(true));
    }

    @Test
    public void isAllCrashedMustReturnFalseIfNotAllRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        Racer racer2 = new Racer();
        racer2.setDamage(MAX_DAMAGE);
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setDamage(NO_DAMAGE);
        race.addRacer(racer3);

        assertThat(race.isAllCrashed(), is(false));
    }

    @Test
    public void isAllCrashedMustReturnFalseIfNoRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setDamage(NO_DAMAGE);
        race.addRacer(racer);

        Racer racer2 = new Racer();
        racer2.setDamage(NO_DAMAGE);
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setDamage(NO_DAMAGE);
        race.addRacer(racer3);

        assertThat(race.isAllCrashed(), is(false));
    }

    @Test
    public void getMessageWhenNoRacersJoined() {
        Race race = new Race();

        assertThat(race.getMessage(), is(nullValue()));
    }

    @Test
    public void getMessageWhenRacersJoined() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));

        assertThat(race.getMessage(), is(equalTo("Time to RACE!  Alice rolls first!")));
    }

    @Test
    public void getMessageAfterRacerRolls() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));
        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        assertThat(race.getMessage(), is(equalTo("Alice chose NORMAL speed, and rolled 1 and moved 1.  Bob rolls next!")));
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

        race.roll(0, NORMAL);

        assertThat(race.getMessage(), is(equalTo("All racers CRASHED!!!  This race is over!")));
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

        race.roll(0, NORMAL);

        assertThat(race.getMessage(), is(equalTo("Alice wins the race!  Congratulations!!!")));
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

        race.roll(0, NORMAL);

        assertThat(race.getMessage(), is(equalTo("Alice chose NORMAL speed, and rolled 0 and moved 0.  Alice CRASHED!!!  Bob rolls next!")));
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

        race.roll(0, NORMAL);

        assertThat(race.getMessage(), is(equalTo("Alice chose NORMAL speed, and rolled 0 and moved 0.  Alice has 1 damage.  Bob rolls next!")));
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

        race.roll(0, SUPER);

        assertThat(race.getMessage(), is(equalTo("Alice chose SUPER speed, and rolled 0 and moved 0.  Alice now has 3 damage.  Bob rolls next!")));
    }
}
