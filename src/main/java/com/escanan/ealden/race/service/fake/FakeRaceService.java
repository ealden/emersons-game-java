package com.escanan.ealden.race.service.fake;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;

public class FakeRaceService implements RaceService {
    private Race currentRace;

    @Override
    public Race getCurrentRace() {
        return currentRace;
    }

    @Override
    public Race newRace() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));
        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        return save(race);
    }

    @Override
    public Race save(Race race) {
        currentRace = race;

        return currentRace;
    }
}
