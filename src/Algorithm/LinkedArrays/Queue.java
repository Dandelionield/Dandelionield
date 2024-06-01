package Algorithm.LinkedArrays;

/*
 *
 * @author Dandelion
 * 
 */

import Algorithm.LinkedArrays.DoubleList;
import Algorithm.Nodes.DoubleNode;

public class Queue<T extends Comparable<T>> extends DoubleList<T>{

	public Queue(){
		
		super();
		
	}
	
	public Queue(DoubleNode<T> Nodo){
		
		super(Nodo);
		
	}
	
	public DoubleNode<T> getPeek(){
		
		DoubleNode<T> n = super.getFirstNode();
		
		return n;
		
	}
	
	public DoubleNode<T> getTop(){
		
		DoubleNode<T> n = super.getLastNode();
		
		return n;
		
	}
	
	public void add(T Data){
		
		super.addAtLast(Data);
		
	}
	
	public DoubleNode<T> pop(){
		
		DoubleNode<T> n = super.getFirstNode();
		
		if (n==null){
			
			return null;
			
		}
		
		super.remove();
		
		n.setNextReference(null);
		
		return n;
		
	}
	
	public String toString(){
		
		if (super.getLastNode()!=null){
			
			DoubleNode<T> n = super.getLastNode();
			
			String bup = "";
			
			while(n.getPreviousReference()!=null){
				
				bup = " ["+n.getData()+"]"+bup;
				
				n = n.getPreviousReference();
				
			}
			
			return "["+n.getData()+"]"+bup;
			
		}else if (super.getLastNode()!=null){
			
			DoubleNode<T> n = super.getLastNode();
			
			String bup = "";
			
			while(n.getNextReference()!=null){
				
				bup+= "["+n.getData()+"] ";
				
				n = n.getNextReference();
				
			}
			
			return bup+"["+n.getData()+"]";
			
		}else{
			
			return "[]";
			
		}
		
	}

}