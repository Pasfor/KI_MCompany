import AIComponents.GameState;
import AIs.McAIOutput;
import AIs.McAiDet;
import AIs.RndAI;
import DeterminedTesting.DetMCTS_ClassicE_SmartR;
import DeterminedTesting.DetMCTS_SmartE_AllR;
import DeterminedTesting.DetMCTS_SmartE_SmartR;
import GameSession.*;
import output.Output;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Output.getInstance().writeToFile("Player One:DetMCTS_ClassicE_SmartR || Player Two: DetMCTS_SmartE_AllR");
        for(int i = 0; i<20 ;i++)
        {
            DetMCTS_ClassicE_SmartR one = new DetMCTS_ClassicE_SmartR(1,null);
            DetMCTS_SmartE_AllR two = new DetMCTS_SmartE_AllR(2,one);
            one.setEnemy(two);
            new GameSession(one, two).run();
        }


        Output.getInstance().writeToFile("Player One:DetMCTS_ClassicE_SmartR || Player Two: DetMCTS_SmartE_SmartR");
        for(int i = 0; i<20 ;i++)
        {
            DetMCTS_ClassicE_SmartR one = new DetMCTS_ClassicE_SmartR(1,null);
            DetMCTS_SmartE_SmartR two = new DetMCTS_SmartE_SmartR(2,one);
            one.setEnemy(two);
            new GameSession(one, two).run();
        }

        Output.getInstance().writeToFile("Player One:DetMCTS_SmartE_SmartR || Player Two: DetMCTS_SmartE_AllR");
        for(int i = 0; i<20 ;i++)
        {
            DetMCTS_SmartE_SmartR one = new DetMCTS_SmartE_SmartR(2,null);
            DetMCTS_SmartE_AllR two = new DetMCTS_SmartE_AllR(2,one);
            one.setEnemy(two);
            new GameSession(one, two).run();
        }
    }
}
