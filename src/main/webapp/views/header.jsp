<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Navigation Header -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">
            <i class="bi bi-people-fill me-2"></i>Sistema RH
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">
                        <i class="bi bi-house me-1"></i>Inicio
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/empleados?accion=listar">
                        <i class="bi bi-person-badge me-1"></i>Empleados
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/contrataciones?accion=listar">
                        <i class="bi bi-file-earmark-text me-1"></i>Contrataciones
                    </a>
                </li>
            </ul>
            <span class="navbar-text">
                Sistema de Recursos Humanos
            </span>
        </div>
    </div>
</nav>
