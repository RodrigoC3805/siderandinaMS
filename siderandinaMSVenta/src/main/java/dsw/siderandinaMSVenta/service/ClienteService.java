package dsw.siderandinaMSVenta.service;

import java.util.Optional;

import dsw.siderandinaMSVenta.client.UsuarioClient;
import dsw.siderandinaMSVenta.dto.ClienteDTO;
import dsw.siderandinaMSVenta.repository.TipoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsw.siderandinaMSVenta.model.Cliente;
import dsw.siderandinaMSVenta.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TipoClienteRepository tipoClienteRepository;
    @Autowired
    private UsuarioClient usuarioClient;

    public Optional<Cliente> findByIdUsuario(Integer idUsuario) {
        return clienteRepository.findByIdUsuario(idUsuario);
    }
    public boolean existsByRUC(String RUC){
        return clienteRepository.existsByRuc(RUC);
    }
    public Cliente save(ClienteDTO clienteDTO){
        Cliente cliente = Cliente.builder()
                .idCliente(clienteDTO.getIdCliente())
                .ruc(clienteDTO.getRuc())
                .razonSocial(clienteDTO.getRazonSocial())
                .direccion(clienteDTO.getDireccion())
                .telefono(clienteDTO.getTelefono())
                .tipoCliente(tipoClienteRepository.findById(clienteDTO.getIdTipoCliente()).get())
                .idUsuario(clienteDTO.getIdUsuario())
                .build();
        return clienteRepository.save(cliente);
    }
}
