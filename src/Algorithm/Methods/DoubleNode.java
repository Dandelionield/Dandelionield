package Algorithm.Methods;

/*
 *
 * @author Dandelion
 * 
 */

public class DoubleNode<T> extends Node<T>{

	private DoubleNode<T> previousReference;
	
	public DoubleNode(){
		
		super();
		
		this.previousReference = null;
		
	}
	
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
		
		DoubleNode<T> n = (DoubleNode<T>) super.getReference();
		
		if (n==null){return null;}
		
		n.setPreviousReference(this);
		
		return n;
		
	}
	
	public DoubleNode<T> getPreviousReference(){
		
		if (this.previousReference==null){return null;}
		
		DoubleNode<T> n = this.previousReference;
		
		n.setNextReference(this);
		
		return n;
		
	}
	
	public DoubleNode<T> switchNodes(){
		
		DoubleNode<T> bup = new DoubleNode<>();
		DoubleNode<T> u = bup;
		
		DoubleNode<T> n = (DoubleNode<T>) super.getReference();
		
		while(n!=null){
			
			bup.setData(n.getData());
			bup.setPreviousReference(new DoubleNode<T>());
			
			bup = bup.getPreviousReference();
			n = n.getNextReference();
			
		}
		
		bup = bup.getNextReference();
		bup.setPreviousReference(null);
		
		bup = new DoubleNode<>();
		DoubleNode<T> p = bup;
		
		n = this.getPreviousReference();
		
		while(n!=null){
			
			bup.setData(n.getData());
			bup.setNextReference(new DoubleNode<T>());
			
			bup = bup.getNextReference();
			n = n.getPreviousReference();
			
		}
		
		bup = bup.getPreviousReference();
		bup.setNextReference(null);
		
		return new DoubleNode<T>(this.getData(), u, p);
		
	}
	
	public String toString() {
		
		StringBuilder bup = new StringBuilder();
		
		DoubleNode<T> n = this;
		
		while (n.getPreviousReference()!=null){
			
			bup.insert(0, n.getPreviousReference().getData()+" <--> ");
			
			n = n.getPreviousReference();
			
		}

		bup.append(this.getData()!=null ? "{"+this.getData().toString()+"}" : "");

		n = this;
		
		while (n.getNextReference()!=null){
			
			bup.append(" <--> ").append(n.getNextReference().getData());
			
			n = n.getNextReference();
			
		}

		return bup.toString();
		
	}
	
	public String getID(){
		
		return super.toString().replace("Algorithm.Methods.DoubleNode", "");
		
	}

}