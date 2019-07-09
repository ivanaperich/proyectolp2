/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.DAO;

import com.ufro.IXLodging.modelo.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nicole
 */
public interface ReservaDAO extends CrudRepository<Reserva, Integer> {

    @Query("SELECT r FROM Reserva r INNER JOIN FETCH r.idPieza p INNER JOIN FETCH "
            + "p.idHospedaje h WHERE h.idUsuario.idUsuario = :idUsuario")
    public List<Reserva> reservasMisHospedajes(@Param("idUsuario") int idUsuario);

}
