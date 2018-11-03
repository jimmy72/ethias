package be.vdab.ethias.services;

import be.vdab.ethias.gs_producing_web_service.Car;
import be.vdab.ethias.gs_producing_web_service.GetCarRequest;
import be.vdab.ethias.gs_producing_web_service.GetCarResponse;

public interface CarService {
	public abstract Car findCar(GetCarRequest carRequest);
	public abstract GetCarResponse getCarResponse(String brand, String model);
}
