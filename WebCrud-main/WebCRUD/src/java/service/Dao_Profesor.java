package service;

import db.ConexionSingleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.CRUD;
import model.Profesor;

public class Dao_Profesor implements CRUD<Profesor> {

    private ConexionSingleton oConexionSingleton;

    public Dao_Profesor(ConexionSingleton oConexionSingleton) {
        this.oConexionSingleton = oConexionSingleton;
    }

    @Override
    public void insert(Profesor obj) {
        String sql = "INSERT INTO profesor ( rut, nombre, correo, password, tipo_usuario) VALUES ( ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = oConexionSingleton.getConnection().prepareStatement(sql)) {
            statement.setString(1, obj.getRut());
            statement.setString(2, obj.getNombre());
            statement.setString(3, obj.getCorreo());
            statement.setString(4, obj.getPassword());
            statement.setInt(5, obj.getTipo_usuario());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Profesor obj) {
        String sql = "DELETE FROM profesor WHERE id = ?";
        try (PreparedStatement statement = oConexionSingleton.getConnection().prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Profesor> get(int id) {
        String sql = "SELECT * FROM profesor WHERE id = ?";
        try (PreparedStatement statement = oConexionSingleton.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Profesor(
                        resultSet.getInt("id"),
                        resultSet.getString("rut"),
                        resultSet.getString("nombre"),
                        resultSet.getString("correo"),
                        resultSet.getString("password"),
                        resultSet.getInt("tipo_usuario")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Profesor obj) {
        String sql = "UPDATE profesor SET rut=?, nombre=?, correo=?, password=?, tipo_usuario=? WHERE id=?";
        try (PreparedStatement statement = oConexionSingleton.getConnection().prepareStatement(sql)) {
            statement.setString(1, obj.getRut());
            statement.setString(2, obj.getNombre());
            statement.setString(3, obj.getCorreo());
            statement.setString(4, obj.getPassword());
            statement.setInt(5, obj.getTipo_usuario());
            statement.setInt(6, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<List<Profesor>> getAll() {
    String sql = "SELECT * FROM profesor";
    List<Profesor> list = new ArrayList<>();
    try {
        PreparedStatement statement = oConexionSingleton.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Profesor profesor = new Profesor(
                    resultSet.getInt("id"),
                    resultSet.getString("rut"),
                    resultSet.getString("nombre"),
                    resultSet.getString("correo"),
                    resultSet.getString("password"),
                    resultSet.getInt("tipo_usuario")); // Corregido el nombre de la columna aquí
            list.add(profesor);
        }
        return Optional.of(list);
    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener la lista de profesores: " + e.getMessage());
    }
}

}
