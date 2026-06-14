# System Invariants

## Introduction

This document defines all rules that must always remain true during the lifetime of the application.

Any violation of an invariant indicates a bug in the game engine.

---

# Room Invariants

* Every room has a unique room code.
* A room can have a maximum of 6 players.
* A room cannot start with fewer than 6 players.
* A room can have only one host.
* Room status must always be one of:

  * WAITING
  * PLAYING
  * FINISHED

---

# Player Invariants

* Every player has a unique ID.
* Every player occupies exactly one seat.
* No two players can occupy the same seat.
* Every player belongs to exactly one room.
* Every player has at most one active WebSocket connection.
* A disconnected player retains game state until timeout.

---

# Deck Invariants

* Deck always contains exactly 48 cards before dealing.
* No card with rank 2 exists.
* Every card is unique.
* Deck contains exactly four suits.
* Deck cannot contain duplicate cards.

---

# Card Invariants

* Card rank never changes.
* Card suit never changes.
* Card point value never changes.
* Card owner is either:

  * Deck
  * One Player
  * One Trick

Never more than one simultaneously.

---

# Deal Invariants

* Every player receives exactly 8 cards.
* Total cards dealt equals 48.
* No duplicate cards are dealt.
* Deck is empty after dealing.

---

# Auction Invariants

* Minimum bid = 50.
* Maximum bid = 250.
* Every new bid must be greater than the current highest bid.
* A player who passes cannot bid again.
* Highest bid belongs to exactly one player.
* Auction ends when only one bidder remains.
* If all players pass, one random player is assigned a bid of 150.

---

# Trump Invariants

* Trump suit is selected exactly once.
* Only the winning bidder can select trump.
* Trump suit cannot change after selection.

---

# Team Invariants

* Bidder selects exactly two cards.
* Selected cards must be different.
* Bidder cannot select a card currently in their own hand.
* Team card identities remain hidden until played.
* Team membership cannot change after selection.

---

# Turn Invariants

* Exactly one player has the current turn.
* Turn always moves clockwise after a valid play unless a trick winner leads the next trick.
* Player cannot play twice in the same trick.
* Every player plays exactly one card per trick.

---

# Card Play Invariants

* If a player owns a card of the lead suit, they must play that suit.
* A player without the lead suit may:

  * Play a trump card
  * Play any non-trump card
* A played card cannot return to a player's hand.
* Played cards cannot be modified.

---

# Trick Invariants

* A trick contains exactly six cards.
* Exactly one winner exists for every trick.
* Highest trump wins if any trump is played.
* Otherwise, highest card of the lead suit wins.
* Trick winner leads the next trick.

---

# Round Invariants

* One round contains exactly eight tricks.
* Round ends only after all cards are played.
* No player has cards remaining after round completion.

---

# Score Invariants

* Bid team score equals collected points.
* If bid team score >= bid amount:

  * Bid team receives bid amount.
* Otherwise:

  * Opponent team receives points collected by opponents.
* Score cannot be negative.

---

# Timer Invariants

* Bid timer = 20 seconds.
* Play timer = 30 seconds.
* Bid timeout triggers automatic pass.
* Play timeout triggers automatic valid card selection.

---

# Connection Invariants

* Game state remains on server.
* Client is never source of truth.
* Server validates every action.
* Client cannot modify score.
* Client cannot modify turn order.
* Client cannot modify cards.

---

# Security Invariants

* Every move must be validated server-side.
* Hidden cards remain secret until revealed.
* Team identities remain hidden until revealed by gameplay.
* Clients never receive another player's hand.
* Clients only receive information they are authorized to see.

---

# State Machine Invariants

Valid transitions only.

WAITING

↓

DEALING

↓

BIDDING

↓

TRUMP_SELECTION

↓

TEAM_SELECTION

↓

PLAYING

↓

ROUND_FINISHED

↓

SCORING

↓

NEXT_ROUND

↓

FINISHED

Invalid transitions must always be rejected.

---

# Engine Invariants

* GameEngine is the only component allowed to modify game state.
* UI is read-only.
* WebSocket layer forwards commands only.
* Business rules exist only inside GameEngine.
