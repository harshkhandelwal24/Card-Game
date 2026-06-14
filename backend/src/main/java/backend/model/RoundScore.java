package backend.model;

import java.util.Objects;

/**
 * Represents the final scoring outcome of a single round.
 * This is a VALUE OBJECT (immutable-style usage recommended).
 */
public class RoundScore {

    private int biddingTeamPoints;
    private int opponentTeamPoints;
    private int bidValue;
    private boolean bidSuccess;

    public RoundScore() {
    }

    public RoundScore(int biddingTeamPoints,
                       int opponentTeamPoints,
                       int bidValue,
                       boolean bidSuccess) {
        this.biddingTeamPoints = biddingTeamPoints;
        this.opponentTeamPoints = opponentTeamPoints;
        this.bidValue = bidValue;
        this.bidSuccess = bidSuccess;
    }

    // ---------------- Getters ----------------

    public int getBiddingTeamPoints() {
        return biddingTeamPoints;
    }

    public int getOpponentTeamPoints() {
        return opponentTeamPoints;
    }

    public int getBidValue() {
        return bidValue;
    }

    public boolean isBidSuccess() {
        return bidSuccess;
    }

    // ---------------- Setters ----------------
    // Keep setters minimal but allowed for engine flexibility

    public void setBiddingTeamPoints(int biddingTeamPoints) {
        this.biddingTeamPoints = biddingTeamPoints;
    }

    public void setOpponentTeamPoints(int opponentTeamPoints) {
        this.opponentTeamPoints = opponentTeamPoints;
    }

    public void setBidValue(int bidValue) {
        this.bidValue = bidValue;
    }

    public void setBidSuccess(boolean bidSuccess) {
        this.bidSuccess = bidSuccess;
    }

    // ---------------- Utility ----------------

    public int getTotalPoints() {
        return biddingTeamPoints + opponentTeamPoints;
    }

    @Override
    public String toString() {
        return "RoundScore{" +
                "biddingTeamPoints=" + biddingTeamPoints +
                ", opponentTeamPoints=" + opponentTeamPoints +
                ", bidValue=" + bidValue +
                ", bidSuccess=" + bidSuccess +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoundScore)) return false;
        RoundScore that = (RoundScore) o;
        return biddingTeamPoints == that.biddingTeamPoints &&
                opponentTeamPoints == that.opponentTeamPoints &&
                bidValue == that.bidValue &&
                bidSuccess == that.bidSuccess;
    }

    @Override
    public int hashCode() {
        return Objects.hash(biddingTeamPoints, opponentTeamPoints, bidValue, bidSuccess);
    }
}   
