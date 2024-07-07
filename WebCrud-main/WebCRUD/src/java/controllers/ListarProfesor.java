package controllers;

import db.ConexionSingleton;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListarPersonas")
public class ListarProfesor extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Connection conn;

    public void init() throws ServletException {
        ConexionSingleton oConexion = ConexionSingleton.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Consulta SQL para obtener las personas
            String sql = "SELECT id, rut, nombre, correo, password, tipo_usuario FROM profesor";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Mostrar resultados
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("rut") + "</td>");
                out.println("<td>" + rs.getString("nombre") + "</td>");
                out.println("<td>" + rs.getString("correo") + "</td>");
                out.println("<td>" + rs.getString("password") + "</td>");
                out.println("<td>" + rs.getInt("tipo_usuario") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Error: " + ex.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        out.println("</body></html>");
    }

    public void destroy() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
