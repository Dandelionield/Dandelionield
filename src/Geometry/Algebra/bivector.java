package Geometry.Algebra;

/*
 *
 * @author Dandelion
 * 
 */

import Geometry.Euclidean.vector;
import Geometry.Euclidean.degree;

public class bivector{
	
	private final double Magnitude;
	private final vector a;
	private final vector b;
	private final degree Theta;
	
	public bivector(vector a, vector b){
		
		this.a = a;
		this.b = b;
		Theta = a.doBetweenTwo(b);
		Magnitude = a.getMagnitude()*a.getMagnitude()*Theta.getSen();
		
	}
	
	public bivector(vector[] v){
		
		this.a = v[0];
		this.b = v[1];
		Theta = a.doBetweenTwo(b);
		Magnitude = a.getMagnitude()*a.getMagnitude()*Theta.getSen();
		
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
		
		return a+" ∧ "+b;
		
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

}