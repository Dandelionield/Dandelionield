package Algorithm.Nodes;

/*
 *
 * @author Dandelion
 * 
 */

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryNode<T> extends DoubleNode<T>{

	private BinaryNode<T> previousReference;
	
	private boolean position = true;
	
	public static final boolean RIGHT = true;
	public static final boolean LEFT = false;
	
	public BinaryNode(){
		
		super();
		
		this.previousReference = null;
		
	}
	
	public BinaryNode(boolean position){
		
		super();
		
		this.previousReference = null;
		
		this.position = position;
		
	}
	
	public BinaryNode(T Data){
		
		super(Data);
		
		this.previousReference = null;
		
	}
	
	public BinaryNode(T Data, boolean position){
		
		super(Data);
		
		this.previousReference = null;
		
		this.position = position;
		
	}
	
	public BinaryNode(T Data, BinaryNode<T> previousReference, BinaryNode<T> rightReference, BinaryNode<T> leftReference){
		
		super(Data, leftReference, rightReference);
		
		this.previousReference = previousReference;
		
	}
	
	public BinaryNode(T Data, boolean position, BinaryNode<T> previousReference, BinaryNode<T> rightReference, BinaryNode<T> leftReference){
		
		super(Data, leftReference, rightReference);
		
		this.previousReference = previousReference;
		
		this.position = position;
		
	}
	
	public void setPosition(boolean position){
		
		this.position = position;
		
	}
	
	public void setPreviousReference(BinaryNode<T> previousReference){
		
		this.previousReference = previousReference;
		
	}
	
	public void setRightReference(BinaryNode<T> rightReference){
		
		super.setNextReference(rightReference);
		
	}
	
	public void setLeftReference(BinaryNode<T> leftReference){
		
		super.setPreviousReference(leftReference);
		
	}
	
	public BinaryNode<T> getPreviousReference(){
		
		if (this.previousReference==null){return null;}
		
		if (this.position==BinaryNode.RIGHT){
			
			this.previousReference.setRightReference(this);
			
			return this.previousReference;
			
		}else{
			
			this.previousReference.setLeftReference(this);
			
			return this.previousReference;
			
		}
		
	}
	
	public BinaryNode<T> getRightReference(){
		
		BinaryNode<T> n = (BinaryNode<T>) super.getNextCutReference();
		
		if (n==null){return null;}
		
		n.setPosition(BinaryNode.RIGHT);
		
		n.setPreviousReference(this);
		
		return n;
		
	}
	
	public BinaryNode<T> getLeftReference(){
		
		BinaryNode<T> n = (BinaryNode<T>) super.getPreviousCutReference();
		
		if (n==null){return null;}
		
		n.setPosition(BinaryNode.LEFT);
		
		n.setPreviousReference(this);
		
		return n;
		
	}
	
	public int getLevel(){
		
		return previousReference==null ? 0 : 1 + this.previousReference.getLevel();
		
	}
	
	public int getHeight(){
		
		if (this.getRightReference()==null && this.getLeftReference()==null){
			
			return 1;
			
		}else if (this.getRightReference()==null){
			
			return this.getLeftReference().getHeight() + 1;
			
		}else if (this.getLeftReference()==null){
			
			return this.getRightReference().getHeight() + 1;
			
		}
		
		return Math.max(this.getLeftReference().getHeight(), this.getRightReference().getHeight()) + 1;
		
	}
	
	public int getBalancingFactor(){
		
		if (this.getRightReference()==null && this.getLeftReference()==null){
			
			return 0;
			
		}else if (this.getRightReference()==null){
			
			return -this.getLeftReference().getHeight();
			
		}else if (this.getLeftReference()==null){
			
			return this.getRightReference().getHeight();
			
		}
		
		return this.getRightReference().getHeight() - this.getLeftReference().getHeight();
		
	}
	
	public boolean getPosition(){
		
		return this.position;
		
	}
	
	public String preOrder(){
		
		return this.getData()+""+(this.getLeftReference()!=null ? ", "+this.getLeftReference().preOrder() : "")+""+(this.getRightReference()!=null ? ", "+this.getRightReference().preOrder() : "");
		
	}
	
	public String inOrder(){
		
		return (this.getLeftReference()!=null ? this.getLeftReference().inOrder()+", " : "")+""+this.getData()+""+(this.getRightReference()!=null ? ", "+this.getRightReference().inOrder() : "");
		
	}
	
	public String postOrder(){
		
		return (this.getLeftReference()!=null ? this.getLeftReference().postOrder()+", " : "")+""+(this.getRightReference()!=null ? this.getRightReference().postOrder()+", " : "")+""+this.getData();
		
	}
	
	public String toString(){
		
		return toString(this.getID());
		
	}
	
	private String toString(String ID){
		
		BinaryNode<T> n = this.getPreviousReference();
		BinaryNode<T> m = this.getRightReference();
		BinaryNode<T> v = this.getLeftReference();
		
		String bup = this.getID().equals(ID) ? "("+this.getData()+")" : this.getData()+"";
		
		if (m!=null){
			
			if (m.getLeftReference()==null && m.getRightReference()==null){
				
				bup+= "|>"+m.getData();
				
			}else{
				
				m.setPreviousReference(null);
				
				bup+= "|>{"+m.toString(null)+"}";
				
				m.setPreviousReference(this);
				
			}
			
		}
		
		if (v!=null){
			
			if (v.getLeftReference()==null && v.getRightReference()==null){
				
				bup = v.getData()+"<|"+bup;
				
			}else{
				
				v.setPreviousReference(null);
				
				bup = "{"+v.toString(null)+"}<|"+bup;
				
				v.setPreviousReference(this);
				
			}
			
		}
		
		if (n==null){
			
			return bup;
			
		}else{
			
			return this.printPreviousReference(n, bup, this.position);
			
		}
		
	}
	
	private String printPreviousReference(BinaryNode<T> n, String wd, boolean position){
		
		String bup = "";
		
		if (position==BinaryNode.LEFT){
			
			bup = "{"+wd+"}<|"+n.getData();
			
			BinaryNode<T> m = n.getRightReference();
			
			if (m!=null){
				
				m.setPreviousReference(null);
				
				bup+= "|>"+m.toString(null)+"";
				
				m.setPreviousReference(n);
				
			}
			
		}else{
			
			bup = n.getData()+"|>{"+wd+"}";
			
			BinaryNode<T> v = n.getLeftReference();
			
			if (v!=null){
				
				v.setPreviousReference(null);
				
				bup = ""+v.toString(null)+"<|"+bup;
				
				v.setPreviousReference(n);
				
			}
			
		}
		
		if (n.getPreviousReference()==null){
			
			return bup;
			
		}else{
			
			return this.printPreviousReference(n.getPreviousReference(), bup, n.position);
			
		}
		
	}

}