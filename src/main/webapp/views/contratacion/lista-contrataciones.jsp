<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Contrataciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <style>
        .table th {
            background-color: #0d6efd;
            color: white;
        }
    </style>
</head>
<body class="bg-light">
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="text-primary"><i class="bi bi-person-badge me-2"></i>Gestión de Contrataciones</h1>
        <a href="${pageContext.request.contextPath}/contrataciones?accion=nuevo" class="btn btn-success">
            <i class="bi bi-plus-circle me-1"></i> Nueva Contratación
        </a>
    </div>

    <!-- Debug: Verificar datos recibidos -->
    <div class="alert alert-info d-none">
        <strong>Debug Info:</strong><br>
        Contrataciones: ${contrataciones.size()}<br>
        Empleados: ${empleados.size()}<br>
        Departamentos: ${departamentos.size()}<br>
        Cargos: ${cargos.size()}<br>
        Tipos: ${tiposContratacion.size()}
    </div>

    <c:if test="${not empty mensaje}">
        <div class="alert alert-info alert-dismissible fade show" role="alert">
                ${mensaje}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h5 class="card-title mb-0"><i class="bi bi-list-ul me-2"></i>Lista de Contrataciones</h5>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Empleado</th>
                        <th>Departamento</th>
                        <th>Cargo</th>
                        <th>Tipo</th>
                        <th>Fecha</th>
                        <th>Salario</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="contratacion" items="${contrataciones}">
                        <tr>
                            <td>${contratacion.idContratacion}</td>

                            <!-- Empleado - FIXED -->
                            <td>
                                <c:set var="nombreEmpleado" value="No encontrado" />
                                <c:forEach var="empleado" items="${empleados}">
                                    <c:if test="${empleado.idEmpleado == contratacion.idEmpleado}">
                                        <c:set var="nombreEmpleado" value="${empleado.nombrePersona}" />
                                    </c:if>
                                </c:forEach>
                                    ${nombreEmpleado}
                                <small class="text-muted">(ID: ${contratacion.idEmpleado})</small>
                            </td>

                            <!-- Departamento - FIXED -->
                            <td>
                                <c:set var="nombreDepartamento" value="No encontrado" />
                                <c:forEach var="departamento" items="${departamentos}">
                                    <c:if test="${departamento.idDepartamento == contratacion.idDepartamento}">
                                        <c:set var="nombreDepartamento" value="${departamento.nombreDepartamento}" />
                                    </c:if>
                                </c:forEach>
                                    ${nombreDepartamento}
                                <small class="text-muted">(ID: ${contratacion.idDepartamento})</small>
                            </td>

                            <!-- Cargo - FIXED -->
                            <td>
                                <c:set var="nombreCargo" value="No encontrado" />
                                <c:forEach var="cargo" items="${cargos}">
                                    <c:if test="${cargo.idCargo == contratacion.idCargo}">
                                        <c:set var="nombreCargo" value="${cargo.cargo}" />
                                    </c:if>
                                </c:forEach>
                                    ${nombreCargo}
                                <small class="text-muted">(ID: ${contratacion.idCargo})</small>
                            </td>

                            <!-- Tipo Contratación - FIXED -->
                            <td>
                                <c:set var="nombreTipo" value="No encontrado" />
                                <c:forEach var="tipo" items="${tiposContratacion}">
                                    <c:if test="${tipo.idTipoContratacion == contratacion.idTipoContratacion}">
                                        <c:set var="nombreTipo" value="${tipo.tipoContratacion}" />
                                    </c:if>
                                </c:forEach>
                                    ${nombreTipo}
                                <small class="text-muted">(ID: ${contratacion.idTipoContratacion})</small>
                            </td>

                            <td>${contratacion.fechaContratacion}</td>
                            <td>$${contratacion.salario}</td>
                            <td>
                                        <span class="badge ${contratacion.estado ? 'bg-success' : 'bg-danger'}">
                                                ${contratacion.estado ? 'Activo' : 'Inactivo'}
                                        </span>
                            </td>
                            <td>
                                <div class="btn-group">
                                    <a href="${pageContext.request.contextPath}/contrataciones?accion=editar&id=${contratacion.idContratacion}"
                                       class="btn btn-sm btn-warning" title="Editar">
                                        <i class="bi bi-pencil"></i>
                                    </a>
                                    <c:choose>
                                        <c:when test="${contratacion.estado}">
                                            <a href="${pageContext.request.contextPath}/contrataciones?accion=desactivar&id=${contratacion.idContratacion}"
                                               class="btn btn-sm btn-danger" title="Desactivar">
                                                <i class="bi bi-x-circle"></i>
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${pageContext.request.contextPath}/contrataciones?accion=reactivar&id=${contratacion.idContratacion}"
                                               class="btn btn-sm btn-success" title="Reactivar">
                                                <i class="bi bi-check-circle"></i>
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <c:if test="${empty contrataciones}">
                <div class="alert alert-warning text-center">
                    <i class="bi bi-exclamation-triangle me-2"></i>No se encontraron contrataciones registradas.
                </div>
            </c:if>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>