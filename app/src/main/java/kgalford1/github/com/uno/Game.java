package kgalford1.github.com.uno;

import java.util.Random;

public class Game {
    private Deck deck;
    private Player human;
    private Player computer;
    private boolean humanTurn;
    private Card top;

    Game() {
        // Create game deck with all 80 cards.
        deck = new Deck();
        deck.gameDeck();

        // Create empty decks for human and computer.
        human = new Player();
        computer = new Player();

        // Randomly select who plays first.
        setHumanTurn();

        // Deal 7 cards to each player.
        dealCards();

        // Draw a card from the deck for the top of the pile.
        firstCard();
    }

    private void setHumanTurn() {  // Randomly select who plays first.
        Random random = new Random();
        humanTurn = random.nextBoolean();
    }

    private void dealCards() {  // Deal 7 cards to each player.
        for (int i = 0; i < 7; i++) {
            human.addCard(deck.dealCard());
            computer.addCard(deck.dealCard());
        }
    }

    private void firstCard() {  // Draw a card from the deck for the top of the pile.
        top = deck.dealCard();
    }

    public void computerTurn() {
        if (human.length() == 0)  // If human has won, do not play.
            return;

        if (!computer.computerTurn(top, this) && !isEmpty())  // Play a card if possible.
            computer.addCard(deck.dealCard());  // Else draw a card.
    }

    public void setTop(Card top) {
        this.top = top;
    }

    public Card getTop() {
        return top;
    }

    public Player getHuman() {
        return human;
    }

    public Player getComputer() {
        return computer;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean isHumanTurn() {
        return humanTurn;
    }

    public int length() {
        return deck.length();
    }

    public boolean isEmpty() {
        return this.length() == 0;
    }
}
