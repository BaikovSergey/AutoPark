package ru.job4j.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "auto_park_car_photos")
public class CarPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "sell_order_id")
    private int sellOrderId;

    public CarPhoto() {

    }

    public CarPhoto(String name, int sellOrderId) {
        this.name = name;
        this.sellOrderId = sellOrderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(int sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarPhoto carPhoto = (CarPhoto) o;
        return id == carPhoto.id &&
                sellOrderId == carPhoto.sellOrderId &&
                Objects.equals(name, carPhoto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sellOrderId);
    }
}
