package dsw.siderandinaMS.RRHH.repository;

import dsw.siderandinaMS.RRHH.model.MotivoJustificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoJustificacionRepository extends JpaRepository<MotivoJustificacion, Integer> {
}
