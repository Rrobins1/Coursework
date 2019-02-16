//******************************************************
// Index contains a mapping of the words to document ID
//******************************************************

package index;

import java.util.LinkedList;
import java.util.*;


public class Index {
    public HashMap<String, LinkedList<Integer>> index; //Tree map so it is sorted


    public Index(){
        this.index = new HashMap<>();
    }

    //add entry to the index
    public boolean addEntry(String token, int docID){
        if(index.containsKey(token)) {
            LinkedList<Integer> foundList = index.get(token);
            foundList.add(docID);
        }
        else {
            LinkedList<Integer> newList = new LinkedList<>();
            newList.add(docID);
            index.put(token, newList);
        }
        return true;
    }

    //retrieve documents for specific word
    public LinkedList<Integer> getDocuments(String token){
        return index.get(token);
    }


    @Override public String toString(){
        String output ="";
        for(Map.Entry<String,LinkedList<Integer>> entry : index.entrySet()) {
            String token = entry.getKey();
            LinkedList<Integer> value = entry.getValue();

            output +=  token + " => " + value + "\n";
        }
        return output;
    }
}
