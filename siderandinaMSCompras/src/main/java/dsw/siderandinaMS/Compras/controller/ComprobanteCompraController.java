package dsw.siderandinaMS.Compras.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsw.siderandinaMS.Compras.model.ComprobanteCompra;
import dsw.siderandinaMS.Compras.model.PedidoCompra;
import dsw.siderandinaMS.Compras.repository.ComprobanteCompraRepository;
import dsw.siderandinaMS.Compras.utils.ErrorResponse;

@RestController
@RequestMapping(path="api/almacen/comprobantecompra")
public class ComprobanteCompraController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ComprobanteCompraRepository comprobanteCompraRepository;

    @PostMapping("/find")
    public ResponseEntity<?> getComprobanteComprabyPedidoId(@RequestBody PedidoCompra pedidoCompra) {
        ComprobanteCompra comprobanteCompra = null;
        try {
            comprobanteCompra = comprobanteCompraRepository.findByPedidoCompra_IdPedidoCompra(pedidoCompra.getIdPedidoCompra());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (comprobanteCompra==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Comprobante not found").build());
        return ResponseEntity.ok(comprobanteCompra);
    }
}
