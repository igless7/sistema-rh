package com.monicadev.sistemarh.sistemarh.controller;

import com.monicadev.sistemarh.sistemarh.dao.TipoContratacionDAOImpl;
import com.monicadev.sistemarh.sistemarh.model.TipoContratacion;
import com.monicadev.sistemarh.sistemarh.service.TipoContratacionService;
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

@WebServlet(name = "TipoContratacionServlet", value = "/TipoContratacionServlet")
public class TipoContratacionServlet extends HttpServlet {
    private TipoContratacionService tipoContratacionService;

    @Override
    public void init() {
        Connection conexion = obtenerConexion();
        this.tipoContratacionService = new TipoContratacionService(new TipoContratacionDAOImpl(conexion));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion == null || accion.equals("listar")) {
                // Mostrar todos los tipos de contratación
                List<TipoContratacion> tipos = tipoContratacionService.obtenerTodosTiposContratacion();
                request.setAttribute("tiposContratacion", tipos);
                request.getRequestDispatcher("/lista-tipos-contratacion.jsp").forward(request, response);

            } else if (accion.equals("editar")) {
                // Editar tipo existente
                int id = Integer.parseInt(request.getParameter("id"));
                TipoContratacion tipo = tipoContratacionService.obtenerTipoContratacion(id);
                request.setAttribute("tipoContratacion", tipo);
                request.getRequestDispatcher("/formulario-tipo-contratacion.jsp").forward(request, response);

            } else if (accion.equals("nuevo")) {
                // Crear nuevo tipo
                request.getRequestDispatcher("/formulario-tipo-contratacion.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new ServletException("Error en el libro de hechizos", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion.equals("guardar")) {
                // Guardar o actualizar tipo
                String idStr = request.getParameter("idTipoContratacion");
                String nombreTipo = request.getParameter("tipoContratacion");

                if (idStr == null || idStr.isEmpty()) {
                    // Aprender nuevo hechizo
                    TipoContratacion nuevo = new TipoContratacion(nombreTipo);
                    tipoContratacionService.crearTipoContratacion(nuevo);
                } else {
                    // Mejorar hechizo existente
                    int id = Integer.parseInt(idStr);
                    TipoContratacion existente = new TipoContratacion(id, nombreTipo);
                    tipoContratacionService.actualizarTipoContratacion(existente);
                }

                response.sendRedirect("tipos-contratacion?accion=listar");

            } else if (accion.equals("eliminar")) {
                // Olvidar hechizo
                int id = Integer.parseInt(request.getParameter("id"));
                tipoContratacionService.eliminarTipoContratacion(id);
                response.sendRedirect("tipos-contratacion?accion=listar");
            }

        } catch (Exception e) {
            throw new ServletException("Error procesando el hechizo", e);
        }
    }

    private Connection obtenerConexion() {
        Connection conexion = null;
        try {
            conexion = DBConnection.getConnection();
            return conexion;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

