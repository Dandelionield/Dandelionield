package Algorithm.Trees;

/*
 *
 * @author Dandelion
 * 
 */

import Algorithm.Nodes.BinaryNode;

public class BinaryTree<T>{
	
	private BinaryNode<T> Root;
	
	public BinaryTree(){
		
		this.Root = null;
		
	}
	
	public BinaryTree(T Root){
		
		if (Root!=null){
			
			this.Root = new BinaryNode<T>(Root);
			
		}
		
	}
	
	public BinaryTree(BinaryNode<T> Root){
		
		if (Root!=null){
		
			if (Root.getPreviousReference()!=null){
				
				if (Root.getPosition()){
					
					Root.getPreviousReference().setRightReference(null);
					
				}else{
					
					Root.getPreviousReference().setLeftReference(null);
					
				}
				
				Root.setPreviousReference(null);
				
			}
			
		}
		
		this.Root = Root;
		
	}
	
	public BinaryNode<T> getRoot(){
		
		return this.Root;
		
	}
	
	public BinaryTree<T> getRightBranch(){
		
		if (this.Root==null){return null;}
		
		BinaryNode<T> n = this.Root.getRightReference();
		
		n.setPreviousReference(null);
		
		return new BinaryTree<T>(n);
		
	}
	
	public BinaryTree<T> getLeftBranch(){
		
		if (this.Root==null){return null;}
		
		BinaryNode<T> n = this.Root.getLeftReference();
		
		n.setPreviousReference(null);
		
		return new BinaryTree<T>(n);
		
	}
	
	public int getHeight(){
		
		if (this.Root==null){return 0;}
		
		return this.Root.getHeight();
		
	}
	
	public int getBalancingFactor(){
		
		if (this.Root==null){return 0;}
		
		return this.Root.getBalancingFactor();
		
	}
	
	public void add(T Data){
		
		this.add(new BinaryNode<T>(Data));
		
	}
	
	public void add(BinaryNode<T> Nodo){
		
		if (this.Root!=null && (Nodo!=null ? Nodo.getData()!=null : false)){
			
			Nodo = this.ClearNodo(Nodo);
		
			if (this.Root.getRightReference()==null){
				
				this.Root.setRightReference(Nodo);

			}else if (this.Root.getLeftReference()==null){
				
				this.Root.setLeftReference(Nodo);
				
			}else{
			
				boolean b = true;
				
				BinaryNode<T> Right = this.Root.getRightReference();
				BinaryNode<T> Left = this.Root.getLeftReference();
				
				do{
					
					if (Right.getHeight()==0){
						
						if (Right.getRightReference()==null){
							
							Right.setRightReference(Nodo);
							
						}else{
							
							Right.setLeftReference(Nodo);
							
						}
						
						b = false;
						
					}else if (Left.getHeight()==0){
						
						if (Left.getRightReference()==null){
							
							Left.setRightReference(Nodo);
							
						}else{
							
							Left.setLeftReference(Nodo);
							
						}
						
						b = false;
						
					}else if (Right.getHeight()==1){
						
						if (Right.getRightReference()==null){
							
							Right.setRightReference(Nodo);
							
						}else{
							
							Right.setLeftReference(Nodo);
							
						}
						
						b = false;
						
					}else if (Left.getHeight()==1){
						
						if (Left.getRightReference()==null){
							
							Left.setRightReference(Nodo);
							
						}else{
							
							Left.setLeftReference(Nodo);
							
						}
						
						b = false;
						
					}
					
					Right = this.Root.getRightReference();
					Left = this.Root.getLeftReference();
					
				}while(b);
				
			}
			
		}else if (this.Root==null && (Nodo!=null ? Nodo.getData()!=null : false)){
			
			if (Nodo.getPreviousReference()!=null){
				
				if (Nodo.getPosition()){
					
					Nodo.getPreviousReference().setRightReference(null);
					
				}else{
					
					Nodo.getPreviousReference().setLeftReference(null);
					
				}
				
				Nodo.setPreviousReference(null);
				
			}
			
			this.Root = Nodo;
			
		}
		
	}
	
	public void remove(T Data){
		
		if (this.Root!=null && Data!=null){
			
			if (this.Root.getData().toString().equals(Data.toString())){
				
				BinaryNode<T> n = this.Root.getRightReference();
				
				BinaryNode<T> b = this.Root.getLeftReference();
				BinaryNode<T> c = this.Root.getRightReference();
				
				while(n.getLeftReference()!=null){
				
					n = n.getLeftReference();
					
				}
				
				if (n.getRightReference()!=null){
					
					BinaryNode<T> bup = n.getRightReference();
					BinaryNode<T> past = n.getPreviousReference();
					
					bup.setPreviousReference(null);
					
					if (n.getPosition()){
					
						past.setRightReference(bup);
						
					}else{
						
						past.setLeftReference(bup);
						
					}
					
					bup.setPreviousReference(past);
					
					n.setPreviousReference(null);
					
				}else{
					
					if (n.getPosition()){
					
						n.getPreviousReference().setRightReference(null);
						
					}else{
						
						n.getPreviousReference().setLeftReference(null);
						
					}
					
					n.setPreviousReference(null);
					
				}
				
				n.setLeftReference(b);
				
				if (c.getID().equals(n.getID())==false){
					
					n.setRightReference(c);
					
				}
				
				b.setPreviousReference(n);
				
				if (c.getID().equals(n.getID())==false){
					
					c.setPreviousReference(n);
					
				}
				
				this.Root.setPreviousReference(null);
				this.Root.setLeftReference(null);
				this.Root.setRightReference(null);
				
				this.Root = n;
				
			}else{
				
				remove(this.Root, Data);
				
			}
			
		}
		
	}
	
