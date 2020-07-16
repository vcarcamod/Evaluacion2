/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.dao;

import cl.anfp.dao.utils.ConexionSql;
import cl.anfp.models.Division;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author exvicad
 */
public class DivisionesDAO extends ConexionSql {
    
    public int registrarDivision(Division d) throws ClassNotFoundException, SQLException{
        String sentencia = "insert into divisiones values (?,?)";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, 0);
        ps.setString(2, d.getNombre());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            //return -1;
            throw ex;
        }finally{
            desconectar();
        }
    }
    public int modificarDivision(Division d) throws ClassNotFoundException, SQLException{
        String sentencia = "update divisiones set nombre=? where codigo=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, d.getNombre());
        ps.setInt(2, d.getCodigo());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            return -1;
        }finally{
        desconectar();
        }
    }
    public int eliminarDivision(Division d) throws ClassNotFoundException, SQLException{
        String sentencia = "delete from divisiones where codigo=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, d.getCodigo());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            return -1;
        }finally{
        desconectar();
        }
    }
    public ArrayList<Division> obtenerDivisiones() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from divisiones";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Division> lista = new ArrayList();

        while(rs.next()){
            lista.add(new Division(rs.getInt("codigo"), rs.getString("nombre")));
        }
        return lista;
        }catch(Exception e){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
    public Division obtenerDivision(int codigo) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from divisiones where codigo=?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        Division e = null;
        if(rs.next()){
           e = new Division(rs.getInt("codigo"), rs.getString("nombre"));
        }
        return e;
        }catch(Exception ex){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public Division validarDivision(String nombre) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from divisiones where nombre=?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        Division e = null;
        if(rs.next()){
           e = new Division(rs.getInt("codigo"), rs.getString("nombre"));
        }
        return e;
        }catch(Exception ex){
            return null;
        }finally{
            desconectar();
        }
    }
    
}
