package com.example.demo.Entity;

import java.util.Date;
import java.util.List;
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
import lombok.Data;

@Data
@Entity
public class Jugador {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Date fecha_Nacimiento;
	private String descripcion;
	private Integer clase_id;
	private String genero;
	private Integer rango_id;	
	private String nuuid;
	
	@ManyToOne
    @JoinColumn(name = "rango", nullable = false)
    private Rango  rango;
	

	@ManyToOne
    @JoinColumn(name = "clase", nullable = false)
    private Clase  clase;
	
	 @ManyToMany
	    @JoinTable(
	        name = "jugador_habilidad",
	        joinColumns = @JoinColumn(name = "jugador_id"),
	        inverseJoinColumns = @JoinColumn(name = "habilidad_id")
	    )
	    private List<Habilidad> habilidades;
	
	public Jugador() {
		
	}
	

	
	

}
