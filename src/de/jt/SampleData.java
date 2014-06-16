package de.jt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Sample data.
 * 
 * @author Hendrik Stein
 *
 */
public final class SampleData {

	/**
	 * Utility class.
	 */
	private SampleData() {
		// Utility
	}

	/**
	 * Get sample accounts with positive balance.
	 * 
	 * @param amount
	 *            the amount of accounts with positive balance.
	 * @return the list of accounts
	 */
	public static List<Account> getSampleAccountsPositiveBalance(int amount) {
		return getAccounts(amount, 1000);

	}

	/**
	 * Get sample accounts with negative balance.
	 * 
	 * @param amount
	 *            the amount of accounts with negative balance.
	 * @return the list of accounts
	 */
	public static List<Account> getSampleAccountsNegativeBalance(int amount) {
		return getAccounts(amount, -1000);
	}

	/**
	 * Create accounts.
	 * 
	 * @param amount
	 *            the amount
	 * @param peak
	 *            the positive or negative peak
	 * @return the list of accounts
	 */
	private static List<Account> getAccounts(int amount, int peak) {
		List<Account> list = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			double random = new Random().nextDouble();
			// Random double between 0 and 1000
			double result = random * peak;
			list.add(new Account(BigDecimal.valueOf(result).setScale(2,
					BigDecimal.ROUND_DOWN), UUID.randomUUID().toString()));
		}
		return list;
	}
	
}
