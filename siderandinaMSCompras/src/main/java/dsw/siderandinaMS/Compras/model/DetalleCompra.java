package dsw.siderandinaMS.Compras.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detalle_compra")
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_detalle_compra")
    private Integer idDetalleCompra;
    private Double cantidad;
    @Column(name="cantidad_recibida")
    private Double cantidadRecibida;
    @Column(name="monto_subtotal_linea")
    private Double montoSubtotalLinea;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_pedido_compra", referencedColumnName = "id_pedido_compra")
    private PedidoCompra pedidoCompra;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto producto;
}
