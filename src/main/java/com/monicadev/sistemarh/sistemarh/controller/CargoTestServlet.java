package com.monicadev.sistemarh.sistemarh.controller;

import com.monicadev.sistemarh.sistemarh.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/test-cargo")
public class CargoTestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cargo = "";
        String descripcion = "";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT cargo, descripcionCargo FROM cargos LIMIT 1");
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                cargo = rs.getString("cargo");
                descripcion = rs.getString("descripcionCargo");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("cargo", cargo);
        request.setAttribute("descripcion", descripcion);
        request.getRequestDispatcher("/views/contratacion/test.jsp").forward(request, response);
    }



}

