/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.controladores;

import com.ufro.IXLodging.DAO.HospedajeDAO;
import com.ufro.IXLodging.DAO.PiezaDAO;
import com.ufro.IXLodging.DAO.UsuarioDAO;
import com.ufro.IXLodging.modelo.Credencial;
import com.ufro.IXLodging.modelo.Hospedaje;
import com.ufro.IXLodging.modelo.Pieza;
import com.ufro.IXLodging.modelo.Usuario;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author usuario
 */
@Controller
public class PiezaController {

    @Autowired
    PiezaDAO pDao;
    @Autowired
    UsuarioDAO uDao;
    @Autowired
    HospedajeDAO hDao;

    @GetMapping("/hospedajes/{idHospedaje}/nuevaPieza")
    public String mostrarForm(
            @PathVariable("idHospedaje") Integer idHospedaje,
            Model model,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");

        model.addAttribute("idHospedaje", idHospedaje);
        model.addAttribute("pieza", new Pieza());

        return "ingresaPieza";

    }

    @PostMapping("/hospedajes/{idHospedaje}/nuevaPieza")
    public String muestraFormPieza(
            Model model,
            @PathVariable("idHospedaje") int idHospedaje,
            @ModelAttribute Pieza p,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");
        int idUsuario = c.getIdCredencial();
        Hospedaje h = hDao.findById(idHospedaje);
        Usuario u = uDao.findById(idUsuario);
        p.setIdHospedaje(h);
        pDao.save(p);
        return "redirect:/hospedajes/"+idHospedaje+"/piezas";
    }

}
