package com.edatutas.model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import com.edatutas.model.chef.Chef;
import com.edatutas.model.customer.Customer;
import com.edatutas.model.table.Table;
import com.edatutas.model.waiter.Waiter;

public class Restaurant {
	private static Table[] tables;

	public static void main(String[] args) throws InterruptedException {
		Semaphore tableSemaphore = new Semaphore(1, true);

		tables = new Table[5];

		for (int i = 0; i < 5; i++)
			tables[i] = new Table();

		ArrayList<Thread> threads = new ArrayList<Thread>();

		for (int i = 1; i < 4; i++) {
			Thread waiterThread = new Thread(new Waiter(i, tableSemaphore, tables));
			threads.add(waiterThread);
		}

		for (int i = 1; i < 3; i++) {
			Thread chiefThread = new Thread(new Chef(i, tableSemaphore, tables));
			threads.add(chiefThread);
		}

		int customer = 1;
		while (customer < 10) {
			Thread customerThread = new Thread(new Customer(customer, tableSemaphore, tables));
			threads.add(customerThread);
			customer++;
		}

		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			thread.join();
		}

	}
}