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

import be.vdab.ethias.entities.CarPolicy;
import be.vdab.ethias.entities.Customer;
import be.vdab.ethias.entities.Location;
import be.vdab.ethias.entities.Policy;
import be.vdab.ethias.entities.PolicyType;
import be.vdab.ethias.valueobjects.Address;
import net.bytebuddy.implementation.bind.annotation.Super;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private PolicyRepository policyRepository;
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
		Location location = new Location(1L, (short) 1000, "BRUSSEL");
		Address address = new Address("Teststreet", "99", location);
		Customer customer = new Customer("TestFirstName", "TestSurname", 72092520736L, address, "testemail@hotmail.com");
		Policy policy = new CarPolicy("efijeifjeif", new PolicyType(1L, "CAR"), LocalDate.now(), customer, "Ferrari", "V8", BigDecimal.valueOf(150000));
		customer.addPolicy(policy);
		int numberOfCustomers = super.countRowsInTable("customers");
		System.out.println("Number of customers: " + numberOfCustomers);
	    customerRepository.save(customer);
	    assertEquals(numberOfCustomers + 1, super.countRowsInTable("customers") );
	    System.out.println(super.countRowsInTable("policies"));
	}
}
