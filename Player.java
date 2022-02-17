// Taranjot Singh Dhaliwal
//3112404
import java.util.ArrayList;
public class Player {
    private String name;
    private ArrayList<Card> hand ;
    public Player( String name) {
        this.name= name; 
        hand= new ArrayList<>();
    }

    public void addHand( Card c) {
        hand.add(c);
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public String toString() {
        return name;
    }
}
