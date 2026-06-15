package backend.ai.impl;

import backend.ai.PlayStrategy;
import backend.engine.TrickEngine;
import backend.model.*;

public class BasicPlayStrategy implements PlayStrategy {

    @Override
    public Card chooseCard(
            Player player,
            TrickEngine trickEngine
    ) {

        Trick trick = trickEngine.getTrick();

        if (trick.getPlayedCards().isEmpty()) {

            return player.getHand().get(0);
        }

        Suit lead = trick.getLeadSuit();

        for (Card c : player.getHand()) {

            if (c.getSuit() == lead) {
                return c;
            }
        }

        Suit trump = trickEngine.getTrumpSuit();

        for (Card c : player.getHand()) {

            if (c.getSuit() == trump) {
                return c;
            }
        }

        return player.getHand().get(0);
    }
}