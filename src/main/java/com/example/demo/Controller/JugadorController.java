package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Clase;
import com.example.demo.Entity.Habilidad;
import com.example.demo.Entity.Jugador;
import com.example.demo.Repository.JugadorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {
	
	@Autowired
	JugadorRepository jugadorRepository;
	
	@PostMapping("/login")
    public String getNuuidByNombre(@RequestBody Jugador jugador) throws JsonMappingException, JsonProcessingException{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jugador.getNombre());
            List<Jugador> j = jugadorRepository.findByNombre(jugador.getNombre());

            if (!j.isEmpty()) {                 
                return j.get(0).getNuuid();
            } 
            return null;
    }
	
	@GetMapping
	public List<Jugador> getCategoriaAll() {

		return jugadorRepository.findAll();
	}
	
	@GetMapping("/{clase}")
	public  List<Jugador> getJugadoresaByClase(@PathVariable Integer clase) {
		
		List<Jugador> jugadores = jugadorRepository.findByClase_id(clase);	
				
		return jugadores;

	}
	
	@PostMapping
	public Jugador postJugador(@RequestBody Jugador jugador) {
		
		jugadorRepository.save(jugador);
		
		return jugador;
		

	}
	

	@GetMapping("/{nuuid}/habilidades")
	public List<Habilidad> listHabilidadJugador(@PathVariable String nuuid){
				
		List<Jugador> ju = jugadorRepository.findByNuuid(nuuid);
				
		if(ju!=null) {
			return ju.get(0).getHabilidades();
		}
		
		return null;
	}

	@PutMapping("/{nuuid}/habilidades")
	public Jugador agregarHabilidad(@PathVariable String nuuid, @RequestBody Habilidad habilidad) {
		
			List<Jugador> jugadores = jugadorRepository.findByNuuid(nuuid);
					
					if (jugadores!=null) {
						
						Jugador jugadorHabi = jugadores.get(0);						
						
						jugadorHabi.getHabilidades().add(habilidad);				
												
						jugadorRepository.save(jugadorHabi);
						
						return jugadorHabi;
					}
		return null;
	}
	
	@PutMapping("/{nuuid}")
	public Jugador actuakizarInformacion(@PathVariable String nuuid, @RequestBody Jugador jugador) {
		
			List<Jugador> jugadores = jugadorRepository.findByNuuid(nuuid);
					
					if (jugadores!=null) {
					
						Jugador jugadorActualizar = jugadores.get(0);
						
						jugadorActualizar.setClase(jugador.getClase());
						jugadorActualizar.setClase_id(jugador.getClase_id());
						jugadorActualizar.setDescripcion(jugador.getDescripcion());
						jugadorActualizar.setGenero(jugador.getGenero());
						jugadorActualizar.setHabilidades(jugador.getHabilidades());
						jugadorActualizar.setNombre(jugador.getNombre());
						jugadorActualizar.setNuuid(jugador.getNuuid());
						jugadorActualizar.setRango(jugador.getRango());
						jugadorActualizar.setRango_id(jugador.getRango_id());
						
						jugadorRepository.save(jugadorActualizar);
						return jugadorActualizar;
						
					}
		return null;
	}
	
	
}
