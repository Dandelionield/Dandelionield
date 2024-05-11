package Algorithm.Trees;

/*
 *
 * @author Dandelion
 * 
 */

import Algorithm.Nodes.BinaryNode;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T>{

	public AVLTree(){
		
		super();
		
	}
	
	public AVLTree(T Root){
		
		super(Root);
		
	}
	
	public AVLTree(BinaryNode<T> Root){
		
		super(Root);
		
	}
	
	public void add(T Data){
		
		this.add(new BinaryNode<T>(Data));
		
	}
	
	public void add(BinaryNode<T> Nodo){
		
		super.add(Nodo);
		
		this.balance();
		
	}
	
	public void remove(T Data){
		
		super.remove(Data);
		
		this.balance();
		
	}
	
	private void balance(){
		
		if (this.getRoot()!=null){
			
			boolean b = true;
			
			do{
				
				b = this.simpleBalance(this.getRoot());
				
			}while(b);
			
			do{
				
				b = this.complexBalance(this.getRoot());
				
			}while(b);
			
		}
	}
	
	private boolean complexBalance(BinaryNode<T> Nodo){
		
		if (Nodo!=null){
			
			int h = Nodo.getHeight();
			
			if (h>4){
				
				boolean b = this.complexBalance(Nodo.getRightReference());
				
				b = this.complexBalance(Nodo.getLeftReference()) || b;
				
				return b;
				
			}else if (h==4){
				
				int factor = Nodo.getBalancingFactor();
				int child = 0;
				
				BinaryNode<T> a = null;
				BinaryNode<T> b = null;
				BinaryNode<T> c = null;
				
				if (factor==2){
					
					child = Nodo.getRightReference().getBalancingFactor();
					
					if (child==1){
						
						this.complexRightRotation(Nodo);
						
					}else if (child==-1){
						
						b = Nodo.getRightReference();
						
						this.simpleLeftRotation(b);
						
						this.complexRightRotation(Nodo);
						
					}
					
					return true;
					
				}else if (factor==-2){
					
					child = Nodo.getLeftReference().getBalancingFactor();
					
					if (child==1){
						
						c = Nodo.getLeftReference();
						
						this.simpleRightRotation(c);
						
						this.complexLeftRotation(Nodo);
						
					}else if (child==-1){
						
						this.complexLeftRotation(Nodo);
						
					}
					
					return true;
					
				}else{
					
					return false;
					
				}
				
			}else{
				
				return false;
				
			}
			
		}
		
		return false;
		
	}
	
	private boolean simpleBalance(BinaryNode<T> Nodo){
		
		if (Nodo!=null){
			
			int h = Nodo.getHeight();
			
			if (h>3){
				
				boolean b = this.simpleBalance(Nodo.getRightReference());
				
				b = this.simpleBalance(Nodo.getLeftReference()) || b;
				
				return b;
				
			}else if (h==3){
				
				int factor = Nodo.getBalancingFactor();
				int child = 0;
				
				BinaryNode<T> a = null;
				BinaryNode<T> b = null;
				BinaryNode<T> c = null;
				
				if (factor==2){
					
					b = Nodo.getRightReference();
					
					child = b.getBalancingFactor();
					
					if (child==1){
						
						this.simpleRightRotation(Nodo);
						
					}else if (child==0){
						
						c = b.getLeftReference();
						c.setPosition(BinaryNode.RIGHT);
						c.setPreviousReference(null);
						
						b.setLeftReference(null);
						
						this.simpleRightRotation(Nodo);
						
						Nodo.setRightReference(c);
						c.setPreviousReference(Nodo);
						
					}else if (child==-1){
						
						c = b.getLeftReference();
						c.setPosition(BinaryNode.RIGHT);
						
						c.setPreviousReference(null);
						b.setLeftReference(null);
						
						Nodo.setRightReference(c);
						c.setPreviousReference(Nodo);
						
						c.setRightReference(b);
						b.setPreviousReference(c);
						
						this.simpleRightRotation(Nodo);
						
					}
					
					return true;
					
				}else if (factor==-2){
					
					child = Nodo.getLeftReference().getBalancingFactor();
					
					if (child==1){
						
						b = Nodo.getLeftReference();
						
						c = b.getRightReference();
						c.setPosition(BinaryNode.LEFT);
						
						c.setPreviousReference(null);
						b.setRightReference(null);
						
						Nodo.setLeftReference(c);
						c.setPreviousReference(Nodo);
						
						c.setLeftReference(b);
						b.setPreviousReference(c);
						
						this.simpleLeftRotation(Nodo);
						
					}else if (child==0){
						
						c = Nodo.getLeftReference();
						
						b = c.getRightReference();
						b.setPosition(BinaryNode.LEFT);
						b.setPreviousReference(null);
						
						c.setRightReference(null);
						
						this.simpleLeftRotation(Nodo);
						
						Nodo.setLeftReference(b);
						b.setPreviousReference(Nodo);
						
					}else if (child==-1){
						
						this.simpleLeftRotation(Nodo);
						
					}
					
					return true;
					
				}
				
				return false;
				
			}else{
				
				return false;
				
			}
			
		}
		
		return false;
		
	}
	
	private void complexRightRotation(BinaryNode<T> n){
		
		System.out.println(n);
		
		BinaryNode<T> b = n.getRightReference();
		BinaryNode<T> c = b.getLeftReference();
		
		c.setPreviousReference(null);
		c.setPosition(BinaryNode.RIGHT);
		b.setLeftReference(null);
		
		this.simpleRightRotation(n);
		
		n.setRightReference(c);
		c.setPreviousReference(n);
		
	}
	
	private void simpleRightRotation(BinaryNode<T> n){
		
		BinaryNode<T> a = n.getPreviousReference();
		BinaryNode<T> b = n.getRightReference();
		
		b.setPreviousReference(null);
		
		if (a!=null){
		
			b.setPosition(n.getPosition());
		
			if (b.getPosition()){
	
				a.setRightReference(b);
				
			}else{
				
				a.setLeftReference(b);
				
			}
			
			b.setPreviousReference(a);
			
		}
		
		n.setRightReference(null);
		n.setPreviousReference(null);
		
		b.setLeftReference(n);
		n.setPreviousReference(b);
		n.setPosition(BinaryNode.LEFT);
		
		if (a==null){
		
			super.setRoot(b);
			
		}
		
	}
	
	private void complexLeftRotation(BinaryNode<T> n){
		
		BinaryNode<T> c = n.getLeftReference();
		BinaryNode<T> b = c.getRightReference();
		
		b.setPreviousReference(null);
		b.setPosition(BinaryNode.LEFT);
		c.setRightReference(null);
		
		this.simpleLeftRotation(n);
		
		n.setLeftReference(b);
		b.setPreviousReference(n);
		
	}
	
	private void simpleLeftRotation(BinaryNode<T> n){
		
		BinaryNode<T> a = n.getPreviousReference();
		BinaryNode<T> c = n.getLeftReference();
		
		c.setPreviousReference(null);
		
		if (a!=null){
		
			c.setPosition(n.getPosition());
		
			if (c.getPosition()){
	
				a.setRightReference(c);
				
			}else{
				
				a.setLeftReference(c);
				
			}
			
			c.setPreviousReference(a);
			
		}
		
		n.setLeftReference(null);
		n.setPreviousReference(null);
		
		c.setRightReference(n);
		n.setPreviousReference(c);
		n.setPosition(BinaryNode.RIGHT);
		
		if (a==null){
		
			super.setRoot(c);
			
		}
		
	}

}