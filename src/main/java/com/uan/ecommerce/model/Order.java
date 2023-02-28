package com.uan.ecommerce.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String status = "created";
    private Date startDate;
    private Date devolutionDate;
    private double pay;

    @ManyToOne
    private Client client;

    @OneToOne(mappedBy = "order")
    private Detail details;

    public Order() {
    }

    public Order(Integer id, String number, String status, Date startDate, Date devolutionDate, double pay) {
        this.id = id;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Detail getDetails() {
        return details;
    }

    public void setDetails(Detail details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", devolutionDate=" + devolutionDate +
                ", pay=" + pay +
                '}';
    }
}