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
                            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Registro</a></li>
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
              <div class="col-sm-4"></div>
              <div class="col-sm-4">
                <h3>Acceso restringido</h3>
                <form action="UsuarioController" method="post">
                    <input type="hidden" name="accion" value="login">
                    <div class="input-group">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                      <input id="usuario" type="text" class="form-control" name="usuario" placeholder="Usuario">
                    </div>
                    <br>
                    <div class="input-group">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                      <input id="password" type="password" class="form-control" name="password" placeholder="Password">
                    </div>
                <br>
                <button type="submit" class="btn"><span class="glyphicon glyphicon-log-in"></span> Ingresar</button>
                <button type="button" class="btn" onClick="goHome();"><span class="glyphicon glyphicon-home"></span> Volver</button>
                </form>
                <br><br>
              </div>
              <div class="col-sm-4"></div>
            </div>
            <br><br>
            <%@ include file="/resources/include/mensajes.jspf" %>
          </div>
    </body>
</html>
