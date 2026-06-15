package backend.handler;

import backend.engine.RoomEngine;
import backend.event.GameEvent;
import backend.model.Card;

public class PartnerHandler {

    private final RoomEngine roomEngine;

    public PartnerHandler(RoomEngine roomEngine) {
        this.roomEngine = roomEngine;
    }

    public void handle(GameEvent event) {

        Card[] cards = (Card[]) event.getData();

        roomEngine
                .getGameEngine()
                .getRoundEngine()
                .choosePartnerCards(
                        cards[0],
                        cards[1]
                );
    }
}