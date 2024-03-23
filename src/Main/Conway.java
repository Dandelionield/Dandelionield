package Main;

/*
 *
 * @author Dandelion
 * 
 */

import Taylor.Math.*;
import Taylor.Arithmetic.*;

import Geometry.Euclidean.*;
import Geometry.Algebra.*;

import Physics.Fundamental.*;
import Physics.Measurement.*;

import Graphic.R2Space.*;
import Graphic.Component.*;
import Graphic.User.Interface.*;

import System.Numeric.*;

import java.util.ArrayList;

public class Conway{
	
	public static void main(String[] abc){
		
		System.out.print("Hola");
		
	}
	
	private final ArrayList<Matriz> m = new ArrayList<>();
	private final int width;
	private final int height;
	
	public Conway(Matriz m){
		
		this.m.add(m);
		width = m.getColumn(0).length;
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
	
	public String toString(){
		
		return (this.getCurrent()+"").replace(".0", " ").replace("0", ".").replace(",", " ");
		
	}
	
	public void play(){
		
		Matriz mz = this.getCurrent();
		
		Matriz v = Matriz.getFullComponents(0, width, height);
		
		for (int f=0; f<width; f++){
			
			for (int c=0; c<height; c++){
				
				byte p = this.pValue(f, c, mz);
				
				if (mz.get(f, c)==1){
					
					if (p==2 || p==3){
						
						v = v.set(f, c, 1);
						
					}else{
						
						v = v.set(f, c, 0);
						
					}
					
				}else{
					
					if (p==3){
						
						v = v.set(f, c, 1);
						
					}
					
				}
				
			}
			
		}
		
		m.add(v);
		
	}//*/
	
	private byte pValue(int f, int c, Matriz mz){
		
		int p = 0;
		
		try{
			
			p+= mz.get(f-1, c-1);
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(f-1, c);
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(f-1, c+1);
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(f, c-1);
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(f, c+1);
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(f+1, c-1);
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(f+1, c);
			
		}catch(Exception e){}
		
		try{
			
			p+= mz.get(f+1, c+1);
			
		}catch(Exception e){}
		
		return (byte) p;
		
	}
	
}