package be.vdab.ethias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.ethias.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
