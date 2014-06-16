package de.jt;

import java.math.BigDecimal;

/**
 * Bank account.
 * 
 * @author Hendrik Stein
 *
 */
public class Account {
	/** Balance. */
	private BigDecimal balance;

	/** Bank Id. */
	private String id;

	/**
	 * Create an instance.
	 * 
	 * @param balance
	 *            the balance
	 * @param id
	 *            the id
	 */
	public Account(BigDecimal balance, String id) {
		this.balance = balance;
		this.id = id;
	}

	/**
	 * Get the balance.
	 * 
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * Set the id.
	 * 
	 * @return the id.
	 */
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", id=" + id + "]";
	}

}
