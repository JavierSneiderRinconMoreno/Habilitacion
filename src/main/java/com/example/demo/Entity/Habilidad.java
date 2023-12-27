package com.example.demo.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class Habilidad implements Serializable{

	@Id
	@SequenceGenerator(name="sequence_habilidad_id",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_habilidad_id")
	private Integer id;
	
	private String nombre;
	private String descripcion;
	private String nuuid;
	
	@ManyToOne
	@JoinColumn(name="rango_id")
	private Rango rango;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "habilidades")
    private List<Jugador> jugadores;
}


