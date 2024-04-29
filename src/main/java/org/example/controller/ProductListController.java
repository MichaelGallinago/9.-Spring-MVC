package org.example.controller;

import net.minidev.json.JSONObject;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductListController {
    private final ProductService productService;

    public ProductListController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/items")
    public HttpStatus createItem(@RequestBody JSONObject jsonItem) {
        productService.addProduct(jsonItem.getAsString("name"));
        return HttpStatus.OK;
    }

    @GetMapping("/items")
    public Iterable<Product> getItemList() {
        return productService.getProductList();
    }

    @PutMapping("/items/{id}")
    public HttpStatus markItem(@PathVariable int id) {
        productService.markProduct(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/items/{id}")
    public HttpStatus deleteItem(@PathVariable int id) {
        productService.deleteProduct(id);
        return HttpStatus.OK;
    }
}
