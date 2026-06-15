package backend.handler;

import backend.engine.RoomEngine;
import backend.event.GameEvent;
import backend.model.Suit;

public class TrumpHandler {

    private final RoomEngine roomEngine;

    public TrumpHandler(RoomEngine roomEngine) {
        this.roomEngine = roomEngine;
    }

    public void handle(GameEvent event) {

        Suit suit = (Suit) event.getData();

        roomEngine
                .getGameEngine()
                .getRoundEngine()
                .setTrump(
                        event.getPlayer(),
                        suit
                );
    }
}