package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Habilidad {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	 private String nombre;
	 private Integer nuuid;
	 private Integer rango_id;
	 
	 
	 @ManyToMany(mappedBy = "habilidad")
		private Jugador jugador;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "rango")
		private Rango rango;
}


