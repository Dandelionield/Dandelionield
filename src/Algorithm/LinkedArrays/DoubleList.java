package Algorithm.LinkedArrays;

/*
 *
 * @author Dandelion
 * 
 */

import Algorithm.Nodes.DoubleNode;

public class DoubleList<T extends Comparable<T>>{
	
	private DoubleNode<T> firstNode;
	private DoubleNode<T> lastNode;
	
	public DoubleList(){
		
		firstNode = lastNode = null;
		
	}
	
	public DoubleList(DoubleNode<T> Nodo){
		
		this.firstNode = this.fixFirst(Nodo);
		this.lastNode = this.fixLast(Nodo);
		
	}
	
	public DoubleNode<T> get(T Data){
		
		if (this.firstNode==null){
			
			return null;
			
		}
		
		if (this.firstNode.getData().toString().equals(Data.toString())){
			
			return this.firstNode;
			
		}
		
		if (this.lastNode.getData().toString().equals(Data.toString())){
			
			return this.lastNode;
			
		}
		
		DoubleNode<T> n = this.firstNode;
		
		while(n.getNextReference()!=null){
			
			if (n.getData().toString().equals(Data.toString())){
			
				return n;
				
			}
			
			n = n.getNextReference();
			
		};
		
		return null;
		
	}
	
	public T get(boolean b){
		
		if (this.firstNode!=null){
		
			T n = this.firstNode.getData();
			
			DoubleNode<T> p = this.firstNode;
			
			while(p!=null){
				
				if (b ? p.getData().compareTo(n)>0 : p.getData().compareTo(n)<0){
					
					n = p.getData();
					
				}
				
				p = p.getNextReference();
				
			}
			
			return n;
			
		}
		
		return null;
		
	}
	
	public DoubleNode<T> getFirstNode(){
		
		return this.firstNode;
		
	}
	
	public DoubleNode<T> getLastNode(){
		
		return this.lastNode;
		
	}
	
	public void add(T Data){
		
		if (this.firstNode!=null){
			
			this.firstNode = new DoubleNode<T>(Data, null, this.firstNode);
			
			this.firstNode.getNextReference().setPreviousReference(this.firstNode);
			
		}else{
			
			this.firstNode = this.lastNode = new DoubleNode<T>(Data);
			
		}
		
	}
	
	public void addAtLast(T Data){
		
		if (this.lastNode!=null){
			
			this.lastNode = new DoubleNode<T>(Data, this.lastNode, null);
			
			this.lastNode.getPreviousReference().setNextReference(this.lastNode);
			
		}else{
			
			this.lastNode = this.firstNode = new DoubleNode<T>(Data);
			
		}
		
	}
	
	public void remove(){
		
		if (this.firstNode!=null){
			
			this.firstNode = this.firstNode.getNextReference();
			this.firstNode.setPreviousReference(null);
			
		}
		
	}
	
	public void remove(T Data){
		
		if (this.firstNode!=null){
			
			if (this.firstNode.getData().toString().equals(Data.toString())){
			
				this.remove();
				
			}else if (this.lastNode.getData().toString().equals(Data.toString())){
				
				this.removeAtLast();
				
			}else{
			
				DoubleNode<T> n = this.firstNode;
				DoubleNode<T> m = this.lastNode;
				
				DoubleNode<T> BackUp1 = null;
				DoubleNode<T> BackUp2 = null;
				
				while(n.getNextReference()!=null){
					
					if (n.getData().toString().equals(Data.toString())){
						
						while(m.getPreviousReference()!=null ){
							
							if (m.getID().equals(n.getID())){
								
								BackUp1.setNextReference(null);
								BackUp2.setPreviousReference(null);
						
								BackUp1.setNextReference(BackUp2);
								BackUp2.setPreviousReference(BackUp1);
								
								break;
								
							}
							
							BackUp2 = m;
							m = m.getPreviousReference();
							
						}
						
						break;
						
					}
					
					BackUp1 = n;
					n = n.getNextReference();
					
				};
				
			}
			
		}
		
	}
	
	public void removeAtLast(){
		
		if (this.lastNode!=null){
			
			this.lastNode = this.lastNode.getPreviousReference();
			this.lastNode.setNextReference(null);
			
		}
		
	}
	
	public void shortBy(boolean b){
		
		if (this.firstNode!=null){
		
			DoubleList<T> n = new DoubleList<>();
			T p = null;
			
			do{
				
				p = this.get(b);
				
				n.addAtLast(p);
				
				this.remove(p);
				
			}while(this.firstNode!=null);
			
			this.firstNode = n.firstNode;
			this.lastNode = n.lastNode;
			
		}
		
	}
	
	public String toString(){
		
		if (this.lastNode!=null){
			
			DoubleNode<T> n = this.lastNode;
			
			String bup = "}";
			
			while(n.getPreviousReference()!=null){
				
				bup = ", "+n.getData()+""+bup;
				
				n = n.getPreviousReference();
				
			}
			
			return "{"+n.getData()+bup;
			
		}else if (this.firstNode!=null){
			
			DoubleNode<T> n = this.firstNode;
			
			String bup = "{";
			
			while(n.getNextReference()!=null){
				
				bup+= n.getData()+", ";
				
				n = n.getNextReference();
				
			}
			
			return bup+""+n.getData()+"}";
			
		}else{
			
			return "{}";
			
		}
		
	}
	
	public int length(){
		
		if (this.firstNode==null){
			
			return 0;
			
		}else{
			
			int c = 0;
			DoubleNode<T> n = this.firstNode;
			
			do{
				
				c++;
				
				n = n.getNextReference();
				
			}while(n!=null);
			
			return c;
			
		}
		
	}
	
	private DoubleNode<T> fixFirst(DoubleNode<T> n){
		
		while(n.getPreviousReference()!=null){
			
			n = n.getPreviousReference();
			
		}
		
		return n;
		
	}
	
	private DoubleNode<T> fixLast(DoubleNode<T> m){
		
		while(m.getNextReference()!=null){
			
			m = m.getNextReference();
			
		}
		
		return m;
		
	}
	
}