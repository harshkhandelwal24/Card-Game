package backend.engine;

import backend.model.*;

import java.util.List;
import java.util.Map;

public class GameEngine {

    private final Game game;

    private final List<Player> players;

    private RoundEngine roundEngine;

    private int dealerIndex = 0;

    public GameEngine(Game game, List<Player> players) {

        this.game = game;
        this.players = players;

        game.setPlayers(players);

        for (Player p : players) {
            game.getCumulativeScore().put(p, 0);
        }
    }

    // =================================

    public void startGame() {

        game.setState(GameState.IN_PROGRESS);

        game.setCurrentRoundNumber(1);

        game.setDealer(players.get(0));

        dealerIndex = 0;

        startNextRound();
    }

    // =================================

    public void startNextRound() {

        Round round = new Round();

        round.setDealer(game.getDealer());

        game.getRounds().add(round);

        game.setCurrentRound(round);

        roundEngine =
                new RoundEngine(round, players);

        roundEngine.startRound();
    }

    // =================================

    public void finishRound() {

        roundEngine.scoring();

        updateScores();

        rotateDealer();

        game.setCurrentRoundNumber(
                game.getCurrentRoundNumber() + 1
        );

        if (checkGameEnd()) {

            game.setState(GameState.GAME_OVER);

        } else {

            game.setState(GameState.ROUND_OVER);

            startNextRound();
        }
    }

    // =================================

    private void updateScores() {

        Round round = game.getCurrentRound();

        RoundScore score = round.getScore();

        Team biddingTeam = round.getTeam();

        for (Player p : biddingTeam.getMembers()) {

            int current =
                    game.getCumulativeScore().get(p);

            game.getCumulativeScore().put(
                    p,
                    current + score.getBiddingTeamPoints()
            );
        }

        for (Player p : players) {

            if (!biddingTeam.getMembers().contains(p)) {

                int current =
                        game.getCumulativeScore().get(p);

                game.getCumulativeScore().put(
                        p,
                        current + score.getOpponentTeamPoints()
                );
            }
        }
    }

    // =================================

    private void rotateDealer() {

        dealerIndex++;

        dealerIndex %= players.size();

        game.setDealer(
                players.get(dealerIndex)
        );
    }

    // =================================

    private boolean checkGameEnd() {

        return game.getCurrentRoundNumber() > 6;
    }

    // =================================

    public Player getWinner() {

        Player winner = null;

        int max = Integer.MIN_VALUE;

        for (Map.Entry<Player, Integer> entry
                : game.getCumulativeScore().entrySet()) {

            if (entry.getValue() > max) {

                max = entry.getValue();

                winner = entry.getKey();
            }
        }

        return winner;
    }

    // =================================

    public void restartGame() {

        game.getRounds().clear();

        game.getCumulativeScore().clear();

        for (Player p : players) {
            game.getCumulativeScore().put(p, 0);
        }

        dealerIndex = 0;

        startGame();
    }

    // =================================

    public RoundEngine getRoundEngine() {
        return roundEngine;
    }

    public Game getGame() {
        return game;
    }
}