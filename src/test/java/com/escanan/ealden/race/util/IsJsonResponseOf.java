package com.escanan.ealden.race.util;

import com.google.gson.Gson;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.io.IOException;
import java.util.Map;

public class IsJsonResponseOf extends TypeSafeMatcher<String> {
    private final String json;

    public IsJsonResponseOf(String url) throws IOException {
        this.json = JsonResponse.of(url);
    }

    @Override
    protected boolean matchesSafely(String input) {
        Gson gson = new Gson();

        Map expected = gson.fromJson(json, Map.class);
        Map actual = gson.fromJson(input, Map.class);

        return expected.equals(actual);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(json);
    }
}
