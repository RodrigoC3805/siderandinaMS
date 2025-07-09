package dsw.siderandinaMS.RRHH.dto;

import dsw.siderandinaMS.RRHH.model.Usuario;
import lombok.Data;

@Data
public class RegisterProveedorRequest {
    Usuario usuario;
    ProveedorDTO proveedor;
}
