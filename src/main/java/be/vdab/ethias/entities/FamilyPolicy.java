package be.vdab.ethias.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "family_policies")
public class FamilyPolicy extends Policy {

	private static final long serialVersionUID = 1L;

	private int members;
		
	@Override
	public BigDecimal calculatePremium() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMembers() {
		return members;
	}

}
