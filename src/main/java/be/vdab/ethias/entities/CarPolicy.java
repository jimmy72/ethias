package be.vdab.ethias.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car_policies")
public class CarPolicy extends Policy{

	private static final long serialVersionUID = 1L;
	
	@Column(name="omnium")
	private boolean omnium;
	
	@Column(name="legal_assistance")
	private boolean legalAssistance;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id")
	private Car car;
	
	protected CarPolicy() {
		super();
	}
	
	public CarPolicy(String policyNumber, PolicyType policyType, LocalDate date, Customer customer,
			boolean omnium, boolean legalAssistance, Car car) {
		super(policyNumber, policyType, date, customer);
		this.omnium = omnium;
		this.legalAssistance = legalAssistance;
		this.car = car;
	}
	
	
	public boolean isOmnium() {
		return omnium;
	}

	public void setOmnium(boolean omnium) {
		this.omnium = omnium;
	}

	public boolean isLegalAssistance() {
		return legalAssistance;
	}

	public void setLegalAssistance(boolean legalAssistance) {
		this.legalAssistance = legalAssistance;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		if(car == null) {
			throw new NullPointerException("Car cannot be null!!!");
		}
		
		if(! car.getCarPolicy().equals(this)) {
			car.setCarPolicy(this);
		}
		this.car = car;
	}

	@Override
	public BigDecimal getPremium() {
		return this.getCar().getCatalogPrice().divide(BigDecimal.valueOf(18),2, RoundingMode.HALF_UP);
	}

}
