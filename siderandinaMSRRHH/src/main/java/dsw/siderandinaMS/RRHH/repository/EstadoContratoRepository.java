package dsw.siderandinaMS.RRHH.repository;

import dsw.siderandinaMS.RRHH.model.EstadoContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoContratoRepository extends JpaRepository<EstadoContrato, Integer> {
}