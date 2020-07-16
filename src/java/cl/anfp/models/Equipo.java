/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.models;

/**
 *
 * @author exvicad
 */
public class Equipo {
    
    private int codigo;
    private String nombre;
    private Ciudad procedencia;
    private Estadio estadio;
    private Division division;

    public Equipo(int codigo) {
        this.codigo = codigo;
    }
    
    public Equipo(int codigo, String nombre, Ciudad procedencia, Estadio estadio, Division division) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.procedencia = procedencia;
        this.estadio = estadio;
        this.division = division;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(Ciudad procedencia) {
        this.procedencia = procedencia;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
    
    
    
}
