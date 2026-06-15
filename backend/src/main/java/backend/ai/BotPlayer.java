package backend.ai;

import backend.model.*;

public class BotPlayer {

    private final Player player;

    private BidStrategy bidStrategy;
    private TrumpStrategy trumpStrategy;
    private PartnerStrategy partnerStrategy;
    private PlayStrategy playStrategy;

    public BotPlayer(Player player) {

        this.player = player;

        bidStrategy = null;
        trumpStrategy = null;
        partnerStrategy = null;
        playStrategy = null;
    }

    public Player getPlayer() {
        return player;
    }

    public BidStrategy getBidStrategy() {
        return bidStrategy;
    }

    public void setBidStrategy(BidStrategy bidStrategy) {
        this.bidStrategy = bidStrategy;
    }

    public TrumpStrategy getTrumpStrategy() {
        return trumpStrategy;
    }

    public void setTrumpStrategy(TrumpStrategy trumpStrategy) {
        this.trumpStrategy = trumpStrategy;
    }

    public PartnerStrategy getPartnerStrategy() {
        return partnerStrategy;
    }

    public void setPartnerStrategy(PartnerStrategy partnerStrategy) {
        this.partnerStrategy = partnerStrategy;
    }

    public PlayStrategy getPlayStrategy() {
        return playStrategy;
    }

    public void setPlayStrategy(PlayStrategy playStrategy) {
        this.playStrategy = playStrategy;
    }
}