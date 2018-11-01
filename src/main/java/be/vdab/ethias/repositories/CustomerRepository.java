package be.vdab.ethias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.ethias.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
