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

import be.vdab.ethias.entities.Customer;
import be.vdab.ethias.entities.Policy;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private CustomerRepository repository;
	
	@Test
	public void findAll() {
		List<Customer> customers = repository.findAll();
		assertEquals(5, customers.size());
		for(Customer customer : customers) {
			//System.out.println(customer.getEmail());
			Set<Policy> policies = customer.getPolicies();
			for(Policy policy : policies) {
				System.out.println(policy.getPolicyNumber());
			}
		}
	}
}
