package dsw.siderandinaMS.RRHH.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String message;
}
