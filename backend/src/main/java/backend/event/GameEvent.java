package backend.event;

import backend.model.Player;

public class GameEvent {

    private GameEventType type;

    private Player player;

    private Object data;

    public GameEvent() {
    }

    public GameEvent(GameEventType type,
                     Player player,
                     Object data) {

        this.type = type;
        this.player = player;
        this.data = data;
    }

    public GameEventType getType() {
        return type;
    }

    public void setType(GameEventType type) {
        this.type = type;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}