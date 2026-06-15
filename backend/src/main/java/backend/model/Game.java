package backend.model;

import java.util.*;

public class Game {

    private UUID id;

    private List<Player> players;

    private List<Round> rounds;

    private Round currentRound;

    private int currentRoundNumber;

    private Player dealer;

    private Map<Player, Integer> cumulativeScore;

    private GameState state;

    public Game() {

        id = UUID.randomUUID();

        players = new ArrayList<>();

        rounds = new ArrayList<>();

        cumulativeScore = new HashMap<>();

        state = GameState.WAITING;

        currentRoundNumber = 0;
    }

    public UUID getId() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public void setCurrentRoundNumber(int currentRoundNumber) {
        this.currentRoundNumber = currentRoundNumber;
    }

    public Player getDealer() {
        return dealer;
    }

    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public Map<Player, Integer> getCumulativeScore() {
        return cumulativeScore;
    }

    public void setCumulativeScore(Map<Player, Integer> cumulativeScore) {
        this.cumulativeScore = cumulativeScore;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

}
