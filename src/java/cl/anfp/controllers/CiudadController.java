/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.controllers;

import cl.anfp.models.Ciudad;
import cl.anfp.dao.CiudadesDAO;
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
public class CiudadController extends HttpServlet {

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
                case "agregar": addCiudad(request,response);
                    break;
                case "eliminar": delCiudad(request,response);
                    break;
                default: 
                    Literales msg = Literales.MSJ_ACCION_INCORRECTA;    
                    response.sendRedirect("ciudades.jsp?msjNok=" + msg.getLiteral());
            }
        }else{
            Literales msg = Literales.MSJ_ACCESO_RESTRINGIDO;    
            response.sendRedirect("ciudades.jsp?msjNok=" + msg.getLiteral());
        }
    }
    
    private void addCiudad(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(request.getParameter("ciudad") != null){
            String ciudad = request.getParameter("ciudad").trim();
            if(!ciudad.isEmpty()){
                try{
                    CiudadesDAO cd = new CiudadesDAO();
                    if(cd.validaCiudad(ciudad) == null){
                        Ciudad temp = new Ciudad(ciudad);
                        cd.registrarCiudad(temp);
                        Literales msg = Literales.MSJ_REGISTRO_DATOS_OK;    
                        response.sendRedirect("ciudades.jsp?msjOk=" + msg.getLiteral());
                    }else{
                        Literales msg = Literales.MSJ_REGISTRO_DATOS_EXISTE;    
                        response.sendRedirect("ciudades.jsp?msjNok=" + msg.getLiteral());
                    }
                }catch(Exception ex){
                    Literales msg = Literales.MSJ_REGISTRO_NOK_GENERICO;
                    log(ex.getMessage(), ex);
                    response.sendRedirect("ciudades.jsp?msjNok=" + msg.getLiteral() + ex.getMessage());
                }
            }else{
                Literales msg = Literales.MSJ_CAMPO_REQUERIDO;    
                response.sendRedirect("ciudades.jsp?msjNok=" + msg.getLiteral());
            }
        }
    }
    
    private void delCiudad(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
        int id = Integer.parseInt(request.getParameter("id").trim());
        if(id <= 0){
            response.sendRedirect("ciudades.jsp?msjNok=Id invalido. ");
        }else{
            CiudadesDAO ed = new CiudadesDAO();
            Ciudad ciudad = new Ciudad(id);
            int respuesta = ed.eliminarCiudad(ciudad);
            if(respuesta==1){
            response.sendRedirect("ciudades.jsp?msjOk=Ciudad eliminada. ");
            }else{
                response.sendRedirect("ciudades.jsp?msjNok=No se pudo eliminar la Ciudad. ");
            }
        }
        }catch(Exception e){
            log(e.getMessage(), e);
            response.sendRedirect("ciudades.jsp?msjNok=No se pudo eliminar la Ciudad. ");
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
