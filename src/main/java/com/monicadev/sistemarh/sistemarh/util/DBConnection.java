package com.monicadev.sistemarh.sistemarh.util;

import com.monicadev.sistemarh.sistemarh.config.DatabaseConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class DBConnection {
    private static volatile HikariDataSource dataSource;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (dataSource == null) {
            synchronized (DBConnection.class) {  // Thread-safe singleton
                if (dataSource == null) {
                    HikariConfig config = new HikariConfig();
                    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    config.setJdbcUrl(DatabaseConfig.getDbUrl());
                    config.setUsername(DatabaseConfig.getDbUser());
                    config.setPassword(DatabaseConfig.getDbPassword());

                    // Configuración recomendada para producción
                    config.setMaximumPoolSize(10);
                    config.setMinimumIdle(5);
                    config.setConnectionTimeout(30000);  // 30 segundos
                    config.setIdleTimeout(600000);      // 10 minutos
                    config.setMaxLifetime(1800000);     // 30 minutos
                    config.setPoolName("HikariPool");  // Identificador útil en logs

                    dataSource = new HikariDataSource(config);
                    System.out.println("✅ Archivo hikari.properties cargado correctamente");
                }
            }
        }
        return dataSource.getConnection();
    }


    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

}

