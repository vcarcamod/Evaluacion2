/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.dao;

import cl.anfp.dao.utils.ConexionSql;
import cl.anfp.models.Equipo;
import cl.anfp.models.Jugador;
import cl.anfp.models.Posicion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author exvicad
 */
public class JugadoresDAO extends ConexionSql {
    
    public int registrarJugador(Jugador j) throws ClassNotFoundException, SQLException{
        String sentencia = "insert into jugadores values (?,?,?,?,?,?,?)";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, 0);
        ps.setString(2, j.getNombre());
        ps.setString(3, j.getApellido());
        ps.setString(4, j.getFec_nacimiento());
        ps.setInt(5, j.getPosicion().getCodigo());
        ps.setInt(6, j.getSueldo_anual_clp());
        ps.setInt(7, j.getEquipo().getCodigo());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception e){
            //return -1;
            throw e;
        }finally{
            desconectar();
        }
    }
    public int modificarJugador(Jugador j) throws ClassNotFoundException, SQLException{
        String sentencia = "update jugadores set nombre=?,apellido=?,fec_nacimiento=?,posicion=?,sueldo_anual_clp=?,equipo=? where id=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, j.getNombre());
        ps.setString(2, j.getApellido());
        ps.setString(3, j.getFec_nacimiento());
        ps.setInt(4, j.getPosicion().getCodigo());
        ps.setInt(5, j.getSueldo_anual_clp());
        ps.setInt(6, j.getEquipo().getCodigo());
        ps.setInt(7, j.getId());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception e){
            return -1;
        }finally{
        desconectar();
        }
    }
    public int eliminarJugador(Jugador j) throws ClassNotFoundException, SQLException{
        String sentencia = "delete from jugadores where id=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, j.getId());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception e){
            return -1;
        }finally{
        desconectar();
        }
    }
    public ArrayList<Jugador> obtenerJugadores() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from v_jugadores";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Jugador> lista = new ArrayList();

        while(rs.next()){
            PosicionesDAO pd = new PosicionesDAO();
            Posicion posicion = pd.obtenerPosicion(rs.getInt("posicion"));
            EquiposDAO ed = new EquiposDAO();
            Equipo equipo = ed.obtenerEquipo(rs.getInt("equipo"));
            lista.add(new Jugador(rs.getInt("id"), rs.getString("nombre"),
                    rs.getString("apellido"),rs.getString("fec_nacimiento"),rs.getInt("edad"), posicion,
            rs.getInt("sueldo_anual_clp"), equipo));
        }
        return lista;
        }catch(Exception e){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
    public Jugador obtenerJugador(int id) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from v_jugadores where id = ?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Jugador j = null;
        if(rs.next()){
           j = new Jugador(rs.getInt("id"), rs.getString("nombre"),
                    rs.getString("apellido"), rs.getString("fec_nacimiento"), 
                   rs.getInt("edad"), new Posicion(rs.getInt("posicion")),
            rs.getInt("sueldo_anual_clp"), new Equipo(rs.getInt("equipo")));
        }
        return j;
        }catch(Exception e){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public Jugador validarJugador(String nombre, String apellido, int posicion, int equipo) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from jugadores where nombre = ? and apellido = ? and posicion = ? and equipo = ?";
        try{
        conectar();
        PreparedStatement ps = obtenerPS(sentencia);
        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setInt(3, posicion);
        ps.setInt(4, equipo);
        ResultSet rs = ps.executeQuery();
        Jugador j = null;
        if(rs.next()){
           j = new Jugador(rs.getInt("id"), rs.getString("nombre"),
                    rs.getString("apellido"),rs.getString("fec_nacimiento"), new Posicion(rs.getInt("posicion")),
            rs.getInt("sueldo_anual_clp"), new Equipo(rs.getInt("equipo")));
        }
        return j;
        }catch(Exception e){
            return null;
        }finally{
            desconectar();
        }
    }
}
