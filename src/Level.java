public class Level {
    private int lvl;
    private int field[][];

    public Level(int lvl) {
        this.lvl = lvl;
        this.initLVL();
    }

    private void initLVL() {
        switch (this.lvl) {
            case 1:
                System.out.println("one");
                this.field=new int[][] {
                {0, 8, 0, 0, 0},
                {0, 0, 0, 0, 0, 8},
                {0, 0, 0, 8, 0, 0, 0},
                {8, 0, 8, 0, 0, 0, 0, 8},
                {0, 0, 0, 0, 8, 0, 0, 0, 0},
                {8, 0, 0, 0, 0, 8, 0, 0},
                {0, 0, 8, 8, 0, 0, 8},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 8, 0, 0}
            };
                break;
            case 2:
                System.out.println("two");
                break;
            case 3:
                System.out.println("three");
                break;
            case 4:
                System.out.println("four");
                break;
        }
    }

    public void rotateLVL()
    {
        System.out.println("brtuh");
    }

    //____Log functions___
    public void printLVL() {
        for (int[] line : this.field) {
            System.out.println();
            for(int i=0 ; i<(9-line.length); i++)
            {
                System.out.print(" ");
            }
             for (int field: line)
             {
                 System.out.print(" "+field);
             }
        }
    }
}
