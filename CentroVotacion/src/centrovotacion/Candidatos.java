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
public class Candidatos implements Serializable{
    private String nombreCandidato;
    private String formacion;
    private String experiencia;
    private String cuiCandidato;
    
    public Candidatos(String nombreCandidato, String formacion, String experiencia, String cuiCandidato){
        this.nombreCandidato = nombreCandidato;
        this.formacion = formacion;
        this.experiencia = experiencia;
        this.cuiCandidato = cuiCandidato;
    }

    public String getNombreCandidato() {
        return nombreCandidato;
    }

    public void setNombreCandidato(String nombreCandidato) {
        this.nombreCandidato = nombreCandidato;
    }

    public String getFormacion() {
        return formacion;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getCuiCandidato() {
        return cuiCandidato;
    }

    public void setCuiCandidato(String cuiCandidato) {
        this.cuiCandidato = cuiCandidato;
    }
    
    
}
