import AIComponents.GameState;
import AIs.McAIOutput;
import AIs.McAiDet;
import AIs.RndAI;
import DeterminedTesting.DetMCTS_ClassicE_SmartR;
import DeterminedTesting.DetMCTS_SmartE_AllR;
import DeterminedTesting.DetMCTS_SmartE_SmartR;
import GameSession.*;
import StandardTesting.*;
import UCT_MoveChoice_Tests.*;
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
//        Output.getInstance().writeToFile("Player One:DetMCTS_ClassicE_SmartR || Player Two: DetMCTS_SmartE_SmartR");
//
//        IntStream.range(0,200).parallel().forEach((i) -> {
//            DetMCTS_ClassicE_SmartR one = new DetMCTS_ClassicE_SmartR(1,null);
//            DetMCTS_SmartE_SmartR two = new DetMCTS_SmartE_SmartR(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });

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
 *  UCT and Movechoice testing Determine
 */
//        Output.getInstance().writeToFile("Player One: Det_MCTS_MoveS_UCT1 || Player Two: Det_MCTS_MoveM_UCT1");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            Det_MCTS_MoveS_UCT1 one = new Det_MCTS_MoveS_UCT1(1,null);
//            Det_MCTS_MoveM_UCT1 two = new Det_MCTS_MoveM_UCT1(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//
//        Output.getInstance().writeToFile("Player One: Det_MCTS_MoveS_UCT1 || Player Two: Det_MCTS_MoveS_UCT2");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            Det_MCTS_MoveS_UCT1 one = new Det_MCTS_MoveS_UCT1(1,null);
//            Det_MCTS_MoveS_UCT2 two = new Det_MCTS_MoveS_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//
//        Output.getInstance().writeToFile("Player One: Det_MCTS_MoveS_UCT1 || Player Two: Det_MCTS_MoveM_UCT2");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            Det_MCTS_MoveS_UCT1 one = new Det_MCTS_MoveS_UCT1(1,null);
//            Det_MCTS_MoveM_UCT2 two = new Det_MCTS_MoveM_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//        //===== var 2
//        Output.getInstance().writeToFile("Player One: Det_MCTS_MoveM_UCT1 || Player Two: Det_MCTS_MoveS_UCT2");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            Det_MCTS_MoveM_UCT1 one = new Det_MCTS_MoveM_UCT1(1,null);
//            Det_MCTS_MoveS_UCT2 two = new Det_MCTS_MoveS_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//
//        Output.getInstance().writeToFile("Player One: Det_MCTS_MoveM_UCT1 || Player Two: Det_MCTS_MoveM_UCT2");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            Det_MCTS_MoveM_UCT1 one = new Det_MCTS_MoveM_UCT1(1,null);
//            Det_MCTS_MoveM_UCT2 two = new Det_MCTS_MoveM_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//        //===== var 3
//        Output.getInstance().writeToFile("Player One: Det_MCTS_MoveS_UCT2 || Player Two: Det_MCTS_MoveM_UCT2");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            Det_MCTS_MoveS_UCT2 one = new Det_MCTS_MoveS_UCT2(1,null);
//            Det_MCTS_MoveM_UCT2 two = new Det_MCTS_MoveM_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
/**
 * Not determinde Uct and move
 */
