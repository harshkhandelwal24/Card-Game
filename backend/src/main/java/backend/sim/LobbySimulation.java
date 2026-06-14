package backend.sim;

import backend.model.Player;
import backend.room.Room;

public class LobbySimulation {

    public static void main(String[] args) {

        // Step 1: Create host
        Player host = new Player("Alice");

        // Step 2: Create room
        Room room = new Room("ABC123", host);

        // Step 3: Add players
        room.addPlayer(new Player("Bob"));
        room.addPlayer(new Player("Charlie"));
        room.addPlayer(new Player("David"));
        room.addPlayer(new Player("Emma"));
        room.addPlayer(new Player("Frank"));

        // Step 4: Print lobby state
        System.out.println("=== LOBBY STATE ===");

        room.getPlayers().forEach(player -> {
            System.out.println("Seat " + room.getSeat(player)
                    + " -> " + player.getName());
        });
    }
}