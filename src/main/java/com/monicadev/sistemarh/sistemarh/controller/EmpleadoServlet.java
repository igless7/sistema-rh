package com.monicadev.sistemarh.sistemarh.controller;

import com.monicadev.sistemarh.sistemarh.dao.EmpleadoDAOImpl;
import com.monicadev.sistemarh.sistemarh.model.Empleado;
import com.monicadev.sistemarh.sistemarh.service.EmpleadoService;
import com.monicadev.sistemarh.sistemarh.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "EmpleadoServlet", value = "/empleados")
public class EmpleadoServlet extends HttpServlet {
    private EmpleadoService empleadoService;

    @Override
    public void init() {
        Connection conexion = null;
        try {
            conexion = DBConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.empleadoService = new EmpleadoService(new EmpleadoDAOImpl(conexion));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion == null || accion.equals("listar")) {
                List<Empleado> empleados = empleadoService.obtenerTodosEmpleados();
                request.setAttribute("empleados", empleados);
                request.getRequestDispatcher("/views/empleados/lista-empleados.jsp").forward(request, response);

            } else if (accion.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Empleado empleado = empleadoService.obtenerEmpleado(id);
                request.setAttribute("empleado", empleado);
                request.getRequestDispatcher("/views/empleados/formulario-empleado.jsp").forward(request, response);

            } else if (accion.equals("nuevo")) {
                request.getRequestDispatcher("/views/empleados/formulario-empleado.jsp").forward(request, response);

            } else if (accion.equals("despedir")) {
                int id = Integer.parseInt(request.getParameter("id"));
                empleadoService.despedirEmpleado(id);
                response.sendRedirect("empleados?accion=listar");

            } else if (accion.equals("reactivar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                empleadoService.reactivarEmpleado(id);
                response.sendRedirect("empleados?accion=listar");
            }

        } catch (SQLException e) {
            throw new ServletException("Error en recursos humanos", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion.equals("guardar")) {
                String idStr = request.getParameter("idEmpleado");
                String dui = request.getParameter("numeroDui");
                String nombre = request.getParameter("nombrePersona");
                String usuario = request.getParameter("usuario");
                String telefono = request.getParameter("numeroTelefono");
                String correo = request.getParameter("correoInstitucional");
                LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
                boolean activo = "true".equals(request.getParameter("activo"));

                if (idStr == null || idStr.isEmpty()) {
                    // Nueva contratación
                    Empleado nuevo = new Empleado(dui, nombre, usuario, telefono, correo, fechaNacimiento, activo);
                    empleadoService.contratarEmpleado(nuevo);
                } else {
                    // Actualización de datos
                    int id = Integer.parseInt(idStr);
                    Empleado existente = new Empleado(id, dui, nombre, usuario, telefono, correo, fechaNacimiento, activo);
                    empleadoService.actualizarEmpleado(existente);
                }

                response.sendRedirect("empleados?accion=listar");
            }

        } catch (Exception e) {
            throw new ServletException("Error procesando empleado", e);
        }
    }

}

