package dsw.siderandinaMS.Compras.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="estado_pedido")
public class EstadoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_estado_pedido")
    private Integer idEstadoPedido;
    private String descripcion;
}
