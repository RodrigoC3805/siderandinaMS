package dsw.siderandinaMS.RRHH.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MotivoJustificacionRequest {
    private Integer idMotivoJustificacion;
    private String descripcion;
}
