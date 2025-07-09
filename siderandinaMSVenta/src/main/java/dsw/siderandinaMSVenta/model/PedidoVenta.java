package dsw.siderandinaMSVenta.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido_venta")
public class PedidoVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_venta")
    private Integer idPedidoVenta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cotizacion")
    private Cotizacion cotizacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado_pedido")
    private EstadoPedido estadoPedido;

    @Column(name = "codigo_venta")
    private String codigoVenta;

    @Column(name = "fecha_pedido")
    private Timestamp fechaPedido;

    @Column(name = "direccion_entrega")
    private String direccionEntrega;
}
