package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.Configurations;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Settings", urlPatterns = "/api/settings")
public class SettingsController extends ApiController {
    private static final String TEST_MODE_PARAM = "testMode";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Boolean> json = new HashMap<>();
        json.put(TEST_MODE_PARAM, Configurations.isTestMode());

        renderJson(json, response);
    }
}
