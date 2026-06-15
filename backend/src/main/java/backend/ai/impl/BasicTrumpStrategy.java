package backend.ai.impl;

import backend.ai.TrumpStrategy;
import backend.model.*;

public class BasicTrumpStrategy implements TrumpStrategy {

    @Override
    public Suit chooseTrump(Player player) {

        int clubs = 0;
        int diamonds = 0;
        int hearts = 0;
        int spades = 0;

        for (Card c : player.getHand()) {

            switch (c.getSuit()) {

                case CLUBS -> clubs++;
                case DIAMONDS -> diamonds++;
                case HEARTS -> hearts++;
                case SPADES -> spades++;
            }
        }

        int max = Math.max(
                Math.max(clubs, diamonds),
                Math.max(hearts, spades)
        );

        if (clubs == max) return Suit.CLUBS;
        if (diamonds == max) return Suit.DIAMONDS;
        if (hearts == max) return Suit.HEARTS;

        return Suit.SPADES;
    }
}