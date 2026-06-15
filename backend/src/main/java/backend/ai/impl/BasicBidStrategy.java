package backend.ai.impl;

import backend.ai.BidStrategy;
import backend.engine.AuctionEngine;
import backend.model.Player;

public class BasicBidStrategy implements BidStrategy {

    @Override
    public Integer decideBid(
            Player player,
            AuctionEngine auctionEngine
    ) {

        int current = auctionEngine
                .getAuction()
                .getHighestBid();

        if (current >= 120) {
            return null;
        }

        return current + 10;
    }
}