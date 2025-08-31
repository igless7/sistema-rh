package com.monicadev.sistemarh.sistemarh.service;

import com.monicadev.sistemarh.sistemarh.dao.DepartamentoDAO;
import com.monicadev.sistemarh.sistemarh.model.Departamento;

import java.sql.SQLException;
import java.util.List;

public class DepartamentoService {
    private DepartamentoDAO departamentoDAO;

    public DepartamentoService(DepartamentoDAO departamentoDAO) {
        this.departamentoDAO = departamentoDAO;
    }

    public void crearDepartamento(Departamento departamento) throws SQLException {
        departamentoDAO.guardar(departamento);
    }

    public Departamento obtenerDepartamento(int id) throws SQLException {
        return departamentoDAO.obtenerPorId(id);
    }

    public List<Departamento> obtenerTodosDepartamentos() throws SQLException {
        return departamentoDAO.obtenerTodos();
    }

    public void actualizarDepartamento(Departamento depto) throws SQLException {
        departamentoDAO.actualizar(depto);
    }

    public void eliminarDepartamento(int id) throws SQLException {
        departamentoDAO.eliminar(id);
    }
}
