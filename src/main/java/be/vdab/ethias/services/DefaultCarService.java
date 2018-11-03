package be.vdab.ethias.services;

import org.springframework.stereotype.Service;

import be.vdab.ethias.gs_producing_web_service.Car;
import be.vdab.ethias.gs_producing_web_service.GetCarRequest;
import be.vdab.ethias.repositories.CarRepository;

@Service
class DefaultCarService implements CarService {
	private final CarRepository carRepository;

	DefaultCarService(CarRepository carRepository){
		this.carRepository = carRepository;
	}

	@Override
	public Car findCar(GetCarRequest carRequest) {
		return carRepository.findCar(carRequest);
	}
	
	
}
