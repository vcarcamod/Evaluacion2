<%@page import="cl.anfp.models.Equipo"%>
<%@page import="cl.anfp.dao.EquiposDAO"%>
<%@page import="cl.anfp.models.Posicion"%>
<%@page import="cl.anfp.dao.PosicionesDAO"%>
<%@page import="cl.anfp.models.Division"%>
<%@page import="cl.anfp.dao.DivisionesDAO"%>
<%@page import="cl.anfp.models.Estadio"%>
<%@page import="cl.anfp.models.Estadio"%>
<%@page import="cl.anfp.dao.EstadiosDAO"%>
<%@page import="cl.anfp.models.Ciudad"%>
<%@page import="cl.anfp.dao.CiudadesDAO"%>
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
            function eliminarJugador(id){
                var form = document.getElementById("FormEliminarJugador");
                form.id.value = id;
                form.submit();
            }
            function modificarJugador(id){
                document.getElementById("modif_id").value = id;
                var nombre = document.getElementById("nombre"+id).value;
                document.getElementById("modif_nombre").value = nombre;
                var apellido = document.getElementById("apellido"+id).value;
                document.getElementById("modif_apellido").value = apellido;
                var fec_nac = document.getElementById("fec_nac"+id).value;
                document.getElementById("modif_fec_nacimiento").value = fec_nac;
                var posicion = document.getElementById("posicion"+id).value;
                document.getElementById("modif_posicion").value = posicion;
                var equipo = document.getElementById("equipo"+id).value;
                document.getElementById("modif_equipo").value = equipo;
                var sueldo = document.getElementById("sueldo"+id).value;
                document.getElementById("modif_sueldo_anual_clp").value = sueldo;
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
                            <% OpcionesMenu opcion = OpcionesMenu.JUGADORES; %>
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
                <h3>Jugadores</h3>
                <p>En este sitio podrá gestionar la lista de jugadores registrados.</p>
                <table class="table table-striped">
                      <thead>
                          <tr>
                              <th>Apellido</th>
                              <th>Nombre</th>
                              <th>Edad</th>
                              <th>Equipo</th>
                              <th>Posición</th>
                              <th>Sueldo Anual</th>
                              <th width="88">Modificar</th>
                              <th width="88">Eliminar</th>
                          </tr>
                      </thead>
                      <tbody>
                      <%
                          JugadoresDAO jd = new JugadoresDAO();
                          ArrayList<Jugador> jugadores = jd.obtenerJugadores();
                          if(jugadores.size() > 0){
                            for(Jugador jugador : jugadores){
                        %>
                          <tr>
                              <td>
                                  <input type="hidden" id="apellido<%= jugador.getId()%>" value="<%= jugador.getApellido()%>">
                                  <%= jugador.getApellido()%>
                              </td>
                            <td>
                                <input type="hidden" id="nombre<%= jugador.getId()%>" value="<%= jugador.getNombre()%>">
                                <%= jugador.getNombre() %>
                            </td>
                            <td>
                                <input type="hidden" id="fec_nac<%= jugador.getId()%>" value="<%= jugador.getFec_nacimiento()%>">
                                <%= jugador.getEdad() %>
                            </td>
                            <td>
                                <input type="hidden" id="equipo<%= jugador.getId()%>" value="<%= jugador.getEquipo().getCodigo() %>">
                                <%= jugador.getEquipo().getNombre() %>
                            </td>
                            <td>
                                <input type="hidden" id="posicion<%= jugador.getId()%>" value="<%= jugador.getPosicion().getCodigo() %>">
                                <%= jugador.getPosicion().getNombre() %>
                            </td>
                            <td>
                                <input type="hidden" id="sueldo<%= jugador.getId()%>" value="<%= jugador.getSueldo_anual_clp()%>">
                                $<%= jugador.getSueldo_anual_clp() %></td>
                            <td align="center">
                                <a href="#" onclick="modificarJugador(<%= jugador.getId()%>)" data-toggle="modal" data-target="#myModalModif"><span class="glyphicon glyphicon glyphicon-edit" style="cursor: pointer"></span></a>
                            </td>
                            <td align="center">
                                <a href="#" onclick="eliminarJugador(<%= jugador.getId()%>)"><span class="glyphicon glyphicon glyphicon-remove" style="cursor: pointer"></span></a>
                            </td>
                          </tr>
                          <%
                                }
                            }else{
                            %>
                            <tr>
                                <td colspan="7"><h4>No hay jugadores registrados</h4></td>
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
            <form name="FormEliminarJugador" id="FormEliminarJugador" method="post" action="JugadorController">
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="id" value="">
            </form>
            <form name="FormModificarJugador" id="FormEliminarJugador" method="post" action="JugadorController">
                <input type="hidden" name="accion" value="modificar">
                <input type="hidden" name="id" value="">
            </form>
            <%@ include file="/resources/include/mensajes.jspf" %>
            <%@ include file="/resources/include/jugadores/modalNuevoJugador.jspf" %>
            <%@ include file="/resources/include/jugadores/modalModificarJugador.jspf" %>
        </div>
    </body>
</html>
