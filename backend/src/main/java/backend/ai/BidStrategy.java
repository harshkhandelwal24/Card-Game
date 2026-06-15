package backend.ai;

import backend.engine.AuctionEngine;
import backend.model.Player;

public interface BidStrategy {

    Integer decideBid(
            Player player,
            AuctionEngine auctionEngine
    );

}