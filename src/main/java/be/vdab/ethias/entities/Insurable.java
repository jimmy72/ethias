package be.vdab.ethias.entities;

import java.math.BigDecimal;

public interface Insurable {
	public abstract BigDecimal calculatePremium();
}
