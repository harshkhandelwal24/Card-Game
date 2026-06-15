package backend.sim;

import backend.engine.GameEngine;
import backend.model.Game;
import backend.model.GameState;
import backend.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameSimulation {

    public static void main(String[] args) {

        List<Player> players = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            players.add(new Player("Player " + i));
        }

        Game game = new Game();

        GameEngine gameEngine = new GameEngine(game, players);

        gameEngine.startGame();

        System.out.println("==============================");
        System.out.println("GAME STARTED");
        System.out.println("==============================");

        while (game.getState() != GameState.GAME_OVER) {

            System.out.println();
            System.out.println("------------------------------");
            System.out.println("Round : " + game.getCurrentRoundNumber());
            System.out.println("Dealer : " + game.getDealer().getName());
            System.out.println("------------------------------");

            /*
             * Here you should execute one complete round.
             *
             * Reuse the logic currently present in RoundSimulation:
             *
             * 1. startAuction()
             * 2. bidding
             * 3. finalizeAuction()
             * 4. setTrump()
             * 5. choosePartnerCards()
             * 6. startPlayPhase()
             * 7. play all 8 tricks
             *
             * After that, call:
             */

            break;   // remove after integrating full round simulation
        }

        System.out.println();
        System.out.println("==============================");
        System.out.println("GAME FINISHED");
        System.out.println("==============================");
    }
}