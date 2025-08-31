<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sistema de Recursos Humanos</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
  <style>
    .welcome-card {
      transition: transform 0.3s ease;
      border: none;
      border-radius: 15px;
    }
    .welcome-card:hover {
      transform: translateY(-5px);
      box-shadow: 0 10px 20px rgba(0,0,0,0.1);
    }
    .hero-section {
      background: linear-gradient(135deg, #0d6efd 0%, #0a58ca 100%);
      border-radius: 15px;
    }
  </style>
</head>
<body class="bg-light">
<!-- Navigation Header -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
      <i class="bi bi-people-fill me-2"></i>Sistema RH
    </a>
    <div class="navbar-nav ms-auto">
      <a class="nav-link active" href="${pageContext.request.contextPath}/">
        <i class="bi bi-house me-1"></i>Inicio
      </a>
    </div>
  </div>
</nav>

<div class="container my-5">
  <!-- Hero Section -->
  <div class="hero-section text-white p-5 mb-5">
    <div class="row align-items-center">
      <div class="col-md-8">
        <h1 class="display-4 fw-bold">Sistema de Recursos Humanos</h1>
        <p class="lead">Gestiona empleados y contrataciones de manera eficiente</p>
      </div>
      <div class="col-md-4 text-center">
        <i class="bi bi-people-fill" style="font-size: 4rem;"></i>
      </div>
    </div>
  </div>

  <!-- Cards Section -->
  <div class="row g-4">
    <div class="col-md-6">
      <div class="card welcome-card h-100">
        <div class="card-body text-center p-5">
          <i class="bi bi-person-badge text-primary" style="font-size: 3rem;"></i>
          <h3 class="card-title mt-3">Gestión de Empleados</h3>
          <p class="card-text">Administra la información de los empleados, estado y datos personales.</p>
          <a href="${pageContext.request.contextPath}/empleados?accion=listar" class="btn btn-primary btn-lg">
            <i class="bi bi-list-ul me-2"></i>Ver Empleados
          </a>
        </div>
      </div>
    </div>

    <div class="col-md-6">
      <div class="card welcome-card h-100">
        <div class="card-body text-center p-5">
          <i class="bi bi-file-earmark-text text-success" style="font-size: 3rem;"></i>
          <h3 class="card-title mt-3">Gestión de Contrataciones</h3>
          <p class="card-text">Administra las contrataciones, asignación de cargos y departamentos.</p>
          <a href="${pageContext.request.contextPath}/contrataciones?accion=listar" class="btn btn-success btn-lg">
            <i class="bi bi-list-check me-2"></i>Ver Contrataciones
          </a>
        </div>
      </div>
    </div>
  </div>

  <!-- Stats Section -->
  <div class="row mt-5">
    <div class="col-12">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title mb-4">Accesos Rápidos</h4>
          <div class="row text-center">
            <div class="col-md-3 mb-3">
              <a href="${pageContext.request.contextPath}/empleados?accion=nuevo" class="btn btn-outline-primary w-100">
                <i class="bi bi-person-plus me-2"></i>Nuevo Empleado
              </a>
            </div>
            <div class="col-md-3 mb-3">
              <a href="${pageContext.request.contextPath}/contrataciones?accion=nuevo" class="btn btn-outline-success w-100">
                <i class="bi bi-file-earmark-plus me-2"></i>Nueva Contratación
              </a>
            </div>
            <div class="col-md-3 mb-3">
              <a href="${pageContext.request.contextPath}/empleados?accion=listar" class="btn btn-outline-info w-100">
                <i class="bi bi-search me-2"></i>Buscar Empleados
              </a>
            </div>
            <div class="col-md-3 mb-3">
              <a href="${pageContext.request.contextPath}/contrataciones?accion=listar" class="btn btn-outline-warning w-100">
                <i class="bi bi-search me-2"></i>Buscar Contrataciones
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Footer -->
<footer class="bg-dark text-white text-center py-4 mt-5">
  <div class="container">
    <p class="mb-0">Sistema de Recursos Humanos &copy; 2025</p>
  </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>