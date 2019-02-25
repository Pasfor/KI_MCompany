import gamecomponents.Level;

public class Main {

    public static void main(String[] args) {

        Level one = new Level(1);
        one.printLVL();

        one.setMole(5,4,1);

        one.setMole(6,1,2);

        one.setMole(0,3,2);
        one.setMole(4,5,2);
        one.setMole(2,2,2);

        one.printLVL();

        one.returnValidMoves(new int[]{5,4},2);

//        Level LVLtwo = new Level(2);
//        LVLtwo.printLVL();
//
//        Level LVLthree = new Level(3);
//        LVLthree.printLVL();
//
//        Level LVLfour = new Level(4);
//        LVLfour.printLVL();
    }
}
