//****************************************************************
// IndexDriver is used to take in a text file and output an index
//****************************************************************

package index;

import fileParser.*;

import java.io.IOException;
import java.util.Scanner;

//Makes a reverse index of a file
public class IndexDriver {
    private static final char TAG_START = '<';
    private static final String DOC_TAG_END = "</DOC>";


    private static Scanner scanner;
    private static int currentDocument = -1;


    //Indexes a file
    public static Index processFile(String filePath) throws IOException {
        FileParser fileParser = new FileParser(filePath);
        Index index = new Index();
        boolean isInTag = false;

        String currentLine = fileParser.readLine();
        while(currentLine != null){
            for(String token : Tokenizer.tokenizeLine(currentLine) ){

                if(token.charAt(0) == '<'){
                    changeDocumentStatus(token);
                }
                else {
                    index.addEntry(token, currentDocument);
                }
            }
            currentLine = fileParser.readLine();
        }

        //reset defaults
        currentDocument = -1;
        scanner = null;
        return index;
    }

    //Changes current document id
    private static void changeDocumentStatus(String token){
        String currentSection;
        scanner = new Scanner(token);

        while(scanner.hasNext()){
            currentSection = scanner.next();
            System.out.println(currentSection);
            if(currentSection.contains("/DOC")){
                currentDocument = -1;
            }
            else if(currentSection.contains(">")) {
                currentSection = currentSection.replace(">", "");
                System.out.println(currentSection );
                currentDocument = (Integer.parseInt(currentSection));
            }
        }
    }
}
