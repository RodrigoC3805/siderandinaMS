package dsw.siderandinaMS.RRHH.dto;

import dsw.siderandinaMS.RRHH.model.Trabajador;
import dsw.siderandinaMS.RRHH.model.Usuario;
import lombok.Data;

@Data
public class RegisterWorkerRequest {
    private Trabajador trabajador;
    private Usuario usuario;
}
