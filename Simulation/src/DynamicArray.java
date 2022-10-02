// TO DO: add your implementation and JavaDocs.

import java.util.Collection;
import java.util.Iterator;

/**This data structure is a DynamicArray of generic types.
 * It resizes automatically when the size reaches capacity to 2* the capacity. 
 * @author Matthew Emo
 * @param <T> this is a generic type that can be stored in the DynamicArray
 */
public class DynamicArray<T> implements Iterable<T> {
	/**
	 * These are the variables for the DynamicArray.
	 * @param INITCAP the default size
	 */
	private static final int INITCAP = 2; //default initial capacity
	/**
	 * These are the variables for the DynamicArray.
	 * @param storage where the generic data is stored
	 */
	private T[] storage; //underlying array, you MUST use this for credit (do not change the name or type)
	/**
	 * These are the variables for the DynamicArray.
	 * @param currentSize how many items are stored in the array
	 */
	private int currentSize = 0; // this is how many item are in the DynamicArry
	
	/**
	 * Constructor.
	 * Initial capacity of the storage should be INITCAP = 2
	 */
	@SuppressWarnings("unchecked")
	public DynamicArray(){
		
		this.storage =  (T[]) new Object[INITCAP];
		
	}
	
	/**
	 * This is the constructor for a designated index. 
	 * @param initCapacity is and capacity greater then 0
	 */
	@SuppressWarnings("unchecked")
	public DynamicArray(int initCapacity){
		// constructor
		// The initial capacity of the storage should be initCapacity
		// Throw IllegalArgumentException if initCapacity < 1
		// Use this _exact_ error message for the exception
		// (quotes are not part of the message):
		// "Capacity cannot be zero or negative."
		if (initCapacity<1) {
			throw new IllegalArgumentException("Capacity cannot be zero or negative.");
		} else {
			this.storage = (T[]) new Object[initCapacity];
		}
	}
	
	/**
	 * Keeps track of the current amount of values in the array.
	 * @return how many items in the array
	 */
	public int size(){	
		return currentSize;
	}  
	
	/**
	 * keeps track of the max amount of values that can be in the array.
	 * @return the capacity of the array
	 */
	public int capacity() {
		// Report the max number of elements before expansion.
		// O(1)
		return this.storage.length;
	}
	
