/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.dao;

import cl.anfp.dao.utils.ConexionSql;
import cl.anfp.models.Ciudad;
import cl.anfp.models.Estadio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author exvicad
 */
public class EstadiosDAO extends ConexionSql {
    
    public int registrarEstadio(Estadio e) throws ClassNotFoundException, SQLException{
        String sentencia = "insert into estadios values (?,?,?,?)";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, 0);
        ps.setString(2, e.getNombre());
        ps.setInt(3, e.getCiudad().getCodigo());
        ps.setInt(4, e.getCapacidad());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            //return -1;
            throw ex;
        }finally{
            desconectar();
        }
    }
    public int modificarEstadio(Estadio e) throws ClassNotFoundException, SQLException{
        String sentencia = "update estadios set nombre=?,ciudad=?,capacidad=? where codigo=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, e.getNombre());
        ps.setInt(2, e.getCiudad().getCodigo());
        ps.setInt(3, e.getCapacidad());
        ps.setInt(4, e.getCodigo());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            return -1;
        }finally{
        desconectar();
        }
    }
    public int eliminarEstadio(Estadio e) throws ClassNotFoundException, SQLException{
        String sentencia = "delete from estadios where codigo=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, e.getCodigo());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            return -1;
        }finally{
        desconectar();
        }
    }
    public ArrayList<Estadio> obtenerEstadios() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from estadios";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Estadio> lista = new ArrayList();

        while(rs.next()){
            CiudadesDAO cd = new CiudadesDAO();
            Ciudad ciudad = cd.obtenerCiudad(rs.getInt("ciudad"));
            lista.add(new Estadio(rs.getInt("codigo"), rs.getString("nombre"),
                    ciudad, rs.getInt("capacidad")));
        }
        return lista;
        }catch(Exception e){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
    public Estadio obtenerEstadio(int codigo) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from estadios where codigo=?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        Estadio e = null;
        if(rs.next()){
           e = new Estadio(rs.getInt("codigo"), rs.getString("nombre"),
                    new Ciudad(rs.getInt("ciudad")),rs.getInt("capacidad"));
        }
        return e;
        }catch(Exception ex){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public Estadio validarEstadio(String nombre) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from estadios where nombre=?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        Estadio e = null;
        if(rs.next()){
           e = new Estadio(rs.getInt("codigo"), rs.getString("nombre"),
                    new Ciudad(rs.getInt("ciudad")),rs.getInt("capacidad"));
        }
        return e;
        }catch(Exception ex){
            return null;
        }finally{
            desconectar();
        }
    }
    
}
