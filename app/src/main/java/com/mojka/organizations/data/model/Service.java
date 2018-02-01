package com.mojka.organizations.data.model;

import java.util.List;

public class Service {
    private Integer id;
    private Double lat;
    private Double lng;
    private Integer type;
    private String work_hours_normal_from;
    private String work_hours_normal_to;
    private String work_hours_weekend_from;
    private String work_hours_weekend_to;
    private Boolean is_working_sat;
    private Boolean is_working_sun;
    private String work_hours_sat_sun_from;
    private String work_hours_sat_sun_to;
    private Integer order_duration;
    private Integer order_at_one_time;
    private Integer box_num;
    private Integer master_num;
    private Integer price_start;
    private Integer price_end;
    private String name;
    private String description;
    private List<Image> images;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWork_hours_normal_from() {
        return work_hours_normal_from;
    }

    public void setWork_hours_normal_from(String work_hours_normal_from) {
        this.work_hours_normal_from = work_hours_normal_from;
    }

    public String getWork_hours_normal_to() {
        return work_hours_normal_to;
    }

    public void setWork_hours_normal_to(String work_hours_normal_to) {
        this.work_hours_normal_to = work_hours_normal_to;
    }

    public String getWork_hours_weekend_from() {
        return work_hours_weekend_from;
    }

    public void setWork_hours_weekend_from(String work_hours_weekend_from) {
        this.work_hours_weekend_from = work_hours_weekend_from;
    }

    public String getWork_hours_weekend_to() {
        return work_hours_weekend_to;
    }

    public void setWork_hours_weekend_to(String work_hours_weekend_to) {
        this.work_hours_weekend_to = work_hours_weekend_to;
    }

    public Boolean getIs_working_sat() {
        return is_working_sat;
    }

    public void setIs_working_sat(Boolean is_working_sat) {
        this.is_working_sat = is_working_sat;
    }

    public Boolean getIs_working_sun() {
        return is_working_sun;
    }

    public void setIs_working_sun(Boolean is_working_sun) {
        this.is_working_sun = is_working_sun;
    }

    public String getWork_hours_sat_sun_from() {
        return work_hours_sat_sun_from;
    }

    public void setWork_hours_sat_sun_from(String work_hours_sat_sun_from) {
        this.work_hours_sat_sun_from = work_hours_sat_sun_from;
    }

    public String getWork_hours_sat_sun_to() {
        return work_hours_sat_sun_to;
    }

    public void setWork_hours_sat_sun_to(String work_hours_sat_sun_to) {
        this.work_hours_sat_sun_to = work_hours_sat_sun_to;
    }

    public Integer getOrder_duration() {
        return order_duration;
    }

    public void setOrder_duration(Integer order_duration) {
        this.order_duration = order_duration;
    }

    public Integer getOrder_at_one_time() {
        return order_at_one_time;
    }

    public void setOrder_at_one_time(Integer order_at_one_time) {
        this.order_at_one_time = order_at_one_time;
    }

    public Integer getBox_num() {
        return box_num;
    }

    public void setBox_num(Integer box_num) {
        this.box_num = box_num;
    }

    public Integer getMaster_num() {
        return master_num;
    }

    public void setMaster_num(Integer master_num) {
        this.master_num = master_num;
    }

    public Integer getPrice_start() {
        return price_start;
    }

    public void setPrice_start(Integer price_start) {
        this.price_start = price_start;
    }

    public Integer getPrice_end() {
        return price_end;
    }

    public void setPrice_end(Integer price_end) {
        this.price_end = price_end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
