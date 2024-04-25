package Algorithm.Methods;

/*
 *
 * @author Dandelion
 * 
 */

import Algorithm.Methods.List;
import Algorithm.Methods.Node;

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
		
		while(n.getReference()!=null){
			
			n = n.getReference();
			
		}
		
		return n;
		
	}
	
	public Node<T> pop(){
		
		Node<T> n = super.getNode();
		
		super.remove();
		
		return n;
		
	}
	
	public void push(Node<T> Nodo){
		
		super.add(Nodo);
		
	}
	
	public void push(T Data){
		
		super.add(Data);
		
	}
	
	public String toString(){
		
		return super.toString().replace(",", "]").replace(" ", "\n[").replace("{", "[").replace("}", "]");
		
	}
	
}