package com.restaurant.model;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image")
    private byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    public Restaurant() {
    }

    public Restaurant(byte[] image, Address address, LocalTime startTime, LocalTime endTime) {
        this.image = image;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
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
        Restaurant that = (Restaurant) o;
        return id == that.id && Objects.equals(address, that.address) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", address=" + address +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
