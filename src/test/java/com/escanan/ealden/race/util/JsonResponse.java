package com.escanan.ealden.race.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonResponse {
    private static final String RESPONSES_DIR = "src/test/resources/responses";
    private static final String JSON_EXTENSION = ".json";

    public static String of(String url) throws IOException {
        Path file = Paths.get(RESPONSES_DIR + url + JSON_EXTENSION);

        return new String(Files.readAllBytes(file));
    }
}
