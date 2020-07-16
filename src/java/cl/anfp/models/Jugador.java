/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.models;

import java.time.Instant;

/**
 *
 * @author exvicad
 */
public class Jugador {

    private int id;
    private String nombre;
    private String apellido;
    private String fec_nacimiento;
    private int edad;
    private Posicion posicion;
    private int sueldo_anual_clp;
    private Equipo equipo;
    
    public Jugador() {
    }

    public Jugador(int id) {
        this.id = id;
    }
    
    public Jugador(int id, String nombre, String apellido, String fec_nacimiento, Posicion posicion, int sueldo_anual_clp, Equipo equipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fec_nacimiento = fec_nacimiento;
        this.posicion = posicion;
        this.sueldo_anual_clp = sueldo_anual_clp;
        this.equipo = equipo;
    }
    
    public Jugador(int id, String nombre, String apellido, String fec_nacimiento, int edad, Posicion posicion, int sueldo_anual_clp, Equipo equipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        String[] f_nac = fec_nacimiento.split("/");
        this.fec_nacimiento = f_nac[2]+"/"+f_nac[1]+"/"+f_nac[0];;
        this.edad = edad;
        this.posicion = posicion;
        this.sueldo_anual_clp = sueldo_anual_clp;
        this.equipo = equipo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFec_nacimiento() {
        return fec_nacimiento;
    }

    public void setFec_nacimiento(String fec_nacimiento) {
        this.fec_nacimiento = fec_nacimiento;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public int getSueldo_anual_clp() {
        return sueldo_anual_clp;
    }

    public void setSueldo_anual_clp(int sueldo_anual_clp) {
        this.sueldo_anual_clp = sueldo_anual_clp;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
       
}
