package org.example.service;

import org.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {
    private final ArrayList<Product> products = new ArrayList<>();
    private int idCounter = 0;

    public void addProduct(String name) {
        Product newProduct = new Product(idCounter, name);
        products.add(newProduct);
        idCounter++;
    }

    public ArrayList<Product> getProductList() {
        return products;
    }

    public void markProduct(int id) {
        getProductById(id).switchIsChecked();
    }

    public void deleteProduct(int id) {
        products.remove(getProductById(id));
    }

    public Product getProductById(int id) {
        Optional<Product> optionalProduct = products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();

        if (!optionalProduct.isPresent()) {
            throw new NoSuchElementException();
        }

        return optionalProduct.get();
    }
}
