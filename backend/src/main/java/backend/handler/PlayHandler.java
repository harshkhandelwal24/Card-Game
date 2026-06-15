package backend.handler;

import backend.engine.RoomEngine;
import backend.event.GameEvent;
import backend.model.Card;

public class PlayHandler {

    private final RoomEngine roomEngine;

    public PlayHandler(RoomEngine roomEngine) {
        this.roomEngine = roomEngine;
    }

    public void handle(GameEvent event) {

        Card card = (Card) event.getData();

        roomEngine
                .getGameEngine()
                .getRoundEngine()
                .playCard(
                        event.getPlayer(),
                        card
                );
    }
}