<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <div class="container mt-4">
            <div class="card border-info">
                <div calss="card-header bg-info text-white">
                    <a class="btn btn-primary" href="agregar.htm">Nuevo registro</a>
                </div>
                <div class="card-body">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                          <th>Carrera</th>
                          <th>Nombre</th>
                          <th>Apellido</th>
                          <th>Celular</th>
                          <th>Foto</th>
                          <th></th>
                        </tr>
                        </thead>
                        <c:forEach var="dato" items="${lista}">
                        <tbody>
                        <tr>
                          <td>${dato.tec}</td>
                          <td>${dato.nombre}</td>
                          <td>${dato.apellido}</td>
                          <td>${dato.celular}</td>
                          <td><img style="height: 100px; width: 100px;" src='<c:url value="${dato.imagen}"/>' /></td>
                          <td>
                              <a href="editar.htm?id=${dato.id}" class="btn btn-warning">Editar</a>
                              <a href="delete.htm?id=${dato.id}&imagen=${dato.imagen}" class="btn btn-danger">Borrar</a>
                          </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                      </table>
                </div>
            </div>
        </div>
    </body>
</html>
