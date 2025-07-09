package dsw.siderandinaMS.RRHH.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsw.siderandinaMS.RRHH.model.TipoTrabajador;

@Repository
public interface TipoTrabajadorRepository extends JpaRepository<TipoTrabajador, Integer> {

}
