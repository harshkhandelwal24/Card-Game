// package backend.model;

// import backend.engine.TrickEngine;
// import backend.exception.InvalidMoveException;
// import org.junit.jupiter.api.Test;

// import java.util.*;

// import static org.junit.jupiter.api.Assertions.*;

// public class TrickEngineTest {

//     // =====================================================
//     // HELPERS
//     // =====================================================

//     private List<Player> createPlayers() {
//         List<Player> players = new ArrayList<>();
//         for (int i = 1; i <= 6; i++) {
//             players.add(new Player("P" + i));
//         }
//         return players;
//     }

//     private Card card(Suit suit, Rank rank) {
//         return new Card(suit, rank);
//     }

//     private void give(Player p, Card... cards) {
//         for (Card c : cards) {
//             p.receiveCard(c);
//         }
//     }

//     private void dealUniformHands(List<Player> players) {
//         for (Player p : players) {
//             give(p,
//                     card(Suit.HEARTS, Rank.ACE),
//                     card(Suit.HEARTS, Rank.KING),
//                     card(Suit.HEARTS, Rank.QUEEN),
//                     card(Suit.HEARTS, Rank.JACK),
//                     card(Suit.HEARTS, Rank.TEN),
//                     card(Suit.HEARTS, Rank.NINE)
//             );
//         }
//     }

//     // =====================================================
//     // TEST 1: VALID PLAY SEQUENCE
//     // =====================================================
//     @Test
//     void testValidPlaySequence() {

//         List<Player> players = createPlayers();
//         dealUniformHands(players);

//         Trick trick = new Trick();
//         TrickEngine engine = new TrickEngine(trick, players, new HashMap<>(), Suit.HEARTS);

//         engine.startTrick(players.get(0));

//         engine.playCard(players.get(0), card(Suit.HEARTS, Rank.ACE));
//         engine.playCard(players.get(1), card(Suit.HEARTS, Rank.KING));
//         engine.playCard(players.get(2), card(Suit.HEARTS, Rank.QUEEN));
//         engine.playCard(players.get(3), card(Suit.HEARTS, Rank.JACK));
//         engine.playCard(players.get(4), card(Suit.HEARTS, Rank.TEN));
//         engine.playCard(players.get(5), card(Suit.HEARTS, Rank.NINE));

//         assertEquals(TrickState.COMPLETED, trick.getState());
//         assertNotNull(trick.getWinner());
//     }

//     // =====================================================
//     // TEST 2: SUIT VIOLATION
//     // =====================================================
//     @Test
//     void testSuitViolationRejected() {

//         List<Player> players = createPlayers();

//         Player p1 = players.get(0);
//         Player p2 = players.get(1);

//         give(p1,
//                 card(Suit.HEARTS, Rank.ACE)
//         );

//         give(p2,
//                 card(Suit.HEARTS, Rank.KING),
//                 card(Suit.CLUBS, Rank.ACE)
//         );

//         Trick trick = new Trick();
//         TrickEngine engine = new TrickEngine(trick, players, new HashMap<>(), Suit.HEARTS);

//         engine.startTrick(p1);

//         engine.playCard(p1, card(Suit.HEARTS, Rank.ACE));

//         assertThrows(InvalidMoveException.class, () ->
//                 engine.playCard(p2, card(Suit.CLUBS, Rank.ACE))
//         );
//     }

//     // =====================================================
//     // TEST 3: TURN VIOLATION
//     // =====================================================
//     @Test
//     void testTurnViolationRejected() {

//         List<Player> players = createPlayers();
//         dealUniformHands(players);

//         Trick trick = new Trick();
//         TrickEngine engine = new TrickEngine(trick, players, new HashMap<>(), Suit.HEARTS);

//         engine.startTrick(players.get(0));

//         assertThrows(IllegalStateException.class, () ->
//                 engine.playCard(players.get(1), card(Suit.HEARTS, Rank.ACE))
//         );
//     }

//     // =====================================================
//     // TEST 4: LEAD SUIT ASSIGNMENT
//     // =====================================================
//     @Test
// void testLeadSuitAssignment() {

//     List<Player> players = createPlayers();

//     Player p0 = players.get(0);

//     give(p0,
//             card(Suit.CLUBS, Rank.ACE)
//     );

//     Trick trick = new Trick();
//     TrickEngine engine = new TrickEngine(trick, players, new HashMap<>(), Suit.CLUBS);

//     engine.startTrick(p0);

//     engine.playCard(p0, card(Suit.CLUBS, Rank.ACE));

//     assertEquals(Suit.CLUBS, trick.getLeadSuit());
// }

