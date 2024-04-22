package org.example.model;

public class Product {
    private final int id;
    private final String name;
    private boolean isChecked;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
        this.isChecked = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void switchIsChecked() {
        isChecked = !isChecked;
    }
}
