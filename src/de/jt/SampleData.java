package de.jt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public final class SampleData {

	private SampleData() {
		// Utility
	}

	public static List<Account> getSampleAccountsPositiveBalance(int amount) {
		return getAccounts(amount, 1000);

	}

	public static List<Account> getSampleAccountsNegativeBalance(int amount) {
		return getAccounts(amount, -1000);
	}

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
