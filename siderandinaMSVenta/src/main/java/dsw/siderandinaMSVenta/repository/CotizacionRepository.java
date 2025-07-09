package dsw.siderandinaMSVenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dsw.siderandinaMSVenta.model.Cotizacion;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {
}