package dsw.siderandinaMS.Eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class SiderandinaMsEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiderandinaMsEurekaApplication.class, args);
	}

}
