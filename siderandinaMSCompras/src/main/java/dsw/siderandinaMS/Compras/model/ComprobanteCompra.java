package dsw.siderandinaMS.Compras.model;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="comprobante_compra")
public class ComprobanteCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comprobante_compra")
    private Integer idComprobanteCompra;
    @Column(name = "numero_comprobante", nullable = false, updatable = false, insertable = false)
    private Integer numeroComprobante;
    @Column(name = "fecha_emision")
    private Timestamp fechaEmision;

    @OneToOne
    @JoinColumn(name = "id_pedido_compra", unique = true)
    private PedidoCompra pedidoCompra;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_pago", referencedColumnName = "id_pago")
    private Pago pago;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_tipo_comprobante", referencedColumnName = "id_tipo_comprobante")
    private TipoComprobante tipoComprobante;
}
