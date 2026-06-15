package backend.sim;

import backend.engine.BotEngine;
import backend.engine.RoundEngine;
import backend.engine.TrickEngine;
import backend.model.*;

import java.util.ArrayList;
import java.util.List;

public class BotSimulation {

    public static void main(String[] args) {

        // ==========================================
        // CREATE PLAYERS
        // ==========================================

        List<Player> players = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            players.add(new Player("Bot " + i));
        }

        // ==========================================
        // CREATE ROUND
        // ==========================================

        Round round = new Round();

        RoundEngine roundEngine = new RoundEngine(round, players);

        BotEngine botEngine = new BotEngine();

        // ==========================================
        // START ROUND
        // ==========================================

        roundEngine.startRound();

        System.out.println();
        System.out.println("==========================");
        System.out.println("BOT ROUND STARTED");
        System.out.println("==========================");

        // ==========================================
        // AUCTION
        // ==========================================

        roundEngine.startAuction();

        int currentBid = 40;

        for (Player player : players) {

            int bid =
                    botEngine.makeBid(
                            player,
                            roundEngine.getAuctionEngine()
                    );

            if (bid == -1) {

                roundEngine
                        .getAuctionEngine()
                        .pass(player);

                System.out.println(
                        player.getName() + " passes"
                );
            } else {

                currentBid = bid;

                roundEngine
                        .getAuctionEngine()
                        .placeBid(player, bid);

                System.out.println(
                        player.getName()
                                + " bids "
                                + bid
                );
            }
        }

        roundEngine
                .getAuctionEngine()
                .finalizeAuction();

        Player bidder =
                round.getAuction()
                        .getHighestBidder();

        System.out.println();
        System.out.println(
                "Auction Winner : "
                        + bidder.getName()
        );

        System.out.println(
                "Winning Bid : "
                        + round.getAuction().getHighestBid()
        );

        // ==========================================
        // TRUMP
        // ==========================================

        Suit trump =
                botEngine.chooseTrump(bidder);

        roundEngine.setTrump(
                bidder,
                trump
        );

        System.out.println();
        System.out.println(
                "Trump : "
                        + trump
        );

        // ==========================================
        // PARTNER CARDS
        // ==========================================

        Card[] partnerCards =
                botEngine.choosePartnerCards(bidder);

        roundEngine.choosePartnerCards(
                partnerCards[0],
                partnerCards[1]
        );

        System.out.println();

        System.out.println(
                "Partner Card 1 : "
                        + partnerCards[0]
        );

        System.out.println(
                "Partner Card 2 : "
                        + partnerCards[1]
        );

        System.out.println();

        System.out.println("Bidding Team");

        for (Player p : round.getTeam().getMembers()) {

            System.out.println(
                    p.getName()
            );
        }

        // ==========================================
        // PLAY PHASE
        // ==========================================

        roundEngine.startPlayPhase();

        System.out.println();
        System.out.println("==========================");
        System.out.println("PLAY");
        System.out.println("==========================");

        while (round.getState() == RoundState.PLAYING) {

            TrickEngine trickEngine =
                    roundEngine.getTrickEngine();

            Player current =
                    trickEngine.getCurrentPlayer();

            Card chosen =
                    botEngine.playCard(
                            current,
                            trickEngine
                    );

            roundEngine.playCard(
                    current,
                    chosen
            );

            System.out.println(
                    current.getName()
                            + " plays "
                            + chosen
            );

            if (trickEngine.getTrick().getState()
                    == TrickState.COMPLETED) {

                Trick trick =
                        trickEngine.getTrick();

                System.out.println();

                System.out.println(
                        "Winner : "
                                + trick.getWinner().getName()
                );

                System.out.println(
                        "Points : "
                                + trick.getPoints()
                );

                System.out.println("------------------------");
            }
        }

        // ==========================================
        // SCORING
        // ==========================================

        roundEngine.scoring();

        RoundScore score =
                round.getScore();

        System.out.println();

        System.out.println("==========================");
        System.out.println("RESULT");
        System.out.println("==========================");

        System.out.println(
                "Bid Value : "
                        + score.getBidValue()
        );

        System.out.println(
                "Bidding Team : "
                        + score.getBiddingTeamPoints()
        );

        System.out.println(
                "Opponents : "
                        + score.getOpponentTeamPoints()
        );

        System.out.println(
                "Bid Success : "
                        + score.isBidSuccess()
        );
    }
}