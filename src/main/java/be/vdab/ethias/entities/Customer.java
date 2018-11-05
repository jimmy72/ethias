package be.vdab.ethias.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import be.vdab.ethias.valueobjects.Address;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	private String surname;
	
	@Column(name = "national_registry_number")
	private Long nationalRegistryNumber;
	
	@Embedded
	private Address address;
	
	private String email;
	
	@OneToMany(mappedBy = "customer") 
	@OrderBy("policy_number") 
	private Set<Policy> policies;

	public Customer() {}
	
	public Customer(String firstName, String surname, Long nationalRegistryNumber, Address address, String email) {
		this.firstName = firstName;
		this.surname = surname;
		this.nationalRegistryNumber = nationalRegistryNumber;
		this.address = address;
		this.email = email;
		this.policies = new LinkedHashSet<>();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Policy> getPolicies() {
		return Collections.unmodifiableSet(policies);
	}
	
	public boolean addPolicy(Policy policy) {
		if(policy == null) {
			throw new NullPointerException("Policy cannot be null!!!");
		}
		boolean policyAdded = this.policies.add(policy);
		Customer oldCustomer = policy.getCustomer();
		if(oldCustomer != null && oldCustomer != this) {
			oldCustomer.removePolicy(policy);
		}
		if(this != oldCustomer) {
			policy.setCustomer(this);
		}
		return policyAdded;
	}
	
	public boolean removePolicy(Policy policy) {
		return this.policies.remove(policy);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nationalRegistryNumber == null) ? 0 : nationalRegistryNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (nationalRegistryNumber == null) {
			if (other.nationalRegistryNumber != null)
				return false;
		} else if (!nationalRegistryNumber.equals(other.nationalRegistryNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", surname=" + surname + ", address=" + address + ", email=" + email
				+ "]";
	}
	
}
