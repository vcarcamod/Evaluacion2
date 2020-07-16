<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <%@ include file="/resources/include/header.jspf" %>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <nav class="navbar navbar-default" style="background-color: #ed1b2d; margin-bottom: 0px">
                        <div class="container-fluid">
                          <div class="navbar-header">
                              <a class="navbar-brand logo" href="./"></a>
                          </div>
                          <ul class="nav navbar-nav navbar-right">
                            <li><a href="registro_usuario.jsp"><span class="glyphicon glyphicon-user"></span> Registro</a></li>
                            <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Ingreso</a></li>
                          </ul>
                        </div>
                    </nav>
                    <nav class="navbar navbar-default" style="background-color: #215584;">
                        <div class="container-fluid">
                          <ul class="nav navbar-nav botonera">
                            <!--<li class="active"><a href="./">Inicio</a></li>-->
                          </ul>
                        </div>
                    </nav>
                </div>
            </div>
            <div class="row">
              <div class="col-sm-2"></div>
              <div class="col-sm-8">
                <h3>Sistema de gestión ANFP</h3>
                <p>En este sitio podrá realizar las siguientes acciones de gestión.</p>
                <ul>
                    <li>Llevar un registro de los jugadores.</li>
                    <li>Llevar un registro de los equipos.</li>
                    <li>Además podrá gestionar los siguientes datos:
                        <ul>
                            <li>Divisiones</li>
                            <li>Posiciones</li>
                            <li>Estadios</li>
                            <li>Ciudades</li>
                        </ul>
                    </li>
                </ul>
                <p>Para poder acceder debe estar registrado en el sistema.</p>
              </div>
              <div class="col-sm-2"></div>
            </div>
            <br><br>
            <%@ include file="/resources/include/mensajes.jspf" %>
        </div>
    </body>
</html>
