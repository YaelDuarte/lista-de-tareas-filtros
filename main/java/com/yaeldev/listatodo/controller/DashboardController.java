package com.yaeldev.listatodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yaeldev.listatodo.service.EstadisticasService;

@Controller
@Order(1)
public class DashboardController {
	
	@Autowired
	EstadisticasService estaServ;
	
	@GetMapping("/")
	public String dashboard(Model model) {
		
		model.addAttribute("totales", estaServ.contarTareasd());
		model.addAttribute("completadas", estaServ.contarTareasCompletadas());
		model.addAttribute("pendientes", estaServ.contarTareasPendientes());
		
		return "tareas/dashboard";
	}

}
