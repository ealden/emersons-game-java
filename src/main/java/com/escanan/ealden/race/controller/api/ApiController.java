package com.escanan.ealden.race.controller.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Map;

public abstract class ApiController extends HttpServlet {
    private final Gson gson = new Gson();

    protected void jsonResponse(Object responseObject, HttpServletResponse response) throws IOException {
        String responseBody = gson.toJson(responseObject);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(responseBody);
        out.flush();
    }

    protected Map<String, String> jsonRequest(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        Type collectionType = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> parameters = new Gson().fromJson(sb.toString(), collectionType);

        return parameters;
    }
}
