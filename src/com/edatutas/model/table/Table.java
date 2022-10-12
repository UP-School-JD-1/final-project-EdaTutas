package com.edatutas.model.table;
import java.util.ArrayList;

import com.edatutas.model.order.Order;
public class Table {
    private static int count = 1;

    private int id;
    private int customerId;
    private int waiterId;
    private int chefId;
    private String status;
    private ArrayList<Order> orders;
    
    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Table() {
        this.id = count++;
        this.customerId = -1;
        this.status = "Empty";
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNo() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}