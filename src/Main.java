import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Level LVLone = new Level(1);
         LVLone.printLVL();
         LVLone.rotateLVL();
         LVLone.printLVL();

         ArrayList<int[]> out = LVLone.returnValidMoves(new int[]{5, 4},4 );


         for (int i=0; i<out.size();i++)
        {
            System.out.println("\n"+out.get(i)[0] +" "+out.get(i)[1]+"");
        }

    }
}
