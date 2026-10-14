package com.huertos.parcels.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InputSanitizerTest {

    @Test
    void removesHtmlAndNormalizesSpaces() {
        String input = "  Huerto <script>alert(1)</script>  Centro   ";
        assertEquals("Huerto alert(1) Centro", InputSanitizer.sanitizePlainText(input));
    }

    @Test
    void returnsNullWhenInputIsNull() {
        assertNull(InputSanitizer.sanitizePlainText(null));
    }
}
