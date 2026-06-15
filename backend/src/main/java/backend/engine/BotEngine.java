package backend.engine;

import backend.ai.impl.*;
import backend.model.*;

public class BotEngine {

    private final BasicBidStrategy bidStrategy =
            new BasicBidStrategy();

    private final BasicTrumpStrategy trumpStrategy =
            new BasicTrumpStrategy();

    private final BasicPartnerStrategy partnerStrategy =
            new BasicPartnerStrategy();

    private final BasicPlayStrategy playStrategy =
            new BasicPlayStrategy();

    public int makeBid(Player player, AuctionEngine auctionEngine) {

        Integer bid =
                bidStrategy.decideBid(player, auctionEngine);

        return bid == null ? -1 : bid;
    }

    public Suit chooseTrump(Player player) {

        return trumpStrategy.chooseTrump(player);
    }

    public Card[] choosePartnerCards(Player player) {

        return partnerStrategy.choosePartnerCards(player);
    }

    public Card playCard(
            Player player,
            TrickEngine trickEngine) {

        return playStrategy.chooseCard(
                player,
                trickEngine
        );
    }
}
