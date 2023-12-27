package com.example.demo.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class Clase implements Serializable{

	@Id
	@SequenceGenerator(name="Sequence_clase_id",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Sequence_clase_id")
	private Integer id;
	
	private String descripcion;
	private String nuuid;
	
	@JsonIgnore
	@OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
	private List<Jugador> jugadores = new ArrayList<>();
}
