package output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Output {
    private static Output ourInstance = new Output();

    public static Output getInstance() {
        return ourInstance;
    }

    private Output() {
        super();
    }
    public synchronized void writeToFile(String str) {
        try (PrintWriter p = new PrintWriter(new FileOutputStream("Last_best_vs_best_vs_HC.txt", true))) {
            p.println(str);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }


}
