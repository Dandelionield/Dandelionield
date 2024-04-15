package Algorithm.Methods;

/*
 *
 * @author Dandelion
 * 
 */

public class Node<T>{

	private T Data;
	private Node<T> nextReference;
	
	public Node(T Data){
		
		this.Data = Data;
		this.nextReference = null;
		
	}
	
	public Node(T Data, Node<T> nextReference){
		
		this.Data = Data;
		this.nextReference = nextReference;
		
	}
	
	public void setData(T Data){
		
		this.Data = Data;
		
	}
	
	public void setReference(Node<T> nextReference){
		
		this.nextReference = nextReference;
		
	}
	
	public T getData(){
		
		return this.Data;
		
	}
	
	public Node<T> getReference(){
		
		return this.nextReference;
		
	}
	
	public String toString(){
		
		return this.nextReference==null ? this.Data.toString() : this.Data.toString()+" --> "+this.nextReference.toString();
		
	}
	
	public String getID(){
		
		return super.toString().replace("Algorithm.Methods.Node", "");
		
	}

}
