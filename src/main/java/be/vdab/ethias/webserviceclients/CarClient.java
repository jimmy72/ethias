package be.vdab.ethias.webserviceclients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import be.vdab.ethias.gs_producing_web_service.GetCarRequest;
import be.vdab.ethias.gs_producing_web_service.GetCarResponse;

public class CarClient extends WebServiceGatewaySupport {
	private static final Logger log = LoggerFactory.getLogger(CarClient.class);

	public GetCarResponse getCarResponse(String brand, String model) {

		GetCarRequest carRequest = new GetCarRequest();
		carRequest.setBrand(brand);
		carRequest.setModel(model);

		log.info("Requesting price for " + brand + " " + model);
		GetCarResponse carResponse = (GetCarResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8080/ws/cars", carRequest, 
						new SoapActionCallback("http://vdab.be/ethias/gs-producing-web-service/getCarRequest"));
		return carResponse;
	}

}
