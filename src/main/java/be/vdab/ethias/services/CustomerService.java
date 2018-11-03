package be.vdab.ethias.services;

import java.util.List;
import java.util.Optional;

import be.vdab.ethias.entities.Customer;

public interface CustomerService {
	public abstract List<Customer> findAll();
	public abstract Optional<Customer> findById(Long id);
}
