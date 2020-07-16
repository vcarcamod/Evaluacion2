package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\n");
      out.write("<!--\n");
      out.write("To change this license header, choose License Headers in Project Properties.\n");
      out.write("To change this template file, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");
      out.write("<html lang=\"es\">\n");
      out.write("    <head>\n");
      out.write("        \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-sm-12\">\n");
      out.write("                    <nav class=\"navbar navbar-default\" style=\"background-color: #ed1b2d; margin-bottom: 0px\">\n");
      out.write("                        <div class=\"container-fluid\">\n");
      out.write("                          <div class=\"navbar-header\">\n");
      out.write("                              <a class=\"navbar-brand logo\" href=\"./\"></a>\n");
      out.write("                          </div>\n");
      out.write("                          <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("                            <li><a href=\"login_admin.jsp\"><span class=\"glyphicon glyphicon-user\"></span> Registro</a></li>\n");
      out.write("                            <li><a href=\"login.jsp\"><span class=\"glyphicon glyphicon-log-in\"></span> Ingreso</a></li>\n");
      out.write("                          </ul>\n");
      out.write("                        </div>\n");
      out.write("                    </nav>\n");
      out.write("                    <nav class=\"navbar navbar-default\" style=\"background-color: #215584;\">\n");
      out.write("                        <div class=\"container-fluid\">\n");
      out.write("                          <ul class=\"nav navbar-nav botonera\">\n");
      out.write("                            <!--<li class=\"active\"><a href=\"./\">Inicio</a></li>-->\n");
      out.write("                          </ul>\n");
      out.write("                        </div>\n");
      out.write("                    </nav>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row\">\n");
      out.write("              <div class=\"col-sm-2\"></div>\n");
      out.write("              <div class=\"col-sm-8\">\n");
      out.write("                <h3>Sistema de gestión ANFP</h3>\n");
      out.write("                <p>En este sitio podrá realizar las siguientes acciones de gestión.</p>\n");
      out.write("                <ul>\n");
      out.write("                    <li>Llevar un registro de los jugadores.</li>\n");
      out.write("                    <li>Llevar un registro de los equipos.</li>\n");
      out.write("                    <li>Además podrá gestionar los siguientes datos:\n");
      out.write("                        <ul>\n");
      out.write("                            <li>Divisiones</li>\n");
      out.write("                            <li>Posiciones</li>\n");
      out.write("                            <li>Estadios</li>\n");
      out.write("                            <li>Ciudades</li>\n");
      out.write("                        </ul>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("                <p>Para poder acceder debe estar registrado en el sistema.</p>\n");
      out.write("              </div>\n");
      out.write("              <div class=\"col-sm-2\"></div>\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
