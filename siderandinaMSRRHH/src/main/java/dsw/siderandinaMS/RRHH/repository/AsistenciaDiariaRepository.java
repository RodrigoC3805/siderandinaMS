package dsw.siderandinaMS.RRHH.repository;

import dsw.siderandinaMS.RRHH.model.AsistenciaDiaria;
import dsw.siderandinaMS.RRHH.model.Trabajador;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaDiariaRepository extends JpaRepository<AsistenciaDiaria, Integer> {
    // Busca la asistencia de un trabajador en una fecha específica
    Optional<AsistenciaDiaria> findByTrabajadorAndFecha(Trabajador trabajador, LocalDate fecha);
    List<AsistenciaDiaria> findByTrabajador(Trabajador trabajador);
}
