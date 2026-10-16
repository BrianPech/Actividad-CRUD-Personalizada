package com.mycompany.actividad.modelo;

import java.sql.Date;

public class TipoPolizaVigencia {

    private int id;
    private String tipo;
    private double mensual;
    private double trimestral;
    private double anual;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean activo;

    public TipoPolizaVigencia() {
    }

    public TipoPolizaVigencia(String tipo, double mensual, double trimestral, double anual, String descripcion, boolean activo) {
        this.tipo = tipo;
        this.mensual = mensual;
        this.trimestral = trimestral;
        this.anual = anual;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public TipoPolizaVigencia(int id, String tipo, double mensual, double trimestral, double anual, String descripcion, boolean activo) {
        this.id = id;
        this.tipo = tipo;
        this.mensual = mensual;
        this.trimestral = trimestral;
        this.anual = anual;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public TipoPolizaVigencia(int id, String tipo, double mensual, double trimestral, double anual, String descripcion, Date fechaCreacion, Date fechaActualizacion, boolean activo) {
        this.id = id;
        this.tipo = tipo;
        this.mensual = mensual;
        this.trimestral = trimestral;
        this.anual = anual;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMensual() {
        return mensual;
    }

    public void setMensual(double mensual) {
        this.mensual = mensual;
    }

    public double getTrimestral() {
        return trimestral;
    }

    public void setTrimestral(double trimestral) {
        this.trimestral = trimestral;
    }

    public double getAnual() {
        return anual;
    }

    public void setAnual(double anual) {
        this.anual = anual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "TipoPolizaVigencia{" + "id=" + id + ", tipo=" + tipo + ", mensual=" + mensual + ", trimestral=" + trimestral + ", anual=" + anual + ", descripcion=" + descripcion + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + ", activo=" + activo + '}';
    }
}
