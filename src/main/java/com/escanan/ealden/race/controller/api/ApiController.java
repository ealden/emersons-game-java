package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.controller.api.model.RequestParameters;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public abstract class ApiController extends HttpServlet {
    private final Gson gson = new Gson();

    protected void renderJson(Object responseObject, HttpServletResponse response) throws IOException {
        String responseBody = gson.toJson(responseObject);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(responseBody);
        out.flush();
    }

    protected Map<String, String> requestParameters(HttpServletRequest request) throws IOException {
        return RequestParameters.fromRequest(request);
    }
}
