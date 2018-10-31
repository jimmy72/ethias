package be.vdab.ethias.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "policies")
public class Policy implements Serializable {

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
	@JoinColumn(name = "insurance_type_id")
	private InsuranceType insuranceType;

	public long getId() {
		return id;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public InsuranceType getInsuranceType() {
		return insuranceType;
	}
	
	
}
