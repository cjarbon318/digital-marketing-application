/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;
import model.MarketModel.MarketChannelAssignment;

public class Channel {

    String channelname;
    MarketChannelAssignment marketChannelAssignment;

    public Channel(String channelname, MarketChannelAssignment marketChannelAssignment) {
        this.channelname = channelname;
        this.marketChannelAssignment = marketChannelAssignment;
    }

    public String getChannelName() {
        return channelname;
    }

    public void setChannelName(String channelname) {
        this.channelname = channelname;
    }

    public MarketChannelAssignment getMarketChannelAssignment() {
        return marketChannelAssignment;
    }

    public void setMarketChannelAssignment(MarketChannelAssignment marketChannelAssignment) {
        this.marketChannelAssignment = marketChannelAssignment;
    }
}