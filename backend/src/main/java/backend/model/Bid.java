package backend.model;

import java.time.Instant;

public class Bid {

    private final Player player;
    private final int amount;
    private final Instant time;

    public Bid(Player player, int amount, Instant time) {
        this.player = player;
        this.amount = amount;
        this.time = time;
    }

    public Player getPlayer() {
        return player;
    }

    public int getAmount() {
        return amount;
    }

    public Instant getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "player=" + player.getName() +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}