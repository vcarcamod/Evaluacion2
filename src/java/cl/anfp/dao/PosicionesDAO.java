/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.dao;

import cl.anfp.dao.utils.ConexionSql;
import cl.anfp.models.Posicion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author exvicad
 */
public class PosicionesDAO extends ConexionSql {
    
    public int registrarPosicion(Posicion p) throws ClassNotFoundException, SQLException{
        String sentencia = "insert into posiciones values (?,?)";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, 0);
        ps.setString(2, p.getNombre());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            //return -1;
            throw ex;
        }finally{
            desconectar();
        }
    }
    public int modificarPosicion(Posicion p) throws ClassNotFoundException, SQLException{
        String sentencia = "update posiciones set nombre=? where codigo=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, p.getNombre());
        ps.setInt(2, p.getCodigo());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            return -1;
        }finally{
        desconectar();
        }
    }
    public int eliminarPosicion(Posicion p) throws ClassNotFoundException, SQLException{
        String sentencia = "delete from posiciones where codigo=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, p.getCodigo());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            return -1;
        }finally{
        desconectar();
        }
    }
    public ArrayList<Posicion> obtenerPosiciones() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from posiciones";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Posicion> lista = new ArrayList();

        while(rs.next()){
            lista.add(new Posicion(rs.getInt("codigo"), rs.getString("nombre")));
        }
        return lista;
        }catch(Exception e){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
    public Posicion obtenerPosicion(int codigo) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from posiciones where codigo=?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        Posicion e = null;
        if(rs.next()){
           e = new Posicion(rs.getInt("codigo"), rs.getString("nombre"));
        }
        return e;
        }catch(Exception ex){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public Posicion validarPosicion(String nombre) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from posiciones where nombre=?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        Posicion e = null;
        if(rs.next()){
           e = new Posicion(rs.getInt("codigo"), rs.getString("nombre"));
        }
        return e;
        }catch(Exception ex){
            return null;
        }finally{
            desconectar();
        }
    }
    
}
