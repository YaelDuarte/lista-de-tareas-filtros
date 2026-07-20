package com.yaeldev.listatodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaeldev.listatodo.repository.TareasRepositorio;

@Service
public class EstadisticasService {

	@Autowired
	private TareasRepositorio tareasRepo;
	
	
	public long contarTareasd() {
		return tareasRepo.count();
	}
	
	public long contarTareasCompletadas(){
		return tareasRepo.findByCompletada(true).size();
	}
	
	public long contarTareasPendientes() {
		return tareasRepo.findByCompletada(false).size();
	}
}
