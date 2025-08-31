<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Formulario de Contratación</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
</head>
<body class="bg-light">
<jsp:include page="../header.jsp" />
<div class="container mt-4">
  <div class="row justify-content-center">
    <div class="col-lg-10">
      <div class="card shadow">
        <div class="card-header bg-primary text-white">
          <h4 class="card-title mb-0">
            <i class="bi bi-person-plus me-2"></i>
            <c:choose>
              <c:when test="${not empty contratacion}">Editar Contratación</c:when>
              <c:otherwise>Nueva Contratación</c:otherwise>
            </c:choose>
          </h4>
        </div>
        <div class="card-body">
          <form action="${pageContext.request.contextPath}/contrataciones?accion=guardar" method="post">
            <input type="hidden" name="idContratacion" value="${contratacion.id}">

            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="idEmpleado" class="form-label">Empleado <span class="text-danger">*</span></label>
                <select class="form-select" id="idEmpleado" name="idEmpleado" required>
                  <option value="">Seleccionar empleado...</option>
                  <c:forEach var="empleado" items="${empleados}">
                    <option value="${empleado.idEmpleado}"
                      ${contratacion.idEmpleado == empleado.idEmpleado ? 'selected' : ''}>
                        ${empleado.nombrePersona} (${empleado.numeroDui})
                    </option>
                  </c:forEach>
                </select>
              </div>

              <div class="col-md-6 mb-3">
                <label for="idDepartamento" class="form-label">Departamento <span class="text-danger">*</span></label>
                <select class="form-select" id="idDepartamento" name="idDepartamento" required>
                  <option value="">Seleccionar departamento...</option>
                  <c:forEach var="departamento" items="${departamentos}">
                    <option value="${departamento.idDepartamento}"
                      ${contratacion.idDepartamento == departamento.idDepartamento ? 'selected' : ''}>
                        ${departamento.nombreDepartamento}
                    </option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="idCargo" class="form-label">Cargo <span class="text-danger">*</span></label>
                <select class="form-select" id="idCargo" name="idCargo" required>
                  <option value="">Seleccionar cargo...</option>
                  <c:forEach var="cargo" items="${cargos}">
                    <option value="${cargo.idCargo}"
                      ${contratacion.idCargo == cargo.idCargo ? 'selected' : ''}>
                        ${cargo.cargo}
                    </option>
                  </c:forEach>
                </select>
              </div>

              <div class="col-md-6 mb-3">
                <label for="idTipoContratacion" class="form-label">Tipo de Contratación <span class="text-danger">*</span></label>
                <select class="form-select" id="idTipoContratacion" name="idTipoContratacion" required>
                  <option value="">Seleccionar tipo...</option>
                  <c:forEach var="tipo" items="${tiposContratacion}">
                    <option value="${tipo.idTipoContratacion}"
                      ${contratacion.idTipoContratacion == tipo.idTipoContratacion ? 'selected' : ''}>
                        ${tipo.tipoContratacion}
                    </option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="fechaContratacion" class="form-label">Fecha de Contratación <span class="text-danger">*</span></label>
                <input type="date" class="form-control" id="fechaContratacion" name="fechaContratacion"
                       value="${contratacion.fechaContratacion}" required>
              </div>

              <div class="col-md-6 mb-3">
                <label for="salario" class="form-label">Salario ($) <span class="text-danger">*</span></label>
                <input type="number" step="0.01" min="0" class="form-control" id="salario" name="salario"
                       value="${contratacion.salario}" required>
              </div>
            </div>

            <c:if test="${not empty contratacion}">
              <div class="mb-3 form-check form-switch">
                <input class="form-check-input" type="checkbox" id="estado" name="estado" value="true"
                  ${contratacion.estado ? 'checked' : ''}>
                <label class="form-check-label" for="estado">Contratación Activa</label>
              </div>
            </c:if>

            <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
              <a href="${pageContext.request.contextPath}/contrataciones?accion=listar" class="btn btn-secondary me-md-2">
                <i class="bi bi-arrow-left me-1"></i> Volver
              </a>
              <button type="submit" class="btn btn-primary">
                <i class="bi bi-check2-circle me-1"></i> Guardar
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
  // Validación básica del formulario
  document.querySelector('form').addEventListener('submit', function(e) {
    let isValid = true;
    const requiredFields = this.querySelectorAll('[required]');

    requiredFields.forEach(field => {
      if (!field.value.trim()) {
        isValid = false;
        field.classList.add('is-invalid');
      } else {
        field.classList.remove('is-invalid');
      }
    });

    if (!isValid) {
      e.preventDefault();
      alert('Por favor, complete todos los campos obligatorios.');
    }
  });
</script>
</body>
</html>