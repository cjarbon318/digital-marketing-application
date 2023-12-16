/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kal bugrara
 */
public class SolutionOfferCatalog {
    private List<SolutionOffer> solutionOffers;

    public SolutionOfferCatalog() {
        this.solutionOffers = new ArrayList<>();
    }

    public void addSolutionOffer(SolutionOffer solutionOffer) {
        this.solutionOffers.add(solutionOffer);
    }

    public void removeSolutionOffer(SolutionOffer solutionOffer) {
        this.solutionOffers.remove(solutionOffer);
    }

    public List<SolutionOffer> getSolutionOffers() {
        return this.solutionOffers;
    }

    public void generateRandomSolutionOffers(int numberOfOffers) {
        Random random = new Random();
        for (int i = 0; i < numberOfOffers; i++) {
            String bundle = "Bundle" + (i + 1);
            SolutionOffer solutionOffer = new SolutionOffer(bundle);

            double price = random.nextDouble() * 1000.0;
            solutionOffer.setPrice(price);

            addSolutionOffer(solutionOffer);
        }
    }
}