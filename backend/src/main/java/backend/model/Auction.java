package backend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Auction {

    private List<Bid> bids = new ArrayList<>();

    private Player highestBidder;
    private int highestBid;

    private Set<Player> passedPlayers = new HashSet<>();

    private Player currentTurn;

    private AuctionState state = AuctionState.WAITING;
    private Suit trumpSuit;

    public Suit getTrumpSuit() {
        return trumpSuit;
    }

    public void setTrumpSuit(Suit trumpSuit) {
        this.trumpSuit = trumpSuit;
    }

    // =========================
    // GETTERS ONLY (NO SET LISTS)
    // =========================

    public List<Bid> getBids() {
        return bids;
    }

    public Player getHighestBidder() {
        return highestBidder;
    }

    public int getHighestBid() {
        return highestBid;
    }

    public Set<Player> getPassedPlayers() {
        return passedPlayers;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    public AuctionState getState() {
        return state;
    }

    // =========================
    // SETTERS (ONLY FOR STATE FIELDS)
    // =========================

    public void setHighestBidder(Player highestBidder) {
        this.highestBidder = highestBidder;
    }

    public void setHighestBid(int highestBid) {
        this.highestBid = highestBid;
    }

    public void setCurrentTurn(Player currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void setState(AuctionState state) {
        this.state = state;
    }
}