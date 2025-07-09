package dsw.siderandinaMS.Compras.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsw.siderandinaMS.Compras.model.DetalleCompra;
import dsw.siderandinaMS.Compras.model.PedidoCompra;
import dsw.siderandinaMS.Compras.repository.DetalleCompraRepository;
import dsw.siderandinaMS.Compras.utils.ErrorResponse;

@RestController
@RequestMapping(path="api/almacen/detallecompra")
public class DetalleCompraController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DetalleCompraRepository detalleCompraRepository;

    @PostMapping("/find")
    public ResponseEntity<?> getDetalleComprabyCompraId(@RequestBody PedidoCompra pedidoCompra) {
        List<DetalleCompra> listaDetalleCompra = null;
        try{
            listaDetalleCompra = detalleCompraRepository.findByPedidoCompra_IdPedidoCompra(pedidoCompra.getIdPedidoCompra());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaDetalleCompra.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("DetalleCompra not found").build());
        return ResponseEntity.ok(listaDetalleCompra);
    }
    
    @PostMapping
    public ResponseEntity<?> createDetallesCompra(@RequestBody List<DetalleCompra> detallesCompra) {
        try {
            List<DetalleCompra> savedDetalles = detalleCompraRepository.saveAll(detallesCompra);
            return ResponseEntity.ok(savedDetalles);
        } catch (Exception e) {
            logger.error("Error al crear detalles de compra", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.builder().message("Error al crear detalles de compra").build());
        }
    }
}
