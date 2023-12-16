package model.ProductManagement;

import java.util.ArrayList;
import java.util.List;

import model.CustomerManagement.CustomerProfile;
import model.ProductManagement.SolutionOrder;

public class MasterSolutionOrderList {

    private List<SolutionOffer> solutions;
    private static MasterSolutionOrderList masterSolutionOrderList;


    private MasterSolutionOrderList() {
        this.solutions = new ArrayList<>();
    }

    public static MasterSolutionOrderList getInstance() {
        if (masterSolutionOrderList == null) {
            masterSolutionOrderList = new MasterSolutionOrderList();
        }
        return masterSolutionOrderList;
    }

    public List<SolutionOffer> getSolutionOffers() {
        return solutions;
    }

    public SolutionOffer createSolutionOffer(String bundle) {
        SolutionOffer solutionOffer = new SolutionOffer(bundle);
        solutions.add(solutionOffer);
        return solutionOffer;
    }

    public void removeSolutionOffer(SolutionOffer solutionOffer) {
        solutions.remove(solutionOffer);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (SolutionOffer solutionOffer : solutions) {
            totalPrice += solutionOffer.getPrice();
        }
        return totalPrice;
    }
}
