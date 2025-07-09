package dsw.siderandinaMS.Compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsw.siderandinaMS.Compras.dto.PagoRequest;
import dsw.siderandinaMS.Compras.model.Pago;
import dsw.siderandinaMS.Compras.service.PagoService;

@RestController
@RequestMapping("/api/cliente/pago")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<?> registrarPago(@RequestBody PagoRequest request) {
        try {
            Pago pago = pagoService.registrarPago(request.getIdMetodoPago(), request.getMontoPagado());
            return ResponseEntity.ok(pago);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al registrar pago: " + e.getMessage());
        }
    }
}