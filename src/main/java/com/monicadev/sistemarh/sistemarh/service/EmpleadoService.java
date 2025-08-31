package com.monicadev.sistemarh.sistemarh.service;

import com.monicadev.sistemarh.sistemarh.dao.EmpleadoDAO;
import com.monicadev.sistemarh.sistemarh.model.Empleado;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class EmpleadoService {
    private EmpleadoDAO empleadoDAO;

    public EmpleadoService(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    public void contratarEmpleado(Empleado empleado) throws SQLException {
        empleadoDAO.guardar(empleado);
    }

    public Empleado obtenerEmpleado(int id) throws SQLException {
        return empleadoDAO.obtenerPorId(id);
    }

    public List<Empleado> obtenerTodosEmpleados() throws SQLException {
        return empleadoDAO.obtenerTodos();
    }

    public List<Empleado> obtenerEmpleadosActivos() throws SQLException {
        return empleadoDAO.obtenerTodos().stream()
                .filter(Empleado::isActivo)
                .collect(Collectors.toList());
    }

    public void actualizarEmpleado(Empleado empleado) throws SQLException {
        empleadoDAO.actualizar(empleado);
    }

    public void despedirEmpleado(int id) throws SQLException {
        empleadoDAO.despedir(id);
    }

    public void reactivarEmpleado(int id) throws SQLException {
        empleadoDAO.reactivar(id);
    }
}
