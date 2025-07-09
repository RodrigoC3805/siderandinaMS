package dsw.siderandinaMS.Compras.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tipo_comprobante")
public class TipoComprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tipo_comprobante")
    private Integer idTipoComprobante;
    private String descripcion;
}
