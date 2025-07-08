package dsw.siderandinaMS.Config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SiderandinaMsConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiderandinaMsConfigApplication.class, args);
	}

}
