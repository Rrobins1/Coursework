package fileParser;

import index.Index;

import java.io.*;
import java.util.*;

public class IndexReader extends FileParser{
    Index index;

    public IndexReader(String filePath) throws IOException {
        super(filePath);
        this.index = new Index();
    }


    //reads index file
    public Index readFile() throws IOException{
        String currentLine;
        while(true){
                currentLine = this.readLine();
                if(currentLine == null) break;
                lineToIndex(currentLine, index);
            }
        return index;
    }

    private static boolean lineToIndex(String line, Index index){
        if (line == null || line.length() == 0) return false;

        Scanner lineReader = new Scanner(line);
        String word = lineReader.next();
        lineReader.next();

        String documents = lineReader.nextLine();
        documents = documents.substring(2, documents.length() - 1);


        String[] documentsSeperated = documents.split(", ");
        lineReader.close();
        for(String documentID : documentsSeperated){
            index.addEntry(word, Integer.parseInt(documentID));
        }
        return true;
    }
}
