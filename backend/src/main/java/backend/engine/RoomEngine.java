package backend.engine;

import backend.model.*;

public class RoomEngine {

    private final Room room;

    private GameEngine gameEngine;

    public RoomEngine(Room room) {

        this.room = room;
    }

    // ==========================
    // CREATE ROOM
    // ==========================

    public void createRoom(Player host) {

        room.setHost(host);

        room.getPlayers().add(host);

        room.setState(RoomState.WAITING);
    }

    // ==========================
    // JOIN ROOM
    // ==========================

    public void joinRoom(Player player) {

        if (room.getState() != RoomState.WAITING) {

            throw new IllegalStateException(
                    "Room already started");
        }

        if (room.getPlayers().size() >= room.getMaxPlayers()) {

            throw new IllegalStateException(
                    "Room is full");
        }

        room.getPlayers().add(player);

        if (room.getPlayers().size() == room.getMaxPlayers()) {

            room.setState(RoomState.FULL);
        }
    }

    // ==========================
    // LEAVE ROOM
    // ==========================

    public void leaveRoom(Player player) {

        room.getPlayers().remove(player);

        if (room.getPlayers().size() < room.getMaxPlayers()) {

            room.setState(RoomState.WAITING);
        }
    }

    // ==========================
    // CAN START
    // ==========================

    public boolean canStart() {

        return room.getPlayers().size() == room.getMaxPlayers();
    }

    // ==========================
    // START GAME
    // ==========================

    public void startGame() {

        if (!canStart()) {

            throw new IllegalStateException(
                    "Need exactly 6 players");
        }

        Game game = new Game();

        room.setCurrentGame(game);

        gameEngine =
                new GameEngine(
                        game,
                        room.getPlayers());

        gameEngine.startGame();

        room.setState(RoomState.PLAYING);
    }

    // ==========================
    // END GAME
    // ==========================

    public void endGame() {

        room.setState(RoomState.FINISHED);
    }

    // ==========================
    // REMATCH
    // ==========================

    public void rematch() {

        Game game = new Game();

        room.setCurrentGame(game);

        gameEngine =
                new GameEngine(
                        game,
                        room.getPlayers());

        gameEngine.startGame();

        room.setState(RoomState.PLAYING);
    }

    // ==========================
    // GETTERS
    // ==========================

    public Room getRoom() {
        return room;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }
}
