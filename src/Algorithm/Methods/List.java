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
	
	/*public T[] get(){
		
		int c = this.length();
		
		if (c==0){return null;}
		
		T[] v = new T[c];
		
		Node<T> n = this.firstNode;
		
		for(int i=0; i<c; i++){
			
			v[i] = n.getData();
			
			n = n.getReference();
			
		}
		
		return v;
		
	}//*/
	
	public String toString(){
		
		return "{"+this.firstNode.toString().replaceAll(";", ",")+"}";
		
	}
	
	public int length(){
		
		if (this.firstNode==null){
			
			return 0;
			
		}else{
			
			int c = 0;
			Node<T> n = this.firstNode;
			
			do{
				
				c++;
				
				n = n.getReference();
				
			}while(n!=null);
			
			return c;
			
		}
		
	}

}