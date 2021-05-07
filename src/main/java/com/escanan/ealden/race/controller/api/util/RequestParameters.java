package com.escanan.ealden.race.controller.api.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public final class RequestParameters {
    private static final TypeToken<Map<String, String>> PARAMETERS_TOKEN = new TypeToken<Map<String, String>>() {
    };

    private RequestParameters() {
    }

    public static Map<String, String> fromRequest(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        return new Gson().fromJson(sb.toString(), PARAMETERS_TOKEN.getType());
    }
}
