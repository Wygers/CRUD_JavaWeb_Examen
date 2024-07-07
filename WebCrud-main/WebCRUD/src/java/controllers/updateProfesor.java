/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author antho
 */
@WebServlet(name = "updateProfesor", urlPatterns = {"/updateProfesor"})
public class updateProfesor extends HttpServlet {

    private final String DB_URL = "jdbc:mysql://localhost:3306/profesorprueba";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Obtener parámetros del request
        int id = Integer.parseInt(request.getParameter("id"));
        String rut = request.getParameter("rut");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        int tipo_usuario = Integer.parseInt(request.getParameter("tipo_usuario"));

        // Conectar a la base de datos y actualizar el registro
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String query = "UPDATE profesor SET rut=?, nombre=?, correo=?, password=?, tipo_usuario=? WHERE id=?";
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, rut);
                    ps.setString(2, nombre);
                    ps.setString(3, correo);
                    ps.setString(4, password);
                    ps.setInt(5, tipo_usuario);
                    ps.setInt(6, id);
                    int rowsUpdated = ps.executeUpdate();

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Actualización de Profesor</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    if (rowsUpdated > 0) {
                        out.println("<h1>Profesor actualizado exitosamente!</h1>");
                    } else {
                        out.println("<h1>Error al actualizar el profesor</h1>");
                    }
                    out.println("</body>");
                    out.println("</html>");
                }
            } catch (SQLException ex) {
                throw new ServletException("Error al conectar o actualizar en la base de datos", ex);
            }
        } catch (ClassNotFoundException ex) {
            throw new ServletException("Driver JDBC no encontrado", ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para actualizar datos de un profesor";
    }// </editor-fold>

}
