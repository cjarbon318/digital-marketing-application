/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Locale;


import model.OrderManagement.OrderItem;
import model.ProductManagement.SolutionOffer;
/**
 *
 * @author kal bugrara
 */
public class Product {
    private String name;
    private String description;  
    private int floorPrice;
    private int ceilingPrice;
    private int targetPrice;
    ArrayList<OrderItem> orderItems;

    public Product(int fp, int cp, int tp) {

        floorPrice = fp;
        ceilingPrice = cp;
        targetPrice = tp;
        orderItems = new ArrayList<OrderItem>();
    }
    public Product() {
        Faker faker = new Faker(new Locale("en-US"));
        this.name = faker.commerce().productName();
        this.description = faker.lorem().sentence();
        this.floorPrice = faker.number().numberBetween(1, 100);
        this.ceilingPrice = faker.number().numberBetween(this.floorPrice + 1, 200);
        this.targetPrice = faker.number().numberBetween(this.floorPrice, this.ceilingPrice);
        this.orderItems = new ArrayList<>();
    }

    public Product(String n, String d, int fp, int cp, int tp) {
        this.name = n;
        this.description = d;
        this.floorPrice = fp;
        this.ceilingPrice = cp;
        this.targetPrice = tp;
        this.orderItems = new ArrayList<>();
    }

    public int getTargetPrice() {
        return targetPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addOrderItem(OrderItem oi) {
        orderItems.add(oi);
    }


    public int getNumberOfProductSalesAboveTarget() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            if (oi.isActualAboveTarget() == true)
                sum = sum + 1;
        }
        return sum;
    }

    public int getNumberOfProductSalesBelowTarget() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            if (oi.isActualBelowTarget() == true)
                sum = sum + 1;
        }
        return sum;
    }

    public boolean isProductAlwaysAboveTarget() {

        for (OrderItem oi : orderItems) {
            if (oi.isActualAboveTarget() == false)
                return false; //
        }
        return true;
    }

    public int getOrderPricePerformance() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.calculatePricePerformance(); // positive and negative values
        }
        return sum;
    }

    public int getSalesVolume() {
        int sum = 0;
        for (OrderItem oi : orderItems) {
            sum = sum + oi.getOrderItemTotal(); 
        }
        return sum;
    }

    public void setName(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getFloorPrice() {
        return floorPrice;
    }

    public int getCeilingPrice() {
        return ceilingPrice;
    }

}
