package com.monicadev.sistemarh.sistemarh.model;

public class Departamento {
    private int idDepartamento;
    private String nombreDepartamento;
    private String descripcionDepartamento;

    // Constructores, getters y setters
    public Departamento() {}

    // Constructor sin ID (para construcciones nuevas)
    public Departamento(String nombreDepartamento, String descripcionDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
        this.descripcionDepartamento = descripcionDepartamento;
    }

    // Constructor completo (para actualizaciones existentes)
    public Departamento(int idDepartamento, String nombreDepartamento, String descripcionDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.descripcionDepartamento = descripcionDepartamento;
    }

    public int getIdDepartamento() { return idDepartamento; }
    public void setIdDepartamento(int idDepartamento) { this.idDepartamento = idDepartamento; }

    public String getNombreDepartamento() { return nombreDepartamento; }
    public void setNombreDepartamento(String nombreDepartamento) { this.nombreDepartamento = nombreDepartamento; }

    public String getDescripcion() { return descripcionDepartamento; }
    public void setDescripcion(String descripcion) { this.descripcionDepartamento = descripcion; }

    @Override
    public String toString() {
        return "Departamento{" +
                "idDepartamento=" + idDepartamento +
                ", nombreDepartamento='" + nombreDepartamento + '\'' +
                ", descripcion='" + descripcionDepartamento + '\'' +
                '}';
    }
}
