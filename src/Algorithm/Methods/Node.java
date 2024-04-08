package Algorithm.Methods;

/*
 *
 * @author Dandelion
 * 
 */

public class Node<T>{

	private T Data;
	private Node Reference;
	
	public Node(T Data){
		
		this.Data = Data;
		this.Reference = null;
		
	}
	
	public Node(T Data, Node Reference){
		
		this.Data = Data;
		this.Reference = Reference;
		
	}
	
	public void setData(T Data){
		
		this.Data = Data;
		
	}
	
	public void setReference(Node Reference){
		
		this.Reference = Reference;
		
	}
	
	public T getData(){
		
		return this.Data;
		
	}
	
	public Node getReference(){
		
		return this.Reference;
		
	}
	
	public String toString(){
		
		return this.Reference==null ? this.Data.toString() : this.Data.toString()+"; "+this.Reference.toString();
		
	}

}
