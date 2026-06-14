
package backend.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void testReceiveCard() {

        Player player = new Player("Alice", 1);

        assertEquals(0, player.getHand().size());

        player.receiveCard(new Card(Suit.HEARTS, Rank.ACE));

        assertEquals(1, player.getHand().size());
    }

    @Test
    void testPlayCard() {

        Player player = new Player("Alice", 1);

        Card card1 = new Card(Suit.HEARTS, Rank.ACE);
        Card card2 = new Card(Suit.CLUBS, Rank.FIVE);

        player.receiveCard(card1);
        player.receiveCard(card2);

        assertEquals(2, player.getHand().size());

        player.playCard(card1);

        assertEquals(1, player.getHand().size());
        assertFalse(player.getHand().contains(card1));
    }

    @Test
    void testPlayMissingCardThrowsException() {

        Player player = new Player("Alice", 1);

        Card card = new Card(Suit.SPADES, Rank.KING);

        assertThrows(
                IllegalArgumentException.class,
                () -> player.playCard(card)
        );
    }

    @Test
    void testHasSuit() {

        Player player = new Player("Alice", 1);

        player.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player.receiveCard(new Card(Suit.CLUBS, Rank.FIVE));
        player.receiveCard(new Card(Suit.SPADES, Rank.KING));

        assertTrue(player.hasSuit(Suit.HEARTS));

        assertFalse(player.hasSuit(Suit.DIAMONDS));
    }

    @Test
    void testClearHand() {

        Player player = new Player("Alice", 1);

        for (Rank rank : Rank.values()) {
            player.receiveCard(new Card(Suit.HEARTS, rank));
        }

        assertEquals(12, player.getHand().size());

        player.clearHand();

        assertEquals(0, player.getHand().size());
    }

}
