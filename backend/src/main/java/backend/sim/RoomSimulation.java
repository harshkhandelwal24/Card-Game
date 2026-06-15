package backend.sim;

import backend.engine.RoomEngine;
import backend.model.Player;
import backend.model.Room;

public class RoomSimulation {

    public static void main(String[] args) {

        Room room = new Room();

        RoomEngine roomEngine =
                new RoomEngine(room);

        Player host = new Player("Player 1");

        roomEngine.createRoom(host);

        System.out.println("Room Created");
        System.out.println("Host : " + host.getName());

        for (int i = 2; i <= 6; i++) {

            Player p =
                    new Player("Player " + i);

            roomEngine.joinRoom(p);

            System.out.println(
                    p.getName()
                            + " joined");
        }

        System.out.println();

        System.out.println(
                "Players : "
                        + room.getPlayers().size());

        System.out.println(
                "State : "
                        + room.getState());

        System.out.println();

        if (roomEngine.canStart()) {

            System.out.println(
                    "Starting Game...");

            roomEngine.startGame();
        }

        System.out.println();

        System.out.println(
                "Room State : "
                        + room.getState());

        System.out.println(
                "Game State : "
                        + room.getCurrentGame().getState());

        System.out.println(
                "Dealer : "
                        + roomEngine.getGameEngine()
                                .getGame()
                                .getDealer()
                                .getName());
    }
}