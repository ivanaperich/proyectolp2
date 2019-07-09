/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.controladores;

import com.ufro.IXLodging.DAO.CredencialDAO;
import com.ufro.IXLodging.DAO.HospedajeDAO;
import com.ufro.IXLodging.DAO.PiezaDAO;
import com.ufro.IXLodging.DAO.UsuarioDAO;
import com.ufro.IXLodging.modelo.Credencial;
import com.ufro.IXLodging.modelo.Hospedaje;
import com.ufro.IXLodging.modelo.Pieza;
import com.ufro.IXLodging.modelo.Usuario;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Nicole
 */
@Controller
public class HospedajeController {

    @Autowired
    private HospedajeDAO hDao;
    @Autowired
    CredencialDAO cDAO;
    @Autowired
    UsuarioDAO uDao;
    @Autowired
    PiezaDAO pDao;

    @GetMapping("/mishospedajes/ver") //muestra hospedajes ingresados por el usuario
    public String muestratusHospedajes(
            Model model,
            HttpServletRequest request
    ){
        
        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");        
        
            int idUsuario= c.getIdUsuario().getIdUsuario();
            
            Usuario u = uDao.findById(idUsuario);
            
            List<Hospedaje> hospedajes = u.getHospedajeList();
            model.addAttribute("hospedajes", hospedajes);
        
            return "tusHospedajes";
        }
        
    
    
    @GetMapping("/hospedajes/buscar")//ver todos los hospedajes 
    public String muestraHospedajes(
            Model model,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");

        int idUsuario = c.getIdCredencial();
        Usuario u = uDao.findById(idUsuario);

        List<Hospedaje>hospedajes=hDao.findAll();
        model.addAttribute("hospedajes", hospedajes);

        return "buscaHospedaje";
    }
    
    

    @GetMapping("hospedajes/nuevo")
    public String mostrarForm(
            Model model,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");

   
            model.addAttribute("hospedaje", new Hospedaje());

            return "ingresaHospedaje";
        
    }

    @PostMapping("hospedajes/nuevo")
    public String ingresaTuHospedaje(@ModelAttribute("nombreHospedaje") String nombreHospedaje,
            @ModelAttribute("descripcion") String descripcion, @ModelAttribute("ubicacion") String ubicacion,
            HttpServletRequest request
    ) {
        
        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");
        Hospedaje h = new Hospedaje(nombreHospedaje, descripcion, ubicacion);
        h.setDueno(c.getNombreUsuario());
        h.setIdUsuario(c.getIdUsuario());
        hDao.save(h);

        return "redirect:/";
    }
    
    @GetMapping("hospedajes/{idHospedaje}/piezas")
    public String verPiezasHospedaje(
            HttpServletRequest request,
            @PathVariable("idHospedaje") int idHospedaje,
            Model model
    ){
        
        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");
        
        
            int idUsuario = c.getIdCredencial();
            List<Pieza> piezas = pDao.findByIdHospedaje_IdHospedaje(idHospedaje);
            model.addAttribute("piezas", piezas);
            return "verPiezas";
        
    } 

}
