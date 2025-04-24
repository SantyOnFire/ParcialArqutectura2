package com.example.Parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Parcial.model.Equipo;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    // 1. Equipos fundados después de cierta fecha
    @Query(value = "SELECT * FROM equipo WHERE fundacion > :fecha", nativeQuery = true)
    List<Equipo> findByFundacionAfter(@Param("fecha") LocalDate fecha);

    // 2. Equipos por ciudad
    @Query(value = "SELECT * FROM equipo WHERE ciudad = :ciudad", nativeQuery = true)
    List<Equipo> findByCiudad(@Param("ciudad") String ciudad);

    // 3. Equipos ordenados por antigüedad
    @Query(value = "SELECT * FROM equipo ORDER BY fundacion ASC", nativeQuery = true)
    List<Equipo> findAllOrderByFundacion();

    // 4. Conteo de equipos por ciudad
    @Query(value = "SELECT ciudad, COUNT(*) as total FROM equipo GROUP BY ciudad", nativeQuery = true)
    List<Object[]> countByCiudad();
}