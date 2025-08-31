package com.monicadev.sistemarh.sistemarh.dao;

import com.monicadev.sistemarh.sistemarh.model.Cargo;

import java.sql.SQLException;
import java.util.List;

public interface CargoDAO {
    // CRUD completo
    void guardar(Cargo cargo) throws SQLException;          // CREATE
    Cargo obtenerPorId(int id) throws SQLException;        // READ (uno)
    List<Cargo> obtenerTodos() throws SQLException;        // READ (todos)
    void actualizar(Cargo cargo) throws SQLException;      // UPDATE
    void eliminar(int id) throws SQLException;             // DELETE
}
