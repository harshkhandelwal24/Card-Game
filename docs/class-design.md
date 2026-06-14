# Class Design Document

## Purpose

This document defines every class in the system, its responsibilities,
fields, methods, dependencies, and constraints.

The goal is to build a maintainable and scalable architecture where each
class has a single responsibility.

---

# Package Structure

backend/

    model/

    engine/

    service/

    room/

    websocket/

    timer/

    util/

---

# 1. Card

## Description

Represents one immutable playing card.

---

## Fields

Suit suit

Rank rank

int pointValue

---

## Public Methods

getSuit()

getRank()

getPointValue()

toString()

equals()

hashCode()

---

## Responsibilities

Store card information.

Represent one card.

---

## Must NOT

Know about players.

Know about bidding.

Know about scoring.

Know about tricks.

---

## Invariants

Suit never changes.

Rank never changes.

Point value never changes.

Card is immutable.

======================================================

# 2. Deck

## Description

Represents the complete playing deck.

---

## Fields

List<Card> cards

---

## Public Methods

createDeck()

shuffle()

deal()

isEmpty()

remainingCards()

---

## Responsibilities

Generate deck.

Shuffle deck.

Deal cards.

---

## Must NOT

Know current player.

Know bidding.

Know teams.

Know scoring.

---

## Invariants

Contains 48 cards before dealing.

No duplicate cards.

No 2 cards exist.

======================================================

# 3. Player

## Description

Represents one player.

---

## Fields

UUID id

String name

int seatNumber

List<Card> hand

boolean connected

boolean ready

boolean passedBid

int totalScore

---

## Public Methods

receiveCard()

playCard()

removeCard()

hasSuit()

getHand()

reset()

---

## Responsibilities

Store player information.

Store hand.

Play card.

---

## Must NOT

Shuffle deck.

Calculate score.

Resolve tricks.

Determine winner.

---

## Invariants

Maximum 8 cards.

Cannot play absent card.

Seat number unique.

======================================================

# 4. Bid

## Description

Represents one bid.

---

## Fields

Player bidder

int amount

LocalDateTime timestamp

---

## Public Methods

getBidder()

getAmount()

---

## Responsibilities

Store bid information.

---

## Must NOT

Validate bid.

End auction.

======================================================

# 5. Auction

## Description

Controls bidding process.

---

## Fields

List<Bid> bids

Player highestBidder

int currentBid

Set<Player> passedPlayers

Player currentTurn

---

## Public Methods

placeBid()

pass()

nextTurn()

isComplete()

getWinner()

---

## Responsibilities

Run auction.

Validate bids.

Track highest bid.

Determine winner.

---

## Must NOT

Deal cards.

Play tricks.

Calculate score.

---

## Invariants

Bid increases.

Passed player cannot bid again.

Exactly one winner.

======================================================

# 6. Team

## Description

Represents bidding team.

---

## Fields

Player bidder

Card hiddenCardOne

Card hiddenCardTwo

List<Player> members

int points

---

## Public Methods

addPoints()

revealPlayer()

containsPlayer()

---

## Responsibilities

Maintain hidden teammates.

Track collected points.

---

## Must NOT

Play cards.

Resolve tricks.

Deal cards.

======================================================

# 7. Trick

## Description

Represents one trick.

---

## Fields

Player leader

Map<Player,Card> playedCards

Player winner

int trickPoints

Suit leadSuit

Suit trumpSuit

---

## Public Methods

playCard()

determineWinner()

calculatePoints()

isComplete()

---

## Responsibilities

Validate trick.

Determine winner.

Calculate trick points.

---

## Must NOT

Deal cards.

Run auction.

Calculate final score.

---

## Invariants

Exactly 6 cards.

One winner.

One leader.

======================================================

# 8. Round

## Description

Represents one complete deal.

---

## Fields

Deck deck

Auction auction

List<Trick> tricks

Suit trumpSuit

Team biddingTeam

Player dealer

---

## Public Methods

start()

finish()

nextTrick()

isComplete()

---

## Responsibilities

Manage one round.

Track tricks.

Coordinate components.

---

## Must NOT

Manage rooms.

Handle WebSocket.

======================================================

# 9. ScoreBoard

## Description

Maintains scores.

---

## Fields

int biddingTeamScore

int opponentScore

---

## Public Methods

update()

reset()

getWinner()

---

## Responsibilities

Store scores.

Display scores.

---

## Must NOT

Calculate trick winner.

Run auction.

======================================================

# 10. Room

## Description

Represents multiplayer lobby.

---

## Fields

String roomCode

Player host

List<Player> players

RoomStatus status

Game game

---

## Public Methods

join()

leave()

startGame()

destroy()

---

## Responsibilities

Manage lobby.

Manage players.

Launch game.

---

## Must NOT

Calculate score.

Resolve tricks.

Validate bids.

======================================================

# 11. Game

## Description

Represents one running match.

---

## Fields

UUID gameId

Round currentRound

ScoreBoard scoreBoard

GameState state

Player dealer

Player currentTurn

---

## Public Methods

start()

nextRound()

end()

getCurrentPlayer()

---

## Responsibilities

Manage lifecycle.

Coordinate rounds.

Track state.

---

## Must NOT

Shuffle cards directly.

Validate WebSocket.

Generate room codes.

======================================================

# 12. GameEngine

## Description

Core business logic.

Most important class.

Single source of truth.

---

## Public Methods

createGame()

dealCards()

startAuction()

placeBid()

passBid()

selectTrump()

selectTeam()

playCard()

resolveTrick()

calculateScore()

endRound()

nextRound()

finishGame()

---

## Responsibilities

Control entire game.

Enforce rules.

Update state.

Broadcast events.

---

## Must NOT

Render UI.

Store browser session.

Generate HTML.

Know React.

======================================================

# 13. RoomManager

## Description

Maintains active rooms.

---

## Fields

Map<String,Room> rooms

---

## Public Methods

createRoom()

joinRoom()

deleteRoom()

findRoom()

---

## Responsibilities

Manage room lifecycle.

---

## Must NOT

Run games.

Calculate scores.

======================================================

# 14. TimerService

## Description

Controls bid and play timers.

---

## Public Methods

startBidTimer()

startPlayTimer()

cancel()

timeout()

---

## Responsibilities

Trigger automatic actions.

---

## Must NOT

Modify game rules.

Calculate winner.

======================================================

# 15. WebSocketHandler

## Description

Communication layer.

---

## Responsibilities

Receive client messages.

Forward to GameEngine.

Broadcast state.

---

## Must NOT

Contain business logic.

Calculate score.

Validate tricks.

======================================================

# Dependency Flow

Web Browser

↓

React UI

↓

WebSocket

↓

WebSocketHandler

↓

GameEngine

↓

Game

↓

Round

↓

Auction

↓

Trick

↓

Card

======================================================

# Architecture Rule

Business logic belongs ONLY inside GameEngine and domain classes.

React only renders.

WebSocket only transports messages.

RoomManager only manages rooms.

TimerService only manages timers.

No business logic should exist outside the domain layer.