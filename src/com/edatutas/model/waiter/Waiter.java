package com.edatutas.model.waiter;

import java.util.concurrent.Semaphore;
import com.edatutas.model.table.Table;

/*
 * Masalar üzerinden sistemi yürütüyorum.
 * Masadan siparişi alıyor , chef'e iletiyor ve hazırlandığında müşteriye servis ediyor.
 */

public class Waiter implements Runnable {
	private int id;
	private Semaphore semaphoreTable;
	private static Table[] tables;

	public Waiter(int id, Semaphore semaphoreTable, Table[] tables) {
		this.id = id;
		this.semaphoreTable = semaphoreTable;
		Waiter.tables = tables;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		boolean working;
		while (true) {

			try {
				Thread.sleep(1000);
				semaphoreTable.acquire();

				working = false;
				if (!working) {
					for (int i = 0; i < 5; i++) {
						if (tables[i].getStatus() == "Sitted") {
							tables[i].setWaiterId(this.id);
							tables[i].setStatus("Ordered");
							System.out.println(
									"Waiter " + getid() + " is taking the order of Table " + tables[i].getNo());
							working = true;
							break;
						}
					}
					for (int i = 0; i < 5; i++) {
						if (tables[i].getStatus() == "Ordered") {
							tables[i].setStatus("Notified");
							System.out.println("Waiter " + getid() + " is notifying the order of Table "
									+ tables[i].getNo() + " to the Chef ");
							working = true;
							break;
						}
					}
					for (int i = 0; i < 5; i++) {
						if (tables[i].getStatus() == "Prepared") {
							tables[i].setStatus("Delivered");
							System.out.println(
									"Waiter " + getid() + " is delivering the order for Table " + tables[i].getNo());
							working = false;
							break;
						}
					}

				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			semaphoreTable.release();
		}

	}
}