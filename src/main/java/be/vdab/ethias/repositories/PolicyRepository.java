package be.vdab.ethias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.ethias.entities.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

}
