package be.vdab.ethias.services;

import be.vdab.ethias.wsdl.Car;
import be.vdab.ethias.wsdl.GetCarRequest;
import be.vdab.ethias.wsdl.GetCarResponse;

public interface CarService {
	public abstract Car findCar(GetCarRequest carRequest);
	public abstract GetCarResponse getCarResponse(String brand, String model);
}
