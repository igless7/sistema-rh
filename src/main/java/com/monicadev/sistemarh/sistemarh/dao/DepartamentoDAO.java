package com.monicadev.sistemarh.sistemarh.dao;

import com.monicadev.sistemarh.sistemarh.model.Cargo;
import com.monicadev.sistemarh.sistemarh.model.Departamento;

import java.sql.SQLException;
import java.util.List;

public interface DepartamentoDAO {
    // CRUD completo
    void guardar(Departamento departamento) throws SQLException;// CREATE
    Departamento obtenerPorId(int id) throws SQLException;// READ (uno)
    List<Departamento> obtenerTodos() throws SQLException;// READ (todos)
    void actualizar(Departamento departamento) throws SQLException;// UPDATE
    void eliminar(int id) throws SQLException;//DELETE
}
