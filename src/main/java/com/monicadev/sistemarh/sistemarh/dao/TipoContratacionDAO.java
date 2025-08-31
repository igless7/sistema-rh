package com.monicadev.sistemarh.sistemarh.dao;

import com.monicadev.sistemarh.sistemarh.model.TipoContratacion;

import java.sql.SQLException;
import java.util.List;

public interface TipoContratacionDAO {
    // CRUD completo
    void guardar(TipoContratacion tipo) throws SQLException;          // CREATE
    TipoContratacion obtenerPorId(int id) throws SQLException;           // READ (uno)
    List<TipoContratacion> obtenerTodos() throws SQLException;      // READ (todos)
    void actualizar(TipoContratacion tipo) throws SQLException;          // UPDATE
    void eliminar(int id) throws SQLException;                         // DELETE
}
