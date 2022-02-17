// Taranjot Singh Dhaliwal
//3112404
public class Card {
    private Color color;
    private Face face;
    public Card( Color color, Face face) {
        this.color= color;
        this.face= face;
    }

    public Color getColor() {
        return color;
    }

    public Face getFace() {
        return face;
    }

    public String toString() {
        return color+" "+ face;
    }
}