	/**
	 * Sets the index of the DynamicArray to the inputed value.
	 * Replaces all the values of the old array to the new one with the value at the index replaced. 
	 * @param index has to be larger then -1 and less then the capacity()
	 * @param value any generic value the you want to enter in the array
	 * @return the item at the index that is being replaced
	 */
	public T set(int index, T value){
		// Change the item at the given index to be the given value.
		// Return the old item at that index.
		// Note: You cannot add new items with this method.
		// O(1)
		T oldValue;
		if(index<capacity()&& index>=0) {
			oldValue = storage[index];
			storage[index] = value;
		} else {
			// For an invalid index, throw an IndexOutOfBoundsException
			// Use this code to produce the correct error message for
			// the exception (do not use a different message):
			// "Index " + index + " out of bounds!"
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		return oldValue;
	}
	
	/**
	 * returns the value at the inputed index.
	 * has to be a valid index or error.
	 * @param index 0 to < capacity or array
	 * @return the item at the index 
	 */
	public T get(int index){
		
		// O(1)
		if (index<capacity()) {
			// Return the item at the given index
			return storage[index];
		}else {
			// Use the exception (and error message) described in set()
			// for invalid indicies.
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
			
		}
		
		
	}
	
	/**
	 * Add the value to the end of the DynamicArray.
	 * If the capacity is not enough then it will double the current array and add all the element to the new double size array. 
	 * @param value generic type that is the same is the current array
	 * @return value returns true when the item is added to the end of the array
	 */
	@SuppressWarnings("unchecked")
	public boolean add(T value){
		// Append an element to the end of the list and return true.
		// Double the capacity if no space available.
		// Amortized O(1)
		if(size()==capacity()) {
			T[] oldStorage = storage;
			this.storage = (T[]) new Object[capacity()*2];
			for(int i=0; i<oldStorage.length; i++) {
				storage[i] = oldStorage[i];
			}
		}
		
		storage[currentSize++] = value;
		
		return true;
	}
	
	/**
	 * Adds a item to the list at the index that is inputed.
	 * If the capacity it double the size of the array. 
	 * If the index is incorrect you get an error. 
	 * @param index the spot of the array that should have the new value
	 * @param value is and generic type
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T value){
		// Insert the given value at the given index. Shift elements if needed,  
		// double capacity if no space available, throw an exception if you cannot
		// insert at the given index. You _can_ append items with this method.
		
		// For the exception, use the same exception and message as set() and
		// get()... however remember that the condition of the exception is
		// different (different indexes are invalid).
		
		// O(N) where N is the number of elements currently in the list
		T[] oldStorage = storage;
		
		if(index>capacity()|| index<0) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		// check if the capacity need to be larger or is sufficient 
		} else if(size()==capacity()) {
			//System.out.println("Made it to resize");
			
			this.storage = (T[]) new Object[capacity()*2];
			
			for(int i=0; i<index; i++){
				storage[i] = oldStorage[i];
			}
			storage[index] = value;
			
			//System.out.println("oldstorage capcity " + oldStorage.length);
			for(int i=index+1; i<=oldStorage.length; i++){
					
				//System.out.println("OLD: " + (i-1) + " new: " + (i) );
				storage[i] = oldStorage[i-1];
				
				
			}
		} else{
			//System.out.println("Did not make it to resize");
			this.storage = (T[]) new Object[capacity()];
			for(int i=0; i<index; i++){
				storage[i] = oldStorage[i];
			}
			storage[index] = value;
			
			//System.out.println("oldstorage capcity " + oldStorage.length);
			for(int i=index+1; i<oldStorage.length; i++){
					
				//System.out.println("OLD: " + (i-1) + " new: " + (i) );
				storage[i] = oldStorage[i-1];
				
				
			}
		}
		// loops till the index is meet
		// copys over the values of the oldArray till the array is at the index
		// at the inputed index it copies over the new value to be stored at the index   
		// set the storage to one pointed in storage to one past the index while keeping the oldStorage at the same index as the newly added value
		
		// after everything has been added increase the size by one 
		++currentSize;
	}
	
	/**
	 * removes one element at the index inputed and returns the item that was removed.
	 * if below 1/3 of the capacity. Half the capacity of the storage.
	 * @param index removing what is at the current index
	 * @return the item that was removed from the list
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index){
		// Remove and return the element the given index. Shift elements
		// to remove the gap. Throw an exception when there is an invalid
		// index (see set(), get(), etc. above).
		
		// O(N) where N is the number of elements currently in the list
		T valueGetRemoved = null;
		if(index>capacity() || index<0) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}else {
			T[] oldStorage = storage;
			valueGetRemoved = storage[index];
			this.storage = (T[]) new Object[capacity()];
			// adds till get to the index that is supposed to be removed 
			for(int i=0; i<index; i++){
				storage[i] = oldStorage[i];
			}
			// sits the index to one past the index being removed by increasing the oldstorage index by one therefore skipping the index of the item that should be removed
			// -1 from i to make sure that it is still on the right index in storage
			for(int i=index+1; i<oldStorage.length; i++) {
				storage[i-1] = oldStorage[i];
			}
			
		}
		//decrease the the size by one since one was removed
		--currentSize;
		
		// Half the capacity of the storage if the number of elements falls
		// below 1/3 of the capacity.
		if (currentSize <= capacity()/3) {
			T[] oldStorage = storage;
			this.storage = (T[]) new Object[capacity()/2];
			for(int i = 0; i<storage.length; i++) {
				storage[i] = oldStorage[i];
			}
		}
		return valueGetRemoved;
	}
	/**
	 * This a a anonymous class that gives the DynamicArray the ability to iterate.
	 * @return the iteration of the DynamicArray
	 */
	public Iterator<T> iterator(){
		// Uses an anonymous class style, complete the iterator code
		// below. Note that this uses the "diamond syntax" which is
		// only available for nested classes from Java 9 forward.
		// If you get an error on the next line you can add a <T>
		// between the <> or you can (and should) update your 
		// version of the JDK.
		
		return new Iterator<T>(){
			//instance variables here
			//only _required_ methods are outlined below
			//the interface also has some optional methods
			//you may implement if you find them helpful
			private int current =0;
			public T next() {
			    return get(current++);
			}
			
			public boolean hasNext() {
				if(current < size()) {
					return true;
				} else {
					return false;
				}
				
			}
		};
	}
	
	
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//******************************************************
	/**
	 * This the given toString method.
	 * @return string 
	 */
	public String toString(){
		//This method is provided for debugging purposes
		//(use/modify as much as you'd like), it just prints
		//out the list i for easy viewing.
		StringBuilder s = new StringBuilder("Dynamic array with " + size()
			+ " items and a capacity of " + capacity() + ":");
		//for (int i = 0; i < size(); i++) {
		//	s.append("\n  ["+i+"]: " + get(i));
		//}
		return s.toString();
		
	}
	/**
	 * Main.
	 * @param args main
	 */
	//JavaDoc note: How do you document a main? See Simulation.java for an example
	public static void main(String args[]){
		DynamicArray<DynamicArray<Integer>> grid = new DynamicArray<DynamicArray<Integer>>(10);;
		DynamicArray<Integer> idt = new DynamicArray<>();
		
		for(int i =0; i<10; i++) {
			idt.add(1);
		}
		DynamicArray<Integer> y = new DynamicArray<>();
		for(int i =0; i<10; i++) {
			y.add(8);
		}
		System.out.println(idt.toString());
		grid.add(idt);
		grid.add(idt);
		grid.add(idt);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		grid.add(0, y);
		System.out.println(grid.toString());
		for(int i= 0; i<grid.size(); i++) {
			System.out.print("row: "+ "[" + i + "]");
			for(int k=0; k<grid.get(i).size(); k++) {
				
				System.out.print(grid.get(i).get(k) + " ");
			}
			System.out.println();
				
		}
		
		/*
		//System.out.println(grid.size());
		//System.out.println(grid.capacity());
		//System.out.println(grid.toString());
		//grid.add(0, y);
		//grid.add(0, y);
		
		//grid.add(0, y);
		//grid.add(0, y);
		//grid.add(0, y);
		
		System.out.println(grid.toString());
		for(int i= 0; i<grid.size(); i++) {
			System.out.print("row: "+ "[" + i + "]");
			for(int k=0; k<grid.get(i).size()-1; k++) {
				System.out.print(grid.get(i).get(k) + " ");
				
			}
			System.out.println();
				
		}
		
		//System.out.print(grid.size());
		//System.out.print(grid.get(12).get(5));
		
		//System.out.println(grid.size());
		//System.out.println(grid.capacity());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//System.out.println("The current size of the array is: " + idt.size() + " The capacity of the array is: " + idt.capacity());
		//idt.add(11);
		//System.out.println("After adding a element to the array");
		//System.out.println("The current size of the array is: " + idt.size() + " The capacity of the array is: " + idt.capacity());
		//System.out.println("After adding a element to the array");
		//idt.add(12);
		//System.out.println("The current size of the array is: " + idt.size() + " The capacity of the array is: " + idt.capacity());
		//System.out.println("After adding a element to the array");
		//idt.add(13);
		//System.out.println("The current size of the array is: " + idt.size() + " The capacity of the array is: " + idt.capacity());
		//System.out.println("_______________________________");
		
		//System.out.println("The current size of the array is: " + idt.size() + " The capacity of the array is: " + idt.capacity());
		//System.out.println(idt.toString());
		//add(0,-10);
		///System.out.println(idt.toString());
		//System.out.println(this.capacity());
		
		
		
		//idt.remove(2);
		//System.out.println(idt.toString());
		//idt.add(3,100);
		//System.out.println(idt.toString());
		//idt.add(4,20);
		//System.out.println(idt.toString());
		//idt.remove(2);
		//idt.remove(1);
		//idt.remove(1);
		//idt.add(5,69);
		//System.out.println(idt.toString());
		//idt.add(6,70);
		//System.out.println(idt.toString());
		//idt.add(1,99);
		//System.out.println(idt.toString());
		
		//System.out.println("The current size of the array is: " + idt.size() + " The capacity of the array is: " + idt.capacity());
		//idt.add(6,-10);
		
		//System.out.println("The current size of the array is: " + idt.size() + " The capacity of the array is: " + idt.capacity());
		//These are _sample_ tests. If you're seeing all the "yays" that's
		//an excellend first step! But it might not mean your code is 100%
		//working... You may edit this as much as you want, so you can add
		//own tests here, modify these tests, or whatever you need!
		
		/**
		DynamicArray<Integer> ida = new DynamicArray<>();
		if ((ida.size() == 0) && (ida.capacity() == 2)){
			System.out.println("Yay 1");
		}
		
		boolean ok = true;
		for (int i=0; i<3;i++)
			ok = ok && ida.add(i*5);
		
		if (ok && ida.size()==3 && ida.get(2) == 10 && ida.capacity() == 4 ){
			System.out.println("Yay 2");
		}
		
		ida.add(1,-10);
		ida.add(4,100);
		if (ida.set(1,-20)==-10 && ida.get(2) == 5 && ida.size() == 5 
			&& ida.capacity() == 8 ){
			System.out.println("Yay 3");
		}
		//System.out.println(ida.toString());
		//System.out.println(ida.remove(0));
		//System.out.println(ida.toString());
		//System.out.println(ida.remove(0));
		//System.out.println(ida.toString());
		//System.out.println(ida.remove(2));
		//System.out.println(ida.toString());
		if (ida.remove(0) == 0 && ida.remove(0) == -20 && ida.remove(2) == 100 
			&& ida.size() == 2 && ida.capacity() == 4 ){
			System.out.println("Yay 4");
		}
		/**
		System.out.println(ida.toString());
		Iterator<Integer> iterator = ida.iterator();
		System.out.println(iterator.hasNext());
		System.out.println(iterator.next());
		
		//Uncomment this after doing the iterator for testing
		
		System.out.print("Printing values: ");
		for(Integer i : ida) {
			System.out.print(i);
			System.out.print(" ");
		}
		*/
		
	}
}