package backend.room;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// import backend.engine.Game;
import backend.model.Player;

public class Room {

    private static final int MAX_PLAYERS = 6;

    private final String roomCode;
    private final Player host;
    private final List<Player> players;

    private RoomState state;

    // private Game game;

    public Room(String roomCode, Player host) {

        this.roomCode = roomCode;
        this.host = host;
        this.players = new ArrayList<>();

        this.state = RoomState.WAITING;

        // Host joins automatically
        this.players.add(host);
        assignSeats();
    }

    public void addPlayer(Player player) {

        if (state != RoomState.WAITING) {
            throw new IllegalStateException("Cannot join after game starts.");
        }

        if (isFull()) {
            throw new IllegalStateException("Room is full.");
        }

        for (Player p : players) {

            if (p.getId().equals(player.getId())) {
                throw new IllegalArgumentException("Duplicate player id.");
            }
        }

        players.add(player);
        assignSeats();

        if (players.size() == MAX_PLAYERS) {
            state = RoomState.FULL;
        }
    }

    public void removePlayer(Player player) {

        players.remove(player);
        assignSeats();
        if (state == RoomState.FULL) {
            state = RoomState.WAITING;
        }
    }

    public boolean isFull() {

        return players.size() == MAX_PLAYERS;

    }

    public boolean canStart() {

        return isFull();

    }

    public int getPlayerCount() {

        return players.size();

    }

    public Player findPlayer(UUID id) {

        for (Player player : players) {

            if (player.getId().equals(id)) {
                return player;
            }
        }

        return null;
    }

    public void assignSeats() {

        int seat = 1;

        for (Player player : players) {

            player.setSeatNumber(seat);
            seat++;

        }
    }

    public String getRoomCode() {
        return roomCode;
    }

    public Player getHost() {
        return host;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public RoomState getState() {
        return state;
    }

    public void setState(RoomState state) {
        this.state = state;
    }

    // public Game getGame() {
    //     return game;
    // }

    // public void setGame(Game game) {
    //     this.game = game;
    // }

}