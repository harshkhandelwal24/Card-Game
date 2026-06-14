package backend.model;

import java.util.HashMap;
import java.util.Map;

public class Trick {

    private Player leader;

    private Map<Player, Card> playedCards = new HashMap<>();

    private Suit leadSuit;

    private Suit trumpSuit;

    private Player winner;

    private int points;

    private TrickState state = TrickState.OPEN;

    // =========================
    // GETTERS
    // =========================

    public Player getLeader() {
        return leader;
    }

    public Map<Player, Card> getPlayedCards() {
        return playedCards;
    }

    public Suit getLeadSuit() {
        return leadSuit;
    }

    public Suit getTrumpSuit() {
        return trumpSuit;
    }

    public Player getWinner() {
        return winner;
    }

    public int getPoints() {
        return points;
    }

    public TrickState getState() {
        return state;
    }

    // =========================
    // SETTERS (STATE ONLY)
    // =========================

    public void setLeader(Player leader) {
        this.leader = leader;
    }

    public void setLeadSuit(Suit leadSuit) {
        this.leadSuit = leadSuit;
    }

    public void setTrumpSuit(Suit trumpSuit) {
        this.trumpSuit = trumpSuit;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setState(TrickState state) {
        this.state = state;
    }
}