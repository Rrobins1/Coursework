//Index logger only writes the inverted index to file.

package index;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class IndexLogger {

    public static boolean writeToFile(String filepath, Index index) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(filepath);
        out.println(index.toString());
        out.close();
        return true;
    }

    //if no arguments provided
    public static boolean writeToFile(Index index) throws FileNotFoundException {
        return writeToFile("files/index", index);
    }

    public static boolean writeToFile(Index index, String filePath) throws FileNotFoundException {
        return writeToFile(filePath, index);
    }
}
