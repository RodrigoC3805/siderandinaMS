package dsw.siderandinaMS.RRHH.controller;

import dsw.siderandinaMS.RRHH.model.Planilla;
import dsw.siderandinaMS.RRHH.dto.DetallePlanillaResponse;
import dsw.siderandinaMS.RRHH.dto.PlanillaResumenResponse;
import dsw.siderandinaMS.RRHH.model.DetallePlanilla;
import dsw.siderandinaMS.RRHH.service.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dsw.siderandinaMS.RRHH.dto.DetallePlanillaResponse;

import java.util.List;

@RestController
@RequestMapping("/api/rrhh/planilla")
public class PlanillaController {
    @Autowired
    private PlanillaService planillaService;

    // Generar planilla mensual
    @PostMapping("/generar")
    public Planilla generarPlanilla(@RequestBody dsw.siderandinaMS.RRHH.dto.PlanillaRequest request) {
        return planillaService.generarPlanillaMensual(request.getMes(), request.getAnio());
    }

    // Ver detalle de una planilla
    @GetMapping("/{idPlanilla}/detalle")
    public List<DetallePlanillaResponse> listarDetallePlanilla(@PathVariable Integer idPlanilla) {
        return planillaService.listarDetallePlanilla(idPlanilla);
    }
    // Ver históricos de planillas 
    @GetMapping
    public List<PlanillaResumenResponse> listarPlanillasResumen(@RequestParam(required = false) Integer anio) {
        return planillaService.listarPlanillasResumen(anio);
    }
}