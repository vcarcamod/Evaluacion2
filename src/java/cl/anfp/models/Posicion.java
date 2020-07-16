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
public class Posicion {
    
    private int codigo;
    private String nombre;

    public Posicion(int codigo) {
        this.codigo = codigo;
    }
    
    public Posicion(String nombre) {
        this.nombre = nombre;
    }
    
    public Posicion(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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
    
    
    
}
