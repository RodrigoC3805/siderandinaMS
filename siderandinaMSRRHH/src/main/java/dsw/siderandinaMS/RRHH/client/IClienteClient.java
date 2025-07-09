package dsw.siderandinaMS.RRHH.client;

import dsw.siderandinaMS.RRHH.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient (name="siderandinaMSVenta", url="localhost:8082/api/cliente")
public interface IClienteClient {
    @GetMapping("/save")
    ClienteDTO save(@RequestBody ClienteDTO clienteDTO);
    @GetMapping("existsbyruc")
    boolean existsByRuc(@RequestParam String ruc);
}
