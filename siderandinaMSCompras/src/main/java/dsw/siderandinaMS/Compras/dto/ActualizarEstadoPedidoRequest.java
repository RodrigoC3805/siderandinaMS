package dsw.siderandinaMS.Compras.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarEstadoPedidoRequest {
    private Integer idPedidoCompra;
    private Integer idEstadoPedido;
}