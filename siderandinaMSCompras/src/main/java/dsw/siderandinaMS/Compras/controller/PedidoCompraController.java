package dsw.siderandinaMS.Compras.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dsw.siderandinaMS.Compras.dto.ActualizarEstadoPedidoRequest;
import dsw.siderandinaMS.Compras.dto.ComprobanteCompraRequest;
import dsw.siderandinaMS.Compras.dto.PedidoCompraRequest;
import dsw.siderandinaMS.Compras.dto.PedidoCompraResponse;
import dsw.siderandinaMS.Compras.dto.PedidoYDetallesDTO;
import dsw.siderandinaMS.Compras.model.DetalleCompra;
import dsw.siderandinaMS.Compras.model.Pago;
import dsw.siderandinaMS.Compras.model.PedidoCompra;
import dsw.siderandinaMS.Compras.service.PedidoCompraService;
import dsw.siderandinaMS.Compras.utils.ErrorResponse;

@RestController
@RequestMapping(path="api/almacen/pedidocompra")
public class PedidoCompraController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PedidoCompraService pedidoCompraService;

    @GetMapping
    public ResponseEntity<?> getPedidosCompra(@RequestParam(required = false) Integer idEstadoPedido) {
        List<PedidoCompraResponse> listaPedidoCompraResponse;
        if (idEstadoPedido != null) {
            listaPedidoCompraResponse = pedidoCompraService.listPedidosCompraByEstado(idEstadoPedido);
        } else {
            listaPedidoCompraResponse = pedidoCompraService.listPedidosCompra();
        }
        return ResponseEntity.ok(listaPedidoCompraResponse == null ? List.of() : listaPedidoCompraResponse);
    }
    @PostMapping
    public ResponseEntity<?> createPedidoCompra(@RequestBody PedidoCompraRequest pedidoCompraRequest) {
        PedidoCompraResponse pedidoCompraResponse;
        try {
            pedidoCompraResponse = pedidoCompraService.createPedidoCompra(pedidoCompraRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        if (pedidoCompraResponse == null) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("PedidoCompra not inserted").build());
        return ResponseEntity.ok(pedidoCompraResponse);
    }
    @PostMapping("/comprar")
    public ResponseEntity<?> crearPedidoConDetalles(@RequestBody PedidoYDetallesDTO pedidoYDetallesDTO) {
        try {
            PedidoCompra pedido = pedidoYDetallesDTO.getPedidoCompra();
            List<DetalleCompra> detalles = pedidoYDetallesDTO.getDetallesCompra();
            Pago pago = pedidoYDetallesDTO.getPago();
            ComprobanteCompraRequest comprobante = pedidoYDetallesDTO.getComprobanteCompraRequest();

            PedidoCompra savedPedido = pedidoCompraService.createPedidoCompraConDetalles(
                    pedido, detalles, pago, comprobante);

            return ResponseEntity.ok(savedPedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el pedido con sus detalles: " + e.getMessage());
        }
    }

    // Consultar pedidos por proveedor (con filtros)
    @GetMapping("/proveedor")
    public ResponseEntity<?> getPedidosByProveedor(
            @RequestParam Integer idProveedor,
            @RequestParam(required = false) Integer idEstadoPedido) {
        try {
            List<PedidoCompraResponse> pedidos;
            if (idEstadoPedido != null) {
                pedidos = pedidoCompraService.getPedidosByProveedorAndEstado(idProveedor, idEstadoPedido);
            } else {
                pedidos = pedidoCompraService.getPedidosByProveedor(idProveedor);
            }
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al consultar pedidos: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar-estado")
    public ResponseEntity<?> actualizarEstadoPedido(@RequestBody ActualizarEstadoPedidoRequest request) {
        try {
            PedidoCompraResponse response = pedidoCompraService.actualizarEstadoPedido(
                    request.getIdPedidoCompra(), request.getIdEstadoPedido());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar estado: " + e.getMessage());
        }
    }
    @GetMapping("/pedidosenviados-entregados")
    public ResponseEntity<?> getPedidosEnviadosYEntregados() {
        try {
            List<PedidoCompraResponse> pedidos = pedidoCompraService.listPedidosCompraEnviadosYEntregados();
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            logger.error("Error al obtener pedidos enviados y entregados", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.builder().message("Error al obtener pedidos enviados y entregados").build());
        }
    }
}
