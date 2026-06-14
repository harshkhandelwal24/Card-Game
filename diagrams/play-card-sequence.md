Play Card Sequence
Player Browser
      │
      │ PLAY_CARD(cardId)
      ▼
WebSocket Server
      │
      │ Validate Turn
      ▼
Game Engine
      │
      │ Validate Suit
      │
      │ Validate Card Exists
      │
      │ Remove Card
      │
      │ Update Trick
      │
      │ Determine Winner (if trick complete)
      │
      │ Update Game State
      ▼
Game State
      │
      │ Broadcast Updated State
      ▼
WebSocket Server
      │
      ▼
All Connected Players


Internal Play Card Flow
Receive Event

↓

Validate Game State

↓

Validate Player Turn

↓

Validate Card Exists

↓

Validate Suit Following

↓

Play Card

↓

Update Trick

↓

Have 6 Cards Been Played?

        │

    No ─────────► Wait For Next Player

        │

       Yes

        ▼

Determine Trick Winner

↓

Calculate Trick Points

↓

Assign Leader

↓

Start Next Trick

↓

Broadcast Updated State