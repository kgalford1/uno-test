package kgalford1.github.com.uno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;


public class MainActivity extends AppCompatActivity {
    private Game game;
    private TextView cardCountView;
    private TextView topCardView;
    private TextView computerCountView;
    private TextView computerPluralView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();
        findViews();

        renderTable();  // Update the views in the activity.
        showHumanHand();  // Update the views for the human's hand.

        // Computer plays turn if they are the starting player.
        if (!game.isHumanTurn()) {
            game.computerTurn();
            renderTable();
        }
    }

    public void humanHandClick(View v) {  // Click handler for cards in the human's hand.
        if (game.getComputer().hasWon())  // Check if computer has won.
            return;

        Player human = game.getHuman();

        int id = v.getId();  // ID of card views are simply the index of the card in the hand. (Leftmost card is 0)
        Card card = game.getHuman().getDeck().get(id);

        if (human.playCard(card, game.getTop())) {  // If card is allowed to be played.
            human.getDeck().remove(id);  // Remove it from the hand.
            game.setTop(card);  // Set it as the top card of the pile.
            renderTable();
            showHumanHand();

            game.computerTurn();  // The computer now plays its turn.
            renderTable();
        }
    }

    public void humanPickUp(View v) {  // Click handler for when the human clicks the deck to draw.
        if (game.getComputer().hasWon() || game.getHuman().hasWon() || game.isEmpty())  // Check if computer has won.
            return;

        Player human = game.getHuman();

        human.addCard(game.getDeck().dealCard());  // Take card from draw pile and add to hand.
        game.computerTurn();  // The computer now plays its turn.
        renderTable();
        showHumanHand();
    }

    public void newGame(View v) {  // Restarts activity for new game.
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void setCardViewColor(TextView view, Card card) {  // Translates the card color integer to the hex color in colors.xml.
        switch (card.getColor()) {
            case 0:
                view.setBackgroundResource(R.color.red);
                break;
            case 1:
                view.setBackgroundResource(R.color.green);
                break;
            case 2:
                view.setBackgroundResource(R.color.blue);
                break;
            case 3:
                view.setBackgroundResource(R.color.yellow);
                break;
        }
    }

    private void showHumanHand() {
        Deck human = game.getHuman();
        FlexboxLayout handView = findViewById(R.id.human_hand);
        handView.removeAllViews();
        int numberCards = human.length();

        TextView temp;
        Card tempCard;

        // For each card in hand, inflate "card_view.xml", change color and number, then add to handView.

        for (int i = 0; i < numberCards; i++) {
            tempCard = human.getDeck().get(i);
            temp = (TextView) getLayoutInflater().inflate(R.layout.card_view, handView, false);
            temp.setText(String.format("%d", tempCard.getValue()));
            setCardViewColor(temp, tempCard);
            temp.setId(i);  // ID equal to index in hand.
            handView.addView(temp);
        }
    }

    private void renderTable() {
        // Number of cards in the computer's hand.
        int computerNumber = game.getComputer().length();
        computerCountView.setText(String.format("%d", computerNumber));

        // " card." if one card in hand. " cards." otherwise.
        String plural = getString(R.string.computer_text_1_plural);
        if (computerNumber == 1)
            plural = getString(R.string.computer_text_1_single);
        computerPluralView.setText(plural);

        // Number of cards remaining in the deck.
        cardCountView.setText(String.format("%d", game.length()));

        // Top card value and color.
        topCardView.setText(String.format("%d", game.getTop().getValue()));
        setCardViewColor(topCardView, game.getTop());

        // Displays a toast if the human or computer has won.
        if (game.getComputer().hasWon()) {
            Toast toast = Toast.makeText(this, "Computer wins!", Toast.LENGTH_LONG);
            toast.show();
        } else if (game.getHuman().hasWon()) {
            Toast toast = Toast.makeText(this, "You win!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void findViews() {
        computerCountView = findViewById(R.id.computer_text_view_number);
        computerPluralView = findViewById(R.id.computer_text_view_1);
        cardCountView = findViewById(R.id.card_count);
        topCardView = findViewById(R.id.top_card);
    }
}
