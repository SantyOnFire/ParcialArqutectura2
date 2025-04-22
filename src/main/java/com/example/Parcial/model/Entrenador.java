package com.example.Parcial.model;

import jakarta.persistence.*;

@Entity
public class Entrenador {

    @Id
    private Integer id_entrenador;

    private String nombre;
    private String especialidad;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;
}
