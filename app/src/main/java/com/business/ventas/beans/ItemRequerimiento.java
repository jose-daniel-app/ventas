package com.business.ventas.beans;

public class ItemRequerimiento {
    private String itemCode;
    private int qty;
    private double rate;
    private String itemName;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "ItemRequerimiento{" +
                "itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", rate=" + rate +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
