package Algorithm.Methods;

/*
 *
 * @author Dandelion
 * 
 */

import Algorithm.Methods.DoubleNode;

public class DoubleList<T>{
	
	private DoubleNode<T> lastNode;
	private DoubleNode<T> firstNode;
	
	public DoubleList(){
		
		firstNode = lastNode = null;
		
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
	
	@SuppressWarnings("unchecked")
	public void sortBy(boolean b){
		
		if (this.firstNode!=null){
			
			if (this.firstNode.getData() instanceof String){
			
				DoubleNode<T> n = this.firstNode;
				
				String[] v = new String[this.length()];
				int z = 0;
				
				while(n!=null){
					
					v[z] = n.getData().toString();
					
					z++;
					
					n = n.getNextReference();
					
				};
				
				String p = v[0];
				
				for (int f=0; f<v.length; f++){
					
					for (int c=1; c<v.length; c++){
						
						if (b ? p.compareTo(v[c])>0 : p.compareTo(v[c])<0){
							
							p = v[c-1];
							v[c-1] = v[c];
							v[c] = p;
							
						}else{
							
							p = v[c];
							
						}
						
					}
					
					p = v[0];
					
				}
				
				DoubleList<T> newList = new DoubleList<>();
				
				for (int i=v.length-1; i>=0; i--){
					
					newList.add((T) v[i]);
					
				}
				
				this.firstNode = newList.firstNode;
				this.lastNode = newList.lastNode;
				
			}
			
		}
		
	}
	
	public String toString(){
		
		if (this.lastNode!=null){
			
			return "{"+this.lastNode.toString().replace("{", "").replace("}", "").replace(" <--> ", ", ")+"}";
			
		}else if (this.firstNode!=null){
			
			return "{"+this.firstNode.toString().replace("{", "").replace("}", "").replace(" <--> ", ", ")+"}";
			
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
	
}