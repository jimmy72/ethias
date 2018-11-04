package be.vdab.ethias.services;

import org.springframework.stereotype.Service;

import be.vdab.ethias.gs_producing_web_service.Car;
import be.vdab.ethias.gs_producing_web_service.GetCarRequest;
import be.vdab.ethias.gs_producing_web_service.GetCarResponse;
import be.vdab.ethias.soap.clients.CarSoapClient;
import be.vdab.ethias.soap.repositories.CarRepository;

@Service
public class DefaultCarService implements CarService {
	private final CarRepository carRepository;
	private final CarSoapClient carClient;

	public DefaultCarService(CarRepository carRepository, CarSoapClient carClient){
		this.carRepository = carRepository;
		this.carClient = carClient;
	}

	@Override //This is the request to our own soap Service
	public Car findCar(GetCarRequest carRequest) {
		return carRepository.findCar(carRequest);
	}

	@Override //This is the request to the external soap client
	public GetCarResponse getCarResponse(String brand, String model) {
		return carClient.getCarResponse(brand, model);
	}
	
	
}
