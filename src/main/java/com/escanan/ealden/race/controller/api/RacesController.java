package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Races", urlPatterns = "/api/races")
public class RacesController extends ApiController {
    public static final Race CURRENT_RACE;

    static {
        CURRENT_RACE = new Race();
        CURRENT_RACE.addRacer(new Racer());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        jsonResponse(CURRENT_RACE, response);
    }
}