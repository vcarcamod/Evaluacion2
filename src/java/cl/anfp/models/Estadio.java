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
public class Estadio {
    
    private int codigo;
    private String nombre;
    private Ciudad ciudad;
    private int capacidad;

    public Estadio(int codigo) {
        this.codigo = codigo;
    }
    
    public Estadio(String nombre) {
        this.nombre = nombre;
    }

    public Estadio(String nombre, Ciudad ciudad, int capacidad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
    }
    
    public Estadio(int codigo, String nombre, Ciudad ciudad, int capacidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    
    
}
