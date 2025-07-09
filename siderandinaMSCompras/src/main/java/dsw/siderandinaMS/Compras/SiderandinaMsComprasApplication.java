package dsw.siderandinaMS.Compras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "dsw.siderandinaMS.Compras.client")
public class SiderandinaMsComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiderandinaMsComprasApplication.class, args);
	}

}
