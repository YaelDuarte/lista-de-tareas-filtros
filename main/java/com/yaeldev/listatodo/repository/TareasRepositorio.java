package com.yaeldev.listatodo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yaeldev.listatodo.models.Tarea;
import java.util.Date;


@Repository
public interface TareasRepositorio extends JpaRepository<Tarea,Long>{
	List<Tarea> findByTitulo(String titulo);
	List<Tarea> findByTituloContains(String titulo);
	List<Tarea> findByFechaLimiteBefore(Date fechaLimite);
	List<Tarea> findByCompletada(boolean completada);
	List<Tarea> findByPrioridad(String prioridad);
}
