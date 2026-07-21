package com.yaeldev.listatodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yaeldev.listatodo.models.Categoria;
import java.util.List;


@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria,Long>{
	List<Categoria> findByNombre(String nombre);
}
