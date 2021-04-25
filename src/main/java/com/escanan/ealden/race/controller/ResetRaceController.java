package com.escanan.ealden.race.controller;

import com.escanan.ealden.race.service.RaceService;
import com.escanan.ealden.race.service.impl.RaceServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ResetRace", urlPatterns = "/reset")
public class ResetRaceController extends HttpServlet {
    private final RaceService raceService = RaceServiceImpl.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        raceService.newRace();
    }
}
