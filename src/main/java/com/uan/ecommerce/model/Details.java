package com.uan.ecommerce.model;

public class Details {
    private Integer id;
    private String name;
    private double amount;
    private double price;
    private double pay;

    public Details() {
    }

    public Details(Integer id, String name, double amount, double price, double pay) {
        super();
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.pay = pay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", pay=" + pay +
                '}';
    }

}
