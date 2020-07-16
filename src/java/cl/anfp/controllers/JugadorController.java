/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.controllers;

import cl.anfp.dao.EquiposDAO;
import cl.anfp.dao.JugadoresDAO;
import cl.anfp.dao.PosicionesDAO;
import cl.anfp.models.Equipo;
import cl.anfp.models.Jugador;
import cl.anfp.models.Posicion;
import cl.anfp.utils.Literales;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author exvicad
 */
public class JugadorController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("accion")!=null){
            response.setCharacterEncoding("UTF-8");
            String accion = request.getParameter("accion");
            switch(accion){
                case "agregar": agregar(request,response);
                    break;
                case "eliminar": eliminar(request,response);
                    break;
                case "modificar": modificar(request,response);
                    break;
                default: 
                    Literales msg = Literales.MSJ_ACCION_INCORRECTA;    
                    response.sendRedirect("jugadores.jsp?msjNok=" + msg.getLiteral());
            }
        }else{
            Literales msg = Literales.MSJ_ACCESO_RESTRINGIDO;    
            response.sendRedirect("jugadores.jsp?msjNok=" + msg.getLiteral());
        }
    }
    
    private void agregar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(request.getParameter("nombre") != null){
            String nombre = request.getParameter("nombre").trim();
            String apellido = request.getParameter("apellido").trim();
            String fec_nacimiento = request.getParameter("fec_nacimiento").trim();
            String[] f_nac = fec_nacimiento.split("/");
            fec_nacimiento = f_nac[2]+"/"+f_nac[1]+"/"+f_nac[0];
            int cod_posicion = Integer.parseInt(request.getParameter("posicion"));
            int cod_equipo = Integer.parseInt(request.getParameter("equipo"));
            int sueldo_anual = Integer.parseInt(request.getParameter("sueldo_anual_clp"));
            if(!nombre.isEmpty() || !apellido.isEmpty() || !fec_nacimiento.isEmpty() || cod_posicion <= 0 || cod_equipo <= 0){
                try{
                    JugadoresDAO jd = new JugadoresDAO();
                    if(jd.validarJugador(nombre, apellido, cod_posicion, cod_equipo) == null){
                        PosicionesDAO pd = new PosicionesDAO();
                        Posicion posicion = pd.obtenerPosicion(cod_posicion);
                        EquiposDAO eqd = new EquiposDAO();
                        Equipo equipo = eqd.obtenerEquipo(cod_equipo);
                        Jugador temp = new Jugador(0, nombre, apellido, fec_nacimiento, posicion, sueldo_anual, equipo);
                        jd.registrarJugador(temp);
                        Literales msg = Literales.MSJ_REGISTRO_DATOS_OK;    
                        response.sendRedirect("jugadores.jsp?msjOk=" + msg.getLiteral());
                    }else{
                        Literales msg = Literales.MSJ_REGISTRO_DATOS_EXISTE;    
                        response.sendRedirect("jugadores.jsp?msjNok=" + msg.getLiteral());
                    }
                }catch(Exception ex){
                    Literales msg = Literales.MSJ_REGISTRO_NOK_GENERICO;
                    log(ex.getMessage(), ex);
                    response.sendRedirect("jugadores.jsp?msjNok=" + msg.getLiteral() + ex.getMessage());
                }
            }else{
                Literales msg = Literales.MSJ_REGISTRO_NOK_DATOS;    
                response.sendRedirect("jugadores.jsp?msjNok=" + msg.getLiteral());
            }
        }
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
        int id = Integer.parseInt(request.getParameter("id").trim());
        if(id <= 0){
            response.sendRedirect("jugadores.jsp?msjNok=Id invalido. ");
        }else{
            JugadoresDAO jd = new JugadoresDAO();
            Jugador jugador = new Jugador(id);
            int respuesta = jd.eliminarJugador(jugador);
            if(respuesta==1){
            response.sendRedirect("jugadores.jsp?msjOk=Jugador eliminado. ");
            }else{
                response.sendRedirect("jugadores.jsp?msjNok=No se pudo eliminar el jugador");
            }
        }
        }catch(Exception e){
             response.sendRedirect("jugadores.jsp?msjNok="+e.getMessage());
        }
    }
    
    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(request.getParameter("nombre") != null){
            int id = Integer.parseInt(request.getParameter("id").trim());
            String nombre = request.getParameter("nombre").trim();
            String apellido = request.getParameter("apellido").trim();
            String fec_nacimiento = request.getParameter("fec_nacimiento").trim();
            String[] f_nac = fec_nacimiento.split("/");
            fec_nacimiento = f_nac[2]+"/"+f_nac[1]+"/"+f_nac[0];
            int cod_posicion = Integer.parseInt(request.getParameter("posicion"));
            int cod_equipo = Integer.parseInt(request.getParameter("equipo"));
            int sueldo_anual = Integer.parseInt(request.getParameter("sueldo_anual_clp"));
            if(!nombre.isEmpty() || !apellido.isEmpty() || !fec_nacimiento.isEmpty() || cod_posicion <= 0 || cod_equipo <= 0){
                try{
                    JugadoresDAO jd = new JugadoresDAO();
                    Jugador temp = jd.obtenerJugador(id);
                    if(temp != null){
                        PosicionesDAO pd = new PosicionesDAO();
                        Posicion posicion = pd.obtenerPosicion(cod_posicion);
                        EquiposDAO eqd = new EquiposDAO();
                        Equipo equipo = eqd.obtenerEquipo(cod_equipo);
                        temp.setNombre(nombre);
                        temp.setApellido(apellido);
                        temp.setFec_nacimiento(fec_nacimiento);
                        temp.setPosicion(posicion);
                        temp.setEquipo(equipo);
                        temp.setSueldo_anual_clp(sueldo_anual);
                        jd.modificarJugador(temp);
                        Literales msg = Literales.MSJ_REGISTRO_DATOS_OK;    
                        response.sendRedirect("jugadores.jsp?msjOk=" + msg.getLiteral());
                    }else{
                        Literales msg = Literales.MSJ_REGISTRO_DATOS_EXISTE;    
                        response.sendRedirect("jugadores.jsp?msjNok=" + msg.getLiteral());
                    }
                }catch(Exception ex){
                    Literales msg = Literales.MSJ_REGISTRO_NOK_GENERICO;
                    log(ex.getMessage(), ex);
                    response.sendRedirect("jugadores.jsp?msjNok=" + msg.getLiteral() + ex.getMessage());
                }
            }else{
                Literales msg = Literales.MSJ_REGISTRO_NOK_DATOS;    
                response.sendRedirect("jugadores.jsp?msjNok=" + msg.getLiteral());
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
