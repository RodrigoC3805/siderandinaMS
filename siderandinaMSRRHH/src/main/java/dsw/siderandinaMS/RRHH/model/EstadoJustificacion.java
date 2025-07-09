package dsw.siderandinaMS.RRHH.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="estado_justificacion")
public class EstadoJustificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_estado_jus")
    private Integer idEstadoJus;
    private String descripcion;
}
