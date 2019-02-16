//******************************************************
// Breaks lines into tokens, converts to lower case.
//******************************************************
package fileParser;

import java.util.*;

public class Tokenizer {
    public static final String[] stopWords = { "the", "is", "at", "of", "on", "and", "a"};

    //Breaks a line into a list of strings
    public static LinkedList<String> unassembleLine(String line){
        if (line == null) return null;
        LinkedList<String> stringComponents = new LinkedList<>();
        Scanner lineReader = new Scanner(line);

        while(lineReader.hasNext()){
            stringComponents.add( lineReader.next() );
        }
        lineReader.close();
        return stringComponents;
    }

    //Processes a list of strings to break into one word tokens, or tags
    public static LinkedList<String> tokenize(LinkedList<String> stringList){
        Character currentCharacter;
        LinkedList<String> results = new LinkedList<>();

        String currentWord = null;

        boolean isProcessingTag = false; //reference for if end tag needs to be found still


        for( String segment : stringList){
            //check if it is the start of a tag
            if(segment.charAt(0) == '<'){
                isProcessingTag = true;
            }

            //reset current word if currently not parsing a tag
            if(isProcessingTag == false) {
                currentWord = processWord(segment);
                currentWord = currentWord.toLowerCase();
                if(!currentWord.isEmpty()) results.add(currentWord);
            }

            else { //process tag
                if(currentWord == null ){
                    currentWord = segment;
                }
                else { currentWord = currentWord.concat(" " + segment); }


                if(segment.contains(">")){
                    //currentWord = currentWord.concat(" " + segment);
                    isProcessingTag = false;
                    results.add(currentWord);
                    currentWord = null;
                }
            }
        }
        return results;
    }


    //Prune all non alphanumeric components
    private static String processWord(String segment){
        Character currentCharacter;
        String currentWord = "";

        for(String stopWord : stopWords){
            if(segment.equalsIgnoreCase(stopWord)) return currentWord;
        }
        
        for(int index = 0; index < segment.length(); index++){
            currentCharacter = segment.charAt(index);

            if (Character.isLetterOrDigit(currentCharacter)){
                currentWord = currentWord.concat(Character.toString(currentCharacter));
            }
        }
       return currentWord;
    }
    //break lines into words, then tokenize them
    public static LinkedList<String> tokenizeLine( String line) {
        return tokenize( unassembleLine(line));
    }
}
