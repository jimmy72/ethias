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
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	private String surname;
		
	@Embedded
	private Address address;
	
	private String email;
	
	@OneToMany(mappedBy = "customer") 
	@OrderBy("policy_number") 
	private Set<Policy> policies;

	public Long getId() {
		return id;
	}

	public String getSurname() {
		return surname;
	}

	public Address getAddress() {
		return address;
	}
	
	public String getEmail() {
		return email;
	}

	public Set<Policy> getPolicies() {
		return Collections.unmodifiableSet(policies);
	}


	public String getFirstName() {
		return firstName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.toUpperCase().hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equalsIgnoreCase(other.email))
			return false;
		return true;
	}
	
	

}
