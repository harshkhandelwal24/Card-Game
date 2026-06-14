# API Specification (Draft)

## REST

### Create Room

POST /rooms

Response

```
{
  "roomCode":"ABCD"
}
```

---

### Join Room

POST /rooms/{code}/join

---

### Start Game

POST /rooms/{code}/start

---

## WebSocket Events

Client → Server

* PLACE_BID
* PASS_BID
* SELECT_TRUMP
* SELECT_TEAM_CARD
* PLAY_CARD
* RECONNECT

---

Server → Client

* PLAYER_JOINED
* PLAYER_LEFT
* BID_UPDATED
* BID_WON
* TRUMP_SELECTED
* TEAM_UPDATED
* CARD_PLAYED
* TRICK_WON
* SCORE_UPDATED
* GAME_FINISHED
* ERROR
