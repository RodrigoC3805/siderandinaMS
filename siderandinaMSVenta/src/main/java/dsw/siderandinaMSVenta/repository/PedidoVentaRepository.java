package dsw.siderandinaMSVenta.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import dsw.siderandinaMSVenta.model.PedidoVenta;

@Repository
public interface PedidoVentaRepository extends JpaRepository<PedidoVenta, Integer> {
    @Query("SELECT p FROM PedidoVenta p WHERE p.idPedidoVenta NOT IN (SELECT d.idPedidoVenta FROM Despacho d)")
    List<PedidoVenta> findPedidosVentaSinDespacho();

    List<PedidoVenta> findByCotizacion_Cliente_IdCliente(Integer idCliente);
}