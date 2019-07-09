/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.DAO;

import com.ufro.IXLodging.modelo.Pieza;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Nicole
 */
public interface PiezaDAO extends CrudRepository<Pieza, Integer> {

    @Override
    public List<Pieza> findAll();
    public Pieza findById(int id);
    
    public List<Pieza> findByIdHospedaje_IdHospedaje(int idHospedaje);
    
}
