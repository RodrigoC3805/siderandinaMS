package dsw.siderandinaMS.RRHH.controller;

import dsw.siderandinaMS.RRHH.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rrhh/usuario")
public class UsuarioController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/findbyid")
    public ResponseEntity<?> findById (@RequestParam Integer id){
        try {
            return ResponseEntity.ok(usuarioService.findById(id));
        } catch (Exception e){
            logger.error("Error inesperado al obtener el usuario por su id");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/findbyemail")
    public ResponseEntity<?> findByEmail (@RequestParam String email){
        try {
            return ResponseEntity.ok(usuarioService.findByEmail(email));
        } catch (Exception e){
            logger.error("Error inesperado al obtener el usuario por su email");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
