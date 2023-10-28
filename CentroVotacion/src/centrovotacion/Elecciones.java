/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package centrovotacion;

import java.io.Serializable;

/**
 *
 * @author Crist
 */
public class Elecciones implements Serializable{
    private String tituloEleccion;
    private String proposito;
    private String descripcion;
    private String codigo;
    private String fechaInicio;
    private String horaInicio;
    private String fechaFinal;
    private String horaFinal;

    public Elecciones(String tituloEleccion, String proposito, String descripcion, String codigo, String fechaInicio, String horaInicio, String fechaFinal, String horaFinal){
       this.tituloEleccion = tituloEleccion;
       this.proposito = proposito;
       this.descripcion = descripcion;
       this.codigo = codigo;
       this.fechaInicio = fechaInicio;
       this.horaInicio = horaInicio;
       this.fechaFinal = fechaFinal;
       this.horaFinal = horaFinal;
    }

    public String getTituloEleccion() {
        return tituloEleccion;
    }

    public void setTituloEleccion(String tituloEleccion) {
        this.tituloEleccion = tituloEleccion;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }
    
    
}
