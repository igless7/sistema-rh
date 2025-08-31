package com.monicadev.sistemarh.sistemarh.dao;

import com.monicadev.sistemarh.sistemarh.model.Cargo;
import com.monicadev.sistemarh.sistemarh.model.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAOImpl implements DepartamentoDAO {
    private Connection conexion;

    public DepartamentoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }
    @Override
    public void guardar(Departamento departamento) throws SQLException {
        String sql = "INSERT INTO Departamento (nombreDepartamento, descripcionDepartamento) VALUES (?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, departamento.getNombreDepartamento());
            ps.setString(2, departamento.getDescripcion());
            ps.executeUpdate();

            // Obtenemos el número de edificio auto-generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    departamento.setIdDepartamento(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Departamento obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Departamento WHERE idDepartamento = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Departamento(
                            rs.getInt("idDepartamento"),
                            rs.getString("nombreDepartamento"),
                            rs.getString("descripcionDepartamento")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Departamento> obtenerTodos() throws SQLException {
        List<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT * FROM Departamento ORDER BY nombreDepartamento";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                departamentos.add(new Departamento(
                        rs.getInt("idDepartamento"),
                        rs.getString("nombreDepartamento"),
                        rs.getString("descripcionDepartamento")
                ));
            }
        }
        return departamentos;
    }

    @Override
    public void actualizar(Departamento departamento) throws SQLException {
        String sql = "UPDATE Departamento SET nombreDepartamento = ?, descripcionDepartamento = ? WHERE idDepartamento = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, departamento.getNombreDepartamento());
            ps.setString(2, departamento.getDescripcion());
            ps.setInt(3, departamento.getIdDepartamento());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Departamento WHERE idDepartamento = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
