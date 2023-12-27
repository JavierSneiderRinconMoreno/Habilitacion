package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Jugador;
import java.util.List;
import com.example.demo.Entity.Habilidad;



public interface JugadorRepository extends JpaRepository<Jugador, Integer>{
	
	public  List<Jugador> findByNombre(String nombre);
	
	//public List<Jugador>  findByClase_id(Integer clase_id);;
	
	public List<Jugador> findByNuuid(String nuuid);

}
