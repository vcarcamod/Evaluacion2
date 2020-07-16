/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.dao;

import cl.anfp.models.Usuario;
import cl.anfp.dao.utils.ConexionSql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author exvicad
 */
public class UsuarioDAO extends ConexionSql {
    
    public int registrarUsuario(Usuario u) throws ClassNotFoundException, SQLException{
        String sentencia = "insert into usuarios values (?,?,?,?,?)";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, 0);
        ps.setString(2, u.getUsuario());
        ps.setString(3, u.getPassword());
        ps.setString(4, u.getNombre());
        ps.setString(5, u.getApellido());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception e){
            //return -1;
            throw e;
        }finally{
            desconectar();
        }
    }
    public int modificarUsuario(Usuario u) throws ClassNotFoundException, SQLException{
        String sentencia = "update usuario set nombre=?,apellido=?,password=? where id=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, u.getNombre());
        ps.setString(2, u.getApellido());
        ps.setString(3, u.getPassword());
        ps.setInt(4, u.getId());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception e){
            return -1;
        }finally{
        desconectar();
        }
    }
    public int eliminarUsuario(Usuario u) throws ClassNotFoundException, SQLException{
        String sentencia = "delete from usuario where id=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, u.getId());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception e){
            return -1;
        }finally{
        desconectar();
        }
    }
    public ArrayList<Usuario> obtenerUsuarios() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from usuario";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Usuario> lista = new ArrayList();
        while(rs.next()){
            lista.add(new Usuario(rs.getInt("id"), rs.getString("usuario"),
                    rs.getString("password"),rs.getString("nombre"), rs.getString("apellido")));
        }
        return lista;
        }catch(Exception e){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
    public Usuario obtenerUsuario(int id) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from usuario where id = ?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Usuario u = null;
        if(rs.next()){
           u = new Usuario(rs.getInt("id"), rs.getString("usuario"), rs.getString("password"), rs.getString("nombre"), rs.getString("apellido"));
        }
        return u;
        }catch(Exception e){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public Usuario validarUsuario(String usr) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from usuarios where usuario = ?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setString(1, usr);
        ResultSet rs = ps.executeQuery();
        Usuario u = null;
        if(rs.next()){
           u = new Usuario(rs.getInt("id"), rs.getString("usuario"), rs.getString("password"), rs.getString("nombre"), rs.getString("apellido"));
        }
        return u;
        }catch(Exception e){
            return null;
        }finally{
            desconectar();
        }
    }
}
