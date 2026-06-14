package backend.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import backend.room.Room;
// import backend.model.Player;

public class RoomTest {

    @Test
    void testCreateRoom() {

        Player host = new Player("Host");

        Room room = new Room("ABC123", host);

        // Host joins automatically
        assertEquals(1, room.getPlayerCount());
    }

    @Test
    void testAddPlayer() {

        Player host = new Player("Host");

        Room room = new Room("ABC123", host);

        room.addPlayer(new Player("Player1"));

        assertEquals(2, room.getPlayerCount());
    }

    @Test
    void testIsFull() {

        Player host = new Player("Host");

        Room room = new Room("ABC123", host);

        room.addPlayer(new Player("P1"));
        room.addPlayer(new Player("P2"));
        room.addPlayer(new Player("P3"));
        room.addPlayer(new Player("P4"));
        room.addPlayer(new Player("P5"));

        assertTrue(room.isFull());
    }

    @Test
    void testAddingSeventhPlayerFails() {

        Player host = new Player("Host");

        Room room = new Room("ABC123", host);

        room.addPlayer(new Player("P1"));
        room.addPlayer(new Player("P2"));
        room.addPlayer(new Player("P3"));
        room.addPlayer(new Player("P4"));
        room.addPlayer(new Player("P5"));

        assertThrows(
                IllegalStateException.class,
                () -> room.addPlayer(new Player("Extra"))
        );
    }

    @Test
    void testDuplicatePlayerRejected() {

        Player host = new Player("Host");

        Room room = new Room("ABC123", host);

        Player player = new Player("Alice");

        room.addPlayer(player);

        assertThrows(
                IllegalArgumentException.class,
                () -> room.addPlayer(player)
        );
    }

    @Test
    void testAssignSeats() {

        Player host = new Player("Host");

        Room room = new Room("ABC123", host);

        room.addPlayer(new Player("P1"));
        room.addPlayer(new Player("P2"));
        room.addPlayer(new Player("P3"));
        room.addPlayer(new Player("P4"));
        room.addPlayer(new Player("P5"));

        Set<Integer> seats = new HashSet<>();

        room.getPlayers().forEach(p -> seats.add(room.getSeat(p)));

        assertEquals(6, seats.size());

        for (int seat = 1; seat <= 6; seat++) {
            assertTrue(seats.contains(seat));
        }
    }
}