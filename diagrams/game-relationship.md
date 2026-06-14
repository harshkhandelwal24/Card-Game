# Game Relationship Diagram

```
                        +----------------+
                        |      Game      |
                        +----------------+
                                |
      -------------------------------------------------
      |         |          |         |        |        |
      |         |          |         |        |        |
      ▼         ▼          ▼         ▼        ▼        ▼

+---------+ +---------+ +--------+ +-------+ +----------+ +------------+
| Players | |  Deck   | |Auction | | Team  | |ScoreBoard| |CurrentRound|
+---------+ +---------+ +--------+ +-------+ +----------+ +------------+
      |                                         |
      |                                         |
      ▼                                         ▼

 +-----------+                         +----------------+
 |   Player  |                         |     Round      |
 +-----------+                         +----------------+
      |                                         |
      |                                         |
      ▼                                         ▼

 +----------+                          +----------------+
 |   Hand   |                          |     Trick      |
 +----------+                          +----------------+
      |                                         |
      ▼                                         ▼

 +----------+                          +----------------+
 |   Card   |                          | Played Cards   |
 +----------+                          +----------------+
```

Relationship Summary

Game owns Players

Game owns Deck

Game owns Auction

Game owns Team

Game owns ScoreBoard

Game owns CurrentRound

Round owns Tricks

Player owns Hand

Hand contains Cards

Trick contains Played Cards
