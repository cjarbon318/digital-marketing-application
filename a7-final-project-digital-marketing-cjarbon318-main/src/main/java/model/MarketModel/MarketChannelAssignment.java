/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.Random;

import model.ProductManagement.SolutionOffer;

public class MarketChannelAssignment {

    private int advertisingBudget;
    private SolutionOffer solutionOffer;
    private String channel;

    public MarketChannelAssignment(SolutionOffer solutionOffer) {
        this.solutionOffer = solutionOffer;
        generateRandomValues();
    }

    public int getAdvertisingBudget() {
        return advertisingBudget;
    }

    public void setAdvertisingBudget(int advertisingBudget) {
        this.advertisingBudget = advertisingBudget;
    }

    public SolutionOffer getSolutionOffer() {
        return solutionOffer;
    }

    public void setSolutionOffer(SolutionOffer solutionOffer) {
        this.solutionOffer = solutionOffer;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    // Generate random values for channel and advertising budget
    private void generateRandomValues() {
        String[] possibleChannels = {"Online", "TV", "Radio", "Print"};
        Random random = new Random();

        this.channel = possibleChannels[random.nextInt(possibleChannels.length)];
        this.advertisingBudget = random.nextInt(10000) + 1000; // Random budget between 1000 and 11000
    }

    public void printAssignmentDetails() {
        System.out.println("Market Channel Assignment:");
        System.out.println("Channel: " + channel);
        System.out.println("Advertising Budget: $" + advertisingBudget);
        System.out.println("Associated Solution Offer: " + solutionOffer.getBundle());
    }
}