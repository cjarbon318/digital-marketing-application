package model.CustomerManagement;

import java.util.ArrayList;
import com.github.javafaker.Faker;
import model.MarketModel.Channel;

public class ChannelCatalog {
    private ArrayList<Channel> channels;
    private Faker faker;

    public ChannelCatalog() {
        this.channels = new ArrayList<>();
        this.faker = new Faker();
    }

    public void addChannel(Channel channel) {
        channels.add(channel);
    }

    public void removeChannel(Channel channel) {
        channels.remove(channel);
    }

    public Channel getChannelByName(String channelName) {
        for (Channel channel : channels) {
            if (channel.getChannelName().equals(channelName)) {
                return channel;
            }
        }
        return null;
    }

    public ArrayList<Channel> getAllChannels() {
        return channels;
    }

    public void createAndAddRandomChannel() {
        String channelName = generateRandomChannelName();
        Channel newChannel = new Channel(channelName, null);
        addChannel(newChannel);
    }

    private String generateRandomChannelName() {
        String channelType = faker.company().industry();
        String channelModifier = faker.company().buzzword();
        return channelType + " " + channelModifier + " Channel";
    }
}