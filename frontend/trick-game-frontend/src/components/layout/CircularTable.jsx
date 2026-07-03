import Card from "../game/Card";

export default function CircularTable({ players = [], currentTurn, myId, hands = {} }) {
  const radius = players.length > 5 ? 165 : 200;

  return (
    <div style={styles.circle}>
      {players.map((p, i) => {
        const angle = (i / players.length) * 2 * Math.PI;

        const x = radius * Math.cos(angle);
        const y = radius * Math.sin(angle);

        const isTurn = currentTurn === p.id;
        const handSize = Array.isArray(hands[p.id]) ? hands[p.id].length : undefined;
        const showBack = p.id !== myId;

        return (
          <div
            key={p.id}
            data-player-id={p.id}
            style={{
              ...styles.player,
              transform: `translate(${x}px, ${y}px) translateY(20px)`,
              border: isTurn ? "2px solid #22c55e" : "1px solid #334155",
              boxShadow: isTurn ? "0 0 10px #22c55e" : "none",
              background: isTurn ? "#14532d" : "#1e293b"
            }}
          >
            <div style={styles.playerLabel}>👤 {p.name}</div>
            <div style={styles.turnBadge}>{isTurn ? "To play" : "Waiting"}</div>
            {showBack && (
              <div style={styles.cardBackWrapper}>
                <Card card={{}} size="small" faceDown backCount={handSize} />
              </div>
            )}
          </div>
        );
      })}
    </div>
  );
}

const styles = {
  circle: {
    position: "relative",
    width: "min(520px, calc(100% - 16px))",
    aspectRatio: "1 / 1",
    maxWidth: "100%",
    maxHeight: "100%",
    borderRadius: "50%",
    border: "2px solid #334155",
    display: "flex",
    alignItems: "center",
    justifyContent: "center"
  },

  player: {
    position: "absolute",
    padding: "3px 6px",
    background: "#1e293b",
    borderRadius: "10px",
    fontSize: "8px",
    transition: "all 0.3s",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    gap: "2px",
    maxWidth: "75px"
  },

  playerLabel: {
    whiteSpace: "nowrap",
    fontWeight: 700,
    fontSize: "10px",
    overflow: "hidden",
    textOverflow: "ellipsis"
  },

  turnBadge: {
    fontSize: "7px",
    color: "#d1fae5",
    fontWeight: 700,
    textTransform: "uppercase",
    letterSpacing: "0.05em"
  },

  cardBackWrapper: {
    marginTop: "8px"
  }
};