//     // =====================================================
//     // TEST 5: TRUMP OVERRIDE
//     // =====================================================
//     @Test
// void testTrumpOverrideWins() {

//     List<Player> players = createPlayers();

//     Player p0 = players.get(0);
//     Player p1 = players.get(1);

//     // give ONLY required cards
//     give(p0, card(Suit.HEARTS, Rank.ACE));
//     give(p1, card(Suit.SPADES, Rank.TEN)); // TRUMP

//     give(players.get(2), card(Suit.HEARTS, Rank.KING));
//     give(players.get(3), card(Suit.HEARTS, Rank.QUEEN));
//     give(players.get(4), card(Suit.HEARTS, Rank.JACK));
//     give(players.get(5), card(Suit.HEARTS, Rank.TEN));

//     Trick trick = new Trick();
//     trick.setTrumpSuit(Suit.SPADES);

//     TrickEngine engine = new TrickEngine(trick, players, new HashMap<>(), Suit.SPADES);

//     engine.startTrick(p0);

//     engine.playCard(p0, card(Suit.HEARTS, Rank.ACE));
//     engine.playCard(p1, card(Suit.SPADES, Rank.TEN)); // trump
//     engine.playCard(players.get(2), card(Suit.HEARTS, Rank.KING));
//     engine.playCard(players.get(3), card(Suit.HEARTS, Rank.QUEEN));
//     engine.playCard(players.get(4), card(Suit.HEARTS, Rank.JACK));
//     engine.playCard(players.get(5), card(Suit.HEARTS, Rank.TEN));

//     assertEquals(p1, trick.getWinner());
// }

//     // =====================================================
//     // TEST 6: NO TRUMP FALLBACK
//     // =====================================================
//     @Test
//     void testNoTrumpFallback() {

//         List<Player> players = createPlayers();
//         dealUniformHands(players);

//         Trick trick = new Trick();
//         trick.setTrumpSuit(Suit.SPADES);

//         TrickEngine engine = new TrickEngine(trick, players, new HashMap<>(), Suit.SPADES);

//         engine.startTrick(players.get(0));

//         engine.playCard(players.get(0), card(Suit.HEARTS, Rank.TEN));
//         engine.playCard(players.get(1), card(Suit.HEARTS, Rank.ACE));
//         engine.playCard(players.get(2), card(Suit.HEARTS, Rank.KING));
//         engine.playCard(players.get(3), card(Suit.HEARTS, Rank.QUEEN));
//         engine.playCard(players.get(4), card(Suit.HEARTS, Rank.JACK));
//         engine.playCard(players.get(5), card(Suit.HEARTS, Rank.NINE));

//         assertEquals(players.get(1), trick.getWinner());
//     }

//     // =====================================================
//     // TEST 7: POINTS CALCULATION
//     // =====================================================
//     @Test
//     void testPointsCalculation() {

//         List<Player> players = createPlayers();

//         Trick trick = new Trick();
//         TrickEngine engine = new TrickEngine(trick, players, new HashMap<>(), Suit.SPADES);

//         give(players.get(0), card(Suit.SPADES, Rank.THREE));
//         give(players.get(1), card(Suit.HEARTS, Rank.FIVE));
//         give(players.get(2), card(Suit.CLUBS, Rank.ACE));
//         give(players.get(3), card(Suit.DIAMONDS, Rank.TEN));
//         give(players.get(4), card(Suit.HEARTS, Rank.KING));
//         give(players.get(5), card(Suit.SPADES, Rank.SEVEN));

//         engine.startTrick(players.get(0));

//         engine.playCard(players.get(0), card(Suit.SPADES, Rank.THREE));
//         engine.playCard(players.get(1), card(Suit.HEARTS, Rank.FIVE));
//         engine.playCard(players.get(2), card(Suit.CLUBS, Rank.ACE));
//         engine.playCard(players.get(3), card(Suit.DIAMONDS, Rank.TEN));
//         engine.playCard(players.get(4), card(Suit.HEARTS, Rank.KING));
//         engine.playCard(players.get(5), card(Suit.SPADES, Rank.SEVEN));

//         int expected = 30 + 5 + 10 + 10 + 10 + 0;

//         assertEquals(expected, trick.getPoints());
//     }

//     // =====================================================
//     // TEST 8: TRICK COMPLETION
//     // =====================================================
//     @Test
// void testTrickCompletion() {

//     List<Player> players = createPlayers();
//     dealUniformHands(players);

//     Trick trick = new Trick();
//     TrickEngine engine = new TrickEngine(trick, players, new HashMap<>(), Suit.HEARTS);

