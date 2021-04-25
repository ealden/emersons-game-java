package com.escanan.ealden.race;

import com.escanan.ealden.race.util.IsJsonResponseOf;
import org.hamcrest.Matcher;

import java.io.IOException;

public class Matchers {
    public static Matcher<String> jsonResponseOf(String url) throws IOException {
        return new IsJsonResponseOf(url);
    }
}
