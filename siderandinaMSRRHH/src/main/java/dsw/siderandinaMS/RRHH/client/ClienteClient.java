package dsw.siderandinaMS.RRHH.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient
public interface ClienteClient {
    @GetMapping("/api/cliente/")
}
