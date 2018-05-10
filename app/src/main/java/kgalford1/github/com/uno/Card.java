package kgalford1.github.com.uno;

public class Card {
    private int value;  // Face value of card (0 → 9 inclusive)
    private int color;  // (0 = Red) (1 = Green) (2 = Blue) (3 = Yellow)

    Card(int value, int color) {
        setValue(value);
        setColor(color);
    }

    private void setValue(int value) {
        this.value = value;
    }

    private void setColor(int color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public int getColor() {
        return color;
    }

    public String toString() {
        String color = "";
        switch (this.color) {
            case 0:
                color = "Red";
                break;
            case 1:
                color = "Green";
                break;
            case 2:
                color = "Blue";
                break;
            case 3:
                color = "Yellow";
                break;
        }

        return String.format("%s %d", color, value);  // e.g. "Yellow 9"
    }
}
