package be.vdab.ethias.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "house_policies")
public class HousePolicy extends Policy{

	private static final long serialVersionUID = 1L;

	private BigDecimal price;
	
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public BigDecimal getPremium() {
		// TODO Auto-generated method stub
		return null;
	}

}
