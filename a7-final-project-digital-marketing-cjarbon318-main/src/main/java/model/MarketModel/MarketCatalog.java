package model.MarketModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarketCatalog {

    private List<Market> markets;

    public MarketCatalog() {
        this.markets = new ArrayList<>();
    }

    public List<Market> getMarkets() {
        return markets;
    }

    public void addMarket(Market market) {
        markets.add(market);
    }

    public void removeMarket(Market market) {
        markets.remove(market);
    }

    public void generateRandomMarkets(int numMarkets) {

        for (int i = 0; i < numMarkets; i++) {
            Market market = new Market("Market" + (i + 1));
            market.generateRandomValues();
            markets.add(market);
        }
    }

    public void displayMarketCatalogDetails() {
        System.out.println("Market Catalog Details:");
        for (Market market : markets) {
            System.out.println("Market Name: " + market.getName());
        }
    }
}
