package Algorithm.LinkedArrays;

/*
 *
 * @author Dandelion
 * 
 */

import Algorithm.Nodes.Node;

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
	
	public Node<T> getNode(){
		
		return this.firstNode;
		
	}
	
	public void add(Node<T> Nodo){

		if (this.firstNode==null){
			
			this.firstNode = Nodo;
			
		}else if (Nodo!=null){
			
			Node<T> n = Nodo;
			
			while(n.getReference()!=null){
				
				n = n.getReference();
				
			};

			n.setReference(this.firstNode);
			this.firstNode = Nodo;
			
		}
		
	}
	
	public void add(T Data){
		
		Node<T> Nodo = new Node<>(Data, this.firstNode);
		this.firstNode = Nodo;
		
	}
	
	public void addAtLast(Node<T> Nodo){

		if (this.firstNode==null){
			
			this.firstNode = Nodo;
			
		}else{
			
			Node<T> n = this.firstNode;
			
			while(n.getReference()!=null){
				
				n = n.getReference();
				
			};

			n.setReference(Nodo);
			
		}

	}
	
	public void addAtLast(T Data){

		this.addAtLast(new Node<T>(Data));
		
	}
	
	public void remove(){

		if (this.firstNode!=null){

			this.firstNode = this.firstNode.getReference();
			
		}
		
	}
	
	public void remove(T Data){

		if (this.firstNode!=null){

			Node<T> n = this.firstNode;
			Node<T> BackUp = null;
		
			while(n.getReference()!=null){
				
				if (n.getData().toString().equals(Data.toString())){
					
					if (BackUp!=null){
						
						BackUp.setReference(n.getReference());
						
					}else{
						
						this.remove();
						
					}
					
					break;
					
				}
				
				BackUp = n;
				n = n.getReference();
				
			};
			
			if (n.getData().toString().equals(Data.toString()) && n.getReference()==null){
				
				this.removeAtLast();
				
			}
			
		}
		
	}
	
	public void removeAtLast(){

		if (this.firstNode!=null){

			Node<T> n = this.firstNode;
			Node<T> BackUp = null;
			
			while(n.getReference()!=null){

				BackUp = n;
				n = n.getReference();
				
			};
			
			BackUp.setReference(null);
			
		}
		
	}
	
	public Node<T> get(T Data){
		
		if (this.firstNode==null){
			
			return null;
			
		}
		
		if (this.firstNode.getData().toString().equals(Data.toString())){
			
			return this.firstNode;
			
		}
		
		Node<T> n = this.firstNode;
		
		while(n.getReference()!=null){
			
			if (n.getData().toString().equals(Data.toString())){
			
				return n;
				
			}
			
			n = n.getReference();
			
		};
		
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public void sortBy(boolean b){
		
		if (this.firstNode!=null){
			
			if (this.firstNode.getData() instanceof String){
			
				Node<T> n = this.firstNode;
				
				String[] v = new String[this.length()];
				int z = 0;
				
				while(n!=null){
					
					v[z] = n.getData().toString();
					
					z++;
					
					n = n.getReference();
					
				};
				
				String p = v[0];
				
				for (int f=0; f<v.length; f++){
					
					for (int c=1; c<v.length; c++){
						
						if (b ? p.compareTo(v[c])<0 : p.compareTo(v[c])>0){
							
							p = v[c-1];
							v[c-1] = v[c];
							v[c] = p;
							
						}else{
							
							p = v[c];
							
						}
						
					}
					
					p = v[0];
					
				}
				
				List<T> newList = new List<>();
				
				for (int i=v.length-1; i>=0; i--){
					
					newList.add((T) v[i]);
					
				}
				
				this.firstNode = newList.firstNode;
				
			}
			
		}
		
	}
	
	/*public T[] getArray(){
		
		int c = this.length();
		
		if (c==0){return null;}
		
		T[] v = new T[c];
		
		Node<T> n = this.firstNode;
		
		for(int i=0; i<c; i++){
			
			v[i] = (T) n.getData();
			
			n = n.getReference();
			
		}
		
		return v;
		
	}//*/
	
	public String toString(){
		
		if (this.firstNode!=null){
		
			Node<T> n = this.firstNode;
			
			String bup = "{";
			
			while(n.getReference()!=null){
				
				bup+= n.getData()+", ";
				
				n = n.getReference();
				
			}
			
			return bup+""+n.getData()+"}";
			
		}
		
		return "{}";
		
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