//     engine.startTrick(players.get(0));

//     engine.playCard(players.get(0), card(Suit.HEARTS, Rank.ACE));
//     engine.playCard(players.get(1), card(Suit.HEARTS, Rank.KING));
//     engine.playCard(players.get(2), card(Suit.HEARTS, Rank.QUEEN));
//     engine.playCard(players.get(3), card(Suit.HEARTS, Rank.JACK));
//     engine.playCard(players.get(4), card(Suit.HEARTS, Rank.TEN));
//     engine.playCard(players.get(5), card(Suit.HEARTS, Rank.NINE));

//     assertEquals(TrickState.COMPLETED, trick.getState());
// }
// @Test
// void testTrumpWinsWhenVoidInLeadSuit() {

//     List<Player> players = createPlayers();

//     Player p1 = players.get(0);
//     Player p2 = players.get(1);
//     Player p3 = players.get(2);
//     Player p4 = players.get(3);
//     Player p5 = players.get(4);
//     Player p6 = players.get(5);

//     // Lead suit = HEARTS (played by P1)
//     give(p1, new Card(Suit.HEARTS, Rank.FIVE));

//     // P2 has HEARTS → must follow
//     give(p2, new Card(Suit.HEARTS, Rank.KING));

//     // P3 has HEARTS → follows
//     give(p3, new Card(Suit.HEARTS, Rank.QUEEN));

//     // P4 is VOID in HEARTS → plays TRUMP (SPADES)
//     give(p4, new Card(Suit.SPADES, Rank.TEN));

//     // others follow HEARTS
//     give(p5, new Card(Suit.HEARTS, Rank.JACK));
//     give(p6, new Card(Suit.HEARTS, Rank.NINE));

//     Trick trick = new Trick();
//     trick.setTrumpSuit(Suit.SPADES);

//     TrickEngine engine = new TrickEngine(trick, players, new HashMap<>(), Suit.SPADES);

//     engine.startTrick(p1);

//     engine.playCard(p1, new Card(Suit.HEARTS, Rank.FIVE));
//     engine.playCard(p2, new Card(Suit.HEARTS, Rank.KING));
//     engine.playCard(p3, new Card(Suit.HEARTS, Rank.QUEEN));

//     // P4 plays TRUMP because no HEARTS
//     engine.playCard(p4, new Card(Suit.SPADES, Rank.TEN));

//     engine.playCard(p5, new Card(Suit.HEARTS, Rank.JACK));
//     engine.playCard(p6, new Card(Suit.HEARTS, Rank.NINE));

//     // ASSERT: trump wins
//     assertEquals(p4, trick.getWinner());
// }
// @Test
// void testVoidInLeadAndTrumpDoesNotAffectWinner() {

//     List<Player> players = createPlayers();

//     Player p1 = players.get(0);
//     Player p2 = players.get(1);
//     Player p3 = players.get(2);
//     Player p4 = players.get(3); // VOID
//     Player p5 = players.get(4);
//     Player p6 = players.get(5);

//     // Lead suit = HEARTS
//     give(p1, new Card(Suit.HEARTS, Rank.FIVE));

//     give(p2, new Card(Suit.HEARTS, Rank.KING));
//     give(p3, new Card(Suit.HEARTS, Rank.QUEEN));

//     // P4 is VOID in HEARTS and SPADES (trump)
//     give(p4, new Card(Suit.CLUBS, Rank.ACE));

//     give(p5, new Card(Suit.HEARTS, Rank.JACK));
//     give(p6, new Card(Suit.HEARTS, Rank.NINE));

//     Trick trick = new Trick();
//     trick.setTrumpSuit(Suit.SPADES);

//     TrickEngine engine = new TrickEngine(trick, players, new HashMap<>(), Suit.SPADES);

//     engine.startTrick(p1);

//     engine.playCard(p1, new Card(Suit.HEARTS, Rank.FIVE));
//     engine.playCard(p2, new Card(Suit.HEARTS, Rank.KING));
//     engine.playCard(p3, new Card(Suit.HEARTS, Rank.QUEEN));

//     // VOID player plays CLUBS (irrelevant)
//     engine.playCard(p4, new Card(Suit.CLUBS, Rank.ACE));

//     engine.playCard(p5, new Card(Suit.HEARTS, Rank.JACK));
//     engine.playCard(p6, new Card(Suit.HEARTS, Rank.NINE));

//     // ASSERT: highest HEARTS still wins (no trump played)
//     assertEquals(p2, trick.getWinner());
// }
// }