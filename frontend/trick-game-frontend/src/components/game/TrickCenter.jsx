import { forwardRef } from "react";
import Card from "./Card";

const TrickCenter = forwardRef(({ trick = [], trickWinner = null }, ref) => {
  const count = trick.length;
  const isComplete = count === 6;

  return (
    <div style={styles.center} ref={ref}>
      {count === 0 ? (
        <div style={{ opacity: 0.5 }}>Waiting for plays...</div>
      ) : (
        <div style={styles.row}>
          {trick.map((t, i) => (
            <div key={i} style={styles.cardWrapper}>
              <div style={styles.cardLabel}>{t.playerName || "Player"}</div>
              <Card card={t.card} size="small" />
            </div>
          ))}
        </div>
      )}
      {trickWinner && (
        <div style={{
          ...styles.winnerBadge,
          ...(isComplete ? styles.winnerBadgeComplete : styles.winnerBadgeActive)
        }}>
          {isComplete ? "🏆" : "👑"} {trickWinner} {isComplete ? "wins" : "leading"}!
        </div>
      )}
    </div>
  );
});

export default TrickCenter;

const styles = {
  center: {
    position: "absolute",
    left: "35%",
    top: "50%",
    transform: "translate(-50%, -50%)",
    width: "min(640px, 80%)",
    minWidth: "480px",
    height: "260px",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    padding: "14px",
    background: "rgba(15, 23, 42, 0.92)",
    border: "1px solid rgba(148, 163, 184, 0.2)",
    borderRadius: "20px",
    zIndex: 10,
    flexDirection: "column",
    position: "relative"
  },

  row: {
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    gap: "10px",
    flexWrap: "wrap",
    width: "100%"
  },

  cardWrapper: {
    width: "126px",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    padding: "8px 6px 6px",
    borderRadius: "14px",
    background: "rgba(15, 23, 42, 0.72)",
    border: "1px solid rgba(148, 163, 184, 0.22)",
    boxShadow: "0 10px 24px rgba(2, 8, 23, 0.28)"
  },

  cardLabel: {
    fontSize: "11px",
    color: "#f8fafc",
    marginBottom: "8px",
    fontWeight: 700,
    textAlign: "center",
    background: "rgba(30, 41, 59, 0.95)",
    padding: "3px 8px",
    borderRadius: "999px",
    border: "1px solid rgba(148, 163, 184, 0.24)"
  },

  winnerBadge: {
    position: "fixed",
    top: "20px",
    left: "50%",
    transform: "translateX(-50%)",
    padding: "10px 18px",
    borderRadius: "12px",
    fontSize: "14px",
    fontWeight: 700,
    border: "1px solid",
    boxShadow: "0 8px 24px",
    whiteSpace: "nowrap",
    zIndex: 15,
    transition: "all 200ms ease-out"
  },

  winnerBadgeActive: {
    background: "linear-gradient(135deg, rgba(59, 130, 246, 0.95), rgba(37, 99, 235, 0.95))",
    color: "#dbeafe",
    borderColor: "rgba(96, 165, 250, 0.5)",
    boxShadow: "0 8px 24px rgba(59, 130, 246, 0.4)"
  },

  winnerBadgeComplete: {
    background: "linear-gradient(135deg, rgba(34, 197, 94, 0.95), rgba(22, 163, 74, 0.95))",
    color: "#dcfce7",
    borderColor: "rgba(134, 239, 172, 0.5)",
    boxShadow: "0 8px 24px rgba(34, 197, 94, 0.4)"
  }
};