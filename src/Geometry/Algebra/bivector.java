package Geometry.Algebra;

/*
 *
 * @author Dandelion
 * 
 */

import Geometry.Euclidean.vector;
import Geometry.Euclidean.degree;
import Taylor.Math.Mayth;

public class bivector implements Comparable<bivector>{
	
	private final double Magnitude;
	private final vector a;
	private final vector b;
	private final degree Theta;
	private final boolean Swapped;
	
	public bivector(vector a, vector b){
		
		this.a = a;
		this.b = b;
		Theta = a.doBetweenTwo(b);
		Magnitude = a.getMagnitude()*a.getMagnitude()*Theta.getSen();
		Swapped = false;
		
	}
	
	public bivector(vector[] v){
		
		this.a = v[0];
		this.b = v[1];
		Theta = a.doBetweenTwo(b);
		Magnitude = a.getMagnitude()*a.getMagnitude()*Theta.getSen();
		Swapped = false;
		
	}
	
	private bivector(bivector B, int limite){
		
		this.a = B.a.doRedondear(limite);
		this.b = B.b.doRedondear(limite);
		Theta = a.doBetweenTwo(b).doRedondear(limite);
		Magnitude = Mayth.Redondear(B.Magnitude, limite);
		Swapped = B.Swapped;
		
	}
	
	private bivector(bivector B, boolean value){
		
		Swapped = value;
		
		this.a = B.a;
		this.b = B.b;
		Theta = a.doBetweenTwo(b);
		Magnitude = B.Magnitude;
		
	}
	
	public int compareTo(bivector b){
		
		if (this.Magnitude>b.Magnitude){
			
			return 1;
			
		}else if (this.Magnitude<b.Magnitude){
			
			return -1;
			
		}
		
		return 0;
		
	}
	
	public vector[] get(){
		
		return new vector[] {a, b};
		
	}
	
	public vector getVectorA(){
		
		return this.a;
		
	}
	
	public vector getVectorB(){
		
		return this.b;
		
	}
	
	public double getMagnitude(){
		
		return this.Magnitude;
		
	}
	
	public double getArea(){
		
		return this.Magnitude;
		
	}
	
	public degree getAngle(){
		
		return this.Theta;
		
	}
	
	public String toString(){
		
		if (Swapped==true){
			
			return "-"+a.doScalar(-1)+" ∧ "+b;
			
		}else{
			
			return a+" ∧ "+b;
			
		}
		
	}
	
	public bivector doSwap(){
		
		return new bivector(this.doScalarRight(-1), true);
		
	}
	
	public double doScalar(vector v){
		
		return this.a.doScalar(this.b.doCross(v));
		
	}
	
	public bivector doScalarRight(double s){
		
		return new bivector(this.a.doScalar(s), this.b);
		
	}
	
	public bivector doScalarLeft(double s){
		
		return new bivector(this.a, this.b.doScalar(s));
		
	}
	
	public bivector doSuma(bivector B){
		
		return new bivector(this.a.doSuma(B.a), this.b.doSuma(B.b));
		
	}
	
	public bivector doResta(bivector B){
		
		return new bivector(this.a.doResta(B.a), this.b.doResta(B.b));
		
	}
	
	public bivector doRedondear(int limite){
		
		return new bivector(this, limite);
		
	}

}