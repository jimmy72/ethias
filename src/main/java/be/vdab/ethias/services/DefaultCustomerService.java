package be.vdab.ethias.services;

import java.util.List;

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

}
