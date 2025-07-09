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
public class UsuarioDTO {
    private Integer idUsuario;
    private String email;
    private String tipoUsuario;

    public static UsuarioDTO fromEntity(Usuario usuario){
        return UsuarioDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .email(usuario.getEmail())
                .tipoUsuario(usuario.getTipoUsuario().getDescripcion())
                .build();
    }
}
