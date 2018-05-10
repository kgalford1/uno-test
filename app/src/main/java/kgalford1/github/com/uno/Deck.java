package kgalford1.github.com.uno;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();

    Deck() {
        deck.clear();
    }

    public void gameDeck() {
        // Clear deck.
        deck.clear();

        // Add two of each Uno card.
        for (int value = 0; value < 10; value++) {
            for (int color = 0; color < 4; color++) {
                for (int dup = 0; dup < 2; dup++)
                    deck.add(new Card(value, color));
            }
        }

        // Shuffle deck.
        Collections.shuffle(deck);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void addCard(Card card) {
        deck.add(0, card);
    }

    public Card dealCard() {  // Removes top card of deck; for drawing a card.
        return this.deck.remove(0);
    }

    public Card dealCard(int i) {  // Remove's ith card of deck; for playing a card from hand.
        return this.deck.remove(i);
    }

    public int length() {
        return deck.size();
    }
}
