package com.monicadev.sistemarh.sistemarh.controller;

import com.monicadev.sistemarh.sistemarh.dao.DepartamentoDAOImpl;
import com.monicadev.sistemarh.sistemarh.model.Departamento;
import com.monicadev.sistemarh.sistemarh.service.DepartamentoService;
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

@WebServlet(name = "DepartamentoServlet", value = "/DepartamentoServlet")
public class DepartamentoServlet extends HttpServlet {
    private DepartamentoService departamentoService;

    @Override
    public void init() {
        Connection conexion = null;
        try {
            conexion = DBConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.departamentoService = new DepartamentoService(new DepartamentoDAOImpl(conexion));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion == null || accion.equals("listar")) {
                // Mostrar todos los departamentos
                List<Departamento> departamentos = departamentoService.obtenerTodosDepartamentos();
                request.setAttribute("departamentos", departamentos);
                request.getRequestDispatcher("/lista-departamentos.jsp").forward(request, response);

            } else if (accion.equals("editar")) {
                // Editar departamento existente
                int id = Integer.parseInt(request.getParameter("id"));
                Departamento depto = departamentoService.obtenerDepartamento(id);
                request.setAttribute("departamento", depto);
                request.getRequestDispatcher("/formulario-departamento.jsp").forward(request, response);

            } else if (accion.equals("nuevo")) {
                // Crear nuevo departamento
                request.getRequestDispatcher("/formulario-departamento.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new ServletException("Error en la construcción", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion.equals("guardar")) {
                // Guardar o actualizar departamento
                String idStr = request.getParameter("idDepartamento");
                String nombre = request.getParameter("nombreDepartamento");
                String descripcion = request.getParameter("descripcionDepartamento");

                if (idStr == null || idStr.isEmpty()) {
                    // Construir nuevo departamento
                    Departamento nuevo = new Departamento(nombre, descripcion);
                    departamentoService.crearDepartamento(nuevo);
                } else {
                    // Renovar departamento existente
                    int id = Integer.parseInt(idStr);
                    Departamento existente = new Departamento(id, nombre, descripcion);
                    departamentoService.actualizarDepartamento(existente);
                }

                response.sendRedirect("departamentos?accion=listar");

            } else if (accion.equals("eliminar")) {
                // Demoler departamento
                int id = Integer.parseInt(request.getParameter("id"));
                departamentoService.eliminarDepartamento(id);
                response.sendRedirect("departamentos?accion=listar");
            }

        } catch (Exception e) {
            throw new ServletException("Error en la construcción", e);
        }
    }
}

