package dsw.siderandinaMS.Compras.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsw.siderandinaMS.Compras.dto.ComprobanteCompraRequest;
import dsw.siderandinaMS.Compras.dto.PedidoCompraRequest;
import dsw.siderandinaMS.Compras.dto.PedidoCompraResponse;
import dsw.siderandinaMS.Compras.model.ComprobanteCompra;
import dsw.siderandinaMS.Compras.model.DetalleCompra;
import dsw.siderandinaMS.Compras.model.EstadoPedido;
import dsw.siderandinaMS.Compras.model.Pago;
import dsw.siderandinaMS.Compras.model.PedidoCompra;
import dsw.siderandinaMS.Compras.model.Proveedor;
import dsw.siderandinaMS.Compras.model.TipoComprobante;
import dsw.siderandinaMS.Compras.repository.ComprobanteCompraRepository;
import dsw.siderandinaMS.Compras.repository.DetalleCompraRepository;
import dsw.siderandinaMS.Compras.repository.EstadoPedidoRepository;
import dsw.siderandinaMS.Compras.repository.PagoRepository;
import dsw.siderandinaMS.Compras.repository.PedidoCompraRepository;
import dsw.siderandinaMS.Compras.repository.ProveedorRepository;
import dsw.siderandinaMS.Compras.repository.TipoComprobanteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PedidoCompraService {
    @Autowired
    PedidoCompraRepository pedidoCompraRepository;
    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    EstadoPedidoRepository estadoPedidoRepository;
    @Autowired
    DetalleCompraRepository detalleCompraRepository;
    @Autowired
    ComprobanteCompraRepository comprobanteCompraRepository;
    @Autowired
    PagoRepository pagoRepository;
    @Autowired
    TipoComprobanteRepository tipoComprobanteRepository;

    public List<PedidoCompraResponse> listPedidosCompra() {
        return PedidoCompraResponse.fromEntities(pedidoCompraRepository.findAll());
    }

    public PedidoCompraResponse createPedidoCompra(PedidoCompraRequest request) {
        PedidoCompra pedidoCompra = PedidoCompra.builder()
                .fechaPedido(Timestamp.from(Instant.now()))
                .montoTotal(request.getMontoTotal())
                .proveedor(proveedorRepository.findById(request.getIdProveedor())
                        .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado")))
                .estadoPedido(estadoPedidoRepository.findById(request.getIdEstadoPedido())
                        .orElseThrow(() -> new EntityNotFoundException("Estado de pedido no encontrado")))
                .build();
        PedidoCompra savedPedido = pedidoCompraRepository.save(pedidoCompra);
        return PedidoCompraResponse.fromEntity(savedPedido);
    }

    @Transactional
    public PedidoCompra createPedidoCompraConDetalles(PedidoCompra pedidoCompra, List<DetalleCompra> detallesCompra,
        Pago pago, ComprobanteCompraRequest comprobanteCompraRequest) {
        //Colocar hora actual
        pedidoCompra.setFechaPedido(Timestamp.from(Instant.now()));
        comprobanteCompraRequest.setFechaEmision(Timestamp.from(Instant.now()));
        // 1. Validar y asociar EstadoPedido
        if (pedidoCompra.getEstadoPedido() == null || pedidoCompra.getEstadoPedido().getIdEstadoPedido() == null) {
            throw new RuntimeException("El objeto EstadoPedido o su ID no pueden ser nulos.");
        }
        // Buscar EstadoPedido en la base de datos
        EstadoPedido estadoPedido = estadoPedidoRepository.findById(pedidoCompra.getEstadoPedido().getIdEstadoPedido())
                .orElseThrow(() -> new RuntimeException(
                        "EstadoPedido no encontrado con el ID: " + pedidoCompra.getEstadoPedido().getIdEstadoPedido()));
        pedidoCompra.setEstadoPedido(estadoPedido);

        // 2. Validar y asociar Proveedor
        if (pedidoCompra.getProveedor() == null || pedidoCompra.getProveedor().getIdProveedor() == null) {
            throw new RuntimeException("El objeto Proveedor o su ID no pueden ser nulos.");
        }
        // Buscar Proveedor en la base de datos
        Proveedor proveedor = proveedorRepository.findById(pedidoCompra.getProveedor().getIdProveedor())
                .orElseThrow(() -> new RuntimeException(
                        "Proveedor no encontrado con el ID: " + pedidoCompra.getProveedor().getIdProveedor()));
        pedidoCompra.setProveedor(proveedor);

        // 3. Guardar PedidoCompra
        PedidoCompra savedPedido = pedidoCompraRepository.save(pedidoCompra);

        // 4. Asociar y guardar DetallesCompra
        for (DetalleCompra detalle : detallesCompra) {
            detalle.setPedidoCompra(savedPedido); // Asociar el pedido al detalle
            detalleCompraRepository.save(detalle);
        }

        // 5. Guardar el Pago (si existe)
        if (pago != null) {
            pagoRepository.save(pago);
        }

        // 6. Guardar el ComprobanteCompra (si existe)
        if (comprobanteCompraRequest != null) {
            TipoComprobante tipoComprobante = tipoComprobanteRepository.findById(1).get();
            comprobanteCompraRequest.setIdPedidoCompra(savedPedido.getIdPedidoCompra()); // Asociar el pedido al comprobante
            ComprobanteCompra comprobanteCompra = new ComprobanteCompra(
                comprobanteCompraRequest.getIdComprobanteCompra(),
                comprobanteCompraRequest.getNumeroComprobante(),    
                comprobanteCompraRequest.getFechaEmision(),
                pedidoCompra,
                pago,
                tipoComprobante
            );
            comprobanteCompraRepository.save(comprobanteCompra);
        }
        return savedPedido;
    }

    public List<PedidoCompraResponse> getPedidosByProveedor(Integer idProveedor) {
        return PedidoCompraResponse.fromEntities(
            pedidoCompraRepository.findByProveedor_IdProveedorOrderByFechaPedidoDesc(idProveedor)
        );
    }

    public List<PedidoCompraResponse> getPedidosByProveedorAndEstado(Integer idProveedor, Integer idEstadoPedido) {
        return PedidoCompraResponse.fromEntities(
            pedidoCompraRepository.findByProveedor_IdProveedorAndEstadoPedido_IdEstadoPedido(idProveedor, idEstadoPedido)
        );
    }

    @Transactional
    public PedidoCompraResponse actualizarEstadoPedido(Integer idPedidoCompra, Integer idEstadoPedido) {
        PedidoCompra pedido = pedidoCompraRepository.findById(idPedidoCompra)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        EstadoPedido estado = estadoPedidoRepository.findById(idEstadoPedido)
            .orElseThrow(() -> new RuntimeException("Estado de pedido no encontrado"));
        pedido.setEstadoPedido(estado);
        pedido = pedidoCompraRepository.save(pedido);
        return PedidoCompraResponse.fromEntity(pedido);
    }

    public List<PedidoCompraResponse> listPedidosCompraByEstado(Integer idEstadoPedido) {
        return PedidoCompraResponse.fromEntities(
            pedidoCompraRepository.findAll().stream()
                .filter(p -> p.getEstadoPedido().getIdEstadoPedido().equals(idEstadoPedido))
                .toList()
        );
    }
    public List<PedidoCompraResponse> listPedidosCompraEnviadosYEntregados() {
        return PedidoCompraResponse.fromEntities(
            pedidoCompraRepository.findAllPedidosEnviadosYEntregados()
        );
    }
}
