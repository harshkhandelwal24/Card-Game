package backend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Player {

    private final UUID id;
    private final String name;
    private int seatNumber;

    private final List<Card> hand;

    private boolean connected;
    private boolean ready;
    private boolean passedAuction;

    private int totalScore;

    // public Player(String name, int seatNumber) {
    //     this.id = UUID.randomUUID();
    //     this.name = name;
    //     this.seatNumber = 0;
    //     this.hand = new ArrayList<>();

    //     this.connected = true;
    //     this.ready = false;
    //     this.passedAuction = false;
    //     this.totalScore = 0;
    // }

    public Player(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.seatNumber = 0; // assigned by Room
    this.hand = new ArrayList<>();
    this.connected = true;
    this.ready = false;
    this.passedAuction = false;
    this.totalScore = 0;
}

    // =========================
    // Card Operations
    // =========================

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public Card playCard(Card card) {

        if (!hand.remove(card)) {
            throw new IllegalArgumentException("Player does not have this card.");
        }

        return card;
    }

    public boolean hasSuit(Suit suit) {

        for (Card card : hand) {

            if (card.getSuit() == suit) {
                return true;
            }

        }

        return false;
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public void clearHand() {
        hand.clear();
    }

    // =========================
    // Ready Status
    // =========================

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isReady() {
        return ready;
    }

    // =========================
    // Round Reset
    // =========================

    public void resetForNewRound() {
        hand.clear();
        ready = false;
        passedAuction = false;
    }

    // =========================
    // Getters & Setters
    // =========================

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
    
    public void setSeatNumber(int seatNumber) {
    this.seatNumber = seatNumber;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean hasPassedAuction() {
        return passedAuction;
    }

    public void setPassedAuction(boolean passedAuction) {
        this.passedAuction = passedAuction;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void addScore(int points) {
        this.totalScore += points;
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", seat=" + seatNumber +
                ", score=" + totalScore +
                '}';
    }
}
