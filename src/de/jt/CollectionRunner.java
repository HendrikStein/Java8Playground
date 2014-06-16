package de.jt;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.function.Consumer;

/**
 * Including new Java 8 collection features.
 * 
 * Lambda Expressions, a new language feature, has been introduced in this
 * release. They enable you to treat functionality as a method argument, or code
 * as data. Lambda expressions let you express instances of single-method
 * interfaces (referred to as functional interfaces) more compactly.
 * 
 * Classes in the new java.util.stream package provide a Stream API to support
 * functional-style operations on streams of elements. The Stream API is
 * integrated into the Collections API, which enables bulk operations on
 * collections, such as sequential or parallel map-reduce transformations.
 * 
 * 
 * @author Hendrik Stein
 *
 */
public class CollectionRunner {

	/**
	 * Main Runner.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CollectionRunner runner = new CollectionRunner();
		List<Account> accounts = SampleData.getSampleAccountsPositiveBalance(5);
		accounts.addAll(SampleData.getSampleAccountsNegativeBalance(5));
		runner.printForEachWithAnonymousConsumer(accounts);
		runner.printForEachLambda(accounts);
		runner.printTotalBalance(accounts);
		runner.printPositiveAccount(accounts);
		runner.printTotalPositiveBalanceSerial();
		runner.printTotalPositiveBalanceParallel();
	}

	/**
	 * Print account list with anonymous consumer class.
	 * 
	 * @param accounts
	 *            the accounts to process
	 * 
	 */
	private void printForEachWithAnonymousConsumer(List<Account> accounts) {
		System.out.println("For each anonymous consumer:\n");
		accounts.forEach(new Consumer<Account>() {
			public void accept(Account account) {
				System.out.println(account.toString());
			}
		});
		System.out.println("\n");
	}

	/**
	 * Print account list with new lambda expression.
	 * 
	 * @param accounts
	 *            the accounts to process
	 * 
	 */
	private void printForEachLambda(List<Account> accounts) {
		System.out.println("For each lambda:\n");
		accounts.forEach(account -> System.out.println(account));
		System.out.println("\n");
	}

	/**
	 * Print the total balance over all accounts using the new map reduce stream
	 * operation.
	 * 
	 * @param accounts
	 *            the accounts to process
	 */
	private void printTotalBalance(List<Account> accounts) {
		System.out.println("Total balance:");
		BigDecimal total = accounts.stream().map(Account::getBalance)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		System.out.println(total);
		System.out.println("\n");
	}

	/**
	 * Print the total balance all positive accounts.
	 * 
	 * @param accounts
	 *            the accounts to process
	 */
	private void printPositiveAccount(List<Account> accounts) {
		System.out.println("Total positive balance:");
		BigDecimal total = accounts.stream()
				.filter(a -> a.getBalance().compareTo(BigDecimal.ZERO) > 0)
				.map(Account::getBalance)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		System.out.println(total);
		System.out.println("\n");
	}

	/**
	 * Print total balance with parallel stream operations for 20000 accounts.
	 */
	private void printTotalPositiveBalanceParallel() {
		List<Account> accounts = SampleData
				.getSampleAccountsPositiveBalance(10000);
		accounts.addAll(SampleData.getSampleAccountsNegativeBalance(10000));

		Instant start = Instant.now();
		BigDecimal total = accounts.parallelStream()
				.filter(a -> a.getBalance().compareTo(BigDecimal.ZERO) > 0)
				.map(Account::getBalance)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println("Parallel took: "
				+ Duration.between(start, Instant.now()));
		System.out.println(total);
		System.out.println("\n");
	}

	/**
	 * Print total balance with serial stream operations for 20000 accounts.
	 */
	private void printTotalPositiveBalanceSerial() {
		List<Account> accounts = SampleData
				.getSampleAccountsPositiveBalance(10000);
		accounts.addAll(SampleData.getSampleAccountsNegativeBalance(10000));

		Instant start = Instant.now();
		BigDecimal total = accounts.stream()
				.filter(a -> a.getBalance().compareTo(BigDecimal.ZERO) > 0)
				.map(Account::getBalance)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println("Serial took: "
				+ Duration.between(start, Instant.now()));
		System.out.println(total);
		System.out.println("\n");
	}

}
