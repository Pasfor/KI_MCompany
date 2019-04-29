import gamecomponents.Level;

public class Debug {

    public static void main(String[]args)
    {
        Level lvl = new Level(4);
        lvl.printLVL();

       for(int[] m : lvl.returnValidMoves(new int[]{4,5},1,false,0))
       {
           System.out.println(""+m[0]+","+m[1]+","+m[2]);
       }
    }
}
