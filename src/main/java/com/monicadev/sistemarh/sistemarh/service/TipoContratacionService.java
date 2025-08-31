package com.monicadev.sistemarh.sistemarh.service;

import com.monicadev.sistemarh.sistemarh.dao.TipoContratacionDAO;
import com.monicadev.sistemarh.sistemarh.model.TipoContratacion;

import java.sql.SQLException;
import java.util.List;

public class TipoContratacionService {
    private TipoContratacionDAO tipoContratacionDAO;

    public TipoContratacionService(TipoContratacionDAO tipoContratacionDAO) {
        this.tipoContratacionDAO = tipoContratacionDAO;
    }

    public void crearTipoContratacion(TipoContratacion tipo) throws SQLException {
        tipoContratacionDAO.guardar(tipo);
    }

    public TipoContratacion obtenerTipoContratacion(int id) throws SQLException {
        return tipoContratacionDAO.obtenerPorId(id);
    }

    public List<TipoContratacion> obtenerTodosTiposContratacion() throws SQLException {
        return tipoContratacionDAO.obtenerTodos();
    }

    public void actualizarTipoContratacion(TipoContratacion tipo) throws SQLException {
        tipoContratacionDAO.actualizar(tipo);
    }

    public void eliminarTipoContratacion(int id) throws SQLException {
        tipoContratacionDAO.eliminar(id);
    }
}
