package dsw.siderandinaMS.Compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsw.siderandinaMS.Compras.model.UnidadesMedida;

@Repository
public interface UnidadesMedidaRepository extends JpaRepository<UnidadesMedida, Integer>{

}
