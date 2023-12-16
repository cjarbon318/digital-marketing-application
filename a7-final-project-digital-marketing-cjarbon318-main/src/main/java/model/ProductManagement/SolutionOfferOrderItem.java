package model.ProductManagement;

import java.util.Objects;

public class SolutionOfferOrderItem {
    private SolutionOffer solutionOffer;
    private int quantity;

    public SolutionOfferOrderItem(SolutionOffer solutionOffer, int quantity) {
        this.solutionOffer = solutionOffer;
        this.quantity = quantity;
    }

    public SolutionOffer getSolutionOffer() {
        return solutionOffer;
    }

    public void setSolutionOffer(SolutionOffer solutionOffer) {
        this.solutionOffer = solutionOffer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double calculateTotalPrice() {
        return solutionOffer.getPrice() * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SolutionOfferOrderItem that = (SolutionOfferOrderItem) o;
        return quantity == that.quantity && Objects.equals(solutionOffer, that.solutionOffer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solutionOffer, quantity);
    }

    @Override
    public String toString() {
        return "SolutionOfferOrderItem{" +
                "solutionOffer=" + solutionOffer +
                ", quantity=" + quantity +
                '}';
    }
}
