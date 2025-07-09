package dsw.siderandinaMS.Compras.dto;

import java.util.List;

import dsw.siderandinaMS.Compras.model.DetalleCompra;
import dsw.siderandinaMS.Compras.model.Pago;
import dsw.siderandinaMS.Compras.model.PedidoCompra;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoYDetallesDTO {
    private PedidoCompra pedidoCompra; // Información del pedido principal
    private List<DetalleCompra> detallesCompra; // Lista de detalles asociados al pedido
    private ComprobanteCompraRequest comprobanteCompraRequest; // Información del comprobante de compra
    private Pago pago; // Información del pago asociado al pedido
}