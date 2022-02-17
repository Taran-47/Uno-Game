// Taranjot Singh Dhaliwal
//3112404
import java.util.ArrayList;
import java.util.Random;

public class GameSim {
    private CircularDoublyLinkedList<Player> players = new CircularDoublyLinkedList<>();
    private Deck deck ;
    private Card lastCard;
    public GameSim() {
        deck = new Deck();
        deck.shuffle();

        addPlayers("Hamm");
        addPlayers("Rex");
        addPlayers("Buzz");
        addPlayers("Woody");
        
        lastCard= deck.pickCard();
        Card o = lastCard;
        System.out.println("Let's play Uno");
        System.out.println("First Card:");
        System.out.println(lastCard);
        Random g = new Random();
        ArrayList<Card> arry= new ArrayList<>(); // Arraylist for cards in hand of a player
        Player active;
        boolean again = false;
        Player p=players.first();;
        do { 
            //if very first card picked from deck has reverse face
            if(o.getFace()== Face.valueOf("REVERSE")){
                players.reverse();
                players.rotate();
                System.out.println("Game reverses direction");
            }
            active = players.first();
            arry = active.getHand();
            if(!again) {
                // case for face Wild
                if(lastCard.getFace()==Face.valueOf("WILD")) {
                    if(p.getHand().size()!=0) {
                        Color z =p.getHand().get(g.nextInt(p.getHand().size())).getColor();
                        System.out.println("New color is "+ z) ;
                        lastCard = new Card (z,null);
                    }
                }
                // case for face Draw4
                if(lastCard.getFace()==Face.valueOf("DRAW4")) {
                    // if deck get empty a new deck is created 
                    if(deck.getCards().size()==0) {
                        deck = new Deck();
                        deck.shuffle();
                    }
                    for(int i=0; i<4; i++){
                        arry.add(deck.pickCard());
                    }
                    // declares new color based any random in hand of player
                    if(p.getHand().size()!=0){
                        Color z =p.getHand().get(g.nextInt(p.getHand().size())).getColor();
                        System.out.println("New color is "+ z) ;
                        lastCard = new Card (z,null);
                    }
                    System.out.println();
                    System.out.println(active +" draws four cards and misses the turn");

                    players.rotate();
                    active = players.first();
                    arry = active.getHand();

                }
                // for face skip
                if ( lastCard.getFace()== Face.valueOf("SKIP")) {
                    players.rotate();
                    System.out.println();
                    System.out.println(active +" misses the turn");

                    active = players.first();
                    arry = active.getHand();

                }
                // for face Draw2
                if ( lastCard.getFace()== Face.valueOf("DRAW2")) {
                    if(deck.getCards().size()==0) {
                        deck = new Deck();
                        deck.shuffle();
                    }
                    arry.add(deck.pickCard());
                    arry.add(deck.pickCard());
                    System.out.println();
                    System.out.println(active +" draws two cards and misses the turn");

                    players.rotate();
                    active = players.first();
                    arry = active.getHand();

                }
                // for face reverse

            }

            again = false;
            boolean valid = false;
            System.out.println();

            display(arry , active);
            for(int i=0; i<arry.size() && !valid; i++) {
                Card c = arry.get(i);
                if(c.getColor()!=Color.valueOf("BLACK")) {
                    // card wiil be played on basis of same color or face that mathes first
                    if(c.getColor()==lastCard.getColor()) {
                        System.out.println(c);
                        lastCard=c;
                        arry.remove(i);
                        valid = true;
                    } 
                    else {
                        if(c.getFace()==lastCard.getFace()) {
                            System.out.println(c);
                            lastCard=c;
                            arry.remove(i);
                            valid=true;
                        }

                    }
                }
            }
            if(!valid) {
                // if card is of black face 
                for(int i=0; i<arry.size() && !valid; i++) {
                    Card c = arry.get(i);
                    if(c.getColor()==Color.valueOf("BLACK")) {
                        p = active;
                        System.out.println(c);
                        arry.remove(i);
                        valid = true;
                        lastCard=c;
                    }
                }
                // if player does not have any matching card or black card then draws a card
                if(!valid) {

                    System.out.println("Draws a card");
                    if(deck.getCards().size()==0) {
                        deck = new Deck();
                        deck.shuffle();
                    }
                    arry.add(deck.pickCard());

                    again= true;

                }
            }
            if(!again){
                //for reverse face
                if ( lastCard.getFace()== Face.valueOf("REVERSE")) {
                    players.reverse();
                    System.out.println("Game reverses direction");
                }
            }

            if(arry.size()==1) {
                System.out.println(active +" yells uno");

            }

            players.rotate();

        }
        while(arry.size()!=0);
        System.out.println();
        System.out.println(active +" Wins");
    }

    public void addPlayers(String s) {
        Player p= new Player(s);
        for( int i=0; i<7; i++) {
            Card c = deck.getCards().get(i);
            deck.getCards().remove(i);
            p.addHand(c);
        }

        players.addFirst(p);

    }

    public static void display(ArrayList<Card> arry, Player active) {
        System.out.print("("+active+"'s hand: ");
        for( int i=0; i<arry.size();i++) {
            System.out.print(arry.get(i));
            if(i!= (arry.size()-1)) System.out.print(", ");
        }
        System.out.print(")");
        System.out.println();
    }

}
