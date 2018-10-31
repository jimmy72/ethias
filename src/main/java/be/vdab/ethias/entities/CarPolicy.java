package be.vdab.ethias.entities;

import java.math.BigDecimal;

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
	
	
	@Override
	public BigDecimal calculatePremium() {
		// TODO Auto-generated method stub
		return null;
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
	
}
