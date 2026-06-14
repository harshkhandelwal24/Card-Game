# Game State Machine

```
CREATED

↓

WAITING_FOR_PLAYERS

↓

DEAL_CARDS

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

GAME_FINISHED
```

## Invalid transitions

WAITING_FOR_PLAYERS → PLAYING

BIDDING → SCORING

PLAYING → DEAL_CARDS

TEAM_SELECTION → WAITING_FOR_PLAYERS

---

Only valid transitions are permitted by the server.
