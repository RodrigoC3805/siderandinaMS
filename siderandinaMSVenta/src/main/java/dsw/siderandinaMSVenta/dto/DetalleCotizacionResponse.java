package dsw.siderandinaMSVenta.dto;

import java.util.List;
import java.util.stream.Collectors;


import dsw.siderandinaMSVenta.model.DetalleCotizacion;
import dsw.siderandinaMSVenta.model.Producto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCotizacionResponse {
    private Integer idDetalleCotizacion;
    private Producto producto;
    private Double cantidad;
    private Double precioCotizado;
    private Double montoSubtotalLinea;
    public static DetalleCotizacionResponse fromEntity(DetalleCotizacion detalleCotizacion) {
        return DetalleCotizacionResponse.builder()
                .idDetalleCotizacion(detalleCotizacion.getIdDetalleCotizacion())
                .producto(detalleCotizacion.getProducto())
                .cantidad(detalleCotizacion.getCantidad())
                .precioCotizado(detalleCotizacion.getPrecioCotizado())
                .montoSubtotalLinea(detalleCotizacion.getMontoSubtotalLinea())
                .build();
    }
    public static List<DetalleCotizacionResponse> fromEntities(List<DetalleCotizacion> listaDetalleCotizacion){
        return listaDetalleCotizacion.stream()
                .map(DetalleCotizacionResponse::fromEntity)
                .collect(Collectors.toList());
    }
}