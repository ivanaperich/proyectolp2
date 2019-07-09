/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.DAO;

import com.ufro.IXLodging.modelo.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Nicole
 */
public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
    
    @Override
    public List<Usuario> findAll();
    public Usuario findById(int idUsuario);
    
}
