/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferOrderItem;

public class MarketCatalog {

    private ArrayList<Market> markets;

    public MarketCatalog() {
        this.markets = new ArrayList<>();
    }

    public ArrayList<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(ArrayList<Market> markets) {
        this.markets = markets;
    }

    public void addMarket(Market market) {
        this.markets.add(market);
    }
}