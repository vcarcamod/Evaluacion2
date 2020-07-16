/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.controllers;

import cl.anfp.dao.DivisionesDAO;
import cl.anfp.models.Division;
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
public class DivisionController extends HttpServlet {

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
                    response.sendRedirect("divisiones.jsp?msjNok=" + msg.getLiteral());
            }
        }else{
            Literales msg = Literales.MSJ_ACCESO_RESTRINGIDO;    
            response.sendRedirect("divisiones.jsp?msjNok=" + msg.getLiteral());
        }
    }
    
    private void agregar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(request.getParameter("nombre") != null){
            String nombre = request.getParameter("nombre").trim();
            if(!nombre.isEmpty()){
                try{
                    DivisionesDAO dd = new DivisionesDAO();
                    if(dd.validarDivision(nombre) == null){
                        Division temp = new Division(nombre);
                        dd.registrarDivision(temp);
                        Literales msg = Literales.MSJ_REGISTRO_DATOS_OK;    
                        response.sendRedirect("divisiones.jsp?msjOk=" + msg.getLiteral());
                    }else{
                        Literales msg = Literales.MSJ_REGISTRO_DATOS_EXISTE;    
                        response.sendRedirect("divisiones.jsp?msjNok=" + msg.getLiteral());
                    }
                }catch(Exception ex){
                    Literales msg = Literales.MSJ_REGISTRO_NOK_GENERICO;
                    log(ex.getMessage(), ex);
                    response.sendRedirect("divisiones.jsp?msjNok=" + msg.getLiteral() + ex.getMessage());
                }
            }else{
                Literales msg = Literales.MSJ_CAMPO_REQUERIDO;    
                response.sendRedirect("divisiones.jsp?msjNok=" + msg.getLiteral());
            }
        }
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
        int id = Integer.parseInt(request.getParameter("id").trim());
        if(id <= 0){
            response.sendRedirect("divisiones.jsp?msjNok=Id invalido. ");
        }else{
            DivisionesDAO ed = new DivisionesDAO();
            Division division = new Division(id);
            int respuesta = ed.eliminarDivision(division);
            if(respuesta==1){
            response.sendRedirect("divisiones.jsp?msjOk=Division eliminada. ");
            }else{
                response.sendRedirect("divisiones.jsp?msjNok=No se pudo eliminar la Division. ");
            }
        }
        }catch(Exception e){
            log(e.getMessage(), e);
            response.sendRedirect("divisiones.jsp?msjNok=No se pudo eliminar la Division. ");
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
