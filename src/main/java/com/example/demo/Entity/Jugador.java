package com.example.demo.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class Jugador implements Serializable {
	
	@Id
	@SequenceGenerator(name="sequence_jugador_id",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_jugador_id")
	private Integer id;
	
	private String nombre;
	private Date fecha_nacimiento;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="clase_id")
	private Clase clase;
	
	private String genero;
	
	@ManyToOne
	@JoinColumn(name="rango_id")
	private Rango rango;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
	        name = "habilidad_jugador",
	        joinColumns = @JoinColumn(name = "jugador_id"),
	        inverseJoinColumns = @JoinColumn(name = "habilidad_id")
	    )
    private List<Habilidad> habilidades;
	
	private String nuuid;
	
	

	
	

}
