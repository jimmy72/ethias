package be.vdab.ethias;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import be.vdab.ethias.webserviceclients.CarClient;

@Configuration
public class CarConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		//het contextpad moet met underscores dus niet met koppeltekens zoals in xml
		marshaller.setContextPath("be.vdab.ethias.gs_producing_web_service");
		return marshaller;
	}

	@Bean
	public CarClient carClient(Jaxb2Marshaller marshaller) {
		CarClient carClient = new CarClient();
		carClient.setDefaultUri("http://localhost:8080/ws");
		carClient.setMarshaller(marshaller);
		carClient.setUnmarshaller(marshaller);
		return carClient;
	}
}
