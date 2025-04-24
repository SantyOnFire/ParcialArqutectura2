package com.example.Parcial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Parcial.model.Entrenador;
@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {

    // 1. Entrenadores por especialidad
    @Query(value = "SELECT * FROM entrenador WHERE especialidad = :especialidad", nativeQuery = true)
    List<Entrenador> findByEspecialidad(@Param("especialidad") String especialidad);

    // 2. Entrenadores por equipo
    @Query(value = "SELECT * FROM entrenador WHERE id_equipo = :equipoId", nativeQuery = true)
    List<Entrenador> findByEquipoId(@Param("equipoId") Long equipoId);

    // 3. Conteo de entrenadores por especialidad
    @Query(value = "SELECT especialidad, COUNT(*) as total FROM entrenador GROUP BY especialidad", nativeQuery = true)
    List<Object[]> countByEspecialidad();

    // 4. Entrenadores con nombres que contengan texto
    @Query(value = "SELECT * FROM entrenador WHERE nombre LIKE %:texto%", nativeQuery = true)
    List<Entrenador> findByNombreContaining(@Param("texto") String texto);
}