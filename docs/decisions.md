# Architecture Decisions

| Decision                     | Reason                        |
| ---------------------------- | ----------------------------- |
| Server authoritative         | Prevent cheating              |
| React frontend               | Fast UI development           |
| Spring Boot backend          | Strong typing and scalability |
| In-memory game state         | MVP simplicity                |
| No authentication            | Faster MVP                    |
| WebSocket communication      | Real-time gameplay            |
| Event-driven engine          | Easier maintenance            |
| Separate game engine         | Testability                   |
| Hidden teams                 | Preserve game mechanics       |
| Console simulation before UI | Validate rules first          |
