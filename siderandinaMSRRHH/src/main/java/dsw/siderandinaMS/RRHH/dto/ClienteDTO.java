package dsw.siderandinaMS.RRHH.dto;

import dsw.siderandinaMS.RRHH.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Integer idCliente;
    private String ruc;
    private String razonSocial;
    private String direccion;
    private String telefono;
    private Integer idTipoCliente;
    private Integer idUsuario;
}
