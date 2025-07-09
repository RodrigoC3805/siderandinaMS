package dsw.siderandinaMS.Compras.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="unidades_medida")
public class UnidadesMedida {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_unidades_medida")
    private Integer idUnidadesMedida;
    private String descripcion;
}
