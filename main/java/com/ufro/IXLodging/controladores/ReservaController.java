/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.controladores;

import com.ufro.IXLodging.DAO.CredencialDAO;
import com.ufro.IXLodging.DAO.HospedajeDAO;
import com.ufro.IXLodging.DAO.PiezaDAO;
import com.ufro.IXLodging.DAO.ReservaDAO;
import com.ufro.IXLodging.DAO.UsuarioDAO;
import com.ufro.IXLodging.modelo.Credencial;
import com.ufro.IXLodging.modelo.Hospedaje;
import com.ufro.IXLodging.modelo.Pieza;
import com.ufro.IXLodging.modelo.Reserva;
import com.ufro.IXLodging.modelo.Usuario;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class ReservaController {

    @Autowired
    private HospedajeDAO hDao;
    @Autowired
    private ReservaDAO rDao;
    @Autowired
    CredencialDAO cDAO;
    @Autowired
    UsuarioDAO uDao;
    @Autowired
    private PiezaDAO pDao;

    @GetMapping("/misReservas") //muestra reservas ingresados por el usuario
    public String muestratusReservas(
            Model model,
            HttpServletRequest request
    ) {
        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");

        int idUsuario = c.getIdUsuario().getIdUsuario();
        Usuario u = uDao.findById(idUsuario);

        List<Reserva> reservas = u.getReservaList();
        model.addAttribute("reservas", reservas);

        return "verMisReservas";
    }

    @GetMapping("/añadirReserva/{idHospedaje}")
    public String mostrarForm(
            Model model,
           @PathVariable("idHospedaje") int idHospedaje,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");
        
        Hospedaje hosp = hDao.findById(idHospedaje);
        List<Pieza> piezas = hosp.getPiezaList();
        model.addAttribute("piezas", piezas);
        model.addAttribute("reserva", new Reserva());

        return "añadirReserva";

    }
    
    @GetMapping("eliminarReserva/{idReserva}")
    public String eliminarReserva(HttpServletResponse response,
            @PathVariable("idReserva") Integer idReserva) throws IOException {       
        this.rDao.deleteById(idReserva); 
        return "verMisReservas";
    }

    @PostMapping("añadirReserva")
    public String ingresaReserva(@ModelAttribute Reserva reserva, HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");

        Usuario u = c.getIdUsuario();
        reserva.setIdUsuario(u);
        rDao.save(reserva);
        

        return "redirect:/misReservas";
    }

    @GetMapping("/misHospedajes/reservas") //muestra reservas ingresados por el usuario
    public String muestraReservashosp(
            Model model,
            HttpServletRequest request
    ) {
        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");

        int idUsuario = c.getIdUsuario().getIdUsuario();

        List<Reserva> reservas = rDao.reservasMisHospedajes(idUsuario);
        model.addAttribute("reservas", reservas);

        return "verReservasHosp";
    }

//    @GetMapping("/misReservas")
//    public String mostrarForm(
//            Model model,
//            HttpServletRequest request
//    ) {
//        return "verMisReservas";
//    }
//    
//   @PostMapping("/misReservas")
//   public String ingresaTuHospedaje(
//           @ModelAttribute Hospedaje h,
//           HttpServletRequest request
//    ) {
//
//
//        return "redirect:/";
//    }
//    
//   @GetMapping("/añadirReserva")
//    public String reservaForm(
//            Model model,
//            HttpServletRequest request
//    ) {
//        return "añadirReserva";
//    }
}
