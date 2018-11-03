package be.vdab.ethias.services;

import be.vdab.ethias.gs_producing_web_service.Car;
import be.vdab.ethias.gs_producing_web_service.GetCarRequest;

public interface CarService {
	public abstract Car findCar(GetCarRequest carRequest);
}
