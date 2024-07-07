package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSingleton {

    private static ConexionSingleton instance;
    private Connection oConnection;

    private ConexionSingleton() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            oConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/profesorprueba", "root", "");
            System.out.println("Conexión exitosa"); // Agrega un mensaje de confirmación aquí
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al establecer la conexión a la base de datos");
        }
    }

    public static ConexionSingleton getInstance() {
        if (instance == null) {
            instance = new ConexionSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        if (oConnection == null) {
            System.out.println("Conexión no establecida"); // Agrega un mensaje aquí para verificar
        }
        return oConnection;
    }
}
