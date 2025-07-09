package dsw.siderandinaMSVenta.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Component
public class UsuarioClient {

    @Value("${msrrhh.url}")
    private String msrrhhUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public UsuarioResponse obtenerUsuarioPorId(Integer id) {
        return restTemplate.getForObject(msrrhhUrl + "/usuarios/" + id, UsuarioResponse.class);
    }

    public List<UsuarioResponse> listarUsuarios() {
        UsuarioResponse[] usuarios = restTemplate.getForObject(msrrhhUrl + "/usuarios", UsuarioResponse[].class);
        return Arrays.asList(usuarios);
    }
}