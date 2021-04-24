package com.escanan.ealden.race.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.escanan.ealden.race.Matchers.jsonResponseOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RollControllerTest {
    private RollController controller;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private PrintWriter writer;

    @Captor
    private ArgumentCaptor<String> responseBody;

    @BeforeEach
    public void setUp() throws IOException {
        controller = new RollController();

        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void doPostMustReturnJsonResponse() throws IOException {
        controller.doPost(request, response);

        verify(writer).print(responseBody.capture());

        assertThat(responseBody.getValue(), is(jsonResponseOf("/api/races")));
    }
}
