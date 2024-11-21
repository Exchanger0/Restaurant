package com.restaurant.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.restaurant.model.Address;

import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;

public class RestaurantDto {
    private int id;
    private String image;
    private Address address;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    public RestaurantDto() {
    }

    public RestaurantDto(int id, String image, Address address, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.image = image;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantDto that = (RestaurantDto) o;
        return id == that.id && Objects.equals(address, that.address) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, startTime, endTime);
    }

    @Override
    public String toString() {
        return "RestaurantDto{" +
                "id=" + id +
                ", address=" + address +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
