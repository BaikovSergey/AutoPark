package ru.job4j.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car_photo")
public class CarPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToOne
    @JoinColumn(name = "sell_order_id", foreignKey = @ForeignKey(name =
            "AUTO_PARK_SELL_ORDER_ID_FK"))
    private SellOrder sellOrderId;

    public CarPhoto() {

    }

    public CarPhoto(String name, SellOrder sellOrderId) {
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

    public SellOrder getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(SellOrder sellOrderId) {
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
