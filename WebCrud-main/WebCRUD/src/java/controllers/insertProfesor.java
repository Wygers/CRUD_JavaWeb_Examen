package controllers;

import db.ConexionSingleton;
import service.Dao_Profesor;
import model.Profesor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "insertPerson", urlPatterns = {"/"})
public class insertProfesor extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        ConexionSingleton oConexion = ConexionSingleton.getInstance();
        Dao_Profesor dao_Profesor = new Dao_Profesor(oConexion);
        Profesor profesor = new Profesor();
        
        profesor.setRut(request.getParameter("txt_rut"));
        profesor.setNombre(request.getParameter("txt_nombre"));
        profesor.setCorreo(request.getParameter("txt_correo"));
        profesor.setPassword(request.getParameter("txt_password"));
        profesor.setTipo_usuario(Integer.parseInt(request.getParameter("txt_tipo_usuario")));

        // Insertar la persona en la base de datos y redirigir según el resultado
        dao_Profesor.insert(profesor);
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirigir al formulario de inserción de profesores si se accede mediante GET
        response.sendRedirect("index.jsp");
    }
}
