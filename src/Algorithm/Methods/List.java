package Algorithm.Methods;

/*
 *
 * @author Dandelion
 * 
 */

import Algorithm.Methods.Node;

public class List<T>{

	private Node<T> firstNode;
	
	public List(){
		
		this.firstNode = null;
		
	}
	
	public List(Node<T> firstNode){
		
		this.firstNode = firstNode;
		
	}
	
	public List(T firstData){
		
		this.firstNode = new Node<>(firstData);
		
	}
	
	public void add(Node<T> Nodo){
		
		Nodo.setReference(this.firstNode);
		this.firstNode = Nodo;
		
	}
	
	public void add(T Data){
		
		Node<T> Nodo = new Node<>(Data, this.firstNode);
		this.firstNode = Nodo;
		
	}
	
	public String toString(){
		
		return "{"+this.firstNode.toString().replaceAll(";", ",")+"}";
		
	}

}