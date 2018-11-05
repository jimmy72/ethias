package be.vdab.ethias.repositories;

import static org.junit.Assert.assertEquals;

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

import be.vdab.ethias.entities.CarPolicy;
import be.vdab.ethias.entities.Customer;
import be.vdab.ethias.entities.Location;
import be.vdab.ethias.entities.Policy;
import be.vdab.ethias.valueobjects.Address;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private LocationRepository locationRepository;
	
	@Test
	public void findAll() {
		List<Customer> customers = customerRepository.findAll();
		assertEquals(5, customers.size());
		for(Customer customer : customers) {
			//System.out.println(customer.getEmail());
			Set<Policy> policies = customer.getPolicies();
			for(Policy policy : policies) {
				if(policy instanceof CarPolicy) {
					System.out.println(((CarPolicy) policy).getCatalogPrice());
				}
				
			}
		}
	}
	
	@Test
	public void create() {
		Location location = new Location((short) 9999, "TestLocation");
		locationRepository.save(location);
		Address address = new Address("Teststreet", "999", location);
		Customer customer = new Customer("TestFirstName", "TestSurname", 72092520736L, address, "testemail@hotmail.com");
		int numberOfCustomers = super.countRowsInTable("customers");
		System.out.println("Number of customers: " + numberOfCustomers);
	    customerRepository.save(customer);
	    assertEquals(numberOfCustomers + 1, super.countRowsInTable("customers") );
	}
}
