<%@page import="cl.anfp.models.Division"%>
<%@page import="cl.anfp.dao.DivisionesDAO"%>
<%@page import="cl.anfp.models.Estadio"%>
<%@page import="cl.anfp.dao.EstadiosDAO"%>
<%@page import="cl.anfp.models.Ciudad"%>
<%@page import="cl.anfp.dao.CiudadesDAO"%>
<%@page import="cl.anfp.models.Equipo"%>
<%@page import="cl.anfp.dao.EquiposDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cl.anfp.models.Jugador"%>
<%@page import="cl.anfp.dao.JugadoresDAO"%>
<%@page import="cl.anfp.dao.UsuarioDAO"%>
<%@page import="cl.anfp.utils.OpcionesMenu"%>
<%@ include file="/resources/include/validaSesion.jspf" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <%@ include file="/resources/include/header_intranet.jspf" %>
        <script>
            function eliminarEquipo(id){
                var form = document.getElementById("FormEliminarEquipo");
                form.id.value = id;
                form.submit();
            }
            function modificarEquipo(id){
                document.getElementById("edit_id").value = id;
                var nombre = document.getElementById("nombre"+id).value;
                document.getElementById("edit_nombre").value = nombre;
                var procedencia = document.getElementById("procedencia"+id).value;
                document.getElementById("edit_procedencia").value = procedencia;
                var estadio = document.getElementById("estadio"+id).value;
                document.getElementById("edit_estadio").value = estadio;
                var division = document.getElementById("division"+id).value;
                document.getElementById("edit_division").value = division;
            }
        </script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <nav class="navbar navbar-default" style="background-color: #ed1b2d; margin-bottom: 0px">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <a class="navbar-brand logo" href="./intranet.jsp"></a>
                            </div>
                            <ul class="nav navbar-nav navbar-right">
                                <li class="txt_bienvenida"><h4>Bienvenido <%= nombreCompleto %></h4></li>
                                <li><a href="#" onclick="logout()"><span class="glyphicon glyphicon-log-out"></span> Cerrar Sesión</a></li>
                            </ul>
                        </div>
                    </nav>
                    <nav class="navbar navbar-default" style="background-color: #215584;">
                        <div class="container-fluid">
                            <% OpcionesMenu opcion = OpcionesMenu.EQUIPOS; %>
                            <ul class="nav navbar-nav botonera">
                                <li<%= opcion == OpcionesMenu.INICIO ? " class=\"active\"" : "" %>><a href="./intranet.jsp">Inicio</a></li>
                                <li<%= opcion == OpcionesMenu.JUGADORES ? " class=\"active\"" : "" %>><a href="./jugadores.jsp">Jugadores</a></li>
                                <li<%= opcion == OpcionesMenu.EQUIPOS ? " class=\"active\"" : "" %>><a href="./equipos.jsp">Equipos</a></li>
                                <li<%= opcion == OpcionesMenu.ESTADIOS ? " class=\"active\"" : "" %>><a href="./estadios.jsp">Estadios</a></li>
                                <li<%= opcion == OpcionesMenu.POSICIONES ? " class=\"active\"" : "" %>><a href="./posiciones.jsp">Posiciones</a></li>
                                <li<%= opcion == OpcionesMenu.DIVISIONES ? " class=\"active\"" : "" %>><a href="./divisiones.jsp">Divisiones</a></li>
                                <li<%= opcion == OpcionesMenu.CIUDADES ? " class=\"active\"" : "" %>><a href="./ciudades.jsp">Ciudades</a></li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
            <div class="row">
              <div class="col-sm-2"></div>
              <div class="col-sm-8">
                <h3>Equipos</h3>
                <p>En este sitio podrá gestionar la lista de Equipos registrados.</p>
                <table class="table table-striped">
                      <thead>
                          <tr>
                              <th>Nombre</th>
                              <th>División</th>
                              <th>Estadio</th>
                              <th>Procedencia</th>
                              <th width="88">Modificar</th>
                              <th width="88">Eliminar</th>
                          </tr>
                      </thead>
                      <tbody>
                      <%
                          EquiposDAO ed = new EquiposDAO();
                          ArrayList<Equipo> equipos = ed.obtenerEquipos();
                          if(equipos.size() > 0){
                            for(Equipo equipo : equipos){
                        %>
                          <tr>
                            <td>
                                <input type="hidden" id="nombre<%= equipo.getCodigo()%>" value="<%= equipo.getNombre()%>">
                                <%= equipo.getNombre()%>
                            </td>
                            <td>
                                <input type="hidden" id="division<%= equipo.getCodigo()%>" value="<%= equipo.getDivision().getCodigo()%>">
                                <%= equipo.getDivision().getNombre() %>
                            </td>
                            <td>
                                <input type="hidden" id="estadio<%= equipo.getCodigo()%>" value="<%= equipo.getEstadio().getCodigo()%>">
                                <%= equipo.getEstadio().getNombre() %>
                            </td>
                            <td>
                                <input type="hidden" id="procedencia<%= equipo.getCodigo()%>" value="<%= equipo.getProcedencia().getCodigo()%>">
                                <%= equipo.getProcedencia().getNombre() %>
                            </td>
                            <td align="center">
                                <a href="#" onclick="modificarEquipo(<%= equipo.getCodigo()%>)" data-toggle="modal" data-target="#myModalEditar"><span class="glyphicon glyphicon glyphicon-edit" style="cursor: pointer"></span></a>
                            </td>
                            <td align="center">
                                <a href="#" onclick="eliminarEquipo(<%= equipo.getCodigo()%>)"><span class="glyphicon glyphicon glyphicon-remove" style="cursor: pointer"></span></a>
                            </td>
                          </tr>
                          <%
                                }
                            }else{
                            %>
                            <tr>
                                <td colspan="7"><h4>No hay Equipos registrados</h4></td>
                            </tr>
                          <%
                            }
                      %>
                      </tbody>
                  </table>
              </div>
                      <div class="col-sm-2">
                          <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-plus"></span> Nuevo</button>
                      </div>
            </div>
            <br><br>
            <%@ include file="/resources/include/mensajes.jspf" %>
            <form name="FormEliminarJugador" id="FormEliminarEquipo" method="post" action="EquipoController">
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="id" value="">
            </form>
            <%@include file="/resources/include/equipos/modalNuevoEquipo.jspf" %>
            <%@include file="/resources/include/equipos/modalEditarEquipo.jspf" %>
        </div>
    </body>
</html>
