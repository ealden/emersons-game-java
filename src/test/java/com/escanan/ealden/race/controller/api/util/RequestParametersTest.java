package com.escanan.ealden.race.controller.api.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestParametersTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private BufferedReader reader;

    @Test
    void fromRequest() throws IOException {
        String requestBody = "{\"roll\":\"1\",\"speedType\":\"NORMAL\"}";

        when(request.getReader()).thenReturn(reader);
        when(reader.readLine()).thenReturn(requestBody).thenReturn(null);

        Map<String, String> parameters = RequestParameters.fromRequest(request);

        assertThat(parameters, hasEntry("roll", "1"));
        assertThat(parameters, hasEntry("speedType", "NORMAL"));
    }
}