//        Output.getInstance().writeToFile("Player One: MCTS_MoveS_UCT1 || Player Two: MCTS_MoveM_UCT1");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            MCTS_MoveS_UCT1 one = new MCTS_MoveS_UCT1(1,null);
//            MCTS_MoveM_UCT1 two = new MCTS_MoveM_UCT1(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//
//        Output.getInstance().writeToFile("Player One: MCTS_MoveS_UCT1 || Player Two: MCTS_MoveS_UCT2");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            MCTS_MoveS_UCT1 one = new MCTS_MoveS_UCT1(1,null);
//            MCTS_MoveS_UCT2 two = new MCTS_MoveS_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//
//        Output.getInstance().writeToFile("Player One: MCTS_MoveS_UCT1 || Player Two: MCTS_MoveM_UCT2");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            MCTS_MoveS_UCT1 one = new MCTS_MoveS_UCT1(1,null);
//            MCTS_MoveM_UCT2 two = new MCTS_MoveM_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//        //==== var 2
//        Output.getInstance().writeToFile("Player One: MCTS_MoveM_UCT1 || Player Two: MCTS_MoveS_UCT2");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            MCTS_MoveM_UCT1 one = new MCTS_MoveM_UCT1(1,null);
//            MCTS_MoveS_UCT2 two = new MCTS_MoveS_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//
//        Output.getInstance().writeToFile("Player One: MCTS_MoveM_UCT1 || Player Two: MCTS_MoveM_UCT2");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            MCTS_MoveM_UCT1 one = new MCTS_MoveM_UCT1(1,null);
//            MCTS_MoveM_UCT2 two = new MCTS_MoveM_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//        //=====var 3
//        Output.getInstance().writeToFile("Player One:  MCTS_MoveS_UCT2 || Player Two: MCTS_MoveM_UCT2");
//        IntStream.range(0,20).parallel().forEach((i) -> {
//            MCTS_MoveS_UCT2 one = new  MCTS_MoveS_UCT2(1,null);
//            MCTS_MoveM_UCT2 two = new MCTS_MoveM_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });

        //=====more=tests=====
//        Output.getInstance().writeToFile("Player One: Det_MCTS_MoveM_UCT1 || Player Two: Det_MCTS_MoveM_UCT2");
//        IntStream.range(0,100).parallel().forEach((i) -> {
//            Det_MCTS_MoveM_UCT1 one = new Det_MCTS_MoveM_UCT1(1, null);
//            Det_MCTS_MoveM_UCT2 two = new Det_MCTS_MoveM_UCT2(2, one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//
//        Output.getInstance().writeToFile("Player One:  MCTS_MoveS_UCT2 || Player Two: MCTS_MoveM_UCT2");
//        IntStream.range(0,100).parallel().forEach((i) -> {
//            MCTS_MoveS_UCT2 one = new  MCTS_MoveS_UCT2(1,null);
//            MCTS_MoveM_UCT2 two = new MCTS_MoveM_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
/**
 * all vs all tests
  */
       //====vs HC based
//
//
//        Output.getInstance().writeToFile("Player One:  RndAI || Player Two: Det_MCTS_MoveM_UCT1");
//        IntStream.range(0,100).parallel().forEach((i) -> {
//            RndAI one = new RndAI(1);
//            Det_MCTS_MoveM_UCT1 two = new Det_MCTS_MoveM_UCT1(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//
//        Output.getInstance().writeToFile("Player One:  RndAI || Player Two: MCTS_MoveM_UCT2");
//        IntStream.range(0,100).parallel().forEach((i) -> {
//            RndAI one = new RndAI(1);
//            MCTS_MoveM_UCT2 two = new MCTS_MoveM_UCT2(2,one);
//            one.setEnemy(two);
//            new GameSession(one, two).run();
//        });
//         best detMCTS vs best notDetMCTs
        Output.getInstance().writeToFile("Player One:  Det_MCTS_MoveM_UCT1|| Player Two: MCTS_MoveM_UCT2");
        IntStream.range(0,20).parallel().forEach((i) -> {
            Det_MCTS_MoveM_UCT1 one = new Det_MCTS_MoveM_UCT1(1,null);
            MCTS_MoveM_UCT2 two = new MCTS_MoveM_UCT2(2,one);
            one.setEnemy(two);
            new GameSession(one, two).run();
            System.out.println(one.timeMS[0]/one.timeMS[1]);
            System.out.println(two.timeMS[0]/one.timeMS[1]);
        });

    }
}
