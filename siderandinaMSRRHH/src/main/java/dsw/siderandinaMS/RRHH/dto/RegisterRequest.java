package dsw.siderandinaMS.RRHH.dto;

import dsw.siderandinaMS.RRHH.dto.ClienteDTO;
import dsw.siderandinaMS.RRHH.model.Usuario;
import lombok.Data;

@Data
public class RegisterRequest {
    private Usuario usuario;
    private ClienteDTO clienteDTO;
}
