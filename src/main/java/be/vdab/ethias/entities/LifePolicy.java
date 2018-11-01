package be.vdab.ethias.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "life_policies")
public class LifePolicy extends Policy {

	private static final long serialVersionUID = 1L;
	private BigDecimal amount;
	
	@Override
	public BigDecimal calculatePremium() {
		// TODO Auto-generated method stub
		return null;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
