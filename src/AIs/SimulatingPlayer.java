package AIs;

import gamecomponents.Level;
import gamecomponents.Mole;
import gamecomponents.Player;

import java.util.ArrayList;
import java.util.List;

public class SimulatingPlayer extends Player {

    private int playerNumber;
    private ArrayList<Integer> moveCards;
    private ArrayList<Mole> moles;

    public SimulatingPlayer(Player player){
       this.playerNumber = player.getPlayerNumber();
        this.moles = new ArrayList<>();
        for (Mole m : player.getMoles()) {
            this.moles.add(new Mole(m));
        }
        this.moveCards= new ArrayList<>();
        for(int value : player.getMoveCards())
        {
            moveCards.add(value);
        }
    }

    @Override
    public boolean makeMove(Level lvl, boolean specialFieldHit) {
        int steps = drawMoveCard();
        //System.out.println("palyer: " + playerNumber + " steps: " + steps);

        //check if possible without moving out of hole
        List<Mole> moveableMoles = moveableMolesNotInHole(lvl, steps, specialFieldHit);
        if (moveableMoles.isEmpty()) {
            moveableMoles = allMoveableMoles(lvl, steps, specialFieldHit);
            return moveRandom(lvl, steps, moveableMoles, specialFieldHit);
        } else {
            return moveInHole(lvl, steps, moveableMoles, specialFieldHit);
        }
    }

    /**
     * returns all Moles that are moveable and NOT in a Hole
     *
     * @param lvl
     * @param steps
     * @return
     */
    private List<Mole> moveableMolesNotInHole(Level lvl, int steps, boolean specialFieldHit) {
        //copy Moles
        List<Mole> copyMoles = new ArrayList<>(this.moles);
        //deleting all on hole
        copyMoles.removeIf(m -> m.getPositionVlaue() == 8);
        //check if it is possible to move without getting out of hole
        copyMoles.removeIf(m -> lvl.returnValidMoves(m.getPosition(), steps, specialFieldHit, m.getPositionVlaue()).isEmpty());
        return copyMoles;
    }

    /**
     * returns ALL moveable moles
     *
     * @param lvl
     * @param steps
     * @return
     */
    private List<Mole> allMoveableMoles(Level lvl, int steps, boolean specialFieldHit) {
        List<Mole> copyMoles = new ArrayList<>(this.moles);
        //check if possible to move
        copyMoles.removeIf(m -> lvl.returnValidMoves(m.getPosition(), steps, specialFieldHit, m.getPositionVlaue()).isEmpty());
        return copyMoles;
    }

    /**
     * choose random Mole out of moveable moles and make a random move
     *
     * @param lvl
     * @param steps
     * @param copyMoles
     */
    private boolean moveRandom(Level lvl, int steps, List<Mole> copyMoles, boolean specialFieldHit) {
        if (!copyMoles.isEmpty()) {
            Mole moveMole = copyMoles.get((int) (Math.random() * copyMoles.size()));
            //get random possible move for this mole
            ArrayList<int[]> possibleMoves = lvl.returnValidMoves(moveMole.getPosition(), steps, specialFieldHit, moveMole.getPositionVlaue());
            int[] move = possibleMoves.get((int) (Math.random() * possibleMoves.size()));

            //Move this Mole
            lvl.resetValue(moveMole.getPosition(), moveMole.getPositionVlaue());
            moveMole.setPosition(move, lvl.getField()[move[0]][move[1]]);
            lvl.setMole(move[0], move[1], this.playerNumber);

            if (moveMole.getPositionVlaue() == 9) {
                return true;
            }
        }
        return false;
    }
    private boolean moveInHole(Level lvl, int steps, List<Mole> copyMoles, boolean specialFieldHit){

        for(Mole m: copyMoles)
        {
            ArrayList<int[]> possibleMoves = lvl.returnValidMoves(m.getPosition(), steps, specialFieldHit, m.getPositionVlaue());
            for(int[] move : possibleMoves)
            {
              if(move[2]==8)
              {
                  //Move this Mole
                  lvl.resetValue(m.getPosition(), m.getPositionVlaue());
                  m.setPosition(move, lvl.getField()[move[0]][move[1]]);
                  lvl.setMole(move[0], move[1], this.playerNumber);

                  if (m.getPositionVlaue() == 9) {
                      return true;
                  }else{return false;}
              }
            }
        }
        return moveRandom(lvl, steps, copyMoles, specialFieldHit);
    }


    public ArrayList<Integer> getMoveCards()
    {
        return this.moveCards;
    }
    @Override
    public void initMoveCards() {
        this.moveCards = new ArrayList<>();
        this.moveCards.add(1);
        this.moveCards.add(2);
        this.moveCards.add(2);
        this.moveCards.add(3);
        this.moveCards.add(3);
        this.moveCards.add(4);
    }

    @Override
    public int getAmountOfMoles() {
        return this.moles.size();
    }

    @Override
    public boolean setMole(Level lvl, int circle) {
        return false;
    }

    @Override
    public int drawMoveCard() {
        if (this.moveCards.isEmpty()) {
            initMoveCards();
        }
        int rndIndex = (int) (Math.random() * this.moveCards.size());
        int moveValue = this.moveCards.get(rndIndex);
        this.moveCards.remove(this.moveCards.get(rndIndex));
        return moveValue;
    }

    @Override
    public ArrayList<Mole> getMoles() {
        return this.moles;
    }

    @Override
    public int getPlayerNumber() {
        return this.playerNumber;
    }


    @Override
    public boolean initMolesToNewLvl(Level lvl) {
        return false;
    }
}

