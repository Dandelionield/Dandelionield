package Algorithm.LinkedArrays;

/*
 *
 * @author Dandelion
 * 
 */

import Algorithm.LinkedArrays.List;
import Algorithm.Nodes.Node;

public class Stack<T> extends List<T>{
	
	public Stack(){
		
		super();
		
	}
	
	public Stack(T firstData){
		
		super(firstData);
		
	}
	
	public Stack(Node<T> firstNode){
		
		super(firstNode);
		
	}
	
	public Node<T> getTop(){
		
		return super.getNode();
		
	}
	
	public Node<T> getPeek(){
		
		Node<T> n = super.getNode();
		
		if (n==null){
			
			return null;
			
		}
		
		while(n.getReference()!=null){
			
			n = n.getReference();
			
		}
		
		return n;
		
	}
	
	public Node<T> pop(){
		
		Node<T> n = super.getNode();
		
		if (n==null){
			
			return null;
			
		}
		
		super.remove();
		
		n.setReference(null);
		
		return n;
		
	}
	
	public void push(Node<T> Nodo){
		
		super.add(Nodo);
		
	}
	
	public void push(T Data){
		
		super.add(Data);
		
	}
	
	public String toString(){
		
		if (super.getNode()!=null){
		
			Node<T> n = super.getNode();
			
			String bup = "";
			
			while(n.getReference()!=null){
				
				bup+= "["+n.getData()+"]\n";
				
				n = n.getReference();
				
			}
			
			return bup+"["+n.getData()+"]";
			
		}
		
		return "[]";
		
	}
	
}