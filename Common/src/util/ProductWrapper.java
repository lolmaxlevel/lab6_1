package util;

import mycollection.Coordinates;
import mycollection.Person;
import mycollection.Product;
import mycollection.UnitOfMeasure;

import java.io.Serializable;
public class ProductWrapper implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private double price; //Значение поля должно быть больше 0
    private Long manufactureCost; //Поле может быть null
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Person owner; //Поле может быть null
    public ProductWrapper(Product p){
        this.name = p.getName();
        this.coordinates = p.getCoordinates();
        this.price = p.getPrice();
        this.manufactureCost = p.getManufactureCost();
        this.unitOfMeasure = p.getUnitOfMeasure();
        this.owner = p.getOwner();
    }

    public ProductWrapper() {}

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public double getPrice() {
        return price;
    }

    public Long getManufactureCost() {
        return manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Person getOwner() {
        return owner;
    }
}
