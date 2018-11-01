package be.vdab.ethias.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity //Hibernate does not require the use of a Discriminator column, so ignore the error
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "policies")
public abstract class Policy implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="policy_number")
	private String policyNumber;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY) 
	@JoinColumn(name = "customer_id") 
	private Customer customer;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "policy_type_id")
	private PolicyType policyType;

	public long getId() {
		return id;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public PolicyType getInsuranceType() {
		return policyType;
	}
	
	
	public abstract BigDecimal calculatePremium();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((policyNumber == null) ? 0 : policyNumber.toUpperCase().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Policy))
			return false;
		Policy other = (Policy) obj;
		if (policyNumber == null) {
			if (other.policyNumber != null)
				return false;
		} else if (!policyNumber.equalsIgnoreCase(other.policyNumber))
			return false;
		return true;
	}
}
