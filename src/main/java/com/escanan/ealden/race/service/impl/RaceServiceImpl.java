package com.escanan.ealden.race.service.impl;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.service.RaceService;

public class RaceServiceImpl implements RaceService {
    public static final RaceService INSTANCE = new RaceServiceImpl();

    private Race race;

    @Override
    public Race getCurrentRace() {
        if (race == null) {
            race = newRace();
        }

        return race;
    }

    @Override
    public Race newRace() {
        Race race = new Race();
        race.addRacer(new Racer());

        this.race = race;

        return race;
    }
}
