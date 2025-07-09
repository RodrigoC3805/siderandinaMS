package dsw.siderandinaMS.Compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsw.siderandinaMS.Compras.model.TipoComprobante;

@Repository
public interface TipoComprobanteRepository extends JpaRepository<TipoComprobante, Integer>{

}
