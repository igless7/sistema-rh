package com.monicadev.sistemarh.sistemarh.dao;

import com.monicadev.sistemarh.sistemarh.model.TipoContratacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoContratacionDAOImpl implements TipoContratacionDAO {
    private Connection conexion;

    public TipoContratacionDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }


    @Override
    public void guardar(TipoContratacion tipo) throws SQLException {
        String sql = "INSERT INTO TipoContratacion (tipoContratacion) VALUES (?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, tipo.getTipoContratacion());
            ps.executeUpdate();

            // Obtenemos el ID del nuevo poder
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    tipo.setIdTipoContratacion(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public TipoContratacion obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM TipoContratacion WHERE idTipoContratacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TipoContratacion(
                            rs.getInt("idTipoContratacion"),
                            rs.getString("tipoContratacion")
                    );
                }
            }
        }
        return null; // Poder no encontrado
    }

    @Override
    public List<TipoContratacion> obtenerTodos() throws SQLException {
        List<TipoContratacion> tipos = new ArrayList<>();
        String sql = "SELECT * FROM TipoContratacion ORDER BY tipoContratacion";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tipos.add(new TipoContratacion(
                        rs.getInt("idTipoContratacion"),
                        rs.getString("tipoContratacion")
                ));
            }
        }
        return tipos;
    }

    @Override
    public void actualizar(TipoContratacion tipo) throws SQLException {
        String sql = "UPDATE TipoContratacion SET tipoContratacion = ? WHERE idTipoContratacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tipo.getTipoContratacion());
            ps.setInt(2, tipo.getIdTipoContratacion());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM TipoContratacion WHERE idTipoContratacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
