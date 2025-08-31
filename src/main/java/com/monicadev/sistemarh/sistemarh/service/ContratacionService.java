package com.monicadev.sistemarh.sistemarh.service;

import com.monicadev.sistemarh.sistemarh.dao.ContratacionDAO;
import com.monicadev.sistemarh.sistemarh.exception.BusinessException;
import com.monicadev.sistemarh.sistemarh.model.Contratacion;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ContratacionService {
    private ContratacionDAO contratacionDAO;

    public ContratacionService(ContratacionDAO contratacionDAO) {
        this.contratacionDAO = contratacionDAO;
    }

    public void crearContratacion(Contratacion contratacion) throws BusinessException {
        // Validaciones de negocio
        if (contratacion.getSalario() <= 0) {
            throw new BusinessException("El salario debe ser mayor a cero");
        }

        if (contratacion.getFechaContratacion().after(new Date())) {
            throw new BusinessException("La fecha de contratación no puede ser futura");
        }

        try {
            contratacionDAO.guardar(contratacion);
        } catch (SQLException e) {
            throw new BusinessException("Error al crear la contratación: " + e.getMessage(), e);
        }
    }

    public Contratacion obtenerContratacion(int id) throws SQLException {
        return contratacionDAO.obtenerPorId(id);
    }

    public List<Contratacion> obtenerTodasContrataciones() throws SQLException {
        return contratacionDAO.obtenerTodos();
    }

    public List<Contratacion> obtenerContratacionesActivas() throws SQLException {
        return contratacionDAO.obtenerTodos().stream()
                .filter(Contratacion::isEstado)
                .collect(Collectors.toList());
    }

    public void actualizarContratacion(Contratacion contratacion) throws SQLException {
        contratacionDAO.actualizar(contratacion);
    }

    public void desactivarContratacion(int id) throws SQLException {
        contratacionDAO.eliminar(id);
    }

    public void reactivarContratacion(int id) throws SQLException {
        contratacionDAO.reactivarContratacion(id);
    }
}
