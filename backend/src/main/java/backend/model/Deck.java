package backend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        createDeck();
    }

    private void createDeck() {

        cards.clear();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    // =========================
    // CORE METHODS
    // =========================

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.remove(cards.size() - 1);
    }

    // =========================
    // COMPATIBILITY METHODS (IMPORTANT)
    // =========================

    public int size() {
        return cards.size();
    }

    public int remainingCards() {
        return cards.size(); // FIX for your tests
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}