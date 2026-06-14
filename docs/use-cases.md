# Use Cases

## UC-01 Create Room

### Actor

Host Player

### Preconditions

* Player is not already in a room.

### Flow

1. Player clicks "Create Room".
2. Backend generates unique room code.
3. Room object is created.
4. Player becomes host.
5. Room status becomes WAITING.
6. Room code is returned.

### Postconditions

* Room exists.
* Host joins room.

---

## UC-02 Join Room

### Actor

Player

### Preconditions

* Room exists.
* Room has fewer than 6 players.
* Game has not started.

### Flow

1. Player enters room code.
2. Backend validates code.
3. Player is added.
4. Player assigned seat.
5. Updated player list broadcast.

### Postconditions

* Player joins room.

---

## UC-03 Start Game

### Actor

Host

### Preconditions

* Exactly 6 players joined.

### Flow

1. Host clicks Start.
2. Deck created.
3. Deck shuffled.
4. Cards dealt.
5. Dealer determined.
6. Game enters BIDDING state.

### Postconditions

* Auction begins.

---

## UC-04 Place Bid

### Actor

Current Bidder

### Preconditions

* Auction active.
* Player has not passed.
* Bid greater than current highest.

### Flow

1. Player submits bid.
2. Server validates amount.
3. Highest bid updated.
4. Next player notified.

### Alternate Flow

Invalid bid rejected.

---

## UC-05 Pass Bid

### Actor

Current Bidder

### Preconditions

* Auction active.

### Flow

1. Player presses Pass.
2. Player marked as passed.
3. Turn moves to next player.

### Special Case

If all pass:

* Random player assigned bid 150.

---

## UC-06 Select Trump

### Actor

Winning Bidder

### Preconditions

* Auction completed.

### Flow

1. Player selects suit.
2. Server validates.
3. Trump stored.
4. Game advances.

---

## UC-07 Select Team Cards

### Actor

Winning Bidder

### Preconditions

* Trump selected.

### Flow

1. Bidder selects two distinct cards.
2. Server verifies bidder does not own them.
3. Hidden team created.
4. Team remains secret.

---

## UC-08 Play Card

### Actor

Current Player

### Preconditions

* Player turn.
* Card exists in hand.
* Suit-following rule satisfied.

### Flow

1. Player selects card.
2. Server validates move.
3. Card removed from hand.
4. Card added to trick.
5. Trick broadcast.

### Alternate Flow

Invalid card rejected.

---

## UC-09 Resolve Trick

### Trigger

Six cards played.

### Flow

1. Determine winner.
2. Calculate trick points.
3. Assign points.
4. Winner becomes next leader.
5. New trick begins.

---

## UC-10 Finish Round

### Trigger

Eight tricks completed.

### Flow

1. Calculate bidding team points.
2. Compare against bid.
3. Update scoreboard.
4. Rotate dealer.
5. Shuffle deck.
6. Begin next round.

---

## UC-11 Player Disconnect

### Trigger

Connection lost.

### Flow

1. Server marks player disconnected.
2. Game pauses.
3. Reconnect timer starts.
4. Player reconnects.
5. State synchronized.
6. Game resumes.

### Alternate Flow

Reconnect timeout exceeded.

Future versions may replace player with AI or terminate the match.

---

## UC-12 Automatic Play Timeout

### Trigger

30-second timer expires.

### Flow

1. Server identifies all legal moves.
2. One legal card is selected automatically.
3. Card played.
4. Game continues.

---

## UC-13 Bid Timeout

### Trigger

20-second timer expires.

### Flow

1. Server automatically records PASS.
2. Auction proceeds to next player.

---

## UC-14 End Game

### Trigger

Game completion condition reached.

### Flow

1. Final scores calculated.
2. Winner announced.
3. Room status becomes FINISHED.
4. Players may choose to start a new game or leave the room.
