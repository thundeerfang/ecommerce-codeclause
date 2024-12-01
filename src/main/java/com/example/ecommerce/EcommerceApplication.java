package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}

@RestController
class ProductController {
    private List<String> productList = new ArrayList<>();
    private List<String> cart = new ArrayList<>();

    public ProductController() {
        productList.add("Laptop - ₹108000");
        productList.add("Mobile - ₹50000");
        productList.add("Ipad - ₹30000");
    }

    @GetMapping("/products")
    public List<String> getProducts() {
        return productList;
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam String product) {
        if (productList.contains(product)) {
            cart.add(product);
            return "Product added to cart.";
        } else {
            return "Product not found.";
        }
    }

    @GetMapping("/cart")
    public List<String> getCart() {
        return cart;
    }

    @GetMapping("/checkout")
    public String checkout() {
        if (cart.isEmpty()) {
            return "Cart is empty.";
        } else {
            double total = 0;
            for (String item : cart) {
                if (item.contains("Laptop")) total += 1000;
                else if (item.contains("Headphones")) total += 50;
                else if (item.contains("Keyboard")) total += 30;
            }
            cart.clear();
            return "Checkout complete. Total: $" + total;
        }
    }
}
