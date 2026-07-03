import { useState } from "react";
import { useGameStore } from "../../store/useGameStore";
import Card from "./Card";

export default function RoundInfoPanel() {
  const game = useGameStore();
  const [isExpanded, setIsExpanded] = useState(true);

  // Only show during PLAYING phase or after
  if (!["PLAYING", "FINISHED"].includes(game.phase)) {
    return null;
  }

  // Extract partner cards from game events
  const partnerCardsEvent = game.events?.find(
    (e) => e.type === "PARTNER_CARDS_CHOSEN" || e.message?.includes("partner cards")
  );

  // Try to extract partner cards from event message
  let partnerCards = [];
  if (partnerCardsEvent?.message) {
    const match = partnerCardsEvent.message.match(/partner cards (.+?)$/);
    if (match) {
      const cardStrings = match[1].split(" and ");
      partnerCards = cardStrings.map((cardStr) => {
        const trimmed = cardStr.trim();
        const parts = trimmed.match(/([A-Z]+)\s+of\s+([A-Z]+)/i);
        if (parts) {
          return {
            rank: parts[1],
            suit: parts[2].toUpperCase()
          };
        }
        return null;
      }).filter(Boolean);
    }
  }

  // Find trump suit icon
  const trumpIcons = {
    HEARTS: "♥",
    DIAMONDS: "♦",
    CLUBS: "♣",
    SPADES: "♠"
  };

  const trumpIcon = trumpIcons[game.trump] || game.trump;

  return (
    <>
      {/* Toggle Button */}
      <button
        type="button"
        onClick={() => setIsExpanded(!isExpanded)}
        style={styles.toggleButton}
      >
        {isExpanded ? "▼" : "▶"} Round Info
      </button>

      {/* Expandable Panel */}
      {isExpanded && (
        <div style={styles.container}>
          {/* Trump Section */}
          <div style={styles.section}>
            <div style={styles.label}>🎯 Trump</div>
            <div style={styles.trumpValue}>
              {trumpIcon} {game.trump}
            </div>
          </div>

          {/* Partner Cards Section */}
          <div style={styles.section}>
            <div style={styles.label}>👥 Partner Cards</div>
            <div style={styles.cardsRow}>
              {partnerCards.length > 0 ? (
                partnerCards.map((card, idx) => (
                  <div key={idx} style={styles.cardWrapper}>
                    <Card
                      card={{
                        suit: card.suit,
                        rank: card.rank
                      }}
                      size="xsmall"
                    />
                  </div>
                ))
              ) : (
                <div style={styles.placeholder}>Selecting...</div>
              )}
            </div>
          </div>
        </div>
      )}
    </>
  );
}

const styles = {
  toggleButton: {
    position: "fixed",
    top: "60px",
    left: "50%",
    transform: "translateX(-50%)",
    padding: "10px 20px",
    fontSize: "14px",
    fontWeight: 700,
    background: "linear-gradient(135deg, rgba(66, 133, 244, 0.9), rgba(51, 102, 204, 0.9))",
    color: "white",
    border: "2px solid rgba(79, 172, 254, 0.6)",
    borderRadius: "12px",
    cursor: "pointer",
    transition: "all 200ms ease-out",
    zIndex: 1001,
    boxShadow: "0 8px 24px rgba(51, 102, 204, 0.3)",
    whiteSpace: "nowrap"
  },

  container: {
    position: "fixed",
    top: "110px",
    left: "50%",
    transform: "translateX(-50%)",
    background: "linear-gradient(135deg, rgba(30, 41, 59, 0.95), rgba(15, 23, 42, 0.95))",
    border: "2px solid rgba(71, 85, 105, 0.5)",
    borderRadius: "16px",
    padding: "16px 24px",
    display: "flex",
    gap: "32px",
    alignItems: "center",
    zIndex: 1000,
    boxShadow: "0 12px 48px rgba(0, 0, 0, 0.6)",
    backdropFilter: "blur(8px)",
    animation: "slideDown 200ms ease-out"
  },

  section: {
    display: "flex",
    flexDirection: "column",
    gap: "8px",
    alignItems: "center"
  },

  label: {
    fontSize: "12px",
    fontWeight: 700,
    color: "#94a3b8",
    textTransform: "uppercase",
    letterSpacing: "0.1em"
  },

  trumpValue: {
    fontSize: "18px",
    fontWeight: 700,
    color: "#fbbf24",
    textShadow: "0 0 12px rgba(251, 191, 36, 0.4)",
    whiteSpace: "nowrap"
  },

  cardsRow: {
    display: "flex",
    gap: "12px",
    alignItems: "center"
  },

  cardWrapper: {
    display: "flex",
    justifyContent: "center",
    alignItems: "center"
  },

  placeholder: {
    fontSize: "12px",
    color: "#64748b",
    fontStyle: "italic"
  }
};
