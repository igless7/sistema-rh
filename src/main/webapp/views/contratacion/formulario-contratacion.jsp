
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Formulario de Contratación</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

  <style>
    .header-banner {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      padding: 2rem 0;
      margin-bottom: 2rem;
      border-radius: 0 0 20px 20px;
    }
    .card-form {
      border: none;
      border-radius: 15px;
      box-shadow: 0 10px 30px rgba(0,0,0,0.1);
      margin-bottom: 2rem;
    }
    .card-header {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      border-radius: 15px 15px 0 0 !important;
      font-weight: bold;
    }
    .form-label {
      font-weight: 500;
      color: #495057;
    }
    .required-field::after {
      content: " *";
      color: #dc3545;
    }
    .btn-primary {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      border: none;
      border-radius: 8px;
      padding: 10px 25px;
      font-weight: 600;
    }
    .btn-primary:hover {
      transform: translateY(-2px);
      box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
    }
    .icon-preview {
      font-size: 1.2em;
      margin-right: 8px;
      vertical-align: middle;
    }
    .section-title {
      color: #6c757d;
      font-weight: 600;
      margin-bottom: 1rem;
      padding-bottom: 0.5rem;
      border-bottom: 2px solid #e9ecef;
    }
  </style>
</head>
<body>
<!-- Header -->
<div class="header-banner">
  <div class="container">
    <div class="row align-items-center">
      <div class="col-md-8">
        <h1 class="display-5 fw-bold">
          <i class="fas fa-handshake me-3"></i>
          ${empty contratacion ? 'Nueva Contratación' : 'Editar Contratación'}
        </h1>
        <p class="lead">Complete la información para ${empty contratacion ? 'crear una nueva' : 'editar la'} contratación</p>
      </div>
      <div class="col-md-4 text-end">
        <a href="contrataciones?accion=listar" class="btn btn-light btn-lg">
          <i class="fas fa-arrow-left me-2"></i>Volver al Listado
        </a>
      </div>
    </div>
  </div>
</div>

