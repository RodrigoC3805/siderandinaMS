package dsw.siderandinaMS.Compras.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadesMedidaRequest {
    private Integer idUnidadesMedida;
    private String descripcion;
}