	public String toString(){
		
		return this.Root!=null ? this.Root.toString() : "()";
		
	}
	
	private boolean remove(BinaryNode<T> Nodo, T Data){
		
		if (Nodo!=null && Data!=null){
		
			if (Nodo.getData().toString().equals(Data.toString())){
				
				if (Nodo.getRightReference()==null && Nodo.getLeftReference()==null){
					
					BinaryNode<T> n = Nodo.getPreviousReference();
					
					if (n!=null){
						
						if (Nodo.getPosition()){
						
							n.setRightReference(null);
							
						}else{
							
							n.setLeftReference(null);
							
						}
						
					}
					
					Nodo.setPreviousReference(null);
					
					return true;
					
				}else if (Nodo.getRightReference()==null || Nodo.getLeftReference()==null){
					
					BinaryNode<T> n = Nodo.getRightReference()==null ? Nodo.getLeftReference() : Nodo.getRightReference();
					BinaryNode<T> bup = Nodo.getPreviousReference();
					
					if (n.getPosition()){
						
						Nodo.setRightReference(null);
						
					}else{
						
						Nodo.setLeftReference(null);
						
					}
					
					Nodo.setPreviousReference(null);
					
					n.setPreviousReference(bup);
					
					n.setPosition(Nodo.getPosition());
					
					if (n.getPosition()){
						
						bup.setRightReference(n);
						
					}else{
						
						bup.setLeftReference(n);
						
					}
					
					return true;
					
				}else{
					
					BinaryNode<T> n = Nodo.getRightReference();
					
					BinaryNode<T> a = Nodo.getPreviousReference();
					BinaryNode<T> b = Nodo.getLeftReference();
					BinaryNode<T> c = Nodo.getRightReference();
					
					while(n.getLeftReference()!=null){
						
						n = n.getLeftReference();
						
					}
					
					if (n.getRightReference()!=null){
						
						BinaryNode<T> bup = n.getRightReference();
						BinaryNode<T> past = n.getPreviousReference();
						
						bup.setPreviousReference(null);
						
						if (n.getPosition()){
						
							past.setRightReference(bup);
							
						}else{
							
							past.setLeftReference(bup);
							
						}
						
						bup.setPreviousReference(past);
						
						n.setPreviousReference(null);
						
					}else{
						
						if (n.getPosition()){
						
							n.getPreviousReference().setRightReference(null);
							
						}else{
							
							n.getPreviousReference().setLeftReference(null);
							
						}
						
						n.setPreviousReference(null);
						
					}
					
					n.setPreviousReference(a);
					n.setLeftReference(b);
					
					if (c.getID().equals(n.getID())==false){
						
						n.setRightReference(c);
						
					}
					
					if (Nodo.getPosition()){
					
						a.setRightReference(n);
						
					}else{
						
						a.setLeftReference(n);
						
					}
					
					b.setPreviousReference(n);
					
					if (c.getID().equals(n.getID())==false){
						
						c.setPreviousReference(n);
						
					}
					
					Nodo.setPreviousReference(null);
					Nodo.setLeftReference(null);
					Nodo.setRightReference(null);
					
					return true;
					
				}
				
			}else{
				
				boolean b = false;
				
				if (Nodo.getRightReference()!=null){
					
					b = remove(Nodo.getRightReference(), Data);
					
				}
				
				if (Nodo.getLeftReference()!=null && b==false){
					
					b = remove(Nodo.getLeftReference(), Data);
					
				}
				
				return b;
				
			}
			
		}
		
		return false;
		
	}
	
	protected void setRoot(BinaryNode<T> Nodo){
		
		this.Root = Nodo;
		
	}
	
	protected BinaryNode<T> ClearNodo(BinaryNode<T> Nodo){
		
		if (Nodo.getPreviousReference()!=null){
			
			if (Nodo.getPosition()){
				
				Nodo.getPreviousReference().setRightReference(null);
				
			}else{
				
				Nodo.getPreviousReference().setLeftReference(null);
				
			}
			
			Nodo.setPreviousReference(null);
			
		}
		
		if (Nodo.getLeftReference()!=null){
			
			Nodo.getLeftReference().setPreviousReference(null);
			
			Nodo.setLeftReference(null);
			
		}
		
		if (Nodo.getRightReference()!=null){
			
			Nodo.getRightReference().setPreviousReference(null);
			
			Nodo.setRightReference(null);
			
		}
		
		return Nodo;
		
	}
	
}