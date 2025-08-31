package com.monicadev.sistemarh.sistemarh.dao;

import com.monicadev.sistemarh.sistemarh.model.Empleado;

import java.sql.SQLException;
import java.util.List;

public interface EmpleadoDAO {
    // CRUD completo
    void guardar(Empleado empleado) throws SQLException;// CREATE
    Empleado obtenerPorId(int id) throws SQLException;// READ (uno)
    List<Empleado> obtenerTodos() throws SQLException;// READ (todos)
    void actualizar(Empleado empleado) throws SQLException;// UPDATE
    void despedir(int id) throws SQLException;
    void reactivar(int id) throws SQLException;
}
