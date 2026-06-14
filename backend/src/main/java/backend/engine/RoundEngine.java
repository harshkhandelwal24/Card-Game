package backend.engine;

import backend.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RoundEngine {

    // =========================
    // CORE STATE
    // =========================

    private final Round round;
    private final List<Player> players;

    private AuctionEngine auctionEngine;
    private TrickEngine trickEngine;

    private Deck deck;

    // =========================
    // INIT
    // =========================

    public RoundEngine(Round round, List<Player> players) {
        this.round = round;
        this.players = players;

        this.deck = new Deck();

        round.setDeck(deck);
        round.setTricks(new ArrayList<>());
        round.setCurrentTrickNumber(0);
        round.setState(RoundState.INIT);
    }

    

    // =====================================================
    // STEP 1: START ROUND
    // =====================================================

    public void startRound() {

        validatePlayers();

        deck.shuffle();

        dealCards();

        round.setState(RoundState.DEALT);

    }



    public void choosePartnerCards(Card card1, Card card2) {

    Player bidder = round.getAuction().getHighestBidder();

    if (bidder.getHand().contains(card1) ||
        bidder.getHand().contains(card2)) {
        throw new IllegalArgumentException(
                "Bidder cannot choose a card from their own hand");
    }

    round.setPartnerCard1(card1);
    round.setPartnerCard2(card2);

    Team team = new Team();
    team.addMember(bidder);

    for (Player p : players) {
        if (p.equals(bidder)) {
            continue;
        }

        if (p.getHand().contains(card1) ||
            p.getHand().contains(card2)) {
            team.addMember(p);
        }
    }

    round.setTeam(team);
    round.setState(RoundState.TEAM_SELECTED);
}

    private void validatePlayers() {
        if (players.size() != 6) {
            throw new IllegalStateException("Exactly 6 players required");
        }

        if (deck.size() != 48) {
            throw new IllegalStateException("Deck must contain 48 cards");
        }
    }

    // =====================================================
    // STEP 2: DEAL CARDS
    // =====================================================

    private void dealCards() {

        int cardsPerPlayer = 8;

        for (Player player : players) {
            for (int i = 0; i < cardsPerPlayer; i++) {
                player.receiveCard(deck.draw());
            }
        }
    }

    // =====================================================
    // STEP 3: AUCTION
    // =====================================================

    public void startAuction() {

        Auction auction = new Auction();
        round.setAuction(auction);

        auctionEngine = new AuctionEngine(auction, players);
        auctionEngine.startAuction();

        // AuctionResult result = auctionEngine.getResult();

        round.setState(RoundState.AUCTION_DONE);
    }

    // =====================================================
    // STEP 4: TRUMP
    // =====================================================

    public void setTrump(Player player, Suit trump) {

    if (round.getState() != RoundState.AUCTION_DONE) {
        throw new IllegalStateException("Trump only after auction");
    }

    Player winner = round.getAuction().getHighestBidder();

    if (winner == null) {
        throw new IllegalStateException("Auction winner not decided");
    }

    if (!winner.equals(player)) {
        throw new IllegalStateException("Only auction winner can select trump");
    }

    round.setTrumpSuit(trump);
    round.getAuction().setTrumpSuit(trump);

    round.setState(RoundState.TRUMP_SELECTED);
}

    // =====================================================
    // STEP 5: TEAM
    // =====================================================

    public void setTeam(Team team) {

        if (round.getState() != RoundState.TRUMP_SELECTED) {
            throw new IllegalStateException("Team only after trump");
        }

        round.setTeam(team);

        round.setState(RoundState.TEAM_SELECTED);

    }

    // =====================================================
    // STEP 6: PLAY PHASE
    // =====================================================

    public void startPlayPhase() {

    Player firstPlayer = round.getAuction().getHighestBidder();

    if (firstPlayer == null) {
        throw new IllegalStateException("Auction winner missing");
    }

    Trick trick = new Trick();
    trick.setTrumpSuit(round.getTrumpSuit());

    trickEngine = new TrickEngine(trick, players, round.getTrumpSuit());
    trickEngine.startTrick(firstPlayer);

    round.setState(RoundState.PLAYING);
}

    // =====================================================
    // STEP 7: PLAY CARD
    // =====================================================

    public void playCard(Player player, Card card) {

        if (round.getState() != RoundState.PLAYING) {
            throw new IllegalStateException("Not in PLAYING state");
        }

        trickEngine.playCard(player, card);

        if (trickEngine.getTrick().getState() == TrickState.COMPLETED) {
            handleTrickCompletion();
        }
    }

    // =====================================================
    // STEP 8: TRICK COMPLETION
    // =====================================================

    private void handleTrickCompletion() {

    Trick completed = trickEngine.getTrick();

    round.getTricks().add(completed);

    round.setCurrentTrickNumber(round.getCurrentTrickNumber() + 1);

    Player nextLeader = completed.getWinner();

    if (nextLeader == null) {
        throw new IllegalStateException("Trick winner is null");
    }

    // GAME END CONDITION
    if (round.getCurrentTrickNumber() >= 8) {
        round.setState(RoundState.COMPLETED);
        return;
    }

    // CREATE NEXT TRICK
    Trick nextTrick = new Trick();
    nextTrick.setTrumpSuit(round.getTrumpSuit());

    trickEngine = new TrickEngine(nextTrick, players, round.getTrumpSuit());
    trickEngine.startTrick(nextLeader);
}

    // =====================================================
// STEP 9: SCORING
// =====================================================

public void scoring() {

    if (round.getState() != RoundState.COMPLETED) {
        throw new IllegalStateException("Round not completed");
    }

    if (round.getTricks().size() != 8) {
        throw new IllegalStateException("All 8 tricks must be completed");
    }

    int biddingPoints = 0;
    int opponentPoints = 0;

    int bidValue = round.getAuction().getHighestBid();

    Set<Player> biddingTeam = round.getTeam().getMembers();

    // Count trick points
    for (Trick trick : round.getTricks()) {

        if (biddingTeam.contains(trick.getWinner())) {
            biddingPoints += trick.getPoints();
        } else {
            opponentPoints += trick.getPoints();
        }
    }

    boolean success = biddingPoints >= bidValue;

    RoundScore score = new RoundScore();

    score.setBidValue(bidValue);
    score.setBidSuccess(success);

    if (success) {

        // Bid successful
        score.setBiddingTeamPoints(bidValue);
        score.setOpponentTeamPoints(0);

    } else {

        // Bid failed
        score.setBiddingTeamPoints(0);
        score.setOpponentTeamPoints(opponentPoints);
    }

    round.setScore(score);

    round.setState(RoundState.COMPLETED);
}

    // =====================================================
    // GETTERS
    // =====================================================

    public Round getRound() {
        return round;
    }

    public AuctionEngine getAuctionEngine() {
        return auctionEngine;
    }

    public TrickEngine getTrickEngine() {
        return trickEngine;
    }
}