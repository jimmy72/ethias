package be.vdab.ethias.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

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
	private long id;
	
	@Column(name = "first_name")
	private String firstName;
	private String surname;
		
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "customer") 
	@OrderBy("policy_number") 
	private Set<Policy> policies;

	public long getId() {
		return id;
	}

	public String getSurname() {
		return surname;
	}

	public Address getAddress() {
		return address;
	}

	public Set<Policy> getPolicies() {
		return Collections.unmodifiableSet(policies);
	}


	public String getFirstName() {
		return firstName;
	}
	
	
}
