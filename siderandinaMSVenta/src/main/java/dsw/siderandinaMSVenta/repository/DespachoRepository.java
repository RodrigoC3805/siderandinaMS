package dsw.siderandinaMSVenta.repository;

import dsw.siderandinaMSVenta.model.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DespachoRepository extends JpaRepository<Despacho, Integer> {
    List<Despacho> findByEstado(String estado);
}