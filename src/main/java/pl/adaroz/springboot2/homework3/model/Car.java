package pl.adaroz.springboot2.homework3.model;

public class Car {

    private long id;
    private String make;
    private String model;
    private String color;

    public Car() {
    }

    public Car(long id, String make, String model, String color) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
