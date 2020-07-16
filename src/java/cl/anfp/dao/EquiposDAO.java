/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.dao;

import cl.anfp.dao.utils.ConexionSql;
import cl.anfp.models.Ciudad;
import cl.anfp.models.Division;
import cl.anfp.models.Equipo;
import cl.anfp.models.Estadio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author exvicad
 */
public class EquiposDAO extends ConexionSql {
    
    public int registrarEquipo(Equipo e) throws ClassNotFoundException, SQLException{
        String sentencia = "insert into equipos values (?,?,?,?,?)";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, 0);
        ps.setString(2, e.getNombre());
        ps.setInt(3, e.getProcedencia().getCodigo());
        ps.setInt(4, e.getEstadio().getCodigo());
        ps.setInt(5, e.getDivision().getCodigo());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            //return -1;
            throw ex;
        }finally{
            desconectar();
        }
    }
    public int modificarEquipo(Equipo e) throws ClassNotFoundException, SQLException{
        String sentencia = "update equipos set nombre=?,procedencia=?,estadio=?,division=? where codigo=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, e.getNombre());
        ps.setInt(2, e.getProcedencia().getCodigo());
        ps.setInt(3, e.getEstadio().getCodigo());
        ps.setInt(4, e.getDivision().getCodigo());
        ps.setInt(5, e.getCodigo());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception ex){
            return -1;
        }finally{
        desconectar();
        }
    }
    public int eliminarEquipo(Equipo e) throws ClassNotFoundException, SQLException{
        String sentencia = "delete from equipos where codigo=?";
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
    public ArrayList<Equipo> obtenerEquipos() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from equipos";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Equipo> lista = new ArrayList();

        while(rs.next()){
            CiudadesDAO cd = new CiudadesDAO();
            Ciudad ciudad = cd.obtenerCiudad(rs.getInt("procedencia"));
            EstadiosDAO ed = new EstadiosDAO();
            Estadio estadio = ed.obtenerEstadio(rs.getInt("estadio"));
            DivisionesDAO dd = new DivisionesDAO();
            Division division = dd.obtenerDivision(rs.getInt("division"));
            lista.add(new Equipo(rs.getInt("codigo"), rs.getString("nombre"),
                    ciudad, estadio, division));
        }
        return lista;
        }catch(Exception e){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
    public Equipo obtenerEquipo(int codigo) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from equipos where codigo = ?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        Equipo e = null;
        if(rs.next()){
           e = new Equipo(rs.getInt("codigo"), rs.getString("nombre"),
                    new Ciudad(rs.getInt("procedencia")),new Estadio(rs.getInt("estadio")), new Division(rs.getInt("division")));
        }
        return e;
        }catch(Exception ex){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public Equipo validarEquipo(String nombre) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from equipos where nombre = ?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        Equipo e = null;
        if(rs.next()){
           e = new Equipo(rs.getInt("codigo"), rs.getString("nombre"),
                    new Ciudad(rs.getInt("ciudad")),new Estadio(rs.getInt("estadio")), new Division(rs.getInt("division")));
        }
        return e;
        }catch(Exception ex){
            return null;
        }finally{
            desconectar();
        }
    }
    
}
