package dsw.siderandinaMS.RRHH.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsw.siderandinaMS.RRHH.model.EstadoJustificacion;

@Repository
public interface EstadoJustificacionRepository extends JpaRepository<EstadoJustificacion, Integer>{
    
}
