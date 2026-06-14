# High Level Architecture

## Principles

* Server authoritative
* Stateless frontend
* Deterministic game engine
* Event driven architecture

---

## Components

Frontend

* React
* Tailwind
* WebSocket Client

Backend

* Game Engine
* Room Manager
* WebSocket Server
* Event Dispatcher

---

## Layers

Presentation Layer

↓

Communication Layer

↓

Game Service Layer

↓

Game Engine

↓

Domain Model

---

## MVP Storage

Game state stored in memory.

No database.

---

## Future

Redis

PostgreSQL

Authentication

Analytics

Replay engine
