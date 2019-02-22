package gamecomponents;

import java.util.ArrayList;

public class Player {
    private int playerNumber;
    private ArrayList<MoveCard> moveCards;
    private ArrayList<Mole> moles;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.moveCards = new ArrayList<>();
        this.moles = new ArrayList<>();
        initMoveCards();
    }

    private void initMoveCards()
    {
        this.moveCards.add(new MoveCard(1));
        this.moveCards.add(new MoveCard(2));
        this.moveCards.add(new MoveCard(2));
        this.moveCards.add(new MoveCard(3));
        this.moveCards.add(new MoveCard(3));
        this.moveCards.add(new MoveCard(4));
    }

}
