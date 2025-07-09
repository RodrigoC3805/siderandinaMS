package dsw.siderandinaMSVenta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dsw.siderandinaMSVenta.dto.CotizacionRequest;
import dsw.siderandinaMSVenta.dto.CotizacionRequest.DetalleCotizacionRequest;
import dsw.siderandinaMSVenta.dto.CotizacionResponse;
import dsw.siderandinaMSVenta.service.CotizacionService;

@RestController
@RequestMapping(path = "api/cliente/cotizacion")
public class CotizacionController {
    @Autowired
    CotizacionService cotizacionService;

    @PostMapping
    public ResponseEntity<CotizacionResponse> crearCotizacion(@RequestBody CotizacionRequest request) {
        CotizacionResponse response = cotizacionService.crearCotizacion(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> listarCotizaciones() {
        return ResponseEntity.ok(cotizacionService.listarCotizaciones());
    }

    // Endpoint para cotizaciones por cliente
    @GetMapping("/por-cliente")
    public ResponseEntity<?> listarCotizacionesPorCliente(@RequestParam Integer idCliente) {
        return ResponseEntity.ok(cotizacionService.listarCotizacionesPorCliente(idCliente));
    }

    //Aceptar cotizacion
    @PutMapping("/actualizar-precios-estado")
    public ResponseEntity<?> actualizarPreciosYEstado(
            @RequestParam Integer idCotizacion,
            @RequestBody List<DetalleCotizacionRequest> detalles) {
        cotizacionService.actualizarPreciosYEstado(idCotizacion, detalles);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/rechazar-cotizacion")
    public ResponseEntity<?> rechazarCotizacion(
            @RequestParam Integer idCotizacion,
            @RequestBody List<DetalleCotizacionRequest> detalles) {
        cotizacionService.rechazarCotizacion(idCotizacion, detalles);
        return ResponseEntity.ok().build();
    }
}