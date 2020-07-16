/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.anfp.models.Usuario;
import cl.anfp.dao.UsuarioDAO;
import cl.anfp.utils.*;
import java.sql.SQLException;

/**
 *
 * @author exvicad
 */
public class UsuarioController extends HttpServlet {
    
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
                case "login": iniciarSesion(request,response);
                    break;
                case "logout": cerrarSesion(request,response);
                    break;
                case "registrar": registrar(request,response);
                    break;
                default: 
                    Literales msg = Literales.MSJ_ACCION_INCORRECTA;    
                    response.sendRedirect("index.jsp?msjNok=" + msg.getLiteral());
        }
        }else{
            Literales msg = Literales.MSJ_ACCESO_RESTRINGIDO;    
            response.sendRedirect("index.jsp?msjNok=" + msg.getLiteral());
        }
    }
    
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        Literales url = Literales.URL_LOGIN;
        
        try{
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        Usuario usuarioIniciando = new Usuario(usuario,password);
        UsuarioDAO ud = new UsuarioDAO();
        Usuario temporal= ud.validarUsuario(usuarioIniciando.getUsuario());
        if(temporal != null){
            if(temporal.getPassword().equals(usuarioIniciando.getPassword())){
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", temporal);
                response.sendRedirect("intranet.jsp");
            }else{
                Literales msg = Literales.MSJ_ERROR_LOGIN_PWD;
                response.sendRedirect(url.getLiteral() + "?msjNok=" + msg.getLiteral());
            }
        }else{
            Literales msg = Literales.MSJ_ERROR_LOGIN_USR;
            response.sendRedirect(url.getLiteral() + "?msjNok=" + msg.getLiteral());
        }
        }catch(IOException | ClassNotFoundException | SQLException e){
            Literales msg = Literales.MSJ_ERROR_LOGIN_GENERICO;
            log(e.getMessage(), e);
            response.sendRedirect(url.getLiteral() + "?msjNok=" + msg.getLiteral());
        }
    }
    
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", null);
        Literales url = Literales.URL_HOME;
        response.sendRedirect(url.getLiteral());
    }
    
    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException{

        Literales url = Literales.URL_REGISTRO;

        try{

        String usuario = request.getParameter("usuario").trim();
        String password = request.getParameter("password").trim();
        String nombre = request.getParameter("nombre").trim();
        String apellido = request.getParameter("apellido").trim();

        if(usuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || password.isEmpty()){
            Literales msg = Literales.MSJ_REGISTRO_NOK_DATOS;
            response.sendRedirect(url.getLiteral() + "?msjNok=" + msg.getLiteral());
        }else{
            Usuario usuarioNuevo = new Usuario(usuario, password, nombre, apellido);
            UsuarioDAO ud = new UsuarioDAO();
            if(ud.validarUsuario(usuario) == null){
                try{
                    int respuesta = ud.registrarUsuario(usuarioNuevo);
                    if(respuesta==1){
                        Literales msg = Literales.MSJ_REGISTRO_OK;
                        response.sendRedirect(url.getLiteral() + "?msjOk=" + msg.getLiteral());
                    }else{
                    Literales msg = Literales.MSJ_REGISTRO_NOK_GENERICO;
                    response.sendRedirect(url.getLiteral() + "?msjNok=" + msg.getLiteral());
                    }
                }catch(Exception e){
                    Literales msg = Literales.MSJ_REGISTRO_NOK_GENERICO;
                    response.sendRedirect(url.getLiteral() + "?msjNok=" + msg.getLiteral() + e.getMessage());
                }
            }else{
                Literales msg = Literales.MSJ_REGISTRO_NOK_USUARIO_EXISTE;
                response.sendRedirect(url.getLiteral() + "?msjNok=" + msg.getLiteral());
            }
        }
       }catch(Exception e){
           Literales msg = Literales.MSJ_REGISTRO_NOK_GENERICO;
           response.sendRedirect(url.getLiteral() + "?msjNok=" + msg.getLiteral() + e.getMessage());
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
