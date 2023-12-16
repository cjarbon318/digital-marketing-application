package model.ProductManagement;

import java.util.ArrayList;
import java.util.List;

import model.ProductManagement.SolutionOfferOrderItem;
import model.CustomerManagement.CustomerProfile;

public class SolutionOrder {
    private List<SolutionOfferOrderItem> orderItems;

    public SolutionOrder() {
        this.orderItems = new ArrayList<>();
    }

    public List<SolutionOfferOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<SolutionOfferOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(SolutionOfferOrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public void removeOrderItem(SolutionOfferOrderItem orderItem) {
        orderItems.remove(orderItem);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (SolutionOfferOrderItem orderItem : orderItems) {
            totalPrice += orderItem.calculateTotalPrice();
        }
        return totalPrice;
    }

    public void displayOrderDetails() {
        System.out.println("Solution Order Details:");
        for (SolutionOfferOrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
        System.out.println("Total Price: $" + calculateTotalPrice());
    }
}
