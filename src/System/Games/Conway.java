package System.Games;

/*
 *
 * @author Dandelion
 * 
 */

import Geometry.Euclidean.Matriz;

import java.util.ArrayList;

public class Conway{
	
	public static void main(String[] abc){
		
		System.out.print("Hola");
		
	}
	
	private final ArrayList<Matriz> m = new ArrayList<>();
	private final int width;
	private final int height;
	
	private boolean infinite = false;
	
	public Conway(Matriz m){
		
		this.m.add(this.fix(m));
		width = m.length;
		height = m.getRow(0).length;
		
	}
	
	public Matriz get(int gen){
		
		return this.m.get(gen);
		
	}
	
	public int getCurrentGeneration(){
		
		return this.m.size()-1;
		
	}
	
	public Matriz getCurrent(){
		
		return this.m.get(this.getCurrentGeneration());
		
	}
	
	public Conway getGrind(int Rows, int Columns){
		
		return new Conway(Matriz.getFullComponents(0, Rows, Columns));
		
	}
	
	public void setInfinite(boolean value){
		
		this.infinite = value;
		
	}
	
	public void set(int Row, int Column){
		
		Matriz mz = this.getCurrent();
		
		try{
			
			mz = mz.set(Row, Column, 1);
			
			m.set(this.getCurrentGeneration(), mz);
			
		}catch(Exception e){}
		
	}
	
	public void kill(int Row, int Column){
		
		Matriz mz = this.getCurrent();
		
		try{
			
			mz = mz.set(Row, Column, 0);
			
			m.set(this.getCurrentGeneration(), mz);
			
		}catch(Exception e){}
		
	}
	
	public String toString(){

		return (this.getCurrent()+"").replace(".0", "").replace("0", ".").replace(",", "").replace("1", "■");
		
	}
	
	public void play(){
		
		Matriz mz = this.getCurrent();
		
		Matriz v = Matriz.getFullComponents(0, width, height);
		
		for (int f=0; f<width; f++){
			
			for (int c=0; c<height; c++){
				
				byte p = this.pValue(f, c, mz);
				
				if (((p==2 || p==3) && mz.get(f, c)==1) || (p==3 && mz.get(f, c)==0)){
					
					v = v.set(f, c, 1);
					
				}
				
			}
			
		}
		
		m.add(v);
		
	}
	
	private byte pValue(int f, int c, Matriz mz){
		
		int p = 0;
		
		try{
			
			p+= mz.get(this.fixGetRow(f-1), this.fixGetColumn(c-1));
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(this.fixGetRow(f-1), this.fixGetColumn(c));
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(this.fixGetRow(f-1), this.fixGetColumn(c+1));
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(this.fixGetRow(f), this.fixGetColumn(c-1));
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(this.fixGetRow(f), this.fixGetColumn(c+1));
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(this.fixGetRow(f+1), this.fixGetColumn(c-1));
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(this.fixGetRow(f+1), this.fixGetColumn(c));
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(this.fixGetRow(f+1), this.fixGetColumn(c+1));
			
		}catch(Exception e){}
		
		return (byte) p;
		
	}
	
	private int fixGetRow(int Row){
		
		if (infinite==true){

			if (Row==-1){
				
				return width-1;
				
			}else if (Row==width){
				
				return 0;
				
			}
			
		}
		
		return Row;
		
	}
	
	private int fixGetColumn(int Column){
		
		if (infinite==true){

			if (Column==-1){
				
				return height-1;
				
			}else if (Column==height){
				
				return 0;
				
			}
			
		}
		
		return Column;
		
	}
	
	private Matriz fix(Matriz mz){
		
		for (int f=0; f<mz.length; f++){
			
			for(int c=0; c<mz.getRow(f).length; c++){
				
				try{
			
					if (mz.get(f, c)!=1){
						
						mz = mz.set(f, c, 0);
						
					}
					
				}catch(Exception e){}
				
			}
			
		}
		
		return mz;
		
	}
	
}