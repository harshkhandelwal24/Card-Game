package backend.model;

import backend.engine.TrickEngine;

import java.util.ArrayList;
import java.util.List;

public class Round {

    // =========================
    // CORE GAME COMPONENTS
    // =========================

    private Deck deck;

    private Auction auction;

    private TrickEngine trickEngine;

    private Team team;

    private Suit trumpSuit;
    private RoundScore score;

    private Player dealer;

    private List<Trick> tricks = new ArrayList<>();

    private RoundState state;

    private int currentTrickNumber;

    // =========================
    // GETTERS / SETTERS
    // =========================

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setScore(RoundScore score) {
    this.score = score;
}

    public Card partnerCard1;
    public Card partnerCard2;


    public RoundScore getScore() {
        return score;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public TrickEngine getTrickEngine() {
        return trickEngine;
    }

    public void setTrickEngine(TrickEngine trickEngine) {
        this.trickEngine = trickEngine;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Suit getTrumpSuit() {
        return trumpSuit;
    }

    public void setTrumpSuit(Suit trumpSuit) {
        this.trumpSuit = trumpSuit;
    }

    public Player getDealer() {
        return dealer;
    }

    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public List<Trick> getTricks() {
        return tricks;
    }

    public void setTricks(List<Trick> tricks) {
        this.tricks = tricks;
    }

    public RoundState getState() {
        return state;
    }

    public void setState(RoundState state) {
        this.state = state;
    }

    public int getCurrentTrickNumber() {
        return currentTrickNumber;
    }

    public void setCurrentTrickNumber(int currentTrickNumber) {
        this.currentTrickNumber = currentTrickNumber;
    }

    // =========================
    // UTILITY METHODS (STATE ONLY)
    // =========================

    public void nextTrick() {
        this.currentTrickNumber++;
    }

    public void addTrick(Trick trick) {
        this.tricks.add(trick);
    }

    public Trick getCurrentTrick() {
        if (tricks.isEmpty()) return null;
        return tricks.get(tricks.size() - 1);
    }

    public Card getPartnerCard1() {
        return partnerCard1;
    }

    public void setPartnerCard1(Card partnerCard1) {
        this.partnerCard1 = partnerCard1;
    }

    public Card getPartnerCard2() {
        return partnerCard2;
    }

    public void setPartnerCard2(Card partnerCard2) {
        this.partnerCard2 = partnerCard2;
    }
}
