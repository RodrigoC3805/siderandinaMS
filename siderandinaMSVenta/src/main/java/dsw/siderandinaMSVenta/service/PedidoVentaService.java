package dsw.siderandinaMSVenta.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsw.siderandinaMSVenta.dto.PedidoVentaRequest;
import dsw.siderandinaMSVenta.dto.PedidoVentaResponse;
import dsw.siderandinaMSVenta.model.Cotizacion;
import dsw.siderandinaMSVenta.model.EstadoPedido;
import dsw.siderandinaMSVenta.model.PedidoVenta;
import dsw.siderandinaMSVenta.repository.CotizacionRepository;
import dsw.siderandinaMSVenta.repository.EstadoPedidoRepository;
import dsw.siderandinaMSVenta.repository.PedidoVentaRepository;

@Service
public class PedidoVentaService {
    @Autowired
    PedidoVentaRepository pedidoVentaRepository;
    @Autowired
    CotizacionRepository cotizacionRepository;
    @Autowired
    EstadoPedidoRepository estadoPedidoRepository;

    public PedidoVentaResponse crearPedidoVenta(PedidoVentaRequest request) {
        Cotizacion cotizacion = cotizacionRepository.findById(request.getIdCotizacion())
            .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));
        EstadoPedido estadoPedido = estadoPedidoRepository.findById(request.getIdEstadoPedido())
            .orElseThrow(() -> new RuntimeException("Estado de pedido no encontrado"));

        PedidoVenta pedidoVenta = PedidoVenta.builder()
            .cotizacion(cotizacion)
            .estadoPedido(estadoPedido)
            .codigoVenta("VEN-" + System.currentTimeMillis())
            .fechaPedido(new Timestamp(System.currentTimeMillis()))
            .direccionEntrega(request.getDireccionEntrega())
            .build();

        pedidoVenta = pedidoVentaRepository.save(pedidoVenta);
        return PedidoVentaResponse.fromEntity(pedidoVenta);
    }

    public List<PedidoVentaResponse> listarPedidosPorCliente(Integer idCliente) {
        return pedidoVentaRepository.findByCotizacion_Cliente_IdCliente(idCliente)
            .stream()
            .map(PedidoVentaResponse::fromEntity)
            .toList();
    }

    public List<PedidoVentaResponse> listarPedidosVentaSinDespacho() {
        List<PedidoVenta> pedidos = pedidoVentaRepository.findPedidosVentaSinDespacho();
        return pedidos.stream().map(PedidoVentaResponse::fromEntity).toList();
    }
    
}
