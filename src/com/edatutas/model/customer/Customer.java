package com.edatutas.model.customer;

import java.util.concurrent.Semaphore;

import com.edatutas.model.order.Order;
import com.edatutas.model.order.OrderType;
import com.edatutas.model.table.Table;

import java.util.ArrayList;
import java.util.Random;

/*
 * 5 adet masa , 9 adet müşteri var. Müşteriler masanın durumu Empty ise otururlar.
 * Ve sipariş oluştururlar. Oluşturdukları siparişler Table dizisine atanır. 
 * Yemekleri servis edildikten sonra yeme süreleri ve sipariş süreleri sonunda masa boşaltılır. 
 * Ve varsa yeni müşteri yerine oturur.
 */

public class Customer implements Runnable {
	private int id;
	private Semaphore semaphoreTable;
	private static Table[] tables;
	private ArrayList<Order> orders;
	private boolean sat = false;
	private boolean sitting = false;

	public Customer(int id, Semaphore semaphoreTable, Table[] tables) {
		this.id = id;
		this.semaphoreTable = semaphoreTable;
		Customer.tables = tables;
		Random random = new Random();
		int foodCount = random.nextInt(2) + 1;
		int drinkCount = random.nextInt(2);
		this.orders = OrderType.getRandomOrder(foodCount, drinkCount);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				semaphoreTable.acquire();
				for (int i = 0; i < 5; i++) {
					if (!this.sat && tables[i].getStatus() == "Empty") {
						tables[i].setCustomerId(this.id);
						tables[i].setStatus("Sitted");
						tables[i].setOrders(this.orders);
						this.sat = true;
						this.sitting = true;
						System.out.println("Customer " + getId() + " sat down at Table  " + tables[i].getNo());
						break;
					} else if (tables[i].getCustomerId() == this.id && tables[i].getStatus() == "Delivered") {
						int timer = 0;
						for (Order order : tables[i].getOrders()) {
							timer += order.getServeTime() + order.getEatTime();
						}
						System.out.println(
								"Customer " + getId() + "\t Order " + orders.toString() + "\t Timer : " + timer);
						Thread.sleep(timer);
						tables[i].setCustomerId(-1);
						tables[i].setStatus("Empty");
						this.sitting = false;
						System.out.println("Customer " + getId() + " got up from the Table  " + tables[i].getNo());
						break;
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			semaphoreTable.release();
			if (!this.sitting && this.sat)
				break;
		}
	}
}