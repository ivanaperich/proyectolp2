/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.controladores;

import com.ufro.IXLodging.DAO.UsuarioDAO;
import com.ufro.IXLodging.modelo.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Nicole
 */
@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioDAO uDao; 
    
    @RequestMapping("/")
    public String login() {
        return "login";
    }
    
    @GetMapping("/registro")
    public String mostrarFormulario (Model model){
        
        model.addAttribute("nuevoUsuario", new Usuario());
        
        return "registro";
    }
    
    @GetMapping("/")
    public String index(){
        
        return "index";
    }
    
}
