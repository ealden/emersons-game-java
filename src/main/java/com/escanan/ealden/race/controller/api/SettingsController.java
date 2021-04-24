package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.controller.api.model.Settings;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Settings", urlPatterns = "/api/settings")
public class SettingsController extends HttpServlet {
    private static final boolean TEST_MODE = true;

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Settings settings = new Settings();
        settings.setTestMode(TEST_MODE);

        String responseBody = gson.toJson(settings);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(responseBody);
        out.flush();
    }
}
