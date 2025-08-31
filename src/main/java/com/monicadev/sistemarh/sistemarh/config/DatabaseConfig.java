package com.monicadev.sistemarh.sistemarh.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class DatabaseConfig {
    private static final String PROPERTIES_FILE = "hikari.properties";
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = DatabaseConfig.class.getClassLoader()
                .getResourceAsStream(PROPERTIES_FILE)) {

            if (input == null) {
                throw new RuntimeException("No se pudo encontrar " + PROPERTIES_FILE);
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error cargando configuración de la base de datos", ex);
        }
    }

    public static String getDbUrl() {
        return properties.getProperty("jdbcUrl");
    }

    public static String getDbUser() {
        return properties.getProperty("username");
    }

    public static String getDbPassword() {
        return properties.getProperty("password");
    }
}
