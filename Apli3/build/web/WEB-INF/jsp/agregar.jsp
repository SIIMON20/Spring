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
                    <h4>Agregar Nuevo registro</h4>
                </div>
                <div class="card-body">
                    <form method="POST" enctype="multipart/form-data">
                        <label>Carrera</label>
                        <input type="text" id="tec" name="tec" class="form-control">
                        <label>Nombres</label>
                        <input type="text" id="nombre" name="nombre" class="form-control">
                        <label>Apellidos</label>
                        <input type="text" id="apellido" name="Apellido" class="form-control">
                        <label>Celular</label>
                        <input type="text" id="celular" name="celular" class="form-control">
                        <label class="form-label">Cargue la foto:</label>
                        <input type="file" id="imagen" name="imagen" class="form-control">
                        <input type="submit" value="Agregar" class="btn btn-success">
                        <a href="index.htm">Regresar</a>
                    </form>
                    
                </div>
            </div>
        </div>
    </body>
</html>
