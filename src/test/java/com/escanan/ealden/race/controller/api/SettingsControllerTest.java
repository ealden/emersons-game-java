package com.escanan.ealden.race.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static com.escanan.ealden.race.controller.api.SettingsController.TEST_MODE_PARAM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;

@ExtendWith(MockitoExtension.class)
class SettingsControllerTest {
    private SettingsController controller;

    @BeforeEach
    void setUp() {
        controller = new SettingsController();
    }

    @Test
    void indexMustReturnSettings() {
        controller.setTestMode(false);

        Map<String, Boolean> settings = controller.index();

        assertThat(settings, hasEntry(TEST_MODE_PARAM, false));
    }
}
