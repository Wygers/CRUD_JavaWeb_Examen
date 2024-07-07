<%@page import="model.Profesor"%>
<%@page import="service.Dao_Profesor"%>
<%@page import="java.util.Optional"%>
<%@page import="java.util.List"%>
<%@page import="db.ConexionSingleton"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Crud Java Web</title>
        <!-- Material Icon CDN -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Materialize CSS CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <!-- Your custom styles -->
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Used as an example only to position the footer at the end of the page.
        You can delete these styles or move it to your custom css file -->
        <style>
            body {
                display: flex;
                min-height: 100vh;
                flex-direction: column;
            }
            main {
                flex: 1 0 auto;
            }


        </style>
    </head>
    <body>
        <header>
            <nav class="cyan">
                <div class="nav-wrapper">
                    <div class="container">
                        <a href="#" class="brand-logo">Registro Profesores</a>
                        <a href="#" data-activates="mobile-menu" class="button-collapse"><i class="material-icons">menu</i></a>
                        <ul class="right hide-on-med-and-down">
                            <li><a href="#">Login</a></li>
                            <li><a href="#">Productos</a></li>
                            <li><a href="#">Carrito</a></li>
                            <li><a href="#">Redes</a></li>
                        </ul>
                        <ul class="side-nav" id="mobile-menu">
                            <li>
                                <div class="userView">
                                    <div class="background">
                                        <img src="http://lorempixel.com/output/abstract-q-c-640-480-10.jpg" alt="Background Sidenav">
                                    </div>
                                    <a href="#!user"><img class="circle" src="http://lorempixel.com/output/people-q-c-640-480-9.jpg" alt="User Image Sidenav"></a>
                                    <a href="#!name"><span class="white-text name">John Doe</span></a>
                                    <a href="#!email"><span class="white-text email">jdandturk@gmail.com</span></a>
                                </div>
                            </li>
                            <li><a href="#">item1</a></li>
                            <li><a href="#">item2</a></li>
                            <li><a href="#">item3</a></li>
                            <li><a href="#">item4</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <%
            ConexionSingleton oConexionSingleton = ConexionSingleton.getInstance();
            if (oConexionSingleton.getConnection() == null) {
                out.print("Error: No se pudo establecer la conexión con la base de datos");
            }
        %>
        <main>
            <div class="container-fluid">
                <div class="row">
                    <div class="col s12 m4 l4">
                        <div class="card-panel">
                            <div class="card-content">
                                <h4>Agregar</h4>
                                <div class="row">
                                    <form class="col s12" name="datosUser" method="post" action="Controller_InsertProfesor.do">
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input id="rut_Profe" type="text" onchange="javascript:return Rut(this.value)" name="txt_rut" class="validate">
                                                <label for="rut_Profe">Rut</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input id="name_Profe" type="text" name="txt_nombre" class="validate">
                                                <label for="name_Profe">Nombre</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="mail_Profe" type="email" name="txt_correo" class="validate">
                                                <label for="mail_Profe">Correo</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="password" type="password" name="txt_password" class="validate">
                                                <label for="password">Password</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="tipo_usuario" type="number" name="txt_tipo_usuario" class="validate">
                                                <label for="tipo_usuario">Tipo Usuario</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <button class="btn waves-effect waves-light" type="submit" name="action">Ingresar
                                                <i class="material-icons right">send</i>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col s12 m8 l8">
                        <div class="card-panel">
                            <div class="card-content">
                                <h4>Leer</h4>
                                <div class="row">
                                    <table class="responsive-table">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Rut</th>
                                                <th>Nombre</th>
                                                <th>Correo</th>
                                                <th>Password</th>
                                                <th>Tipo Usuario</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                try {
                                                    Dao_Profesor oDao_Profesor = new Dao_Profesor(ConexionSingleton.getInstance());
                                                    Optional<List<Profesor>> optionalList = oDao_Profesor.getAll();

                                                    if (optionalList.isPresent()) {
                                                        List<Profesor> oList = optionalList.get();
                                                        if (oList.isEmpty()) {
                                                            out.print("<tr><td colspan='6'>No hay profesores registrados</td></tr>");
                                                        } else {
                                                            for (Profesor oProfesor : oList) {
                                            %>
                                            <tr>
                                                <td><%= oProfesor.getId()%></td>
                                                <td><%= oProfesor.getRut()%></td>
                                                <td><%= oProfesor.getNombre()%></td>
                                                <td><%= oProfesor.getCorreo()%></td>
                                                <td><%= oProfesor.getPassword()%></td>
                                                <td><%= oProfesor.getTipo_usuario()%></td>
                                            </tr>
                                            <%
                                                            }
                                                        }
                                                    } else {
                                                        out.print("<tr><td colspan='6'>Error: No se pudo obtener la lista de profesores</td></tr>");
                                                    }
                                                } catch (Exception e) {
                                                    out.print("<tr><td colspan='6'>Error: " + e.getMessage() + "</td></tr>");
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12 m6 l6">
                        <div class="card-panel">
                            <div class="card-content">
                                <h4>Actualizar</h4>
                                <div class="row">

                                    <form class="col s12" method="post" action="Controller_UpdateProfesor.do">
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input  id="id_ProfeUp" type="number" name="txt_idUp" class="validate">
                                                <label for="id_ProfeUp">Id</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="mail_ProfeUp" type="email" name="txt_correoUp" class="validate">
                                                <label for="mail_ProfeUp">Correo</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="phone_ProfeUp" type="number" name="txt_phoneUp" class="validate">
                                                <label for="phone_ProfeUp">Teléfono</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <textarea id="textarea1Up" name="txt_direccionUp" class="materialize-textarea"></textarea>
                                                <label for="textarea1Up">Dirección</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <button class="btn waves-effect waves-light" type="submit" name="action">Actualizar
                                                <i class="material-icons right">send</i>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col s12 m6 l6">
                        <div class="card-panel">
                            <div class="card-content">
                                <h4>Eliminar</h4>
                                <div class="row">
                                    <form class="col s12" method="post" action="Controller_DeleteProfesor.do">
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input  id="id_ProfeDel" type="number" name="txt_idDel" class="validate">
                                                <label for="id_ProfeDel">Id</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <button class="btn waves-effect waves-light" type="submit" name="action">Eliminar
                                                <i class="material-icons right">send</i>
                                            </button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <footer class="page-footer cyan">
            <div class="container">
                <div class="row">
                    <div class="col s12 m8 l6">
                        <h5 class="white-text">First Footer Content</h5>
                        <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
                    </div>
                    <div class="col s12 m4 l6">
                        <h5 class="white-text">Second Footer Content</h5>
                        <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
                    </div>
                </div>
            </div>
            <div class="footer-copyright">
                <div class="container">
                    © 2017 Copyright Text
                    <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
                </div>
            </div>
        </footer>
        <!-- jQuery CDN -->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
        <!-- Materialize JS CDN -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
        <script src="JS/validarut.js"></script>
        <script src="JS/jquery.rut.js"></script>
        <script>
                                                    $("document").ready(function () {
                                                        $(".button-collapse").sideNav();
                                                    });
                                                    $(function () {
                                                        $("input#rut_Profe").rut({
                                                            formatOn: 'keyup',
                                                            minimumLength: 8, // validar largo mínimo; default: 2
                                                            validateOn: 'change' // si no se quiere validar, pasar null
                                                        });
                                                        var input = document.getElementById('rut_Profe');
                                                        input.addEventListener('input', function () {
                                                            if (this.value.length >= 13)
                                                                this.value = this.value.slice(0, 12);
                                                        })
                                                    });
        </script>
    </body>
</html>
