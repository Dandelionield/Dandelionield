package Algorithm.Methods;

/*
 *
 * @author Dandelion
 * 
 */

public class DoubleNode<T> extends Node<T>{

	private DoubleNode<T> previousReference;
	
	public DoubleNode(T Data){
		
		super(Data);
		
		this.previousReference = null;
		
	}
	
	public DoubleNode(T Data, DoubleNode<T> previousReference, DoubleNode<T> nextReference){
		
		super(Data, nextReference);
		
		this.previousReference = previousReference;
		
	}
	
	public void setNextReference(DoubleNode<T> nextReference){
		
		super.setReference(nextReference);
		
	}
	
	public void setPreviousReference(DoubleNode<T> previousReference){
		
		this.previousReference = previousReference;
		
	}
	
	public DoubleNode<T> getNextReference(){
		
		return (DoubleNode<T>) super.getReference();
		
	}
	
	public DoubleNode<T> getPreviousReference(){
		
		return this.previousReference;
		
	}
	
	public String toString() {
		
		StringBuilder bup = new StringBuilder();
		
		DoubleNode<T> n = this;
		
		while (n.getPreviousReference()!=null){
			
			bup.insert(0, n.getPreviousReference().getData().toString()+" <--> ");
			
			n = n.getPreviousReference();
			
		}

		bup.append("{"+this.getData().toString()+"}");

		n = this;
		
		while (n.getNextReference()!=null){
			
			bup.append(" <--> ").append(n.getNextReference().getData().toString());
			
			n = n.getNextReference();
			
		}

		return bup.toString();
		
	}
	
	public String getID(){
		
		return super.toString().replace("Algorithm.Methods.Node", "");
		
	}

}