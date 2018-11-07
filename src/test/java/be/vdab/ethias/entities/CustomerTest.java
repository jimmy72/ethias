package be.vdab.ethias.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import be.vdab.ethias.valueobjects.Address;


public class CustomerTest {

	private Customer customer;
	private Address address;
	private Location location;
	
	@Before
	public void before() {
		location = new Location((short) 7777, "Testplace");
		address = new Address("Teststreet", "77", location);
		customer = new Customer("TestFirstName", "TestSurname", 72092520736L, address, "testemail@hotmail.com");
	}
	@Test
	public void addPolicy() {
		PolicyType type = new PolicyType("CAR");
		Car car = new Car("QS45J5698OP4523658", "Mercedes", "A-Klasse", BigDecimal.valueOf(28500.00));
		Policy carPolicy = new CarPolicy("777TEST7773", type, LocalDate.now(), customer, true, true, car);
		customer.addPolicy(carPolicy);
		assertEquals(1, customer.getPolicies().size());
		
		BigDecimal catalogPrice = BigDecimal.ZERO;
		Iterator<Policy> iterator = customer.getPolicies().iterator();
		while(iterator.hasNext()) {
			Policy p = iterator.next();
			if(p instanceof CarPolicy) {
				catalogPrice = ((CarPolicy) p).getCar().getCatalogPrice();
			}
		}
		assertTrue(0 == BigDecimal.valueOf(28500).compareTo(catalogPrice));
	}

}
