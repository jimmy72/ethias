package be.vdab.ethias.repositories;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import be.vdab.ethias.gs_producing_web_service.Car;
import be.vdab.ethias.gs_producing_web_service.GetCarRequest;


@Repository
public class CarRepository {
private static final Map<GetCarRequest, Car> cars = new HashMap<>();
	
	@PostConstruct
	public void initData() {
		Car car1 = new Car();
		car1.setBrand("Toyota");
		car1.setModel("Avensis");
		car1.setCatalogPrice(BigDecimal.valueOf(27000));
		GetCarRequest request1 = new GetCarRequest();
		request1.setBrand("Toyota");
		request1.setModel("Avensis");
		cars.put(request1, car1);

		Car car2 = new Car();
		car2.setBrand("Volkswagen");
		car2.setModel("Golf");
		car2.setCatalogPrice(BigDecimal.valueOf(31500));
		GetCarRequest request2 = new GetCarRequest();
		request2.setBrand("Volkswagen");
		request2.setModel("Golf");
		cars.put(request2, car2);
		
		Car car3 = new Car();
		car3.setBrand("Honda");
		car3.setModel("Civic");
		car3.setCatalogPrice(BigDecimal.valueOf(27300));
		GetCarRequest request3 = new GetCarRequest();
		request3.setBrand("Honda");
		request3.setModel("Civic");
		cars.put(request3, car3);
		
	}

	public Car findCar(GetCarRequest carRequest) {
		Assert.notNull(carRequest, "The car request must not be null");
		return cars.get(carRequest);
	}
}
