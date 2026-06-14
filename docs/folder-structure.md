# Folder Structure and Project Organization

# Purpose

This document defines the folder structure, package organization, naming conventions, dependency rules, and coding standards for the 3 Of Spades project.

All developers should follow these guidelines to maintain a clean, scalable, and maintainable codebase.

---

# High Level Directory Structure

```
3-of-spades/

├── backend/
├── frontend/
├── docs/
├── diagrams/
├── tests/
├── scripts/
├── assets/
├── .gitignore
└── README.md
```

---

# Backend Structure

```
backend/

└── src/

    ├── main/

    │     ├── java/

    │     │      └── com/

    │     │             └── threeofspades/

    │     │

    │     │                  ├── model/

    │     │                  ├── engine/

    │     │                  ├── service/

    │     │                  ├── room/

    │     │                  ├── websocket/

    │     │                  ├── timer/

    │     │                  ├── config/

    │     │                  ├── dto/

    │     │                  ├── event/

    │     │                  ├── exception/

    │     │                  ├── util/

    │     │                  └── Application.java

    │

    │     └── resources/

    │             ├── application.yml

    │             └── static/

    │

    └── test/

          └── java/
```

---

# Package Responsibilities

## model

Contains all domain objects.

Classes:

* Card
* Deck
* Player
* Game
* Round
* Trick
* Bid
* Auction
* Team
* ScoreBoard

No Spring annotations should exist here.

Business entities only.

---

## engine

Contains core game logic.

Classes:

* GameEngine
* AuctionEngine
* TrickEngine
* ScoreEngine
* TeamResolver

Responsible for implementing game rules.

This is the heart of the application.

---

## service

Application services.

Classes:

* GameService
* RoomService
* PlayerService

Coordinates requests between controllers/WebSockets and the engine.

Should not contain low-level rule implementations.

---

## room

Lobby management.

Classes:

* Room
* RoomManager
* RoomStatus

Responsible for:

* Create room
* Join room
* Leave room
* Destroy room

---

## websocket

Real-time communication.

Classes:

* WebSocketConfig
* GameWebSocketHandler
* SessionManager

Responsible only for transport.

Must never contain business rules.

---

## timer

Timer-related logic.

Classes:

* TimerService
* BidTimer
* TurnTimer

Responsible for automatic pass and automatic card play.

---

## config

Spring configuration.

Classes:

* WebSocketConfig
* CorsConfig
* JacksonConfig

No business logic.

---

## dto

Data Transfer Objects.

Examples:

* CreateRoomRequest
* JoinRoomRequest
* PlayCardRequest
* BidRequest
* GameStateResponse

DTOs are exchanged with clients.

Never expose domain entities directly.

---

## event

Event definitions.

Examples:

* PlayerJoinedEvent
* BidPlacedEvent
* CardPlayedEvent
* TrickWonEvent
* RoundFinishedEvent
* ScoreUpdatedEvent

Used for event-driven communication.

---

## exception

Custom exceptions.

Examples:

* InvalidMoveException
* InvalidBidException
* RoomFullException
* PlayerNotFoundException
* InvalidGameStateException

Use descriptive exceptions.

---

## util

Utility classes.

Examples:

* CardComparator
* RandomGenerator
* Constants
* IdGenerator

Must remain stateless.

---

# Frontend Structure

```
frontend/

src/

├── components/

├── pages/

├── hooks/

├── services/

├── websocket/

├── context/

├── types/

├── assets/

├── utils/

├── App.tsx

└── main.tsx
```

---

# Frontend Responsibilities

## components

Reusable UI components.

Examples:

* Card
* Button
* Timer
* ScoreBoard
* PlayerSeat

---

## pages

Application screens.

Examples:

* Home
* Lobby
* WaitingRoom
* GameTable
* ResultPage

---

## hooks

Custom React hooks.

Examples:

* useGame
* useTimer
* useWebSocket

---

## services

HTTP and API services.

---

## websocket

WebSocket client implementation.

---

## context

React Context providers.

Examples:

* GameContext
* PlayerContext

---

## types

TypeScript interfaces and enums.

---

## utils

Pure helper functions.

---

# Naming Conventions

## Classes

Use PascalCase.

```
Card

GameEngine

RoomManager
```

---

## Interfaces

Prefix with I only if needed.

Preferred:

```
GameService

TimerStrategy
```

---

## Methods

Use camelCase.

```
playCard()

shuffleDeck()

calculateScore()

resolveTrick()
```

---

## Variables

Use camelCase.

```
currentBid

highestBid

currentPlayer
```

---

## Constants

Use UPPER_CASE.

```
MAX_PLAYERS

MIN_BID

MAX_BID

TURN_TIMEOUT

BID_TIMEOUT
```

---

## Packages

Use lowercase only.

```
model

engine

service

websocket
```

---

# Dependency Rules

Allowed dependency flow:

```
UI

↓

WebSocket

↓

Service

↓

Engine

↓

Model
```

Dependencies must always point downward.

Reverse dependencies are forbidden.

---

# Import Rules

## model

May import:

* java.*
* util

Must NOT import:

* service
* websocket
* engine
* Spring

---

## engine

May import:

* model
* util
* event

Must NOT import:

* websocket
* React
* UI

---

## service

May import:

* engine
* model
* dto

Must NOT import:

* frontend
* React

---

## websocket

May import:

* service
* dto

Must NOT import:

* model directly for mutation

All mutations must go through services and engine.

---

## frontend

Must never know internal backend implementation.

Communication occurs only through REST/WebSocket contracts.

---

# Architectural Rules

* GameEngine is the single source of truth.
* Business rules must never exist in React components.
* Business rules must never exist in WebSocket handlers.
* DTOs must never contain business logic.
* Domain entities should not depend on Spring Framework.
* UI should render state only.
* Validation occurs on the server.

---

# Layer Responsibilities

| Layer     | Responsibility            |
| --------- | ------------------------- |
| UI        | Render state              |
| WebSocket | Send and receive messages |
| Service   | Coordinate requests       |
| Engine    | Execute game rules        |
| Model     | Store domain state        |

---

# Circular Dependency Policy

Circular dependencies are strictly prohibited.

Correct:

```
Service

↓

Engine

↓

Model
```

Incorrect:

```
Engine

↓

Service

↓

Engine
```

---

# Testing Structure

```
test/

├── model/

├── engine/

├── service/

├── websocket/

└── integration/
```

Every engine class should have dedicated unit tests.

---

# Code Quality Guidelines

* One class, one responsibility.
* Prefer composition over inheritance.
* Avoid static mutable state.
* Keep methods small and focused.
* Write unit tests for game rules.
* Do not duplicate business logic.
* Use immutable objects where possible.
* Document public methods.

---

# Long-Term Scalability

The architecture should support future additions without major refactoring:

* Authentication
* Database persistence
* Match history
* Replay system
* AI opponents
* Tournament mode
* Ranking system
* Mobile client
* Spectator mode
* Analytics dashboard

By keeping layers independent and responsibilities well-defined, these features can be added incrementally while preserving the integrity of the core game engine.
