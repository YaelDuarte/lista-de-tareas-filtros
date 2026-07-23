package com.yaeldev.listatodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yaeldev.listatodo.excepcionesp.CategoryNotFound;
import com.yaeldev.listatodo.models.Categoria;
import com.yaeldev.listatodo.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService catServ;
	
	@PostMapping("/categoriaSave")
	public String categoriaSave(Categoria categoria) {
		catServ.agregarCategoria(categoria);
		return "redirect:/categorias";
	}
	
	@GetMapping
	public String categorias(Model model) {
		model.addAttribute("categorias", catServ.listarCategorias());
		
		return "categorias/lista";
	}
	
	@GetMapping("/nuevaCategoria")
	public String nuevaCategoria(Model model) {
		model.addAttribute("categoria",new Categoria());
		return "categorias/formulario";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCategoria(@PathVariable Long id, Model model) throws CategoryNotFound {
		Categoria categoria = catServ.buscarCategoriaPorId(id);
		return "categorias/formulario";
	}
	
	@GetMapping("eliminar/{id}")
	public String eliminarCategoria(@PathVariable Long id, Model model){
		catServ.eliminarCategoriaPorId(id);
		return "redirect:/formulario";
	}
}
