package ru.job4j.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "auto_park_sell_orders")
public class SellOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "condition")
    private String condition;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "body")
    private String body;
    @Column(name = "transmission")
    private String transmission;
    @Column(name = "engine")
    private String engine;
    @Column(name = "drive")
    private String drive;
    @Column(name = "mileage")
    private Integer mileage;
    @Column(name = "engineVolume")
    private double engineVolume;
    @Column(name = "price")
    private Integer price;
    @Column(name = "status")
    private boolean status;
    @OneToOne
    @JoinColumn(name = "car_photo_id", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private CarPhoto carPhotos;
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private User user;

    public SellOrder() {

    }

    public SellOrder(String condition, String brand, String model, String body,
                     String transmission, String engine, String drive, Integer mileage,
                     double engineVolume, Integer price, boolean status, User user) {
        this.condition = condition;
        this.brand = brand;
        this.model = model;
        this.body = body;
        this.transmission = transmission;
        this.engine = engine;
        this.drive = drive;
        this.mileage = mileage;
        this.engineVolume = engineVolume;
        this.price = price;
        this.status = status;
        this.user = user;
    }

    public SellOrder(String condition, String brand, String model, String body,
                     String transmission, String engine, String drive, Integer mileage,
                     double engineVolume, Integer price, boolean status) {
        this.condition = condition;
        this.brand = brand;
        this.model = model;
        this.body = body;
        this.transmission = transmission;
        this.engine = engine;
        this.drive = drive;
        this.mileage = mileage;
        this.engineVolume = engineVolume;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(Integer engineVolume) {
        this.engineVolume = engineVolume;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SellOrder sellOrder = (SellOrder) o;
        return id == sellOrder.id
                && Objects.equals(condition, sellOrder.condition)
                && Objects.equals(brand, sellOrder.brand)
                && Objects.equals(model, sellOrder.model)
                && Objects.equals(body, sellOrder.body)
                && Objects.equals(transmission, sellOrder.transmission)
                && Objects.equals(engine, sellOrder.engine)
                && Objects.equals(drive, sellOrder.drive)
                && Objects.equals(mileage, sellOrder.mileage)
                && Objects.equals(engineVolume, sellOrder.engineVolume)
                && Objects.equals(price, sellOrder.price)
                && Objects.equals(status, sellOrder.status)
                && Objects.equals(carPhotos, sellOrder.carPhotos)
                && Objects.equals(user, sellOrder.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, condition, brand, model, body, transmission, engine, drive,
                mileage, engineVolume, price, status, carPhotos, user);
    }

}
