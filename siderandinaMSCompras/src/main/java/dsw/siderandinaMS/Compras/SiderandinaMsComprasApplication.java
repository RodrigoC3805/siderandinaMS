package dsw.siderandinaMS.Compras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SiderandinaMsComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiderandinaMsComprasApplication.class, args);
	}

}
