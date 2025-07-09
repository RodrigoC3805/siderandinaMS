package dsw.siderandinaMS.Compras.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pedido_compra")
public class PedidoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido_compra")
    private Integer idPedidoCompra;
    @Column(name = "codigo_compra", nullable = false, updatable = false, insertable = false)
    private Integer codigoCompra;
    @Column(name="fecha_pedido")
    private Timestamp fechaPedido;
    @Column(name="monto_total")
    private Double montoTotal;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_proveedor", referencedColumnName = "id_proveedor", nullable = false)
    private Proveedor proveedor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado_pedido", referencedColumnName = "id_estado_pedido", nullable = false)
    private EstadoPedido estadoPedido;

}
