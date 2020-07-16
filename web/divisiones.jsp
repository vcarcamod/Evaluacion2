<%@page import="java.util.ArrayList"%>
<%@page import="cl.anfp.models.Division"%>
<%@page import="cl.anfp.dao.DivisionesDAO"%>
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
            function eliminarDivision(id){
                var form = document.getElementById("FormEliminar");
                form.id.value = id;
                form.submit();
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
                            <% OpcionesMenu opcion = OpcionesMenu.DIVISIONES; %>
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
                <h3>Divisiones</h3>
                <p>En este sitio podrá gestionar la lista de Divisiones de los Equipos registrados.</p>
                <table class="table table-striped">
                      <thead>
                          <tr>
                              <th width="80%">Nombre</th>
                              <th width="10%">Modificar</th>
                              <th width="10%">Eliminar</th>
                          </tr>
                      </thead>
                      <tbody>
                      <%
                          DivisionesDAO pd = new DivisionesDAO();
                          ArrayList<Division> divisiones = pd.obtenerDivisiones();
                          if(divisiones.size() > 0){
                            for(Division division : divisiones){
                        %>
                          <tr>
                            <td><%= division.getNombre()%></td>
                            <td align="center">
                                <a href="#" onclick=""><span class="glyphicon glyphicon glyphicon-edit" style="cursor: pointer"></span></a>
                            </td>
                            <td align="center">
                                <a href="#" onclick="eliminarDivision(<%= division.getCodigo() %>)"><span class="glyphicon glyphicon glyphicon-remove" style="cursor: pointer"></span></a>
                            </td>
                          </tr>
                          <%
                                }
                            }else{
                            %>
                            <tr>
                                <td colspan="7"><h4>No hay Divisiones registradas</h4></td>
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
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                      <h4 class="modal-title">Agregar nueva Division</h4>
                    </div>
                    <div class="modal-body">
                      <form action="./DivisionController" method="post">
                            <input type="hidden" name="accion" value="agregar">
                            <div class="form-group">
                                <label for="nombre">División</label>
                                <input id="nombre" type="text" class="form-control" name="nombre" placeholder="Nombre de la División">
                            </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" id="btnGuardar" class="btn btn-success" value="Guardar">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        </form>
                    </div>
                  </div>

                </div>
              </div>
            <form name="FormEliminar" id="FormEliminar" method="post" action="DivisionController">
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="id" value="">
            </form>
        </div>
    </body>
</html>
