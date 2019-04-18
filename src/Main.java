import AIComponents.GameState;
import AIs.McAIOutput;
import AIs.McAiDet;
import AIs.RndAI;
import DeterminedTesting.DetMCTS_ClassicE_SmartR;
import DeterminedTesting.DetMCTS_SmartE_AllR;
import DeterminedTesting.DetMCTS_SmartE_SmartR;
import GameSession.*;
import StandardTesting.*;
import output.Output;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
/**
 * Determined Test
 */
//        Output.getInstance().writeToFile("Player One:DetMCTS_ClassicE_SmartR || Player Two: DetMCTS_SmartE_AllR");
//        for(int i = 0; i<20 ;i++)
//        {
//            DetMCTS_ClassicE_SmartR one = new DetMCTS_ClassicE_SmartR(1,null);
//            DetMCTS_SmartE_AllR two = new DetMCTS_SmartE_AllR(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }
//
//
        Output.getInstance().writeToFile("Player One:DetMCTS_ClassicE_SmartR || Player Two: DetMCTS_SmartE_SmartR");

        IntStream.range(0,200).parallel().forEach((i) -> {
            DetMCTS_ClassicE_SmartR one = new DetMCTS_ClassicE_SmartR(1,null);
            DetMCTS_SmartE_SmartR two = new DetMCTS_SmartE_SmartR(2,one);
            one.setEnemy(two);
            new GameSession(one, two).run();
        });

//        for(int i = 0; i<200 ;i++)
//        {
//            DetMCTS_ClassicE_SmartR one = new DetMCTS_ClassicE_SmartR(1,null);
//            DetMCTS_SmartE_SmartR two = new DetMCTS_SmartE_SmartR(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }
//
//        Output.getInstance().writeToFile("Player One:DetMCTS_SmartE_SmartR || Player Two: DetMCTS_SmartE_AllR");
//        for(int i = 0; i<20 ;i++)
//        {
//            DetMCTS_SmartE_SmartR one = new DetMCTS_SmartE_SmartR(1,null);
//            DetMCTS_SmartE_AllR two = new DetMCTS_SmartE_AllR(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }


/**
 * Standard MCTS testing
 */

//===========Var1 vs all
//        Output.getInstance().writeToFile("Classic_Expansion_AllRoot || Player Two: Classic_Expansion_SmartRoot");
//        for(int i = 0; i<20 ;i++)
//        {
//            Classic_Expansion_AllRoot one = new Classic_Expansion_AllRoot(1,null);
//            Classic_Expansion_SmartRoot two = new Classic_Expansion_SmartRoot(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }
//
//        Output.getInstance().writeToFile("Classic_Expansion_AllRoot || Player Two: Rnd_SmartClassic_SmartRoots");
//        for(int i = 0; i<20 ;i++)
//        {
//            Classic_Expansion_AllRoot one = new Classic_Expansion_AllRoot(1,null);
//            Rnd_SmartClassic_SmartRoot two = new Rnd_SmartClassic_SmartRoot(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }
//
//        Output.getInstance().writeToFile("Classic_Expansion_AllRoot || Player Two: Rnd_SmartRoot");
//        for(int i = 0; i<20 ;i++)
//        {
//            Classic_Expansion_AllRoot one = new Classic_Expansion_AllRoot(1,null);
//            Rnd_SmartRoot two = new Rnd_SmartRoot(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }
//
//        Output.getInstance().writeToFile("Classic_Expansion_AllRoot || Player Two: SmartExpansion_SmartRoot");
//        for(int i = 0; i<20 ;i++)
//        {
//            Classic_Expansion_AllRoot one = new Classic_Expansion_AllRoot(1,null);
//            SmartExpansion_SmartRoot two = new SmartExpansion_SmartRoot(2,one);
//             one.setEnemy(two);
//            new GameSession(one, two).run();
//        }
//=========Var 1 finished

//=========Var 2 vs all
//       Output.getInstance().writeToFile("Classic_Expansion_SmartRoot || Player Two: Rnd_SmartClassic_SmartRoot");
//        for(int i = 0; i<20 ;i++)
//        {
//            Classic_Expansion_SmartRoot one = new Classic_Expansion_SmartRoot(1,null);
//            Rnd_SmartClassic_SmartRoot two = new Rnd_SmartClassic_SmartRoot(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }
//
//        Output.getInstance().writeToFile("Classic_Expansion_SmartRoot || Player Two: Rnd_SmartRoot");
//        for(int i = 0; i<20 ;i++)
//        {
//            Classic_Expansion_SmartRoot one = new Classic_Expansion_SmartRoot(1,null);
//            Rnd_SmartRoot two = new Rnd_SmartRoot(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }
//
//        Output.getInstance().writeToFile("Classic_Expansion_SmartRoot || Player Two: SmartExpansion_SmartRoot");
//        for(int i = 0; i<20 ;i++)
//        {
//            Classic_Expansion_SmartRoot one = new Classic_Expansion_SmartRoot(1,null);
//            SmartExpansion_SmartRoot two = new SmartExpansion_SmartRoot(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }
//=========Var 2 finished

//=========Var 3 vs all
//        Output.getInstance().writeToFile("Rnd_SmartClassic_SmartRoot || Player Two: Rnd_SmartRoot");
//        for(int i = 0; i<20 ;i++)
//        {
//            Rnd_SmartClassic_SmartRoot one = new Rnd_SmartClassic_SmartRoot(1,null);
//            Rnd_SmartRoot two = new Rnd_SmartRoot(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }
//
//        Output.getInstance().writeToFile("Rnd_SmartClassic_SmartRoot || Player Two: SmartExpansion_SmartRoot");
//        for(int i = 0; i<20 ;i++)
//        {
//            Rnd_SmartClassic_SmartRoot one = new Rnd_SmartClassic_SmartRoot(1,null);
//            SmartExpansion_SmartRoot two = new SmartExpansion_SmartRoot(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }
////=========Var 3 finished
//        //4 vs 5
//        Output.getInstance().writeToFile("Rnd_SmartRoot || Player Two: SmartExpansion_SmartRoot");
//        for(int i = 0; i<20 ;i++)
//        {
//            Rnd_SmartRoot one = new Rnd_SmartRoot(1,null);
//            SmartExpansion_SmartRoot two = new SmartExpansion_SmartRoot(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        }

/**
 *  UCT-Testing
 */

    }
}
