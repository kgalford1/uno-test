package kgalford1.github.com.uno;

public class Player extends Deck {
    Player() {
    }

    public boolean playCard(Card card, Card top) {  // Returns true if allowed to play card.
        return card.getColor() == top.getColor() || card.getValue() == top.getValue();
    }

    public boolean computerTurn(Card top, Game game) {  // Iterates through hand to find a card that can be played.
        Card tryCard;
        for (int i = 0; i < this.length(); i++) {
            tryCard = this.dealCard(i);  // Takes a card from the hand.
            if (playCard(tryCard, top)) {  // Attempts to play it.
                game.setTop(tryCard);  // Puts it on the top of the pile if allowed.
                return true;  // Returns true if card played.
            } else
                this.addCard(tryCard);  // Adds it back to the hand if not allowed.
        }

        return false;  // Returns false if no card was able to be played.
    }

    public boolean hasWon() {
        return this.length() == 0;
    }
}
