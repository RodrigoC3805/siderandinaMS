package dsw.siderandinaMS.RRHH.service;

import dsw.siderandinaMS.RRHH.dto.UsuarioDTO;
import dsw.siderandinaMS.RRHH.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioDTO findById (Integer id){
        return UsuarioDTO.fromEntity(usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
    }
    public UsuarioDTO findByEmail (String email){
        return UsuarioDTO.fromEntity(usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
    }

}
