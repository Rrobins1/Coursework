//******************************************************
// Simple operations for file parsing
//******************************************************
package fileParser;

import java.io.*;

public class FileParser {
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    private File file;

    //Constructor takes a file name to initiate class
    public FileParser(String fileName) throws FileNotFoundException {
        file = new File(fileName);
        fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
    }

    //Reads a line
    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }
}