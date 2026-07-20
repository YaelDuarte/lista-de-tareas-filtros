package com.yaeldev.listatodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import com.yaeldev.listatodo.excepcionesp.UserNotFoundExc;
import com.yaeldev.listatodo.models.Tarea;
import com.yaeldev.listatodo.service.TareasService;

@Controller
@RequestMapping("/tareas")
public class TareasController {
	
	@Autowired
	private TareasService tarServ;
	
	@GetMapping
	public String listarTareas(Model model) {
		model.addAttribute("tarea",tarServ.listarTodasTareas());
		return "tareas/lista";
	}
	
	@GetMapping("/nuevo")
	public String nuevaTarea(Model model) {
		model.addAttribute("tarea",new Tarea());
		return "tareas/formulario.html";
	}
	
	@PostMapping("/guardar")
	public String guardarTarea(@Valid @ModelAttribute Tarea tarea, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "tareas/formulario";
		}
		
		tarServ.agregarTarea(tarea);
		return "redirect:/tareas";
	}
	
	@GetMapping("/eliminar/{id}")
	public String modificarTarea(@PathVariable Long id) {
		tarServ.eliminarTarea(id);
		return "redirect:/tareas";
	}
	
	@GetMapping("/obtener/{id}")
	public String obtenerTareaUnica(Model model, @PathVariable Long id) throws UserNotFoundExc {
		model.addAttribute("tarea", tarServ.obtenerTareaId(id));
		return "tareas/formulario";
	}
	
	@GetMapping("/editar/{id}")
	public String editarTarea(@PathVariable Long id, Model model) throws UserNotFoundExc {
		model.addAttribute("tarea", tarServ.obtenerTareaId(id));
		return "tareas/formulario";
	}
	
	@GetMapping("/filtrarLetra/{palabra}")
	public List<Tarea> filtrarTareas(@PathVariable String palabra) {
		return tarServ.filtrarTareas(palabra);
	}
	
}
