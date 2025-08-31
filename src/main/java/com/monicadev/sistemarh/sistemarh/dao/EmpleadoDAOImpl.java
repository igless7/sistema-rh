package com.monicadev.sistemarh.sistemarh.dao;

import com.monicadev.sistemarh.sistemarh.model.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImpl implements EmpleadoDAO {
    private Connection conexion;

    public EmpleadoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }
    @Override
    public void guardar(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO Empleados (numeroDui, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento, activo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, empleado.getNumeroDui());
            ps.setString(2, empleado.getNombrePersona());
            ps.setString(3, empleado.getUsuario());
            ps.setString(4, empleado.getNumeroTelefono());
            ps.setString(5, empleado.getCorreoInstitucional());
            ps.setDate(6, java.sql.Date.valueOf(empleado.getFechaNacimiento()));
            ps.setBoolean(7, empleado.isActivo());
            ps.executeUpdate();

            // Obtenemos el número de empleado auto-generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    empleado.setIdEmpleado(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Empleado obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Empleados WHERE idEmpleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                            rs.getInt("idEmpleado"),
                            rs.getString("numeroDui"),
                            rs.getString("nombrePersona"),
                            rs.getString("usuario"),
                            rs.getString("numeroTelefono"),
                            rs.getString("correoInstitucional"),
                            rs.getDate("fechaNacimiento").toLocalDate(),
                            rs.getBoolean("activo")
                    );
                }
            }
        }
        return null; // Empleado no encontrado
    }

    @Override
    public List<Empleado> obtenerTodos() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM Empleados ORDER BY idEmpleado ASC ";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                empleados.add(new Empleado(
                        rs.getInt("idEmpleado"),
                        rs.getString("numeroDui"),
                        rs.getString("nombrePersona"),
                        rs.getString("usuario"),
                        rs.getString("numeroTelefono"),
                        rs.getString("correoInstitucional"),
                        rs.getDate("fechaNacimiento").toLocalDate(),
                        rs.getBoolean("activo")
                ));
            }
        }
        return empleados;
    }

    @Override
    public void actualizar(Empleado empleado) throws SQLException {
        String sql = "UPDATE Empleados SET numeroDui = ?, nombrePersona = ?, usuario = ?, numeroTelefono = ?, correoInstitucional = ?, fechaNacimiento = ?, activo = ? WHERE idEmpleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, empleado.getNumeroDui());
            ps.setString(2, empleado.getNombrePersona());
            ps.setString(3, empleado.getUsuario());
            ps.setString(4, empleado.getNumeroTelefono());
            ps.setString(5, empleado.getCorreoInstitucional());
            ps.setDate(6, java.sql.Date.valueOf(empleado.getFechaNacimiento()));
            ps.setBoolean(7, empleado.isActivo());
            ps.setInt(8, empleado.getIdEmpleado());
            ps.executeUpdate();
        }
    }

    @Override
    public void despedir(int id) throws SQLException {
        String sql = "UPDATE Empleados SET activo = false WHERE idEmpleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void reactivar(int id) throws SQLException {
        String sql = "UPDATE Empleados SET activo = true WHERE idEmpleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

}
