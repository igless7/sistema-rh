package com.monicadev.sistemarh.sistemarh.model;

public class TipoContratacion {
    private int idTipoContratacion;
    private String tipoContratacion;

    // Constructores
    public TipoContratacion() {}
    // Constructor sin ID
    public TipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    // Constructor completo
    public TipoContratacion(int idTipoContratacion, String tipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
        this.tipoContratacion = tipoContratacion;
    }

    // Getters y Setters
    public int getIdTipoContratacion() { return idTipoContratacion; }
    public void setIdTipoContratacion(int idTipoContratacion) { this.idTipoContratacion = idTipoContratacion; }

    public String getTipoContratacion() { return tipoContratacion; }
    public void setTipoContratacion(String tipoContratacion) { this.tipoContratacion = tipoContratacion; }

    @Override
    public String toString() {
        return "TipoContratacion{" +
                "idTipoContratacion=" + idTipoContratacion +
                ", tipoContratacion='" + tipoContratacion + '\'' +
                '}';
    }
}
