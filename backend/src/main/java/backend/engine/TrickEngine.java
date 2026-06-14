package backend.engine;

import backend.exception.InvalidMoveException;
import backend.model.*;
import backend.util.CardPointCalculator;

import java.util.List;
import java.util.Map;

public class TrickEngine {

    private final Trick trick;
    private final List<Player> players;
    private final Suit trumpSuit;

    private int currentTurnIndex;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public TrickEngine(Trick trick,
                       List<Player> players,
                       Suit trumpSuit) {

        this.trick = trick;
        this.players = players;
        this.trumpSuit = trumpSuit;

        trick.setTrumpSuit(trumpSuit);
    }

    // =====================================================
    // START TRICK
    // =====================================================

    public void startTrick(Player leader) {

        int idx = players.indexOf(leader);

        if (idx == -1) {
            throw new IllegalArgumentException("Leader not found");
        }

        trick.getPlayedCards().clear();
        trick.setLeader(leader);
        trick.setLeadSuit(null);
        trick.setWinner(null);
        trick.setPoints(0);
        trick.setState(TrickState.OPEN);

        currentTurnIndex = idx;
    }

    // =====================================================
    // PLAY CARD
    // =====================================================

    public void playCard(Player player, Card card) {

        if (trick.getState() != TrickState.OPEN) {
            throw new IllegalStateException("Trick already completed");
        }

        Player expected = players.get(currentTurnIndex);

        if (!expected.equals(player)) {
            throw new IllegalStateException(
                    "Expected "
                            + expected.getName()
                            + " but got "
                            + player.getName()
            );
        }

        if (!player.getHand().contains(card)) {
            throw new InvalidMoveException("Card not in hand");
        }

        // follow suit

        if (!trick.getPlayedCards().isEmpty()) {

            Suit lead = trick.getLeadSuit();

            if (player.hasSuit(lead) && card.getSuit() != lead) {
                throw new InvalidMoveException(
                        "Must follow lead suit"
                );
            }
        }

        // first card

        if (trick.getPlayedCards().isEmpty()) {
            trick.setLeadSuit(card.getSuit());
        }

        player.playCard(card);

        trick.getPlayedCards().put(player, card);

        // trick over

        if (trick.getPlayedCards().size() == players.size()) {

            resolveWinner();
            return;
        }

        currentTurnIndex++;

        if (currentTurnIndex >= players.size()) {
            currentTurnIndex = 0;
        }
    }

    // =====================================================
    // WINNER
    // =====================================================

    private void resolveWinner() {

        Player winner = null;
        Card best = null;

        int total = 0;

        Suit lead = trick.getLeadSuit();

        for (Map.Entry<Player, Card> e : trick.getPlayedCards().entrySet()) {

            Card c = e.getValue();

            total += CardPointCalculator.getPointValue(
                    c.getRank(),
                    c.getSuit()
            );

            if (best == null) {
                best = c;
                winner = e.getKey();
                continue;
            }

            boolean cTrump = c.getSuit() == trumpSuit;
            boolean bTrump = best.getSuit() == trumpSuit;

            if (cTrump && !bTrump) {
                best = c;
                winner = e.getKey();
                continue;
            }

            if (cTrump && bTrump) {

                if (rank(c) > rank(best)) {
                    best = c;
                    winner = e.getKey();
                }

                continue;
            }

            if (!bTrump &&
                    c.getSuit() == lead &&
                    best.getSuit() != lead) {

                best = c;
                winner = e.getKey();
                continue;
            }

            if (c.getSuit() == lead &&
                    best.getSuit() == lead &&
                    rank(c) > rank(best)) {

                best = c;
                winner = e.getKey();
            }
        }

        trick.setWinner(winner);
        trick.setPoints(total);
        trick.setState(TrickState.COMPLETED);

        // prepare next trick leader

        currentTurnIndex = players.indexOf(winner);
    }

    // =====================================================
    // RANK
    // =====================================================

    private int rank(Card c) {

        switch (c.getRank()) {

            case SEVEN:
                return 1;

            case EIGHT:
                return 2;

            case NINE:
                return 3;

            case TEN:
                return 4;

            case JACK:
                return 5;

            case QUEEN:
                return 6;

            case KING:
                return 7;

            case ACE:
                return 8;

            default:
                return 0;
        }
    }

    // =====================================================
    // GETTERS
    // =====================================================

    public Trick getTrick() {
        return trick;
    }

    public Player getCurrentPlayer() {
        return players.get(currentTurnIndex);
    }

    public Suit getTrumpSuit() {
        return trumpSuit;
    }
}