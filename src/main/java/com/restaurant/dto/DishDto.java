package com.restaurant.dto;

import com.restaurant.model.Ingredient;

import java.util.List;
import java.util.Objects;

public class DishDto {
    private int id;
    private String title;
    private String description;
    private String image;
    private int price;
    private String type;
    private List<Ingredient> ingredients;

    public DishDto() {
        price = -1;
    }

    public DishDto(String title, String description, String image, int price, String type, List<Ingredient> ingredients) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.type = type;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishDto dish = (DishDto) o;
        return id == dish.id && Objects.equals(title, dish.title) && Objects.equals(type, dish.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }
}
