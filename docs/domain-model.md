# Domain Model

## Introduction

The domain model represents the core business entities of the 3 Of Spades game and their relationships.

The objective is to separate business logic from presentation and networking, allowing the game engine to operate independently.

---

# Entity Overview

| Entity     | Description                     |
| ---------- | ------------------------------- |
| Game       | Complete lifecycle of one match |
| Round      | One deal consisting of 8 tricks |
| Deck       | Collection of 48 cards          |
| Card       | Single playing card             |
| Player     | One participant                 |
| Auction    | Complete bidding process        |
| Bid        | One player's bid                |
| Team       | Bidder's team                   |
| Trick      | One round of six played cards   |
| ScoreBoard | Overall scores                  |
| Room       | Multiplayer lobby               |
| GameEngine | Controls game execution         |

---

# Game

Represents one complete game.

## Attributes

* gameId
* players
* deck
* auction
* currentRound
* scoreBoard
* dealer
* trumpSuit
* gameState
* biddingTeam
* currentTurn

## Responsibilities

* Maintain overall state
* Coordinate rounds
* Detect game completion

---

# Round

Represents one shuffled deal.

## Attributes

* roundNumber
* tricks
* dealer
* trumpSuit
* biddingWinner

## Responsibilities

* Manage trick progression
* End when all cards are played

---

# Player

Represents a participant.

## Attributes

* id
* name
* seatNumber
* hand
* score
* connected
* passedBid
* ready

## Responsibilities

* Hold cards
* Play cards
* Place bids

---

# Card

Represents one playing card.

## Attributes

* suit
* rank
* pointValue

## Responsibilities

* Immutable value object

---

# Deck

Represents the playing deck.

## Attributes

* cards

## Responsibilities

* Generate deck
* Shuffle cards
* Deal cards

---

# Bid

Represents a bid.

## Attributes

* player
* amount
* timestamp

## Responsibilities

* Store bid information

---

# Auction

Represents bidding phase.

## Attributes

* bids
* currentHighestBid
* highestBidder
* passedPlayers

## Responsibilities

* Validate bids
* Determine winner
* End auction

---

# Team

Represents bidder's team.

## Attributes

* bidder
* selectedCards
* members
* totalPoints

## Responsibilities

* Resolve hidden teammates
* Track collected points

---

# Trick

Represents one trick.

## Attributes

* leader
* playedCards
* winner
* trickPoints

## Responsibilities

* Validate moves
* Determine winner
* Calculate points

---

# ScoreBoard

Represents cumulative scores.

## Attributes

* biddingTeamScore
* opponentScore

## Responsibilities

* Maintain scores
* Display results

---

# Room

Represents multiplayer lobby.

## Attributes

* roomCode
* players
* status
* host

## Responsibilities

* Manage player joins
* Start game
* Destroy room after completion

---

# GameEngine

Central business logic component.

## Responsibilities

* Start game
* Deal cards
* Run auction
* Select trump
* Resolve teams
* Play tricks
* Calculate scores
* End round

---

# Relationships

Game

├── 6 Players

├── 1 Deck

├── 1 Auction

├── 1 Team

├── 1 ScoreBoard

└── 1 Round

Round

├── 8 Tricks

└── Trump Suit

Auction

├── Multiple Bids

└── One Winner

Team

├── Bidder

├── Hidden Cards

└── Members

Trick

├── Six Played Cards

└── One Winner

---

# Design Principles

* Single Responsibility Principle
* Immutable Card objects
* Server-authoritative state
* Stateless UI
* Business logic isolated in GameEngine
* Testable domain model
