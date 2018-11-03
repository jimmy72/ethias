package be.vdab.ethias.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.ethias.entities.Location;

@Embeddable
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String street;
	
	@Column(name ="house_number")
	private String houseNumber;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;
	
	protected Address() {}
	
	public Address(String street, String houseNumber, Location location) {
		this.street = street;
		this.houseNumber = houseNumber;
		this.location = location;
	}

	public String getStreet() {
		return street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public Location getLocation() {
		return location;
	}

}
