package be.vdab.ethias.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.ethias.entities.Customer;
import be.vdab.ethias.repositories.CustomerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultCustomerService implements CustomerService{

	private final CustomerRepository customerRepository;
	
	
	DefaultCustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}


	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
		
	}


	@Override
	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}

	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	@Override
	public void create(Customer customer) {
		customerRepository.save(customer);
	}

}
