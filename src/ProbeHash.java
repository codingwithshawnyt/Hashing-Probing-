//Shawn Ray
//Period 4
//ProbeHash.java
//The purpose of this file is to implement an algorithm 
//that implements hashing with linear probing.

import java.util.Collection; //imports to support data structure
import java.util.Vector;

public class ProbeHash<E> implements Hash<E> { //class declaration
    public Vector<E> hashTable; //main table with hash values
    public int[] typeTable; //table with statuses

    ProbeHash() { //first constructor
        this.hashTable = new Vector<E>(10); //initial length of 10
        this.typeTable = new int[10]; //setting 10 statuses
        for(int i = 0; i < length(); i++) {
            hashTable.add(null); //setting them all equal to null at first
        }
    }

    ProbeHash(Collection<E> list) { //second constructor for if we already have values
        this.hashTable = new Vector<E>(10); //same as prev constructor
        this.typeTable = new int[10]; //same as prev constructor
        for(int i = 0; i < length(); i++) {
            hashTable.add(null); //setting them equal to null at first
        }

        for(E val: list) {
            this.add(val); //adding them to the table for starters;
        } 
    }

    ProbeHash(int initialCapacity) { //third constructor for if we have just capacity
        this.hashTable = new Vector<E>(initialCapacity); //initializing hash table with specified capacity
        this.typeTable = new int[initialCapacity]; //same for the status table
        for(int i = 0; i < initialCapacity; i++) { //filling with nulls
            hashTable.add(null);
        }
    }

    public void rehash() {
        Vector<E> rehash = new Vector<E>(length() * 2); //doubling size when getting too full
        for(int i = 0; i < length(); i++) { //filling with nulls for new capacity
            hashTable.add(null);
        }
        for(int i = 0; i < length(); i++) { //where not null, we add to the rehash table
            if(hashTable.get(i) != null) {
                rehash.add(hashTable.get(i));
            }
        }
        hashTable = rehash; //updating the hash table
    }

    public int hashFunction(E value) {
        return Math.abs(value.hashCode()) % length(); //finding desired absolute value of hash value
    }

    public boolean add(E value) {
        if(this.contains(value)) {
            return false; //checking for duplicates
        }
        int index = hashFunction(value); //finding desired index
        int currIndex = index; //checking current index
        while(true) {
            if(hashTable.get(currIndex) == null){
                hashTable.set(currIndex, value); //if it's empty. we can just set it to our value
                typeTable[currIndex] = 1; //and update status table
                return true;
            }
            else if(hashTable.get(currIndex) != null || typeTable[currIndex] == -1) { //if the spot has something or otherwise isn't open
                if(currIndex == length() - 1) { //we go to the one before
                    currIndex = 0; //and rest curr index
                }
                else {
                    currIndex++; //otherwise we just go to the next slot
                }
            }
            else if((double)hashTable.size() / length() >= 0.75 || currIndex == index){ //checking if our load factor is too high
                rehash();
            }
            else {
                return false; //if all of this is false we aren't adding
            }
        }
    }

    public boolean contains(E value) {
        int index = hashFunction(value); //first get hashCode's desired index;
        int currIndex = index; //setting index to be checked to this
        while(true) {
            if(hashTable.get(currIndex) == value) {
                return true; // find value
            }
            else if(hashTable.get(currIndex) == null) {
                return false; // can't move on
            }
            if(currIndex == length() - 1) {
                currIndex = 0; // wraps
            }
            else if(index == currIndex) {
                return false; // full
            }
            else {
                currIndex++; // else traverse
            }
        }
    }

    public int length() {
        return hashTable.capacity();
    }

    public boolean isEmpty() {
        if(hashTable.size() == 0) { //checking to see if there is anything
            return true;
        }
        return false;
    }

    public boolean remove(E value) {
        int index = hashFunction(value);
        int currIndex = index; //same as earlier
        if(!this.contains(value)) { //if not in there, we can't remove it
            return false;
        }
        while(true) {
            if(hashTable.get(currIndex) == value){ //if we found it
                hashTable.set(currIndex, null); //null it
                typeTable[currIndex] = -1; //update status table
                return true;
            }
            else if(hashTable.get(currIndex) != null) { //if already null
                if(currIndex == length() - 1) { //if we are at end
                    currIndex = 0; //wrap
                }
                else {
                    currIndex++; //or go to next
                }
            }
            else {
                return false; //or we can't remove
            }
        }
    }

    public int size() {
        return hashTable.size(); //instance variable
    }

    public String toString() {
        String result = "[";
        for(int i = 0; i < length(); i++) {  //simply setting up format
            if(hashTable.get(i) != null) {
                result += hashTable.get(i);
                if(i < length() - 1) {
                    result += ", ";
                }
            }
        }
        result += "]";
        return result;
    }
}