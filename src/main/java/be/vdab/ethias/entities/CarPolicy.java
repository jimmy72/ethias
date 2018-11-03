package be.vdab.ethias.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@Transient
	@Autowired
	private CarService carService;
	
	
	@Override
	public BigDecimal calculatePremium() {
		// TODO Auto-generated method stub
		//BigDecimal catalogPrice;
		//catalogPrice = carService.getCarResponse(this.getBrand(), this.getModel()).getCar().getCatalogPrice();
		//return catalogPrice.divide(BigDecimal.valueOf(4));
		return BigDecimal.valueOf(4);
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
