package be.vdab.ethias.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class Location implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "postal_code")
	private short postalCode;
	private String place;
	
	protected Location() {}
	
	public Location(short postalCode, String place) {
		this.postalCode = postalCode;
		this.place = place;
	}
	
	public Location(Long id, short postalCode, String place) {
		this.id = id;
		this.postalCode = postalCode;
		this.place = place;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getPlace() {
		return place;
	}

	public short getPostalCode() {
		return postalCode;
	}
	
}
