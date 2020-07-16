/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author exvicad
 */
public class ConexionSql {
    Connection conexion; // variable tipo Connection para la conexion a base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/"; // inicio de la cadena de conexion
    private static final String BD = "sistemaanfp";  // variable para el nombre de nuestra base de datos
    private static final String DRIVER = "com.mysql.jdbc.Driver"; // driver de jdbc para mysql
    private static final String USR = "root"; // usuario de la base de datos
    private static final String PASSWD = ""; // password del usuario de la base de datos
    
    /**
     * este metodo permite conectar con la base de datos
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public void conectar() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        conexion = DriverManager.getConnection(URL+BD,USR,PASSWD);
    }
    
    /**
     * este metodo permite desconectar la base de datos
     * @throws java.sql.SQLException
     */
    public void desconectar() throws SQLException{
        if(!conexion.isClosed()){
            conexion.close();
        }
    }
    
    /**
    * este metodo obtiene un statemet a partir de la conexion
    * el statement permite ejecutar sentencias en la base de datos
     * @return conexion.createStatement()
     * @throws java.sql.SQLException
    */
    public Statement obtenerStatement() throws SQLException{
        return conexion.createStatement();
    }
    
    /**
    * este metodo obteiene un preparedStatement con una sentencia pre configurada
    * para ejecutar una sentencia con parametros en la base de datos
     * @param sentencia query a ejecutar en la BD.
     * @return conexion.prepareStatement(sentencia)
     * @throws java.sql.SQLException
    */
    public PreparedStatement obtenerPS(String sentencia) throws SQLException{
        return conexion.prepareStatement(sentencia);
    }
}
