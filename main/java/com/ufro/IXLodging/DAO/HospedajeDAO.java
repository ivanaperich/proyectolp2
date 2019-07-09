/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.DAO;

import com.ufro.IXLodging.modelo.Hospedaje;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Nicole
 */
public interface HospedajeDAO extends CrudRepository<Hospedaje, Integer> {
    
    @Override
    public List<Hospedaje> findAll();
    public Hospedaje findById(int id);
    
}
