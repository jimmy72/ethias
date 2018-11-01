package be.vdab.ethias.services;

import java.util.List;

import be.vdab.ethias.entities.Customer;

public interface CustomerService {
	public abstract List<Customer> findAll();
}
