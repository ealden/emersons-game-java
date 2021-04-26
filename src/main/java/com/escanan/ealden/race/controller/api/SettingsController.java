package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.Configurations;
import com.escanan.ealden.race.controller.api.model.Settings;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Settings", urlPatterns = "/api/settings")
public class SettingsController extends ApiController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Settings settings = new Settings();
        settings.setTestMode(Configurations.isTestMode());

        jsonResponse(settings, response);
    }
}
