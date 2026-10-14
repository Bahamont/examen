package com.huertos.parcels.util;

import java.util.regex.Pattern;

/**
 * Sanitiza entradas de texto para reducir riesgo de inyección HTML/XSS
 * y normalizar datos antes de persistirlos.
 */
public final class InputSanitizer {

    private static final Pattern HTML_TAGS = Pattern.compile("<[^>]*>");
    private static final Pattern CONTROL_CHARS = Pattern.compile("[\\p{Cntrl}&&[^\r\n\t]]");
    private static final Pattern MULTI_SPACE = Pattern.compile("\\s{2,}");

    private InputSanitizer() {
    }

    public static String sanitizeText(String input) {
        if (input == null) {
            return null;
        }

        String cleaned = HTML_TAGS.matcher(input).replaceAll("");
        cleaned = CONTROL_CHARS.matcher(cleaned).replaceAll("");
        cleaned = cleaned
                .replace("&", "&amp;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
        cleaned = cleaned.trim();
        cleaned = MULTI_SPACE.matcher(cleaned).replaceAll(" ");
        return cleaned;
    }

    /**
     * Versión para campos de negocio donde no se desea escapar entidades HTML,
     * solo remover etiquetas y normalizar espacios.
     */
    public static String sanitizePlainText(String input) {
        if (input == null) {
            return null;
        }

        String cleaned = HTML_TAGS.matcher(input).replaceAll("");
        cleaned = CONTROL_CHARS.matcher(cleaned).replaceAll("");
        cleaned = cleaned.trim();
        cleaned = MULTI_SPACE.matcher(cleaned).replaceAll(" ");
        return cleaned;
    }
}
