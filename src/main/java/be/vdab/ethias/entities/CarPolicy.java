package be.vdab.ethias.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car_policies")
public class CarPolicy extends Policy{

	private static final long serialVersionUID = 1L;
	private String brand;
	private String model;
	@Column(name="catalog_price")
	private BigDecimal catalogPrice;
	
	protected CarPolicy() {
		super();
	}
	
	public CarPolicy(String policyNumber, PolicyType policyType, LocalDate date, Customer customer, String brand, String model, BigDecimal catalogPrice) {
		super(policyNumber, policyType, date, customer);
		this.brand = brand;
		this.model = model;
		this.catalogPrice = catalogPrice;
	}
	
	@Override
	public BigDecimal calculatePremium() {
		return this.catalogPrice.divide(BigDecimal.valueOf(4));
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public BigDecimal getCatalogPrice() {
		return catalogPrice;
	}

	public void setCatalogPrice(BigDecimal catalogPrice) {
		this.catalogPrice = catalogPrice;
	}
	
	
	
}
