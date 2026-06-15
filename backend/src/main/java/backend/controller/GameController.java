package backend.controller;

import backend.engine.RoomEngine;
import backend.event.GameEvent;
import backend.event.GameEventType;
import backend.handler.AuctionHandler;
import backend.handler.PartnerHandler;
import backend.handler.PlayHandler;
import backend.handler.TrumpHandler;

public class GameController {

    private final RoomEngine roomEngine;

    private final AuctionHandler auctionHandler;
    private final PlayHandler playHandler;
    private final TrumpHandler trumpHandler;
    private final PartnerHandler partnerHandler;

    public GameController(RoomEngine roomEngine) {

        this.roomEngine = roomEngine;

        auctionHandler = new AuctionHandler(roomEngine);
        playHandler = new PlayHandler(roomEngine);
        trumpHandler = new TrumpHandler(roomEngine);
        partnerHandler = new PartnerHandler(roomEngine);
    }

    public void receiveEvent(GameEvent event) {

        validate(event);

        switch (event.getType()) {

            case START_GAME:

                roomEngine.startGame();
                break;

            case BID:

                auctionHandler.handle(event);
                break;

            case PASS:

                roomEngine
                        .getGameEngine()
                        .getRoundEngine()
                        .getAuctionEngine()
                        .pass(event.getPlayer());

                break;

            case SELECT_TRUMP:

                trumpHandler.handle(event);
                break;

            case SELECT_PARTNER:

                partnerHandler.handle(event);
                break;

            case PLAY_CARD:

                playHandler.handle(event);
                break;

            default:

                System.out.println(
                        "Unhandled event : "
                                + event.getType()
                );
        }
    }

    private void validate(GameEvent event) {

        if (event == null) {
            throw new IllegalArgumentException("Event is null");
        }

        if (event.getType() == null) {
            throw new IllegalArgumentException("Event type is null");
        }

        if (event.getType() != GameEventType.START_GAME &&
                event.getPlayer() == null) {

            throw new IllegalArgumentException("Player is null");
        }
    }
}