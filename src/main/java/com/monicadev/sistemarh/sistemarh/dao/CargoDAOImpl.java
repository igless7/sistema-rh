package com.monicadev.sistemarh.sistemarh.dao;

import com.monicadev.sistemarh.sistemarh.model.Cargo;
import com.monicadev.sistemarh.sistemarh.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargoDAOImpl implements CargoDAO {
    private Connection conexion;

    public CargoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }
    @Override
    public void guardar(Cargo cargo) throws SQLException {
        String sql = "INSERT INTO Cargos (cargo, descripcionCargo, jefatura) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cargo.getCargo());
            ps.setString(2, cargo.getDescripcionCargo());
            ps.setBoolean(3, cargo.isJefatura());
            ps.executeUpdate();

            // Obtenemos el ID generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cargo.setIdCargo(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Cargo obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Cargos WHERE idCargo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cargo(
                            rs.getInt("idCargo"),
                            rs.getString("cargo"),
                            rs.getString("descripcionCargo"),
                            rs.getBoolean("jefatura")
                    );
                }
            }
        }
        return null; // No encontrado
    }


    @Override
    public List<Cargo> obtenerTodos() throws SQLException {
        List<Cargo> cargos = new ArrayList<>();
        String sql = "SELECT * FROM Cargos ORDER BY cargo";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cargos.add(new Cargo(
                        rs.getInt("idCargo"),
                        rs.getString("cargo"),
                        rs.getString("descripcionCargo"),
                        rs.getBoolean("jefatura")
                ));
            }
        }
        return cargos;
    }

    @Override
    public void actualizar(Cargo cargo) throws SQLException {
        String sql = "UPDATE Cargos SET cargo = ?, descripcionCargo = ?, jefatura = ? WHERE idCargo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cargo.getCargo());
            ps.setString(2, cargo.getDescripcionCargo());
            ps.setBoolean(3, cargo.isJefatura());
            ps.setInt(4, cargo.getIdCargo());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Cargos WHERE idCargo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
