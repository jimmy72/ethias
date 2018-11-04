package be.vdab.ethias.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import be.vdab.ethias.services.CarService;

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
