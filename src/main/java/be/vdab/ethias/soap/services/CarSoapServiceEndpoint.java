package be.vdab.ethias.soap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import be.vdab.ethias.services.CarService;
import be.vdab.ethias.wsdl.GetCarRequest;
import be.vdab.ethias.wsdl.GetCarResponse;

@Endpoint
public class CarSoapServiceEndpoint {
	
	private static final String NAMESPACE_URI = "http://vdab.be/ethias/wsdl";
	private CarService carService;

	@Autowired
	public CarSoapServiceEndpoint(CarService carService) {
		this.carService = carService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCarRequest")
	@ResponsePayload
	public GetCarResponse getCar(@RequestPayload GetCarRequest carRequest) {
		GetCarResponse response = new GetCarResponse();
		response.setCar(carService.findCar(carRequest));
		System.out.println(carService.findCar(carRequest).getCatalogPrice());
		return response;
	}
}
