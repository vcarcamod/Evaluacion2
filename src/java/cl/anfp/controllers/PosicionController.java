/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.controllers;

import cl.anfp.dao.PosicionesDAO;
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
public class PosicionController extends HttpServlet {

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
                default: 
                    Literales msg = Literales.MSJ_ACCION_INCORRECTA;    
                    response.sendRedirect("posiciones.jsp?msjNok=" + msg.getLiteral());
            }
        }else{
            Literales msg = Literales.MSJ_ACCESO_RESTRINGIDO;    
            response.sendRedirect("posiciones.jsp?msjNok=" + msg.getLiteral());
        }
    }
    
    private void agregar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(request.getParameter("nombre") != null){
            String nombre = request.getParameter("nombre").trim();
            if(!nombre.isEmpty()){
                try{
                    PosicionesDAO pd = new PosicionesDAO();
                    if(pd.validarPosicion(nombre) == null){
                        Posicion temp = new Posicion(nombre);
                        pd.registrarPosicion(temp);
                        Literales msg = Literales.MSJ_REGISTRO_DATOS_OK;    
                        response.sendRedirect("posiciones.jsp?msjOk=" + msg.getLiteral());
                    }else{
                        Literales msg = Literales.MSJ_REGISTRO_DATOS_EXISTE;    
                        response.sendRedirect("posiciones.jsp?msjNok=" + msg.getLiteral());
                    }
                }catch(Exception ex){
                    Literales msg = Literales.MSJ_REGISTRO_NOK_GENERICO;
                    log(ex.getMessage(), ex);
                    response.sendRedirect("posiciones.jsp?msjNok=" + msg.getLiteral() + ex.getMessage());
                }
            }else{
                Literales msg = Literales.MSJ_CAMPO_REQUERIDO;    
                response.sendRedirect("posiciones.jsp?msjNok=" + msg.getLiteral());
            }
        }
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
        int id = Integer.parseInt(request.getParameter("id").trim());
        if(id <= 0){
            response.sendRedirect("posiciones.jsp?msjNok=Id invalido. ");
        }else{
            PosicionesDAO ed = new PosicionesDAO();
            Posicion posicion = new Posicion(id);
            int respuesta = ed.eliminarPosicion(posicion);
            if(respuesta==1){
            response.sendRedirect("posiciones.jsp?msjOk=Posicion eliminada. ");
            }else{
                response.sendRedirect("posiciones.jsp?msjNok=No se pudo eliminar la posicion. ");
            }
        }
        }catch(Exception e){
            log(e.getMessage(), e);
            response.sendRedirect("posiciones.jsp?msjNok=No se pudo eliminar la posicion. ");
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
