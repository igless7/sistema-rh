<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario de Empleado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h2 class="mb-4">
                <c:choose>
                    <c:when test="${not empty empleado}">Editar Empleado</c:when>
                    <c:otherwise>Nuevo Empleado</c:otherwise>
                </c:choose>
            </h2>

            <form action="empleados?accion=guardar" method="post">
                <input type="hidden" name="idEmpleado" value="${empleado.idEmpleado}">

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Número DUI:</label>
                        <input type="text" class="form-control" name="numeroDui"
                               value="${empleado.numeroDui}" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Nombre Completo:</label>
                        <input type="text" class="form-control" name="nombrePersona"
                               value="${empleado.nombrePersona}" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Usuario:</label>
                        <input type="text" class="form-control" name="usuario"
                               value="${empleado.usuario}" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Teléfono:</label>
                        <input type="tel" class="form-control" name="numeroTelefono"
                               value="${empleado.numeroTelefono}" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Correo Institucional:</label>
                    <input type="email" class="form-control" name="correoInstitucional"
                           value="${empleado.correoInstitucional}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Fecha de Nacimiento:</label>
                    <input type="date" class="form-control" name="fechaNacimiento"
                           value="${empleado.fechaNacimiento}" required>
                </div>

                <c:if test="${not empty empleado}">
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" name="activo"
                               value="true" ${empleado.activo ? 'checked' : ''}>
                        <label class="form-check-label">Empleado Activo</label>
                    </div>
                </c:if>

                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <a href="empleados?accion=listar" class="btn btn-secondary me-md-2">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
