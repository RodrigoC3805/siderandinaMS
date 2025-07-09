package dsw.siderandinaMS.Compras.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dsw.siderandinaMS.Compras.model.Proveedor;
import dsw.siderandinaMS.Compras.repository.ProveedorRepository;
import dsw.siderandinaMS.Compras.utils.ErrorResponse;

@RestController
@RequestMapping(path="api/v1/proveedor")
public class ProveedorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ProveedorRepository proveedorRepository;

    @GetMapping
    public ResponseEntity<?> getProveedores() {
        List<Proveedor> listaProveedores = null;
        try {
            listaProveedores = proveedorRepository.findAll();
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaProveedores.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Proveedor not found"));
        return ResponseEntity.ok(listaProveedores);
    }

    @GetMapping("/por-email")
    public ResponseEntity<?> getProveedorByEmail(@RequestParam String email) {
        Proveedor proveedor = proveedorRepository.findByEmail(email);
        if (proveedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado para este email");
        }
        return ResponseEntity.ok(proveedor);
    }
    @GetMapping("/findproveedorbyuserid")
    public ResponseEntity<?> findByUserId(@RequestParam Integer idUsuario) {
        Optional<Proveedor> proveedor = null;
        try {
            proveedor = proveedorRepository.findByUsuarioIdUsuario(idUsuario);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (proveedor.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Proveedor not found").build());
        return ResponseEntity.ok(proveedor.get());
    }
}