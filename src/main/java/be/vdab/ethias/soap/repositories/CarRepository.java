package be.vdab.ethias.soap.repositories;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import be.vdab.ethias.wsdl.Car;
import be.vdab.ethias.wsdl.GetCarRequest;


@Repository
public class CarRepository {
private static final Map<GetCarRequest, Car> cars = new HashMap<>();
	
	@PostConstruct
	public void initData() {
		Car car1 = new Car();
		car1.setBrand("Toyota");
		car1.setModel("Avensis");
		car1.setCatalogPrice(BigDecimal.valueOf(27500.00));
		GetCarRequest request1 = new GetCarRequest();
		request1.setBrand("Toyota");
		request1.setModel("Avensis");
		cars.put(request1, car1);

		Car car2 = new Car();
		car2.setBrand("Toyota");
		car2.setModel("Auris");
		car2.setCatalogPrice(BigDecimal.valueOf(22500.00));
		GetCarRequest request2 = new GetCarRequest();
		request2.setBrand("Toyota");
		request2.setModel("Auris");
		cars.put(request2, car2);
		
		Car car3 = new Car();
		car3.setBrand("Toyota");
		car3.setModel("Yaris");
		car3.setCatalogPrice(BigDecimal.valueOf(15250.00));
		GetCarRequest request3 = new GetCarRequest();
		request3.setBrand("Toyota");
		request3.setModel("Yaris");
		cars.put(request3, car3);

		Car car4 = new Car();
		car4.setBrand("Renault");
		car4.setModel("Clio");
		car4.setCatalogPrice(BigDecimal.valueOf(16700.00));
		GetCarRequest request4 = new GetCarRequest();
		request4.setBrand("Renault");
		request4.setModel("Clio");
		cars.put(request4, car4);
		
		Car car5 = new Car();
		car5.setBrand("Renault");
		car5.setModel("Captur");
		car5.setCatalogPrice(BigDecimal.valueOf(19900.00));
		GetCarRequest request5 = new GetCarRequest();
		request5.setBrand("Renault");
		request5.setModel("Captur");
		cars.put(request5, car5);
		
		Car car6 = new Car();
		car6.setBrand("Renault");
		car6.setModel("Kadjar");
		car6.setCatalogPrice(BigDecimal.valueOf(23900.00));
		GetCarRequest request6 = new GetCarRequest();
		request6.setBrand("Renault");
		request6.setModel("Kadjar");
		cars.put(request6, car6);
		
		Car car7 = new Car();
		car7.setBrand("Mercedes");
		car7.setModel("A-Klasse");
		car7.setCatalogPrice(BigDecimal.valueOf(28500.00));
		GetCarRequest request7 = new GetCarRequest();
		request7.setBrand("Mercedes");
		request7.setModel("A-Klasse");
		cars.put(request7, car7);
		
		Car car8 = new Car();
		car8.setBrand("Mercedes");
		car8.setModel("B-Klasse");
		car8.setCatalogPrice(BigDecimal.valueOf(36800.00));
		GetCarRequest request8 = new GetCarRequest();
		request8.setBrand("Mercedes");
		request8.setModel("B-Klasse");
		cars.put(request8, car8);
		
		Car car9 = new Car();
		car9.setBrand("Mercedes");
		car9.setModel("C-Klasse");
		car9.setCatalogPrice(BigDecimal.valueOf(43500.00));
		GetCarRequest request9 = new GetCarRequest();
		request9.setBrand("Mercedes");
		request9.setModel("C-Klasse");
		cars.put(request9, car9);
	}

	public Car findCar(GetCarRequest carRequest) {
		Assert.notNull(carRequest, "The car request must not be null");
		return cars.get(carRequest);
	}
}
