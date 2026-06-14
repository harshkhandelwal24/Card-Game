package backend.model;

import java.util.Objects;

public class AuctionResult {

    private final Player winner;
    private final int finalBid;
    private final Suit trumpSuit;

    public AuctionResult(Player winner, int finalBid, Suit trumpSuit) {
        this.winner = winner;
        this.finalBid = finalBid;
        this.trumpSuit = trumpSuit;
    }

    // =========================
    // GETTERS
    // =========================

    public Player getWinner() {
        return winner;
    }

    public int getFinalBid() {
        return finalBid;
    }

    public Suit getTrumpSuit() {
        return trumpSuit;
    }

    // =========================
    // UTILITY
    // =========================

    public boolean isTrumpSet() {
        return trumpSuit != null;
    }

    @Override
    public String toString() {
        return "AuctionResult{" +
                "winner=" + (winner != null ? winner.getName() : "null") +
                ", finalBid=" + finalBid +
                ", trumpSuit=" + trumpSuit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuctionResult)) return false;

        AuctionResult that = (AuctionResult) o;

        return finalBid == that.finalBid &&
                Objects.equals(winner, that.winner) &&
                Objects.equals(trumpSuit, that.trumpSuit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winner, finalBid, trumpSuit);
    }
}