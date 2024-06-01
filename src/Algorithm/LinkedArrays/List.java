package Algorithm.LinkedArrays;

/*
 *
 * @author Dandelion
 * 
 */

import Algorithm.Nodes.Node;

public class List<T extends Comparable<T>>{

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
	
	public T get(boolean b){
		
		if (this.firstNode!=null){
		
			T n = this.firstNode.getData();
			
			Node<T> p = this.firstNode;
			
			while(p!=null){
				
				if (b ? p.getData().compareTo(n)>0 : p.getData().compareTo(n)<0){
					
					n = p.getData();
					
				}
				
				p = p.getReference();
				
			}
			
			return n;
			
		}
		
		return null;
		
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
			
			if (this.length()!=1){

				Node<T> n = this.firstNode;
				Node<T> BackUp = null;
				
				while(n.getReference()!=null){

					BackUp = n;
					n = n.getReference();
					
				};
				
				BackUp.setReference(null);
				
			}else{
				
				this.remove();
				
			}
			
		}
		
	}
	
	public void shortBy(boolean b){
		
		if (this.firstNode!=null){
		
			List<T> n = new List<>();
			T p = null;
			
			do{
				
				p = this.get(b);
				
				n.addAtLast(p);
				
				this.remove(p);
				
			}while(this.firstNode!=null);
			
			this.firstNode = n.firstNode;
			
		}
		
	}
	
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