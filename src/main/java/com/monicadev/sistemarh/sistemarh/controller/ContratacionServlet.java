package com.monicadev.sistemarh.sistemarh.controller;

import com.monicadev.sistemarh.sistemarh.dao.*;
import com.monicadev.sistemarh.sistemarh.model.Contratacion;
import com.monicadev.sistemarh.sistemarh.service.*;
import com.monicadev.sistemarh.sistemarh.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/contrataciones")
public class ContratacionServlet extends HttpServlet {
    private ContratacionService contratacionService;
    private DepartamentoService departamentoService;
    private EmpleadoService empleadoService;
    private CargoService cargoService;
    private TipoContratacionService tipoContratacionService;

    @Override
    public void init() {
        Connection conexion = obtenerConexion();
        this.contratacionService = new ContratacionService(new ContratacionDAOImpl(conexion));
        this.departamentoService = new DepartamentoService(new DepartamentoDAOImpl(conexion));
        this.empleadoService = new EmpleadoService(new EmpleadoDAOImpl(conexion));
        this.cargoService = new CargoService(new CargoDAOImpl(conexion));
        this.tipoContratacionService = new TipoContratacionService(new TipoContratacionDAOImpl(conexion));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion == null || accion.equals("listar")) {
                // Mostrar todas las contrataciones
                List<Contratacion> contrataciones = contratacionService.obtenerTodasContrataciones();
                request.setAttribute("contrataciones", contrataciones);
                cargarDatosCombos(request);
                request.getRequestDispatcher("/views/contratacion/lista-contrataciones.jsp").forward(request, response);

            } else if (accion.equals("editar")) {
                // Editar contratación existente
                int id = Integer.parseInt(request.getParameter("id"));
                Contratacion contratacion = contratacionService.obtenerContratacion(id);
                cargarDatosCombos(request);
                request.setAttribute("contratacion", contratacion);
                request.getRequestDispatcher("/views/contratacion/formulario-contrataciones.jsp").forward(request, response);

            } else if (accion.equals("nuevo")) {
                // Crear nueva contratación
                cargarDatosCombos(request);
                request.getRequestDispatcher("/views/contratacion/formulario-contrataciones.jsp").forward(request, response);

            } else if (accion.equals("desactivar")) {
                // Desactivar contratación
                int id = Integer.parseInt(request.getParameter("id"));
                contratacionService.desactivarContratacion(id);
                response.sendRedirect(request.getContextPath() + "/contrataciones?accion=listar");

            } else if (accion.equals("reactivar")) {
                // Reactivar contratación
                int id = Integer.parseInt(request.getParameter("id"));
                contratacionService.reactivarContratacion(id);
                response.sendRedirect(request.getContextPath() + "/contrataciones?accion=listar");
            }

        } catch (SQLException e) {
            throw new ServletException("Error en la estrategia", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion.equals("guardar")) {
                // Guardar o actualizar contratación
                String idStr = request.getParameter("idContratacion");
                int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
                int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
                int idCargo = Integer.parseInt(request.getParameter("idCargo"));
                int idTipoContratacion = Integer.parseInt(request.getParameter("idTipoContratacion"));
                Date fechaContratacion = Date.valueOf(request.getParameter("fechaContratacion"));
                double salario = Double.parseDouble(request.getParameter("salario"));
                boolean estado = "true".equals(request.getParameter("estado"));

                if (idStr == null || idStr.isEmpty()) {
                    // Nueva formación de equipo
                    Contratacion nueva = new Contratacion(idDepartamento, idEmpleado, idCargo,
                            idTipoContratacion, fechaContratacion,
                            salario, estado);
                    contratacionService.crearContratacion(nueva);
                } else {
                    // Reorganización de equipo
                    int id = Integer.parseInt(idStr);
                    Contratacion existente = new Contratacion(id, idDepartamento, idEmpleado, idCargo,
                            idTipoContratacion, fechaContratacion,
                            salario, estado);
                    contratacionService.actualizarContratacion(existente);
                }

                response.sendRedirect(request.getContextPath() + "/contrataciones?accion=listar");
            }

        } catch (Exception e) {
            throw new ServletException("Error formando el equipo", e);
        }
    }

    private void cargarDatosCombos(HttpServletRequest request) throws SQLException {
        request.setAttribute("departamentos", departamentoService.obtenerTodosDepartamentos());
        request.setAttribute("empleados", empleadoService.obtenerEmpleadosActivos());
        request.setAttribute("cargos", cargoService.obtenerTodosCargos());
        request.setAttribute("tiposContratacion", tipoContratacionService.obtenerTodosTiposContratacion());
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

