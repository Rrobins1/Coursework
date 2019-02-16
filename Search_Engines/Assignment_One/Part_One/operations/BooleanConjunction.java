package operations;

import index.Index;
import index.IndexEntry;

import java.util.LinkedList;

public class BooleanConjunction {

    //Two String
    static public IndexEntry conjunctiveQuery(String tokenOne, String tokenTwo, Index index){
        IndexEntry entryOne = new IndexEntry(tokenOne, index);
        IndexEntry entryTwo = new IndexEntry(tokenTwo, index);

        return conjunctiveQuery(entryOne, entryTwo);
    }

    //String, entry
    static public IndexEntry conjunctiveQuery(String tokenOne, IndexEntry entryTwo, Index index){
        IndexEntry entryOne = new IndexEntry(tokenOne, index);

        return conjunctiveQuery(entryOne, entryTwo);
    }

    //Entry, string
    static public IndexEntry conjunctiveQuery(IndexEntry entryOne, String tokenTwo, Index index){
        IndexEntry entryTwo = new IndexEntry(tokenTwo, index);

        return conjunctiveQuery(entryOne, entryTwo);
    }

    //query for two entries
    static public IndexEntry conjunctiveQuery(IndexEntry entryOne, IndexEntry entryTwo){
        int smallestLength = Math.min(entryOne.documents.size(), entryTwo.documents.size());
        int indexOne = 0;
        int indexTwo = 0;

        String resultName = entryOne.name + "_" + entryTwo.name;

        IndexEntry resultEntry = new IndexEntry(resultName);
        LinkedList<Integer> itemOneDocuments = entryOne.documents;
        LinkedList<Integer> itemTwoDocuments = entryTwo.documents;

        if(entryOne.equals(entryTwo) ) { //comparison is done based on name in indexItem
            return entryOne;          //if they are the same entry they are identical
        }

        while(indexOne < smallestLength && indexTwo < smallestLength ){
            Integer itemOneCurrent = itemOneDocuments.get(indexOne);
            Integer itemTwoCurrent = itemTwoDocuments.get(indexTwo);

            if (itemOneCurrent == itemTwoCurrent) {
                indexOne++;
                indexTwo++;
                resultEntry.addDocument(itemOneCurrent);
            } else if(itemOneCurrent < itemTwoCurrent){
                indexOne ++;
            } else indexTwo ++;
        }return resultEntry;
    }
}
