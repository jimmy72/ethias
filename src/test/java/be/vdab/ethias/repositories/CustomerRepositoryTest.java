package be.vdab.ethias.repositories;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
public class CustomerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void findAll() {
		List<Customer> customers = customerRepository.findAll();
		assertEquals(super.countRowsInTable("customers"), customers.size());
		for(Customer customer : customers) {
			//System.out.println(customer.getEmail());
			Set<Policy> policies = customer.getPolicies();
			for(Policy policy : policies) {
				if(policy instanceof CarPolicy) {
					System.out.println(((CarPolicy) policy).getCar().getCatalogPrice());
				}
				
			}
		}
	}
	
	@Test
	public void create() {
		Location location = new Location(5L, (short) 3590, "DIEPENBEEK");
		Address address = new Address("Steenakkerstraat", "26a", location);
		Customer customer = new Customer("TestCustomerFirstName", "TestCustomerSurname", 72092520903L, address, "testemail@hotmail.com");
		Car car = new Car(1L, "QS45J5698OP4523658", "Mercedes", "A-Klasse", BigDecimal.valueOf(28500.00));
		Policy policy = new CarPolicy("1234TEST56789", new PolicyType(1L, "CAR"), LocalDate.now(), customer, true, true, car);
		customer.addPolicy(policy);
		int numberOfCustomers = super.countRowsInTable("customers");
		customerRepository.save(customer);
	    assertEquals(numberOfCustomers + 1, super.countRowsInTable("customers") );
	}
}
