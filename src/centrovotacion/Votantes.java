/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package centrovotacion;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Crist
 */
public class Votantes implements Serializable{
    private String nombreCompleto;
    private String apellidoCompleto;
    private String cui;
    private String genero;
    private Date fechaNacimiento;
    private String direccionResidencia;
    private String departamento;
    private String municipio;
    private String pais;
    private String email;
    private String contrasenia;
    
    public Votantes(String nombreCompleto, String apellidoCompleto, String cui, String genero, Date fechaNacimiento, String direccionResidencia, String departamento, String municipio, String pais,String email, String contrasenia){
    this.nombreCompleto = nombreCompleto;
    this.apellidoCompleto = apellidoCompleto;
    this.cui = cui;
    this.genero = genero;
    this.fechaNacimiento = fechaNacimiento;
    this.direccionResidencia = direccionResidencia;
    this.departamento = departamento;
    this.municipio = municipio;
    this.pais = pais;
    this.email = email;
    this.contrasenia = contrasenia;
}

    Votantes(String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getApellidoCompleto() {
        return apellidoCompleto;
    }

    public void setApellidoCompleto(String apellidoCompleto) {
        this.apellidoCompleto = apellidoCompleto;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    
}
