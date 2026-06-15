package backend.ai;

import backend.engine.TrickEngine;
import backend.model.Card;
import backend.model.Player;

public interface PlayStrategy {

    Card chooseCard(
            Player player,
            TrickEngine trickEngine
    );

}