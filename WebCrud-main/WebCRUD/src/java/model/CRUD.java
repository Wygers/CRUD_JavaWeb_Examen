/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jr972
 */
public interface CRUD<Profesor> {

    void insert(Profesor obj);
    void delete(Profesor obj);
    Optional<Profesor> get(int id);
    void update(Profesor obj);
    Optional<List<Profesor>> getAll();

}
