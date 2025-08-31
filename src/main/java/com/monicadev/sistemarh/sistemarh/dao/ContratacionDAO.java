package com.monicadev.sistemarh.sistemarh.dao;

import com.monicadev.sistemarh.sistemarh.model.Contratacion;

import java.sql.SQLException;
import java.util.List;

public interface ContratacionDAO {
    void guardar(Contratacion contratacion) throws SQLException;          // CREATE
    Contratacion obtenerPorId(int id) throws SQLException;        // READ (uno)
    List<Contratacion> obtenerTodos() throws SQLException;        // READ (todos)
    void actualizar(Contratacion contratacion) throws SQLException;      // UPDATE
    void eliminar(int id) throws SQLException;
    void reactivarContratacion(int id) throws SQLException;
}
