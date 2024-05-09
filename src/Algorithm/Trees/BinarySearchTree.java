package Algorithm.Trees;

/*
 *
 * @author Dandelion
 * 
 */

import Algorithm.Nodes.BinaryNode;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{

	public BinarySearchTree(){
		
		super();
		
	}
	
	public BinarySearchTree(T Root){
		
		super(Root);
		
	}
	
	public BinarySearchTree(BinaryNode<T> Root){
		
		super(Root);
		
	}
	
	public void add(T Data){
		
		this.add(new BinaryNode<T>(Data));
		
	}
	
	public void add(BinaryNode<T> Nodo){
		
		if (this.getRoot()==null && (Nodo!=null ? Nodo.getData()!=null : false)){
			
			Nodo = super.ClearNodo(Nodo);
			
			super.add(Nodo);
			
		}else if ((this.getRoot()!=null ? this.getRoot().getData()!=null : false) && (Nodo!=null ? Nodo.getData()!=null : false)){
			
			Nodo = super.ClearNodo(Nodo);
			
			boolean b = true;
			
			BinaryNode<T> Reference = this.getRoot();
			BinaryNode<T> bup = null;
			
			do{
				
				if (Nodo.getData().compareTo(Reference.getData())>=0){
				
					bup = Reference.getRightReference();
					Nodo.setPosition(BinaryNode.RIGHT);
					
				}else{
					
					bup = Reference.getLeftReference();
					Nodo.setPosition(BinaryNode.LEFT);
					
				}
				
				if (bup==null){
					
					if (Nodo.getPosition()){
						
						Reference.setRightReference(Nodo);
						
					}else{
						
						Reference.setLeftReference(Nodo);
						
					}
					
					b = false;
					
				}else{
					
					Reference = bup;
					
				}
				
			}while(b);
			
		}
		
	}

}