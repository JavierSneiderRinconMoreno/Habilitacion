package com.example.demo.Controller;

import com.example.demo.Entity.Jugador;
import com.example.demo.Repository.ClaseRepository;
import com.example.demo.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {

	@Autowired
	JugadorRepository jugadorRepository;
	
	@Autowired
	ClaseRepository claseRepository;
	

    @GetMapping
	public ResponseEntity<List<Jugador>> listarJugadores() {
	    List<Jugador> jugadores = jugadorRepository.findAll();

	    if (jugadores.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }

	    return ResponseEntity.ok(jugadores);
	}
	
	
    
}
