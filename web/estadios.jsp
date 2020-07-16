<%@page import="cl.anfp.models.Ciudad"%>
<%@page import="cl.anfp.dao.CiudadesDAO"%>
<%@page import="cl.anfp.dao.EstadiosDAO"%>
<%@page import="cl.anfp.models.Estadio"%>
<%@page import="java.util.ArrayList"%>
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
            function eliminarEstadio(id){
                var form = document.getElementById("FormEliminar");
                form.id.value = id;
                form.submit();
            }
            function modificarEstadio(id){
                document.getElementById("edit_id").value = id;
                var nombre = document.getElementById("nombre"+id).value;
                document.getElementById("edit_nombre").value = nombre;
                var ciudad = document.getElementById("ciudad"+id).value;
                document.getElementById("edit_ciudad").value = ciudad;
                var capacidad = document.getElementById("capacidad"+id).value;
                document.getElementById("edit_capacidad").value = capacidad;
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
                            <% OpcionesMenu opcion = OpcionesMenu.ESTADIOS; %>
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
                <h3>Estadios</h3>
                <p>En este sitio podrá gestionar la lista de Estadios registrados.</p>
                <table class="table table-striped">
                      <thead>
                          <tr>
                              <th>Nombre</th>
                              <th>Ciudad</th>
                              <th>Capacidad</th>
                              <th width="88">Modificar</th>
                              <th width="88">Eliminar</th>
                          </tr>
                      </thead>
                      <tbody>
                      <%
                          EstadiosDAO jd = new EstadiosDAO();
                          ArrayList<Estadio> estadios = jd.obtenerEstadios();
                          if(estadios.size() > 0){
                            for(Estadio estadio : estadios){
                        %>
                          <tr>
                            <td>
                                <input type="hidden" id="nombre<%= estadio.getCodigo()%>" value="<%= estadio.getNombre()%>">
                                <%= estadio.getNombre()%>
                            </td>
                            <td>
                                <input type="hidden" id="ciudad<%= estadio.getCodigo()%>" value="<%= estadio.getCiudad().getCodigo() %>">
                                <%= estadio.getCiudad().getNombre() %>
                            </td>
                            <td>
                                <input type="hidden" id="capacidad<%= estadio.getCodigo()%>" value="<%= estadio.getCapacidad()%>">
                                <%= estadio.getCapacidad() %> personas
                            </td>
                            <td align="center">
                                <a href="#" onclick="modificarEstadio(<%= estadio.getCodigo() %>)" data-toggle="modal" data-target="#myModalEditar"><span class="glyphicon glyphicon glyphicon-edit" style="cursor: pointer"></span></a>
                            </td>
                            <td align="center">
                                <a href="#" onclick="eliminarEstadio(<%= estadio.getCodigo() %>)"><span class="glyphicon glyphicon glyphicon-remove" style="cursor: pointer"></span></a>
                            </td>
                          </tr>
                          <%
                                }
                            }else{
                            %>
                            <tr>
                                <td colspan="7"><h4>No hay Estadios registrados</h4></td>
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
            <form name="FormEliminar" id="FormEliminar" method="post" action="EstadioController">
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="id" value="">
            </form>
            <%@include file="/resources/include/estadios/modalNuevoEstadio.jspf" %>
            <%@include file="/resources/include/estadios/modalEditarEstadio.jspf" %>
        </div>
    </body>
</html>
