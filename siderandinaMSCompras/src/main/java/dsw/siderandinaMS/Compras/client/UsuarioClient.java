package dsw.siderandinaMS.Compras.client;

import dsw.siderandinaMS.Compras.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "siderandinaMSRRHH") // nombre registrado en Eureka
public interface UsuarioClient {
    @GetMapping("/api/rrhh/usuario/findbyid")
    UsuarioDTO findById(@RequestParam("id") Integer idUsuario);
}