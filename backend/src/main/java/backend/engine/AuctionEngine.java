package backend.engine;

import backend.model.*;

import java.util.List;
import java.util.Random;

public class AuctionEngine {

    private final Auction auction;
    private final List<Player> players;

    private int currentIndex = 0;
    private boolean started = false;

    private int passCount = 0;

    public AuctionEngine(Auction auction, List<Player> players) {
        this.auction = auction;
        this.players = players;
    }

    // =====================================================
    // START AUCTION
    // =====================================================
    public void startAuction() {

        if (players.size() != 6) {
            throw new IllegalStateException("Exactly 6 players required");
        }

        auction.setState(AuctionState.IN_PROGRESS);
        started = true;

        currentIndex = 0;
        passCount = 0;
    }

    // =====================================================
    // PLACE BID
    // =====================================================
    public void placeBid(Player player, int bid) {

    // System.out.println(player.getName() + " bids " + bid);

    // existing validation

    if (bid <= auction.getHighestBid()) {
        throw new IllegalStateException("Bid must be higher than current bid");
    }

    auction.setHighestBid(bid);
    auction.setHighestBidder(player);

    System.out.println(
        "Highest bid = " +
        auction.getHighestBid() +
        " by " +
        auction.getHighestBidder().getName()
    );

    advance();
}

    // =====================================================
    // PASS
    // =====================================================
    public void pass(Player player) {

    // System.out.println(player.getName() + " passes");

    advance();
}

    // =====================================================
    // AUTO RESOLVE AUCTION
    // =====================================================
    private void checkAutoResolve() {

        // CASE 1: everyone passed once OR full cycle pass
        if (passCount >= players.size()) {

            if (auction.getHighestBidder() == null) {
                Random r = new Random();

                Player winner = players.get(r.nextInt(players.size()));

                auction.setHighestBidder(winner);
                auction.setHighestBid(150);
            }

            auction.setState(AuctionState.TRUMP_SELECTION);
        }
    }

    // =====================================================
    // FINAL TRUMP STEP
    // =====================================================
    public void selectTrump(Player player, Suit suit) {

        if (auction.getState() != AuctionState.TRUMP_SELECTION) {
            throw new IllegalStateException("Trump not allowed now");
        }

        if (auction.getHighestBidder() == null) {
            throw new IllegalStateException("No auction winner");
        }

        if (!auction.getHighestBidder().getId().equals(player.getId())) {
            throw new IllegalStateException("Only winner can select trump");
        }

        auction.setTrumpSuit(suit);
        auction.setState(AuctionState.COMPLETED);
    }

    // =====================================================
    // FINALIZE (SAFETY METHOD)
    // =====================================================
    public void finalizeAuction() {

    if (auction.getHighestBidder() == null) {

        Random random = new Random();

        Player winner = players.get(random.nextInt(players.size()));

        auction.setHighestBidder(winner);
        auction.setHighestBid(150);

        System.out.println(
            "No bids. Random winner = "
            + winner.getName()
            + " Bid = 150"
        );
    }

    auction.setState(AuctionState.COMPLETED);

    System.out.println(
        "Final Winner = "
        + auction.getHighestBidder().getName()
        + " Final Bid = "
        + auction.getHighestBid()
    );
}

    // =====================================================
    // RESULT
    // =====================================================
    public AuctionResult getResult() {

        if (auction.getState() != AuctionState.COMPLETED) {
            throw new IllegalStateException(
                    "Auction not complete (trump not selected)"
            );
        }

        return new AuctionResult(
                auction.getHighestBidder(),
                auction.getHighestBid(),
                auction.getTrumpSuit()
        );
    }

    // =====================================================
    // INTERNAL HELPERS
    // =====================================================
    private void validateActive() {
        if (!started) {
            throw new IllegalStateException("Auction not started");
        }
    }

    private void validateTurn(Player player) {
        Player expected = players.get(currentIndex);

        if (!expected.getId().equals(player.getId())) {
            throw new IllegalStateException("Not your turn");
        }
    }

    private void advance() {
        currentIndex = (currentIndex + 1) % players.size();
    }

    // =====================================================
    // GETTERS
    // =====================================================
    public Auction getAuction() {
        return auction;
    }

    public Player getCurrentWinner() {
        return auction.getHighestBidder();
    }
}