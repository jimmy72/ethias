package be.vdab.ethias.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import be.vdab.ethias.soap.clients.CarSoapClient;

@Configuration
public class CarSoapClientConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		//het contextpad moet met underscores dus niet met koppeltekens zoals in xml
		marshaller.setContextPath("be.vdab.ethias.gs_producing_web_service");
		return marshaller;
	}

	@Bean
	public CarSoapClient carClient(Jaxb2Marshaller marshaller) {
		CarSoapClient carClient = new CarSoapClient();
		carClient.setDefaultUri("http://localhost:8080/ws");
		carClient.setMarshaller(marshaller);
		carClient.setUnmarshaller(marshaller);
		return carClient;
	}
}
