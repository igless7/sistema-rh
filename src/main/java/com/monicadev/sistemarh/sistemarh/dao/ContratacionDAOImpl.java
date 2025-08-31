package com.monicadev.sistemarh.sistemarh.dao;

import com.monicadev.sistemarh.sistemarh.model.Contratacion;
import com.monicadev.sistemarh.sistemarh.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratacionDAOImpl  implements ContratacionDAO{
    private Connection conexion;

    public ContratacionDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }
    @Override
    public void guardar(Contratacion contratacion) throws SQLException {
        String sql = "INSERT INTO Contrataciones (idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, contratacion.getIdDepartamento());
            ps.setInt(2, contratacion.getIdEmpleado());
            ps.setInt(3, contratacion.getIdCargo());
            ps.setInt(4, contratacion.getIdTipoContratacion());
            ps.setDate(5, new java.sql.Date(contratacion.getFechaContratacion().getTime()));
            ps.setDouble(6, contratacion.getSalario());
            ps.setBoolean(7, contratacion.isEstado());
            ps.executeUpdate();

            // Obtenemos el ID del nuevo equipo
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    contratacion.setIdContratacion(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Contratacion obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Contrataciones WHERE idContratacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Contratacion(
                            rs.getInt("idContratacion"),
                            rs.getInt("idDepartamento"),
                            rs.getInt("idEmpleado"),
                            rs.getInt("idCargo"),
                            rs.getInt("idTipoContratacion"),
                            rs.getDate("fechaContratacion"),
                            rs.getDouble("salario"),
                            rs.getBoolean("estado")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Contratacion> obtenerTodos() throws SQLException {
        List<Contratacion> contrataciones = new ArrayList<>();
        String sql = "SELECT * FROM Contrataciones ORDER BY idContratacion ASC";
        /*String sql = "SELECT c.*, e.nombrePersona as nombreEmpleado, " +
                "d.nombreDepartamento, car.cargo as nombreCargo, " +
                "tc.tipoContratacion as nombreTipo " +
                "FROM contrataciones c " +
                "LEFT JOIN empleados e ON c.idEmpleado = e.idEmpleado " +
                "LEFT JOIN departamento d ON c.idDepartamento = d.idDepartamento " +
                "LEFT JOIN cargos car ON c.idCargo = car.idCargo " +
                "LEFT JOIN tipoContratacion tc ON c.idTipoContratacion = tc.id";*/
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                contrataciones.add(new Contratacion(
                        rs.getInt("idContratacion"),
                        rs.getInt("idDepartamento"),
                        rs.getInt("idEmpleado"),
                        rs.getInt("idCargo"),
                        rs.getInt("idTipoContratacion"),
                        rs.getDate("fechaContratacion"),
                        rs.getDouble("salario"),
                        rs.getBoolean("estado")
                ));
            }
        }
        return contrataciones;
    }

    @Override
    public void actualizar(Contratacion contratacion) throws SQLException {
        String sql = "UPDATE Contrataciones SET idDepartamento = ?, idEmpleado = ?, idCargo = ?, idTipoContratacion = ?, fechaContratacion = ?, salario = ?, estado = ? WHERE idContratacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, contratacion.getIdDepartamento());
            ps.setInt(2, contratacion.getIdEmpleado());
            ps.setInt(3, contratacion.getIdCargo());
            ps.setInt(4, contratacion.getIdTipoContratacion());
            ps.setDate(5, new java.sql.Date(contratacion.getFechaContratacion().getTime()));
            ps.setDouble(6, contratacion.getSalario());
            ps.setBoolean(7, contratacion.isEstado());
            ps.setInt(8, contratacion.getIdContratacion());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "UPDATE Contrataciones SET estado = false WHERE idContratacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void reactivarContratacion(int id) throws SQLException {
        String sql = "UPDATE Contrataciones SET estado = true WHERE idContratacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
