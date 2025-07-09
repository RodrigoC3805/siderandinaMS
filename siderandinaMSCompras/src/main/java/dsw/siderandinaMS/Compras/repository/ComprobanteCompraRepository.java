package dsw.siderandinaMS.Compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsw.siderandinaMS.Compras.model.ComprobanteCompra;

@Repository
public interface ComprobanteCompraRepository extends JpaRepository<ComprobanteCompra, Integer> {
    ComprobanteCompra findByPedidoCompra_IdPedidoCompra(Integer idPedidoCompra);

}
