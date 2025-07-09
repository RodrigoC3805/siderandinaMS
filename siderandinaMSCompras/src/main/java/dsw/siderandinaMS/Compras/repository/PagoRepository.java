package dsw.siderandinaMS.Compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsw.siderandinaMS.Compras.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer>{

}
