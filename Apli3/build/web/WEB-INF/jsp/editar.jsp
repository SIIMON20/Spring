<%-- 
    Document   : editar
    Created on : 29/07/2023, 6:55:23 p. m.
    Author     : Fla Lopez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container mt-4 col-lg-4">
            <div class="card border-info">
                <div class="card-header bg-info">
                    <h4>Actualizar registro</h4>
                </div>
                <div class="card-body">
                    <form method="POST">
                        <label>Carrera</label>
                        <input type="text" name="tec" class="form-control" value="${lista[0].Tec}">
                        <label>Nombres</label>
                        <input type="text" name="nombre" class="form-control" value="${lista[0].Nombre}">
                        <label>Apellidos</label>
                        <input type="text" name="Apellido" class="form-control" value="${lista[0].Apellido}">
                        <label>Celular</label>
                        <input type="text" name="celular" class="form-control" value="${lista[0].Celular}">
                        <input type="submit" value="Actualizar" class="btn btn-success">
                        <a href="index.htm">Regresar</a>
                    </form>
                    
                </div>
            </div>
        </div>
    </body>
</html>
