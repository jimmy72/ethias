package be.vdab.ethias.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.ethias.entities.Policy;
import be.vdab.ethias.repositories.PolicyRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultPolicyService implements PolicyService{

private final PolicyRepository policyRepository;
	
	
	DefaultPolicyService(PolicyRepository policyRepository) {
		this.policyRepository = policyRepository;
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void create(Policy policy) {
		this.policyRepository.save(policy);
	}

}
