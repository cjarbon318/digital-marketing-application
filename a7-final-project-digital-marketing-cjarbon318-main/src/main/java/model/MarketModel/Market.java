/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.ProductManagement.SolutionOffer;

public class Market {
    private String name;
    private List<SolutionOffer> solutionOffers;
    private List<MarketChannelAssignment> channels;
    private List<String> characteristics;
    private List<Market> submarkets;
    private int size;

    public Market(String s) {
        this.name = name;
        this.characteristics = new ArrayList<>();
        this.characteristics.add(s);
        this.solutionOffers = new ArrayList<>();
        this.channels = new ArrayList<>();
        this.submarkets = new ArrayList<>();
        this.size = 0;
    }

    public void addSolutionOffer(SolutionOffer offer) {
        solutionOffers.add(offer);
    }

    public void addChannelAssignment(MarketChannelAssignment assignment) {
        channels.add(assignment);
    }

    public void addSubmarket(Market submarket) {
        submarkets.add(submarket);
    }
    public String getName() {
        return name;
    }
    public void setName(String newName) {
        name = newName;
    }
    public void setSize(int newSize) {
        size = newSize;
    }
  
    public int getSize() {
        return size;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }


    public void generateRandomValues() {
        Random random = new Random();

      
        int numCharacteristics = random.nextInt(3) + 1; 
        for (int i = 0; i < numCharacteristics; i++) {
            characteristics.add("Characteristic" + (i + 1));
        }

       
        int numSubmarkets = random.nextInt(3) + 1; 
        for (int i = 0; i < numSubmarkets; i++) {
            submarkets.add(new Market("Submarket" + (i + 1)));
        }


        int numSolutionOffers = random.nextInt(5) + 1; 
        for (int i = 0; i < numSolutionOffers; i++) {
            solutionOffers.add(new SolutionOffer("RandomBundle" + (i + 1)));
        }
    }
}
