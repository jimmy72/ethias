package be.vdab.ethias.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car implements Insurable, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="serial_number")
	private String serialNumber;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="model")
	private String model;
	
	@Column(name="catalog_price")
	private BigDecimal catalogPrice;
	
	@OneToOne(mappedBy = "car", cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY, optional = false)
	private CarPolicy carPolicy;
	
	protected Car() {}
	
	public Car(String serialNumber, String brand, String model, BigDecimal catalogPrice) {
		this.serialNumber = serialNumber;
		this.brand = brand;
		this.model = model;
		this.catalogPrice = catalogPrice;
	}
	
	public Car(Long id, String serialNumber, String brand, String model, BigDecimal catalogPrice) {
		this.id = id;
		this.serialNumber = serialNumber;
		this.brand = brand;
		this.model = model;
		this.catalogPrice = catalogPrice;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getCatalogPrice() {
		return catalogPrice;
	}

	public void setCatalogPrice(BigDecimal catalogPrice) {
		this.catalogPrice = catalogPrice;
	}

	public CarPolicy getCarPolicy() {
		return carPolicy;
	}

	public void setCarPolicy(CarPolicy carPolicy) {
		if(carPolicy == null) {
			throw new NullPointerException("Car policy cannot be null!!!");
		}
		
		if(! carPolicy.getCar().equals(this)) {
			carPolicy.setCar(this);
		}
		this.carPolicy = carPolicy;
	}

	@Override
	public BigDecimal getValue() {
		return this.getCatalogPrice();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.toUpperCase().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Car))
			return false;
		Car other = (Car) obj;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equalsIgnoreCase(other.serialNumber))
			return false;
		return true;
	}
	
	

}
