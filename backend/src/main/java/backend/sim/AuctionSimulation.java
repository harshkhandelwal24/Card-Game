package backend.sim;

import backend.engine.AuctionEngine;
import backend.model.*;

import java.util.ArrayList;
import java.util.List;

public class AuctionSimulation {

    public static void main(String[] args) {

        // =========================
        // CREATE PLAYERS
        // =========================
        List<Player> players = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            players.add(new Player("P" + i));
        }

        // =========================
        // CREATE AUCTION + ENGINE
        // =========================
        Auction auction = new Auction();
        AuctionEngine engine = new AuctionEngine(auction, players);

        engine.startAuction();

        // =========================
        // SIMULATION FLOW
        // =========================

        try {

            engine.placeBid(players.get(0), 50); // P1
            engine.placeBid(players.get(1), 60); // P2

            engine.pass(players.get(2)); // P3

            engine.placeBid(players.get(3), 80); // P4

            engine.pass(players.get(4)); // P5
            engine.pass(players.get(5)); // P6

            Player winner = engine.getCurrentWinner();
            engine.selectTrump(winner, Suit.CLUBS);

            // =========================
            // PRINT RESULT
            // =========================
            System.out.println("=== AUCTION RESULT ===");

            AuctionResult result = engine.getResult();

            System.out.println("Winner: " + result.getWinner().getName());
            System.out.println("Final Bid: " + result.getFinalBid());
            System.out.println("Trump: " + result.getTrumpSuit());

        } catch (Exception e) {
            System.out.println("Simulation failed:");
            e.printStackTrace();
        }
    }
}