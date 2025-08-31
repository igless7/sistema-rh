package com.monicadev.sistemarh.sistemarh.model;

import java.time.LocalDate;

public class Empleado {
    private int idEmpleado;
    private String numeroDui;
    private String nombrePersona;
    private String usuario;
    private String numeroTelefono;
    private String correoInstitucional;
    private LocalDate fechaNacimiento;
    private boolean activo;

    // Constructores
    public Empleado() {}

    // Constructor sin ID (para contrataciones nuevas)
    public Empleado(String numeroDui, String nombrePersona, String usuario,
                    String numeroTelefono, String correoInstitucional,
                    LocalDate fechaNacimiento, boolean activo) {
        this.numeroDui = numeroDui;
        this.nombrePersona = nombrePersona;
        this.usuario = usuario;
        this.numeroTelefono = numeroTelefono;
        this.correoInstitucional = correoInstitucional;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
    }

    // Constructor completo (para empleados existentes)
    public Empleado(int idEmpleado, String numeroDui, String nombrePersona, String usuario,
                    String numeroTelefono, String correoInstitucional, LocalDate fechaNacimiento, boolean activo) {
        this.idEmpleado = idEmpleado;
        this.numeroDui = numeroDui;
        this.nombrePersona = nombrePersona;
        this.usuario = usuario;
        this.numeroTelefono = numeroTelefono;
        this.correoInstitucional = correoInstitucional;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
    }

    // Getters y Setters
    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getNumeroDui() { return numeroDui; }
    public void setNumeroDui(String numeroDui) { this.numeroDui = numeroDui; }

    public String getNombrePersona() { return nombrePersona; }
    public void setNombrePersona(String nombrePersona) { this.nombrePersona = nombrePersona; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getNumeroTelefono() { return numeroTelefono; }
    public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono; }

    public String getCorreoInstitucional() { return correoInstitucional; }
    public void setCorreoInstitucional(String correoInstitucional) { this.correoInstitucional = correoInstitucional; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", numeroDui='" + numeroDui + '\'' +
                ", nombrePersona='" + nombrePersona + '\'' +
                ", usuario='" + usuario + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", correoInstitucional='" + correoInstitucional + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", activo=" + activo +
                '}';
    }
}
