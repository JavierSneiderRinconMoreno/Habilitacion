package com.example.demo.Controller;

import com.example.demo.Entity.Jugador;
import com.example.demo.Entity.Clase;
import com.example.demo.Entity.Habilidad;
import com.example.demo.Repository.ClaseRepository;
import com.example.demo.Repository.HabilidadRepostory;
import com.example.demo.Repository.JugadorRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {
	
	HabilidadRepostory habilidadRepository;

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
    
    @PostMapping("/login")
    public ResponseEntity<String> getNuuidByNombre(@RequestParam String nombre)  {
            Jugador j = jugadorRepository.findByNombre(nombre);

            if (j!=null) {                 
                return ResponseEntity.ok( String.valueOf(j.getNuuid()));
            } 
            return null;
    }
    
    @GetMapping("/{clase}")
    public ResponseEntity<List<Jugador>> listarJugadoresByClase(@PathVariable("clase") String clase){
		
    	Clase clases = claseRepository.findByNuuid(clase);		
		 if (clases == null) {
		        return ResponseEntity.notFound().build();
		    }
		    List<Jugador> jugadoresEnClase = clases.getJugadores();

		    if (jugadoresEnClase.isEmpty()) {
		        return ResponseEntity.notFound().build();
		    }
		    return ResponseEntity.ok(jugadoresEnClase);
	}
    
    @PostMapping
	public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador creado) {
		Jugador jugador = jugadorRepository.findByNombre(creado.getNombre());

	    if (jugador != null) {
	        return ResponseEntity.badRequest().body(null);
	    }

	    Jugador jugadoradorCreado = jugadorRepository.save(creado);
	    return ResponseEntity.status(HttpStatus.CREATED).body(jugadoradorCreado);
	}
    
    
    @GetMapping("/{nuuid}/habilidades")
	 public ResponseEntity<List<Habilidad>> listarHabilidadJugador(@PathVariable("nuuid") String nuuid) {
		Jugador jugador = jugadorRepository.findByNuuid(nuuid);

	     if (jugador == null) {
	         return ResponseEntity.notFound().build();
	     }
	     List<Habilidad> habilidades = jugador.getHabilidades();
	     return ResponseEntity.ok(habilidades);
	 }
    
    
    @PostMapping("/{nuuid1}/habilidades/{nuuid2}")
	public ResponseEntity<String> agregarHabilidadByJugador(
	        @PathVariable("nuuid1") String nuuid_jugador,
	        @PathVariable("nuuid2") String nuuid_habilidad) {

	    Jugador jugador = jugadorRepository.findByNuuid(nuuid_jugador);
	    Habilidad habilidad = habilidadRepository.findByNuuid(nuuid_habilidad);

	    if (jugador == null || habilidad == null) {
	        return ResponseEntity.notFound().build();
	    }

	    List<Habilidad> jugador_habilidad = jugador.getHabilidades();
	    if (jugador_habilidad == null) {
	    	jugador_habilidad = new ArrayList<>();
	    }
	    
	    if (!jugador_habilidad.contains(habilidad)) {
	    	jugador_habilidad.add(habilidad);
	        jugador.setHabilidades(jugador_habilidad); 
	        jugadorRepository.save(jugador); 
	        return ResponseEntity.ok().build();
	    }

	    return ResponseEntity.badRequest().body("Habilidad existente.");
	}
    
    public Jugador actuualizarInfo_Jugador(@PathVariable("nuuid") String nuuid, @RequestBody Jugador actuaizar) {
        Jugador jugador = jugadorRepository.findByNuuid(nuuid);

        if (jugador!=null) {
        	jugador.setNombre(actuaizar.getNombre());
            jugador.setFecha_nacimiento(actuaizar.getFecha_nacimiento());
            jugador.setDescripcion(actuaizar.getDescripcion());
            jugador.setClase(actuaizar.getClase());
            jugador.setGenero(actuaizar.getGenero());
            jugador.setRango(actuaizar.getRango());
            jugador.setHabilidades(actuaizar.getHabilidades());

            Jugador guardar = jugadorRepository.save(jugador);
            return guardar;            
        }
        return null;
        
    }

    
    
    
    
    
    
    
}
