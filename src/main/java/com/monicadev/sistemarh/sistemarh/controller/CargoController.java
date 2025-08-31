package com.monicadev.sistemarh.sistemarh.controller;

import com.monicadev.sistemarh.sistemarh.dao.CargoDAOImpl;
import com.monicadev.sistemarh.sistemarh.model.Cargo;
import com.monicadev.sistemarh.sistemarh.service.CargoService;
import com.monicadev.sistemarh.sistemarh.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CargoController", value = "/CargoController")
public class CargoController extends HttpServlet {
    private CargoService cargoService;

    @Override
    public void init() {
        // Conexión a la base de datos (debes implementar esto)
        //Connection conexion = obtenerConexion();
        Connection conexion = null;
        try {
            conexion = DBConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.cargoService = new CargoService(new CargoDAOImpl(conexion));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion == null || accion.equals("listar")) {
                // Mostrar todos los cargos
                List<Cargo> cargos = cargoService.obtenerTodosCargos();
                request.setAttribute("cargos", cargos);
                request.getRequestDispatcher("/lista-cargos.jsp").forward(request, response);

            } else if (accion.equals("editar")) {
                // Mostrar formulario de edición
                int id = Integer.parseInt(request.getParameter("id"));
                Cargo cargo = cargoService.obtenerCargo(id);
                request.setAttribute("cargo", cargo);
                request.getRequestDispatcher("/formulario-cargo.jsp").forward(request, response);

            } else if (accion.equals("nuevo")) {
                // Mostrar formulario vacío
                request.getRequestDispatcher("/formulario-cargo.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion.equals("guardar")) {
                // Crear o actualizar cargo
                String idStr = request.getParameter("idCargo");
                String nombre = request.getParameter("cargo");
                String descripcion = request.getParameter("descripcionCargo");
                boolean jefatura = "true".equals(request.getParameter("jefatura"));

                Cargo cargo;
                if (idStr == null || idStr.isEmpty()) {
                    // Nuevo cargo
                    cargo = new Cargo(nombre, descripcion, jefatura);
                    cargoService.crearCargo(cargo);
                } else {
                    // Actualizar cargo existente
                    int id = Integer.parseInt(idStr);
                    cargo = new Cargo(id, nombre, descripcion, jefatura);
                    cargoService.actualizarCargo(cargo);
                }

                response.sendRedirect("cargos?accion=listar");

            } else if (accion.equals("eliminar")) {
                // Eliminar cargo
                int id = Integer.parseInt(request.getParameter("id"));
                cargoService.eliminarCargo(id);
                response.sendRedirect("cargos?accion=listar");
            }

        } catch (Exception e) {
            throw new ServletException("Error procesando la solicitud", e);
        }
    }


}

