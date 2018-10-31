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


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "policies")
public abstract class Policy implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
