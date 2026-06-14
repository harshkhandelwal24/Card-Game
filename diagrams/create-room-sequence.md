Sequence Diagrams
Create Room Sequence
Host Browser
      │
      │ Create Room
      ▼
Backend API
      │
      │ Generate Room Code
      ▼
Room Manager
      │
      │ Create Room Object
      ▼
Memory Store
      │
      │ Save Room
      ▼
Room Manager
      │
      │ Return Room Code
      ▼
Backend API
      │
      │ Response
      ▼
Host Browser
