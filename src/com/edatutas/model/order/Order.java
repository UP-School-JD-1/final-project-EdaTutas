package com.edatutas.model.order;

public class Order {
	public final String name;
	public final int serveTime;
	public final int eatTime;

	public Order(String name, int serveTime, int eatTime) {
		this.name = name;
		this.serveTime = serveTime;
		this.eatTime = eatTime;
	}

	public String getName() {
		return name;
	}

	public int getServeTime() {
		return serveTime;
	}

	public int getEatTime() {
		return eatTime;
	}

	public String toString() {
		return name;
	}
}