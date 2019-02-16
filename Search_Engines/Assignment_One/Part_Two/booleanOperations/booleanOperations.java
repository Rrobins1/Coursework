package booleanOperations;
//Supports the AND query

import index.IndexEntry;

import java.util.*;

public class booleanOperations
{

    IndexEntry conjunctiveQuery(IndexEntry indexItemOne, IndexEntry indexItemTwo){
        int smallestLength = Math.min(indexItemOne.documents.size(), indexItemTwo.documents.size());
        int indexOne = 0;
        int indexTwo = 0;

        String resultName = indexItemOne.name + "_" + indexItemTwo.name;

        IndexEntry resultEntry = new IndexEntry(resultName);
        LinkedList<Integer> itemOneDocuments = indexItemOne.documents;
        LinkedList<Integer> itemTwoDocuments = indexItemTwo.documents;

        if(indexItemOne.equals(indexItemTwo) ) { //comparison is done based on name in indexItem
            return indexItemOne;          //if they are the same entry they are identical
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
