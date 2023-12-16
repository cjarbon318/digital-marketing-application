/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.github.javafaker.Faker;

public class SolutionOffer {
    private String bundle;
    private List<Product> products;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SolutionOffer(String bundle) {
        this.bundle = bundle;
        this.products = new ArrayList<>();
        this.price = 0.0;
    }

    public SolutionOffer(String bundle, List<Product> products, double price) {
        this.bundle = bundle;
        this.products = products;
        this.price = price;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getProductsString(int numberOfProductsInBundle) {
        StringBuilder productsString = new StringBuilder();
        int count = 0;
        final Faker faker = new Faker(new Locale("en-US"));
    
        for (Product product : products) {
            if (count < numberOfProductsInBundle) {
                productsString.append("Product: ").append(faker.commerce().productName())
                        .append("\nDescription: ").append(faker.lorem().sentences(3))
                        .append("\nTarget Price: $").append(product.getTargetPrice())
                        .append("\nFloor Price: $").append(product.getFloorPrice())
                        .append("\nCeiling Price: $").append(product.getCeilingPrice())
                        .append("\n\n");
            } else {
                break; 
            }
            count++;
        }
    
        return productsString.toString();
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}