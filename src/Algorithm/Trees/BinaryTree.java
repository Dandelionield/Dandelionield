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
		
		if (this.Root.getRightReference()==null && this.Root.getLeftReference()==null){
			
			return 0;
			
		}else if (this.Root.getRightReference()==null){
			
			return this.Root.getLeftReference().getHeight();
			
		}else if (this.Root.getLeftReference()==null){
			
			return -this.Root.getRightReference().getHeight();
			
		}
		
		return this.Root.getLeftReference().getHeight() - this.Root.getRightReference().getHeight();
		
	}
	
	public String toString(){
		
		return this.Root!=null ? this.Root.toString() : "()";
		
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