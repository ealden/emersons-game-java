package com.escanan.ealden.race.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.escanan.ealden.race.model.Race.DEFAULT_FINISH_LINE;
import static com.escanan.ealden.race.model.Racer.*;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class RacerTest {
    private Race currentRace;

    @BeforeEach
    void setUp() {
        currentRace = new Race();
        currentRace.setFinishLine(DEFAULT_FINISH_LINE);
    }

    @Test
    void rollMustSetPositionTo1WithNoDamageIfRollIsOddAndSpeedTypeNormal() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(1, NORMAL);

        assertThat(racer.getPosition(), equalTo(1));
        assertThat(racer.getDamage(), equalTo(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(3, NORMAL);

        assertThat(racer.getPosition(), equalTo(1));
        assertThat(racer.getDamage(), equalTo(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(5, NORMAL);

        assertThat(racer.getPosition(), equalTo(1));
        assertThat(racer.getDamage(), equalTo(0));
    }

    @Test
    void rollMustSetPositionTo2WithNoDamageIfRollIsEvenAndSpeedTypeNormal() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(2, NORMAL);

        assertThat(racer.getPosition(), equalTo(2));
        assertThat(racer.getDamage(), equalTo(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(4, NORMAL);

        assertThat(racer.getPosition(), equalTo(2));
        assertThat(racer.getDamage(), equalTo(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(6, NORMAL);

        assertThat(racer.getPosition(), equalTo(2));
        assertThat(racer.getDamage(), equalTo(0));
    }

    @Test
    void rollMustSetPositionToRollWithDamageIfSpeedTypeSuper() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(1, SUPER);

        assertThat(racer.getPosition(), equalTo(1));
        assertThat(racer.getDamage(), equalTo(2));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(2, SUPER);

        assertThat(racer.getPosition(), equalTo(2));
        assertThat(racer.getDamage(), equalTo(2));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(3, SUPER);

        assertThat(racer.getPosition(), equalTo(3));
        assertThat(racer.getDamage(), equalTo(2));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(4, SUPER);

        assertThat(racer.getPosition(), equalTo(4));
        assertThat(racer.getDamage(), equalTo(2));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(5, SUPER);

        assertThat(racer.getPosition(), equalTo(5));
        assertThat(racer.getDamage(), equalTo(2));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(6, SUPER);

        assertThat(racer.getPosition(), equalTo(6));
        assertThat(racer.getDamage(), equalTo(2));
    }

    @Test
    void getPositionMustReturnCurrentPositionIfNotYetOnFinishLine() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(1);

        assertThat(racer.getPosition(), equalTo(1));
    }

    @Test
    void getPositionMustReturnFinishLineIfCrossedFinishLine() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(currentRace.getFinishLine() + 10);

        assertThat(racer.getPosition(), equalTo(currentRace.getFinishLine()));
    }

    @Test
    void asJson() {
        Map<String, Object> expected = new HashMap<>();
        expected.put("id", 1L);
        expected.put("name", "Alice");
        expected.put("position", 0);
        expected.put("damage", 0);
        expected.put("rank", 1);
        expected.put("crashed", false);
        expected.put("damaged", false);
        expected.put("winner", false);

        Racer racer = new Racer("Alice");
        racer.setId(1L);
        racer.setRace(currentRace);
        racer.setRank(1);

        assertThat(racer.asJson(), equalTo(expected));
    }

    @Test
    void isWinnerMustReturnTrueIfOnFinishLine() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(currentRace.getFinishLine());

        assertThat(racer.isWinner(), equalTo(true));
    }

    @Test
    void isWinnerMustReturnTrueIfCrossedFinishLine() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(currentRace.getFinishLine() + 1);

        assertThat(racer.isWinner(), equalTo(true));
    }

    @Test
    void isWinnerMustReturnFalseIfNotYetOnFinishLine() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(currentRace.getFinishLine() - 1);

        assertThat(racer.isWinner(), equalTo(false));
    }

    @Test
    void isCrashedMustReturnFalseIfDamageLessThanMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE - 1);

        assertThat(racer.isCrashed(), equalTo(false));
    }

    @Test
    void isCrashedMustReturnTrueIfDamageEqualToMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);

        assertThat(racer.isCrashed(), equalTo(true));
    }

    @Test
    void isCrashedMustReturnTrueIfDamageMoreThanMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE + 1);

        assertThat(racer.isCrashed(), equalTo(true));
    }

    @Test
    void isDamagedMustReturnTrueIfDamageGreaterThanZero() {
        Racer racer = new Racer();
        racer.setDamage(1);

        assertThat(racer.isDamaged(), equalTo(true));
    }

    @Test
    void isDamagedMustReturnFalseIfDamageIsZero() {
        Racer racer = new Racer();
        racer.setDamage(0);

        assertThat(racer.isDamaged(), equalTo(false));
    }

    @Test
    void isDamagedMustReturnFalseIfDamageIsLessThanZero() {
        Racer racer = new Racer();
        racer.setDamage(-1);

        assertThat(racer.isDamaged(), equalTo(false));
    }

    @Test
    void isReadyMustReturnFalseIfNoRacersJoined() {
        Race race = new Race();

        assertThat(race.isReady(), equalTo(false));
    }

    @Test
    void isReadyMustReturnTrueIfARacerJoined() {
        Race race = new Race();
        race.addRacer(new Racer());

        assertThat(race.isReady(), equalTo(true));
    }

    @Test
    void isReadyMustReturnFalseIfRaceIsOver() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setPosition(race.getFinishLine());
        race.addRacer(racer);

        assertThat(race.isReady(), equalTo(false));
    }

    @Test
    void isStartedMustReturnFalseIfNoRacersJoined() {
        Race race = new Race();

        assertThat(race.isStarted(), equalTo(false));
    }

    @Test
    void isStartedMustReturnFalseIfNoRacerHasRolled() {
        Race race = new Race();
        race.addRacer(new Racer());

        assertThat(race.isStarted(), equalTo(false));
    }

    @Test
    void isStartedMustReturnTrueIfOneRacerHasRolled() {
        Race race = new Race();
        race.addRacer(new Racer());

        race.roll(1, NORMAL);

        assertThat(race.isStarted(), equalTo(true));
    }

    @Test
    void isStartedMustReturnTrueIfRacersHaveRolled() {
        Race race = new Race();
        race.addRacer(new Racer());
        race.addRacer(new Racer());

        race.roll(1, NORMAL);
        race.roll(1, NORMAL);

        assertThat(race.isStarted(), equalTo(true));
    }

    @Test
    void isStartedMustReturnFalseIfRaceIsOver() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setPosition(race.getFinishLine());
        race.addRacer(racer);

        race.roll(1, NORMAL);

        assertThat(race.isStarted(), equalTo(false));
    }
}
