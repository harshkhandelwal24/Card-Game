package backend.ai.impl;

import backend.ai.PartnerStrategy;
import backend.model.*;

public class BasicPartnerStrategy implements PartnerStrategy {

    @Override
    public Card[] choosePartnerCards(Player player) {

        return new Card[]{

                new Card(Suit.SPADES, Rank.ACE),

                new Card(Suit.HEARTS, Rank.ACE)

        };
    }
}