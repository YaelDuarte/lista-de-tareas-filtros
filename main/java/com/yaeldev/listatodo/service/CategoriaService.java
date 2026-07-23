package com.yaeldev.listatodo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaeldev.listatodo.excepcionesp.CategoryNotFound;
import com.yaeldev.listatodo.models.Categoria;
import com.yaeldev.listatodo.repository.CategoriaRepositorio;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepositorio catRepo;
	
	public List<Categoria> listarCategorias() {
		return catRepo.findAll();
	}
	
	public void agregarCategoria(Categoria categoria) {
		catRepo.save(categoria);
	}
	
	public void eliminarCategoria(Categoria categoria) {
		catRepo.delete(categoria);
	}
	
	public Categoria buscarCategoriaPorId(Long id) throws CategoryNotFound{
		return catRepo.findById(id).orElseThrow( () -> new CategoryNotFound("Categoria no encontrada") );
	}
	
	public void eliminarCategoriaPorId(Long id){
		catRepo.deleteById(id);
	}
}
