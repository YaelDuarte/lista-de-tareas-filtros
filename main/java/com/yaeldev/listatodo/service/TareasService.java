package com.yaeldev.listatodo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaeldev.listatodo.excepcionesp.UserNotFoundExc;
import com.yaeldev.listatodo.models.Tarea;
import com.yaeldev.listatodo.repository.TareasRepositorio;

@Service
public class TareasService {
	
	@Autowired
	private TareasRepositorio tareasRepo;
	
	
	public List<Tarea> listarTodasTareas(){
		return tareasRepo.findAll();
	}
	
	
	public void eliminarTarea(Long id) {
		if(!tareasRepo.existsById(id) ) {
			throw new RuntimeErrorException(null, "La tarea no fue encontrada");
		}
			tareasRepo.deleteById(id);
	}
	
	public void agregarTarea(Tarea tarea) {
		tareasRepo.save(tarea);
	}
	
	public Tarea obtenerTareaId(Long id) throws UserNotFoundExc {
		return tareasRepo.findById(id).orElseThrow( () -> new UserNotFoundExc("El usuario no fue encontrado"));
	}
	
	/*
	 * FUNCIONES PARA FILTRAR POR PALABRAS
	 */
	@SuppressWarnings("unchecked")
	public List<Tarea> filtrarTareas(String palabra){
		List<Tarea> tareas = new ArrayList<Tarea>();
		return tareas.stream().filter(t -> t.getTitulo().startsWith(palabra)).toList();
	}
	
	public List<Tarea> filtrarPorTitulo(String palabra){
		return tareasRepo.findByTitulo(palabra);
	}
	
	public List<Tarea> filtrarPorPalabraClave(String palabra){
		return tareasRepo.findByTituloContains(palabra);
	}
	
	
	/*
	 * CREACION DE FILTROS POR FECHA 
	 */
	
	
	public List<Tarea> filtrarPorFecha(Date fecha){
		return tareasRepo.findByFechaLimiteBefore(fecha);
	}
	
	public List<Tarea> filtrarCompletadas(){
		return tareasRepo.findByCompletada(true);
	}
	
	// Hacemos una cuenta completa de las tareas hasta el momento asi como las completadas y pendientes 
	
	public long contarTareas() {
		return tareasRepo.count();
	}
	
	public long contarTareasCompletadas() {
		return tareasRepo.findByCompletada(true).size();
	}
	
	public long contarTareasPendientes() {
		return tareasRepo.findByCompletada(false).size();
	}
}
