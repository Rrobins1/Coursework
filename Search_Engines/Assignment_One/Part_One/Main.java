import fileParser.IndexReader;
import index.Index;
import index.IndexEntry;
import operations.BooleanConjunction;

import java.io.IOException;

public class Main {
    private static final String DEFAULT_FILEPATH = "files/index.txt";

    public static void main(String[] args) throws IOException {
        String filePath = DEFAULT_FILEPATH;
        if(args.length == 1){
            filePath = args[0];
        }
        
        IndexReader indexReader = new IndexReader(DEFAULT_FILEPATH);
        Index index = indexReader.readFile();

        IndexEntry resultOne = BooleanConjunction.conjunctiveQuery( "asus", "google", index );
        IndexEntry resultTwo = BooleanConjunction.conjunctiveQuery("screen", "bad", index);
        IndexEntry resultThree = BooleanConjunction.conjunctiveQuery("great", "tablet", index);

        System.out.println("Result One: asus AND google -> " + resultOne.toString());
        System.out.println("Result One: screen AND bad -> " + resultTwo.toString());
        System.out.println("Result One: great AND tablet -> " + resultThree.toString());
    }
}
