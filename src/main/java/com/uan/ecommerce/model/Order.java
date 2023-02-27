package com.uan.ecommerce.model;

import java.util.Date;

public class Order {
    private Integer id;
    private String status = "Created";
    private Date startDate;
    private Date devolutionDate;
    private double pay;

    public Order() {
    }

    public Order(Integer id, String status, Date startDate, Date devolutionDate, double pay) {
        super();
        this.id = id;
        this.status = status;
        this.startDate = startDate;
        this.devolutionDate = devolutionDate;
        this.pay = pay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(Date devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", devolutionDate=" + devolutionDate +
                ", pay=" + pay +
                '}';
    }

}