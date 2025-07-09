package dsw.siderandinaMS.RRHH.dto;

import dsw.siderandinaMS.RRHH.model.AsistenciaDiaria;
import dsw.siderandinaMS.RRHH.model.EstadoJustificacion;
import dsw.siderandinaMS.RRHH.model.Justificacion;
import dsw.siderandinaMS.RRHH.model.MotivoJustificacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JustificacionResponse {
    private Integer idJustificacion;
    private AsistenciaDiaria asistenciaDiaria;
    private MotivoJustificacion motivoJustificacion;
    private LocalDate fechaSolicitud;
    private EstadoJustificacion estadoJustificacion;

    public static JustificacionResponse fromEntity(Justificacion justificacion){
        return JustificacionResponse.builder()
                .idJustificacion(justificacion.getIdJustificacion())
                .fechaSolicitud(justificacion.getFechaSolicitud())
                .asistenciaDiaria(justificacion.getAsistenciaDiaria())
                .motivoJustificacion(justificacion.getMotivoJustificacion())
                .estadoJustificacion(justificacion.getEstadoJustificacion())
                .build();
    }

    public static List<JustificacionResponse> fromEntities(List<Justificacion> justificacion) {
        return justificacion.stream()
                .map(JustificacionResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
