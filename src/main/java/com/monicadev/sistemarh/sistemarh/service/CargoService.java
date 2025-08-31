package com.monicadev.sistemarh.sistemarh.service;

import com.monicadev.sistemarh.sistemarh.dao.CargoDAO;
import com.monicadev.sistemarh.sistemarh.model.Cargo;

import java.sql.SQLException;
import java.util.List;

public class CargoService {
    private CargoDAO cargoDAO;

    public CargoService(CargoDAO cargoDAO) {
        this.cargoDAO = cargoDAO;
    }

    public void crearCargo(Cargo cargo) throws SQLException {
        cargoDAO.guardar(cargo);
    }

    public Cargo obtenerCargo(int id) throws SQLException {
        return cargoDAO.obtenerPorId(id);
    }

    public List<Cargo> obtenerTodosCargos() throws SQLException {
        return cargoDAO.obtenerTodos();
    }

    public void actualizarCargo(Cargo cargo) throws SQLException {
        cargoDAO.actualizar(cargo);
    }

    public void eliminarCargo(int id) throws SQLException {
        cargoDAO.eliminar(id);
    }
}
