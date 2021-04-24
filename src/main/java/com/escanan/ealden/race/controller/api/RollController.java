package com.escanan.ealden.race.controller.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Roll", urlPatterns = "/api/races/roll")
public class RollController extends ApiController {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        jsonResponse(RacesController.CURRENT_RACE, response);
    }
}
