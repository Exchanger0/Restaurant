package com.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

@Entity
@Table(name = "summary")
public class Summary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "First name must not be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty")
    @Column(name = "last_name")
    private String lastName;

    @Positive(message = "Age must be > 0")
    @Column(name = "age")
    private int age;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Incorrect email")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Incorrect phone number")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotEmpty(message = "Position must not be empty")
    @Column(name = "position")
    private String position;

    @PositiveOrZero(message = "Work experience must be >= 0")
    @Column(name = "work_experience")
    private int workExperience;

    @Column(name = "about")
    private String about;

    public Summary() {
    }

    public Summary(String firstName, String lastName, int age, String email, String phoneNumber, String position,
                   int workExperience, String about) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.workExperience = workExperience;
        this.about = about;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "First name must not be null") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "First name must not be null") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "Last name must not be null") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "Last name must not be null") String lastName) {
        this.lastName = lastName;
    }

    @Positive(message = "Age must be > 0")
    public int getAge() {
        return age;
    }

    public void setAge(@Positive(message = "Age must be > 0") int age) {
        this.age = age;
    }

    public @NotNull(message = "Email must not be null") @Email(message = "Incorrect email") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Email must not be null") @Email(message = "Incorrect email") String email) {
        this.email = email;
    }

    public @NotNull(message = "Phone number must be not null") @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Incorrect phone number") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull(message = "Phone number must be not null") @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Incorrect phone number") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotNull(message = "Position must be not null") String getPosition() {
        return position;
    }

    public void setPosition(@NotNull(message = "Position must be not null") String position) {
        this.position = position;
    }

    @PositiveOrZero(message = "Work experience must be >= 0")
    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(@PositiveOrZero(message = "Work experience must be >= 0") int workExperience) {
        this.workExperience = workExperience;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summary summary = (Summary) o;
        return id == summary.id && age == summary.age && workExperience == summary.workExperience && Objects.equals(firstName, summary.firstName) && Objects.equals(lastName, summary.lastName) && Objects.equals(email, summary.email) && Objects.equals(phoneNumber, summary.phoneNumber) && Objects.equals(position, summary.position) && Objects.equals(about, summary.about);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email, phoneNumber, position, workExperience, about);
    }

    @Override
    public String toString() {
        return "Summary{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", workExperience=" + workExperience +
                '}';
    }
}
