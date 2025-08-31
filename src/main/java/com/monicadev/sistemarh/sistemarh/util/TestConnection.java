package com.monicadev.sistemarh.sistemarh.util;

import com.monicadev.sistemarh.sistemarh.config.DatabaseConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestConnection {
    public static void main(String[] args) {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(
//                    DatabaseConfig.getDbUrl(),    // Usa el mismo método que DBConnection
//                    DatabaseConfig.getDbUser(),   // Usa el mismo método que DBConnection
//                    DatabaseConfig.getDbPassword() // Usa el mismo método que DBConnection

            HikariConfig config = new HikariConfig("src/main/resources/hikari.properties");

            HikariDataSource dataSource = new HikariDataSource(config);

            System.out.println("¡Conexión exitosa!");

            try (Connection conn = dataSource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT cargo, descripcionCargo FROM cargos LIMIT 1");
                 ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    String cargo = rs.getString("cargo");
                    String descripcion = rs.getString("descripcionCargo");
                    System.out.println("Cargo: " + cargo + " | Descripción: " + descripcion);
                } else {
                    System.out.println("⚠️ No se encontraron registros en la tabla 'cargos'.");
                }
            }
            //conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
