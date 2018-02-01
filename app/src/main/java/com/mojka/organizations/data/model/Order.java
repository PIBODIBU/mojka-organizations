package com.mojka.organizations.data.model;

public class Order {
    private Integer id;
    private Long date;
    private Client client;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", client=" + client +
                '}';
    }

    public Order(Integer id, Long date, Client client) {
        this.id = id;
        this.date = date;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
