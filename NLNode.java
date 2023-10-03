/**
 * Assignment 4 CS1027
 * Due: 4.6.2023
 * Description: This class represents the nodes of a non-linear data structure
 * @author Orayda Shagifa
 */

import java.util.Comparator;
import java.util.Iterator;

public class NLNode <T> {
	/** reference to the parent of this node */
	private NLNode<T> parent;
	
	/** a reference to a list storing the children of this node */
	private ListNodes<NLNode<T>> children;
	
	/** a reference to the data object stored in this node */
	private T data;
	
	/** constructor creates empty node */
	public NLNode() {
		parent = null;
		data = null;
		
		// initialize children list
		children = new ListNodes<NLNode<T>>();
	}
	
	/**
	 * constructor creates node and sets data to d and parent to p
	 * @param d data object
	 * @param p parent
	 */
	public NLNode (T d, NLNode<T> p) {
		parent = p;
		data = d;
		
		// initialize children list
		children = new ListNodes<NLNode<T>>();
	}
	
	/**
	 * setter sets parent of this node to p
	 * @param p parent
	 */
	public void setParent(NLNode<T> p) {
		parent = p;
	}
	
	/**
	 * getter returns parent of this node
	 * @return parent
	 */
	public NLNode<T> getParent() {
		return parent;
	}
	
	/**
	 * Adds newChild to this node
	 * @param newChild child node to be added to list of children
	 */
	public void addChild(NLNode<T> newChild) {
		children.add(newChild);
		
		//set parent of child to this node
		newChild.setParent(this);
	}
	
	/**
	 * Returns an iterator containing the children of this node
	 * @return iterator containing children
	 */
	public Iterator<NLNode<T>> getChildren() {
		return children.getList();
	}
	
	/**
	 * returns an iterator containing the children of this node sorted in the order specified by the parameter sorter
	 * @param sorter 
	 * @return sorted iterator containing children of this node
	 */
	public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter) {
		return children.sortedList(sorter);
	}
	
	/**
	 * getter returns data object stored in this node
	 * @return data
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * setter sets data in this node to data object referenced by d
	 * @param d data
	 */
	public void setData(T d) {
		data = d;
	}

}
