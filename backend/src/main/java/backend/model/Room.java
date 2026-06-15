package backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room {

    private UUID id;

    private List<Player> players;

    private Player host;

    private Game currentGame;

    private RoomState state;

    private int maxPlayers;

    public Room() {

        this.id = UUID.randomUUID();

        this.players = new ArrayList<>();

        this.state = RoomState.WAITING;

        this.maxPlayers = 6;
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

    public Player getHost() {
        return host;
    }

    public void setHost(Player host) {
        this.host = host;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public RoomState getState() {
        return state;
    }

    public void setState(RoomState state) {
        this.state = state;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}