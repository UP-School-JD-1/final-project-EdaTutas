package com.edatutas.model.chef;

import java.util.concurrent.Semaphore;
import com.edatutas.model.order.Order;
import com.edatutas.model.table.Table;

/*
 * Masalar üzerinden sistemi yürütüyorum.
 * Sipariş bildirildiğinde timer 0 değerindedir.
 * 41. satırda belirtildiği gibi masanın siparişini alır. 
 * Ve servis (hazırlanma süresi olarak belirttim) sürecini timer a atar.
 * Thread o süre boyunca uyur ve yiyecekleri servis eder.  
 */

public class Chef implements Runnable {
	private int id;
	private Semaphore semaphoreTable;
	private static Table[] tables;

	public Chef(int id, Semaphore semaphoreTable, Table[] tables) {
		this.id = id;
		this.semaphoreTable = semaphoreTable;
		Chef.tables = tables;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				semaphoreTable.acquire();
				for (int i = 0; i < 5; i++) {
					if (tables[i].getStatus() == "Notified") {
						int timer = 0;
						for (Order order : tables[i].getOrders()) {
							timer += order.getServeTime();
						}
						Thread.sleep(timer);
						tables[i].setChefId(this.id);
						tables[i].setStatus("Prepared");
						System.out.println(
								"Chef " + getid() + " has prepared the order for Table " + tables[i].getNo());
						break;
					}

				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			semaphoreTable.release();
		}

	}
}