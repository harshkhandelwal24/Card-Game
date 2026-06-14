// package backend.sim;

// import backend.engine.TrickEngine;
// import backend.exception.InvalidMoveException;
// import backend.model.*;

// import java.util.*;

// public class TrickSimulation {

//     public static void main(String[] args) {

//         // =========================
//         // Step 1: Create players
//         // =========================
//         List<Player> players = new ArrayList<>();
//         for (int i = 1; i <= 6; i++) {
//             players.add(new Player("P" + i));
//         }

//         Player p1 = players.get(0);
//         Player p2 = players.get(1);
//         Player p3 = players.get(2);
//         Player p4 = players.get(3);
//         Player p5 = players.get(4);
//         Player p6 = players.get(5);

//         // =========================
//         // Step 2: Assign cards
//         // =========================

//         // Lead suit = HEARTS
//         give(p1, new Card(Suit.HEARTS, Rank.FIVE));
//         give(p2, new Card(Suit.HEARTS, Rank.KING));
//         give(p3, new Card(Suit.HEARTS, Rank.QUEEN));

//         // P4 is VOID in BOTH HEARTS and SPADES (trump)
//         give(p4, new Card(Suit.CLUBS, Rank.ACE));

//         give(p5, new Card(Suit.HEARTS, Rank.JACK));
//         give(p6, new Card(Suit.HEARTS, Rank.NINE));

//         // =========================
//         // Step 3: Setup trick
//         // =========================
//         Trick trick = new Trick();
//         trick.setTrumpSuit(Suit.SPADES);

//         TrickEngine engine = new TrickEngine(trick, players, new HashMap<>(), Suit.SPADES);

//         engine.startTrick(p1);

//         System.out.println("=== TRICK START ===");

//         // =========================
//         // Step 4: Play sequence
//         // =========================

//         play(engine, p1, new Card(Suit.HEARTS, Rank.FIVE));
//         play(engine, p2, new Card(Suit.HEARTS, Rank.KING));
//         play(engine, p3, new Card(Suit.HEARTS, Rank.QUEEN));

//         // VOID in both lead + trump
//         play(engine, p4, new Card(Suit.CLUBS, Rank.ACE));

//         play(engine, p5, new Card(Suit.HEARTS, Rank.JACK));
//         play(engine, p6, new Card(Suit.HEARTS, Rank.NINE));

//         // =========================
//         // Step 5: Result
//         // =========================
//         System.out.println("\n=== TRICK RESULT ===");
//         System.out.println("Winner: " + trick.getWinner().getName());
//         System.out.println("Lead Suit: " + trick.getLeadSuit());
//         System.out.println("Trump Suit: " + trick.getTrumpSuit());
//         System.out.println("Points: " + trick.getPoints());
//         System.out.println("State: " + trick.getState());
//     }

//     // =========================
//     // SAFE PLAY WRAPPER
//     // =========================
//     private static void play(TrickEngine engine, Player player, Card card) {
//         try {
//             System.out.println(player.getName() + " plays " + card);
//             engine.playCard(player, card);
//         } catch (InvalidMoveException e) {
//             System.out.println("INVALID MOVE by " + player.getName() + ": " + e.getMessage());
//         }
//     }

//     // =========================
//     // Helper method
//     // =========================
//     private static void give(Player p, Card... cards) {
//         for (Card c : cards) {
//             p.receiveCard(c);
//         }
//     }
// }