<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestión de Empleados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Lista de Empleados</h1>
        <a href="empleados?accion=nuevo" class="btn btn-primary">Nuevo Empleado</a>
    </div>

    <c:if test="${not empty mensaje}">
        <div class="alert alert-info">${mensaje}</div>
    </c:if>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>DUI</th>
            <th>Usuario</th>
            <th>Teléfono</th>
            <th>Correo</th>
            <th>Fecha Nacimiento</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="empleado" items="${empleados}">
            <tr>
                <td>${empleado.idEmpleado}</td>
                <td>${empleado.nombrePersona}</td>
                <td>${empleado.numeroDui}</td>
                <td>${empleado.usuario}</td>
                <td>${empleado.numeroTelefono}</td>
                <td>${empleado.correoInstitucional}</td>
                <td>${empleado.fechaNacimiento}</td>
                <td>
                            <span class="badge ${empleado.activo ? 'bg-success' : 'bg-danger'}">
                                    ${empleado.activo ? 'Activo' : 'Inactivo'}
                            </span>
                </td>
                <td>
                    <div class="btn-group">
                        <a href="empleados?accion=editar&id=${empleado.idEmpleado}"
                           class="btn btn-sm btn-warning">Editar</a>
                        <c:choose>
                            <c:when test="${empleado.activo}">
                                <a href="empleados?accion=despedir&id=${empleado.idEmpleado}"
                                   class="btn btn-sm btn-danger">Despedir</a>
                            </c:when>
                            <c:otherwise>
                                <a href="empleados?accion=reactivar&id=${empleado.idEmpleado}"
                                   class="btn btn-sm btn-success">Reactivar</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
