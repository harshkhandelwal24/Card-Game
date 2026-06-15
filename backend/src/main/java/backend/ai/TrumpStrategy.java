package backend.ai;

import backend.model.Player;
import backend.model.Suit;

public interface TrumpStrategy {

    Suit chooseTrump(Player player);

}