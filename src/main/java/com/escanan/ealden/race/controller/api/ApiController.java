package com.escanan.ealden.race.controller.api;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class ApiController extends HttpServlet {
    private final Gson gson = new Gson();

    protected void renderJson(Object responseObject, HttpServletResponse response) {
        String responseBody = gson.toJson(responseObject);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            PrintWriter out = response.getWriter();
            out.print(responseBody);
            out.flush();
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    static class JsonException extends RuntimeException {
        public JsonException(Throwable cause) {
            super(cause);
        }
    }
}
