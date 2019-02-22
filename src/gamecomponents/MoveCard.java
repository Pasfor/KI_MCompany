package gamecomponents;

public class MoveCard {
    private boolean available;
    private int moveValue;

    public MoveCard(int moveValue) {
        this.available = true;
        this.moveValue = moveValue;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public int getMoveValue() {
        return this.getMoveValue();
    }
}
