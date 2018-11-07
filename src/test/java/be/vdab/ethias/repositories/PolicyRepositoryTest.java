package be.vdab.ethias.repositories;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.ethias.entities.Car;
import be.vdab.ethias.entities.CarPolicy;
import be.vdab.ethias.entities.Customer;
import be.vdab.ethias.entities.Location;
import be.vdab.ethias.entities.Policy;
import be.vdab.ethias.entities.PolicyType;
import be.vdab.ethias.valueobjects.Address;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PolicyRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private PolicyRepository policyRepository;
	
	
	@Test
	public void create() {
		Location location = new Location(5L, (short) 3590, "DIEPENBEEK");
		Address address = new Address("Steenakkerstraat", "26a", location);
		Customer customer = new Customer(1L, "Jimmy", "Godin", 72092520938L, address, "jimmy.godin@hotmail.com");
		Car car = new Car(1L, "QS45J5698OP4523658", "Mercedes", "A-Klasse", BigDecimal.valueOf(28500.00));
		Policy policy = new CarPolicy("1234TEST56789", new PolicyType(1L, "CAR"), LocalDate.now(), customer, true, true, car);
		int numberOfPolicies = super.countRowsInTableWhere("policies", "customer_id=1");
		assertEquals(2, numberOfPolicies);
		policyRepository.save(policy);
	    assertEquals(numberOfPolicies + 1, super.countRowsInTableWhere("policies", "customer_id=1"));
	}

}