<!-- Main Content -->
<div class="container">
  <div class="row justify-content-center">
    <div class="col-lg-10">
      <div class="card card-form">
        <div class="card-header">
          <h5 class="card-title mb-0">
            <i class="fas fa-info-circle me-2"></i>
            Información de la Contratación
          </h5>
        </div>
        <div class="card-body">
          <form action="contrataciones" method="post" id="contratacionForm">
            <input type="hidden" name="accion" value="guardar">

            <c:if test="${not empty contratacion}">
              <input type="hidden" name="idContratacion" value="${contratacion.idContratacion}">
            </c:if>

            <div class="row">
              <!-- Sección: Información del Empleado -->
              <div class="col-md-6">
                <h6 class="section-title">
                  <i class="fas fa-user-tie icon-preview"></i>Información del Empleado
                </h6>

                <div class="mb-3">
                  <label class="form-label required-field">👨‍💼 Empleado</label>
                  <select name="idEmpleado" class="form-select" required>
                    <option value="">-- Seleccione un Empleado --</option>
                    <c:forEach items="${empleados}" var="emp">
                      <option value="${emp.idEmpleado}"
                        ${contratacion.idEmpleado == emp.idEmpleado ? 'selected' : ''}>
                          ${emp.nombrePersona} - ${emp.numeroDui}
                      </option>
                    </c:forEach>
                  </select>
                  <div class="form-text">Seleccione el empleado a contratar</div>
                </div>

                <div class="mb-3">
                  <label class="form-label required-field">🎯 Cargo</label>
                  <select name="idCargo" class="form-select" required>
                    <option value="">-- Seleccione un Cargo --</option>
                    <c:forEach items="${cargos}" var="cargo">
                      <option value="${cargo.idCargo}"
                        ${contratacion.idCargo == cargo.idCargo ? 'selected' : ''}>
                          ${cargo.cargo}
                        <c:if test="${cargo.jefatura}"> (Jefatura)</c:if>
                      </option>
                    </c:forEach>
                  </select>
                  <div class="form-text">Seleccione el cargo que ocupará</div>
                </div>
              </div>

              <!-- Sección: Información de la Contratación -->
              <div class="col-md-6">
                <h6 class="section-title">
                  <i class="fas fa-building icon-preview"></i>Información del Departamento
                </h6>

                <div class="mb-3">
                  <label class="form-label required-field">🏢 Departamento</label>
                  <select name="idDepartamento" class="form-select" required>
                    <option value="">-- Seleccione un Departamento --</option>
                    <c:forEach items="${departamentos}" var="depto">
                      <option value="${depto.idDepartamento}"
                        ${contratacion.idDepartamento == depto.idDepartamento ? 'selected' : ''}>
                          ${depto.nombreDepartamento}
                      </option>
                    </c:forEach>
                  </select>
                  <div class="form-text">Seleccione el departamento asignado</div>
                </div>

                <div class="mb-3">
                  <label class="form-label required-field">📝 Tipo de Contratación</label>
                  <select name="idTipoContratacion" class="form-select" required>
                    <option value="">-- Seleccione un Tipo --</option>
                    <c:forEach items="${tiposContratacion}" var="tipo">
                      <option value="${tipo.idTipoContratacion}"
                        ${contratacion.idTipoContratacion == tipo.idTipoContratacion ? 'selected' : ''}>
                          ${tipo.tipoContratacion}
                      </option>
                    </c:forEach>
                  </select>
                  <div class="form-text">Seleccione el tipo de contratación</div>
                </div>
              </div>
            </div>

            <hr>

            <div class="row">
              <!-- Sección: Detalles de la Contratación -->
              <div class="col-md-6">
                <h6 class="section-title">
                  <i class="fas fa-calendar-alt icon-preview"></i>Detalles de la Contratación
                </h6>

                <div class="mb-3">
                  <label class="form-label required-field">📅 Fecha de Contratación</label>
                  <input type="date" name="fechaContratacion" class="form-control"
                         value="${contratacion.fechaContratacion}" required>
                  <div class="form-text">Fecha en que inicia la contratación</div>
                </div>
              </div>

              <div class="col-md-6">
                <h6 class="section-title">
                  <i class="fas fa-money-bill-wave icon-preview"></i>Información Salarial
                </h6>

                <div class="mb-3">
                  <label class="form-label required-field">💰 Salario</label>
                  <div class="input-group">
                    <span class="input-group-text">$</span>
                    <input type="number" step="0.01" name="salario" class="form-control"
                           value="${contratacion.salario}" required min="0"
                           placeholder="0.00">
                  </div>
                  <div class="form-text">Salario mensual del empleado</div>
                </div>

                <div class="mb-3">
                  <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" name="estado"
                           id="estadoContratacion" value="true"
                    ${empty contratacion or contratacion.estado ? 'checked' : ''}>
                    <label class="form-check-label" for="estadoContratacion">
                      ✅ Contratación Activa
                    </label>
                  </div>
                  <div class="form-text">Desactive si la contratación ha finalizado</div>
                </div>
              </div>
            </div>

            <hr>

            <!-- Botones de Acción -->
            <div class="row mt-4">
              <div class="col-12">
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                  <a href="contrataciones?accion=listar" class="btn btn-secondary me-md-2">
                    <i class="fas fa-times me-2"></i>Cancelar
                  </a>
                  <button type="submit" class="btn btn-primary">
                    <i class="fas fa-save me-2"></i>
                    ${empty contratacion ? 'Crear Contratación' : 'Actualizar Contratación'}
                  </button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>

      <!-- Información Adicional -->
      <div class="alert alert-info">
        <h6><i class="fas fa-info-circle me-2"></i>Información importante</h6>
        <ul class="mb-0">
          <li>Todos los campos marcados con <span class="text-danger">*</span> son obligatorios</li>
          <li>Verifique que el empleado no tenga una contratación activa en el mismo departamento</li>
          <li>El sistema generará automáticamente un número de contratación único</li>
        </ul>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- Validación del Formulario -->
<script>
  document.getElementById('contratacionForm').addEventListener('submit', function(e) {
    const salario = document.querySelector('input[name="salario"]');
    if (salario.value <= 0) {
      e.preventDefault();
      alert('El salario debe ser mayor a 0');
      salario.focus();
    }

    const fecha = document.querySelector('input[name="fechaContratacion"]');
    const hoy = new Date().toISOString().split('T')[0];
    if (fecha.value > hoy) {
      if (!confirm('⚠️ La fecha de contratación es futura. ¿Está seguro?')) {
        e.preventDefault();
      }
    }
  });

  // Establecer fecha máxima como hoy
  document.addEventListener('DOMContentLoaded', function() {
    const fechaInput = document.querySelector('input[name="fechaContratacion"]');
    if (!fechaInput.value) {
      const hoy = new Date().toISOString().split('T')[0];
      fechaInput.value = hoy;
    }
    fechaInput.max = new Date().toISOString().split('T')[0];
  });
</script>
</body>
</html>
