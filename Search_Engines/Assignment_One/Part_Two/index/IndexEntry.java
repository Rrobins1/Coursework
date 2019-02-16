package index;

import java.util.*;

//Not being used, thought it may be useful, but it was not. Keeping in case I find a use later.
public class IndexEntry implements Comparable<IndexEntry>{
    public String name;
    public LinkedList<Integer> documents;

    public IndexEntry(String name, int document){
        this.name = name;
        this.documents.add(document);
    }

    public IndexEntry(String name){
        this.name = name;
        this.documents = new LinkedList<>();
    }

    public boolean addDocument(int documentID){
        this.documents.add(documentID);
        return true;
    }

    @Override
    public int compareTo(IndexEntry index){
        return this.name.compareTo(index.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString(){
        return this.name + "=" + this.documents;
    }
}
