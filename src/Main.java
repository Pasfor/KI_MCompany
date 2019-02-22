import gamecomponents.Level;

public class Main {

    public static void main(String[] args) {

        Level one = new Level(1);
        one.printLVL();

        one.setMole(3,3,1);
        one.setMole(3,1,1);

        one.printLVL();

        one.returnValidMoves(new int[]{3,3},3);





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
