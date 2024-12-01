package MODELS;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class User {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty dob;
    private SimpleDoubleProperty weight;
    private SimpleDoubleProperty height;
    private SimpleStringProperty category;
    private static int count = 1;

    public User(String name, String dob, double weight, double height ,String category) {
        this.id = new SimpleStringProperty("USR" + count++);
        this.name = new SimpleStringProperty(name);
        this.dob = new SimpleStringProperty(dob);
        this.weight = new SimpleDoubleProperty(weight);
        this.height = new SimpleDoubleProperty(height);
        this.category = new SimpleStringProperty(category);
    }

    public String getID() {
        return id.get();
    }
    public void setId(String id) {
        this.id.set(id);
    }
    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }


    public String getDob() {
        return dob.get();
    }
    public void setDob(String dob) {
        this.dob.set(dob);
    }
    public SimpleStringProperty dobProperty() {
        return dob;
    }


    public double getWeight() {
        return weight.get();
    }
    public void setWeight(double weight) {
        this.weight.set(weight);
    }
    public SimpleDoubleProperty weightProperty() {
        return weight;
    }


    public double getHeight() {
        return height.get();
    }
    public void setHeight(double height) {
        this.height.set(height);
    }
    public SimpleDoubleProperty heightProperty() {
        return height;
    }


    public String getCategory() {
        return category.get();
    }
    public void setCategory(String category) {
        this.category.set(category);
    }
    public SimpleStringProperty categoryProperty() {
        return category;
    }

    @Override
    public String toString() {
        return id.get() + "|" + name.get() + "|" + dob.get() + "|" + weight.get() + "|" + height.get() + "|" + category.get();
    }
}
