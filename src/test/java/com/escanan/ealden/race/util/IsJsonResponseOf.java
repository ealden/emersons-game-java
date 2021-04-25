package com.escanan.ealden.race.util;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.io.IOException;

public class IsJsonResponseOf extends TypeSafeMatcher<String> {
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private static final String BLANK = "";

    private final String url;
    private final String json;

    public IsJsonResponseOf(String url) throws IOException {
        this.url = url;
        this.json = JsonResponse.of(url);
    }

    @Override
    protected boolean matchesSafely(String item) {
        String expected = trimSpacesAndNewLines(json);
        String actual = trimSpacesAndNewLines(item);

        return expected.equals(actual);
    }

    private String trimSpacesAndNewLines(String input) {
        return input.trim().replaceAll(SPACE, BLANK).replaceAll(NEW_LINE, BLANK);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("response of " + url);
    }
}
