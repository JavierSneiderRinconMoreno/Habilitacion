package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Clase;
import java.util.List;


public interface ClaseRepository extends JpaRepository<Clase, Integer>{

	//public List<Clase> findByNombre(String nombre);
}
