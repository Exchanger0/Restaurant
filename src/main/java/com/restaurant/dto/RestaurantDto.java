package com.restaurant.dto;


import com.restaurant.model.Address;

import java.time.LocalTime;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

public class RestaurantDto {
    private byte[] image;
    private Address address;
    private LocalTime startTime;
    private LocalTime endTime;

    public RestaurantDto() {
    }

    public RestaurantDto(byte[] image, Address address, LocalTime startTime, LocalTime endTime) {
        this.image = image;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public RestaurantDto(Map<String, String> values) {
        if (values.containsKey("image"))
            this.image = Base64.getDecoder().decode(values.get("image"));

        if (values.containsKey("startTime"))
            this.startTime = LocalTime.parse(values.get("startTime"));

        if (values.containsKey("endTime"))
            this.endTime = LocalTime.parse(values.get("endTime"));
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
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
        return Objects.equals(address, that.address) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "address=" + address +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
