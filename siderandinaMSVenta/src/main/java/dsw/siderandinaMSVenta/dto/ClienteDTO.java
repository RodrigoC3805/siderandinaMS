package dsw.siderandinaMSVenta.dto;

import dsw.siderandinaMS.RRHH.model.Usuario;
import dsw.siderandinaMSVenta.model.Cliente;
import dsw.siderandinaMSVenta.repository.TipoClienteRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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

    public static ClienteDTO fromEntity(Cliente cliente){
        return ClienteDTO.builder()
                .idCliente(cliente.getIdCliente())
                .ruc(cliente.getRuc())
                .razonSocial(cliente.getRazonSocial())
                .direccion(cliente.getDireccion())
                .telefono(cliente.getTelefono())
                .idTipoCliente(cliente.getTipoCliente().getIdTipoCliente())
                .idUsuario(cliente.getUsuario().getIdUsuario())
                .build();
    }
    public static Cliente toEntity(ClienteDTO clienteDTO){
        TipoClienteRepository tipoClienteRepository;
        return Cliente.builder()
                .idCliente(clienteDTO.getIdCliente())
                .ruc(clienteDTO.getRuc())
                .razonSocial(clienteDTO.getRazonSocial())
                .direccion(clienteDTO.getDireccion())
                .telefono(clienteDTO.getTelefono())
                .idTipoCliente(tipoClienteRepository.findById(clienteDTO.getIdTipoCliente()))
                .idUsuario(clienteDTO.getUsuario().getIdUsuario())
                .build();
    }
}
