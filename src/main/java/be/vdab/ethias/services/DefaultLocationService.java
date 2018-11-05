package be.vdab.ethias.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.ethias.entities.Location;
import be.vdab.ethias.repositories.LocationRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultLocationService implements LocationService {

	private final LocationRepository locationRepository;
	
	
	DefaultLocationService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void create(Location location) {
		this.locationRepository.save(location);
	}

}
