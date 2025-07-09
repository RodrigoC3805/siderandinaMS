package dsw.siderandinaMS.RRHH.service;

import dsw.siderandinaMS.RRHH.client.IClienteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dsw.siderandinaMS.RRHH.dto.AuthResponse;
import dsw.siderandinaMS.RRHH.dto.LoginRequest;
import dsw.siderandinaMS.RRHH.dto.RegisterProveedorRequest;
import dsw.siderandinaMS.RRHH.dto.RegisterRequest;
import dsw.siderandinaMS.RRHH.dto.RegisterWorkerRequest;
import dsw.siderandinaMS.RRHH.repository.TrabajadorRepository;
import dsw.siderandinaMS.RRHH.repository.UsuarioRepository;
import dsw.siderandinaMS.RRHH.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private IClienteClient clienteClient;
    @Autowired
    private final TrabajadorRepository trabajadorRepository;

    //private final ProveedorRepository proveedorRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtUtil jwtService;
    
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (clienteClient.existsByRuc(request.getClienteDTO().getRuc())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese RUC");
        }
        if (usuarioRepository.existsByEmail(request.getUsuario().getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email");
        }
        request.getUsuario().setPassword(passwordEncoder.encode(request.getUsuario().getPassword()));
        request.getUsuario().getTipoUsuario().setIdTipoUsuario(7);
        usuarioRepository.save(request.getUsuario());
        request.getClienteDTO().setIdUsuario(request.getUsuario().getIdUsuario());
        request.getClienteDTO().setIdTipoCliente(1);;
        clienteClient.save(request.getClienteDTO());

        String jwtToken = jwtService.generateToken(request.getUsuario());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
    @Transactional
    public AuthResponse registerWorker(RegisterWorkerRequest request) {
        if (trabajadorRepository.existsByNumeroDocumento(request.getTrabajador().getNumeroDocumento()))
            throw new IllegalArgumentException("Ya existe un trabajador con ese número de documento");
        if (usuarioRepository.existsByEmail(request.getUsuario().getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email");
        }
        if (request.getUsuario().getPassword() == null) {
            throw new IllegalArgumentException("La contraseña no puede ser nula");
        }
        request.getUsuario().setPassword(passwordEncoder.encode(request.getUsuario().getPassword()));
        usuarioRepository.save(request.getUsuario());
        request.getTrabajador().setUsuario(request.getUsuario());
        trabajadorRepository.save(request.getTrabajador());
        String jwtToken = jwtService.generateToken(request.getUsuario());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
    /*
    @Transactional
    public AuthResponse registerProveedor(RegisterProveedorRequest request) {
        if (proveedorRepository.existsByRuc(request.getProveedor().getRuc()))
            throw new IllegalArgumentException("Ya existe un proveedor con ese ruc");
        if (usuarioRepository.existsByEmail(request.getUsuario().getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email");
        }
        if (request.getUsuario().getPassword() == null) {
            throw new IllegalArgumentException("La contraseña no puede ser nula");
        }
        request.getUsuario().setPassword(passwordEncoder.encode(request.getUsuario().getPassword()));
        usuarioRepository.save(request.getUsuario());
        request.getProveedor().setUsuario(request.getUsuario());
        proveedorRepository.save(request.getProveedor());
        String jwtToken = jwtService.generateToken(request.getUsuario());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
    */
    public AuthResponse login(LoginRequest request) {
        var usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        String jwtToken = jwtService.generateToken(usuario);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
