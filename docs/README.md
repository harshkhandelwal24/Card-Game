# 3 Of Spades

## Overview

3 Of Spades is a multiplayer online card game for six players based on bidding, hidden teams, trump selection, and trick-taking mechanics.

The objective is to faithfully recreate the physical card game in an online environment while maintaining game integrity through a server-authoritative architecture.

This project is being developed as a production-quality software system with emphasis on clean architecture, maintainability, scalability, and testability.

---

# Features (Planned)

* Six-player multiplayer rooms
* Private room code sharing
* Bidding system
* Trump selection
* Secret team selection
* Real-time gameplay
* Automatic score calculation
* Turn timers
* Reconnect support
* Responsive web interface

---

# Technology Stack

## Frontend

* React
* TypeScript
* Tailwind CSS
* Vite

## Backend

* Spring Boot
* Java
* WebSocket
* Maven

## Testing

* JUnit
* Integration Tests

---

# Project Structure

```
backend/
frontend/
docs/
tests/
assets/
scripts/
diagrams/
```

---

# Development Philosophy

* Server-authoritative game engine
* UI is only responsible for rendering state
* Game engine independent of networking
* Test-driven development for game rules
* Small iterative milestones

---

# Current Status

Project currently in architecture and domain modeling phase.

---

# Future Roadmap

* Complete game engine
* Console simulation
* Multiplayer support
* React UI
* Deployment
* Friend testing
* Match history
* Rankings
* Spectator mode
* Mobile support
