//Taranjot Singh Dhaliwal
//3112404
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList< Card> cards = new ArrayList<>();
    public Deck(){ 
        createCards();
    }

    public void createCards(){
        Color[] colors = Color.values();
        Face [] faces = Face.values();
        for(int i=0;i<colors.length;i++){
            // Create 4 cards pf  Black wild and draw4 
            if(colors[i]== Color.valueOf("BLACK")) {

                for(int j=0;j<faces.length;j++){
                    if(faces[j]==Face.valueOf("WILD") ||faces[j]==Face.valueOf("DRAW4")) {

                        for( int a =0; a<4; a++){
                            Card c = new Card(colors[i], faces[j]);
                            cards.add(c);
                        }

                    }
                }
            }
            else{
                // create cards of other four colors, one card with face zero and two cards of each other faces except wild and draw4 
                for(int j=0;j<faces.length;j++){
                    if(faces[j]!=Face.valueOf("WILD") &&faces[j]!=Face.valueOf("DRAW4")) {
                        if(faces[j]!=Face.valueOf("ZERO")){
                            for( int a =0; a<2; a++){    
                                Card c = new Card(colors[i], faces[j]);
                                cards.add(c);
                            }
                        }
                        else{
                            Card c = new Card(colors[i], faces[j]);
                            cards.add(c);
                        }
                    }

                }
            }
        }

    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card pickCard(){
        Card c =  cards.get(0);
        cards.remove(0);
        return c;
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public String toString(){
        String z="";
        for(int i=0;i<cards.size(); i++){
            z=z+cards.get(i)+" ";
        }
        return z;
    }
}
