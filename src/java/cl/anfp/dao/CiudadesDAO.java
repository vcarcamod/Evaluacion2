/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.dao;

import cl.anfp.dao.utils.ConexionSql;
import cl.anfp.models.Ciudad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author exvicad
 */
public class CiudadesDAO extends ConexionSql {
    
    public int registrarCiudad(Ciudad c) throws ClassNotFoundException, SQLException{
        String sentencia = "insert into ciudades values (?,?)";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, 0);
        ps.setString(2, c.getNombre());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            //return -1;
            throw ex;
        }finally{
            desconectar();
        }
    }
    public int modificarCiudad(Ciudad c) throws ClassNotFoundException, SQLException{
        String sentencia = "update ciudades set nombre=? where codigo=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, c.getNombre());
        ps.setInt(2, c.getCodigo());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            return -1;
        }finally{
        desconectar();
        }
    }
    public int eliminarCiudad(Ciudad c) throws ClassNotFoundException, SQLException{
        String sentencia = "delete from ciudades where codigo=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, c.getCodigo());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            return -1;
        }finally{
        desconectar();
        }
    }
    public ArrayList<Ciudad> obtenerCiudades() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from ciudades";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Ciudad> lista = new ArrayList();

        while(rs.next()){
            lista.add(new Ciudad(rs.getInt("codigo"), rs.getString("nombre")));
        }
        return lista;
        }catch(Exception e){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
    public Ciudad obtenerCiudad(int codigo) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from ciudades where codigo=?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        Ciudad e = null;
        if(rs.next()){
           e = new Ciudad(rs.getInt("codigo"), rs.getString("nombre"));
        }
        return e;
        }catch(Exception ex){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public Ciudad validaCiudad(String nombre) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from ciudades where nombre=?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        Ciudad e = null;
        if(rs.next()){
           e = new Ciudad(rs.getInt("codigo"), rs.getString("nombre"));
        }
        return e;
        }catch(Exception ex){
            return null;
        }finally{
            desconectar();
        }
    }
    
}
