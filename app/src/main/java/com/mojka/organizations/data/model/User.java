package com.mojka.organizations.data.model;

import java.util.List;

public class User {
    private Integer id;
    private String phone;
    private String password;
    private String name;
    private String email;
    private String token;
    private String description;
    private List<Image> images;
    private String city;

    public User() {
    }

    public User(Integer id, String phone, String password, String name, String email, String token) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.email = email;
        this.token = token;
    }

    public User(Integer id, String phone, String name, String email, String token, String description, String city) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.token = token;
        this.description = description;
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", city='" + city + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
