package dsw.siderandinaMS.RRHH.controller;

import dsw.siderandinaMS.RRHH.dto.AsistenciaRequest;
import dsw.siderandinaMS.RRHH.dto.ReporteAsistenciaDTO;
import dsw.siderandinaMS.RRHH.dto.ReporteHorasExtrasDTO;
import dsw.siderandinaMS.RRHH.dto.ReportePuntualidadDTO;
import dsw.siderandinaMS.RRHH.model.AsistenciaDiaria;
import dsw.siderandinaMS.RRHH.service.AsistenciaService;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rrhh/asistencia")
public class AsistenciaController {
    @Autowired
    private AsistenciaService asistenciaService;

    @PostMapping("/ingreso")
    public AsistenciaDiaria registrarIngreso(@RequestBody AsistenciaRequest req) {
        return asistenciaService.registrarAsistenciaIngreso(req);
    }

    @PostMapping("/salida")
    public AsistenciaDiaria registrarSalida(@RequestBody AsistenciaRequest req) {
        return asistenciaService.registrarAsistenciaSalida(req);
    }

    @GetMapping
    public ResponseEntity<?> listarAsistencias() {
        return ResponseEntity.ok(asistenciaService.listarAsistencias());
    }

    @GetMapping("/por-documento")
    public ResponseEntity<?> listarAsistenciasPorDocumento(@RequestParam String numeroDocumento) {
        List<AsistenciaDiaria> asistencias = asistenciaService.listarAsistenciasPorDocumento(numeroDocumento);
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/excel")
    public void exportarAsistenciasExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=asistencias.xlsx");
        asistenciaService.exportarAsistenciasExcel(response.getOutputStream());
    }

    @GetMapping("/reporte-horas")
    public List<ReporteAsistenciaDTO> reporteHorasTrabajadas(
        @RequestParam(required = false) String fechaInicio,
        @RequestParam(required = false) String fechaFin
    ) {
        return asistenciaService.obtenerReporteHorasTrabajadas(fechaInicio, fechaFin);
    }

    @GetMapping("/reporte-puntualidad")
    public ReportePuntualidadDTO reportePuntualidad(
        @RequestParam(required = false) String fechaInicio,
        @RequestParam(required = false) String fechaFin
    ) {
        return asistenciaService.obtenerReportePuntualidad(fechaInicio, fechaFin);
    }

    @GetMapping("/reporte-horas-extras")
    public List<ReporteHorasExtrasDTO> reporteHorasExtras(
        @RequestParam(required = false) String fechaInicio,
        @RequestParam(required = false) String fechaFin
    ) {
    return asistenciaService.obtenerReporteHorasExtras(fechaInicio, fechaFin);
    }


}