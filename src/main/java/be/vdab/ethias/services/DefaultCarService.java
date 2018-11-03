package be.vdab.ethias.services;

import org.springframework.stereotype.Service;

import be.vdab.ethias.gs_producing_web_service.Car;
import be.vdab.ethias.gs_producing_web_service.GetCarRequest;
import be.vdab.ethias.gs_producing_web_service.GetCarResponse;
import be.vdab.ethias.repositories.CarRepository;
import be.vdab.ethias.webserviceclients.CarClient;

@Service
class DefaultCarService implements CarService {
	private final CarRepository carRepository;
	private final CarClient carClient;

	DefaultCarService(CarRepository carRepository, CarClient carClient){
		this.carRepository = carRepository;
		this.carClient = carClient;
	}

	@Override //This is the car that is send back from our own soap Service
	public Car findCar(GetCarRequest carRequest) {
		return carRepository.findCar(carRequest);
	}

	@Override //This is the car response send back from the external client
	public GetCarResponse getCarResponse(String brand, String model) {
		return carClient.getCarResponse(brand, model);
	}
	
	
}
