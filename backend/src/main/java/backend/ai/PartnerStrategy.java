package backend.ai;

import backend.model.Card;
import backend.model.Player;

public interface PartnerStrategy {

    Card[] choosePartnerCards(Player player);

}