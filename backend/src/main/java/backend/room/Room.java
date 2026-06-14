package backend.room;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import backend.engine.AuctionEngine;
import backend.engine.TrickEngine;
import backend.model.*;

public class Room {

    private static final int MAX_PLAYERS = 6;

    private final String roomCode;
    private final Player host;
    private final List<Player> players;

    private AuctionEngine auctionEngine;
    private Auction auction;

    private RoomState state;

    public Room(String roomCode, Player host) {
        this.roomCode = roomCode;
        this.host = host;
        this.players = new ArrayList<>();

        this.state = RoomState.WAITING;

        this.players.add(host);
    }

    // =====================================================
    // PLAYER MANAGEMENT
    // =====================================================

    public void addPlayer(Player player) {

        if (state != RoomState.WAITING) {
            throw new IllegalStateException("Cannot join after game starts.");
        }

        if (isFull()) {
            throw new IllegalStateException("Room is full.");
        }

        for (Player p : players) {
            if (p.getId().equals(player.getId())) {
                throw new IllegalArgumentException("Duplicate player id.");
            }
        }

        players.add(player);

        if (players.size() == MAX_PLAYERS) {
            state = RoomState.FULL;
        }
    }

    public void removePlayer(Player player) {

        players.remove(player);

        if (state == RoomState.FULL) {
            state = RoomState.WAITING;
        }
    }

    // =====================================================
    // AUCTION FLOW
    // =====================================================

    public void startAuction() {

        if (!canStart()) {
            throw new IllegalStateException("Cannot start auction");
        }

        this.auction = new Auction();
        this.auctionEngine = new AuctionEngine(auction, players);

        auctionEngine.startAuction();

        state = RoomState.AUCTION;
    }

    public AuctionEngine getAuctionEngine() {
        return auctionEngine;
    }

    public Auction getAuction() {
        return auction;
    }

    public AuctionResult getAuctionResult() {

        if (auctionEngine == null) {
            throw new IllegalStateException("Auction not started");
        }

        return auctionEngine.getResult();
    }

    // =====================================================
    // TRUMP SELECTION (FIXED LOGIC)
    // =====================================================

    public void selectTrump(Player player, Suit trump) {

        if (auctionEngine == null) {
            throw new IllegalStateException("Auction not started");
        }

        if (auction.getState() != AuctionState.COMPLETED) {
            throw new IllegalStateException("Auction not completed yet");
        }

        Player winner = auction.getHighestBidder();

        if (winner == null) {
            throw new IllegalStateException("No auction winner");
        }

        if (!winner.getId().equals(player.getId())) {
            throw new IllegalStateException("Only auction winner can select trump");
        }

        // 5. Validate trump input (OPTION A rule)
        if (trump == null) {
        throw new IllegalArgumentException("Trump suit cannot be null");
        }

        auction.setTrumpSuit(trump);

        state = RoomState.TRUMP_SELECTED;
    }

    // =====================================================
    // TRICK ENGINE START
    // =====================================================

    public TrickEngine startTrickEngine(Trick trick) {

        if (auctionEngine == null) {
            throw new IllegalStateException("Auction not completed");
        }

        if (auction.getTrumpSuit() == null) {
            throw new IllegalStateException("Trump not selected");
        }

        state = RoomState.IN_GAME;

        return new TrickEngine(
                trick,
                players,
                auction.getTrumpSuit()
        );
    }

    // =====================================================
    // SEATING (DERIVED)
    // =====================================================

    public int getSeat(Player player) {
        int index = players.indexOf(player);
        if (index == -1) return -1;
        return index + 1;
    }

    public Player getPlayerAtSeat(int seat) {
        if (seat < 1 || seat > players.size()) return null;
        return players.get(seat - 1);
    }

    // =====================================================
    // STATE HELPERS
    // =====================================================

    public boolean isFull() {
        return players.size() == MAX_PLAYERS;
    }

    public boolean canStart() {
        return isFull();
    }

    public int getPlayerCount() {
        return players.size();
    }

    public Player findPlayer(UUID id) {
        for (Player player : players) {
            if (player.getId().equals(id)) {
                return player;
            }
        }
        return null;
    }

    // =====================================================
    // GETTERS
    // =====================================================

    public String getRoomCode() {
        return roomCode;
    }

    public Player getHost() {
        return host;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}

    // public RoomState getState() {
    //     return state;
    // }

    // public void setState(RoomState state) {
    //     this.state = state;
    // }