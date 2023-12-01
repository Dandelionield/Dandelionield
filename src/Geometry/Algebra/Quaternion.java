package Geometry.Algebra;

/*
 *
 * @author Dandelion
 * 
 */

import Geometry.Euclidean.Matriz;
import Geometry.Euclidean.vector;
import Geometry.Euclidean.degree;
import Taylor.Math.Mayth;

public class Quaternion{

	private final vector v;
	private final double a;
	
	private final double Magnitude;
	private final degree Phi;
	private final boolean Conjugated;
	
	public Quaternion(double a, vector v){
		
		this.a = a;
		this.v = v;
		this.Magnitude = Math.sqrt(a*a+Mayth.Potencia(v.getMagnitude()));
		this.Phi = new degree(Math.acos(a/Magnitude), true);
		this.Conjugated = false;
		
	}
	
	private Quaternion(Quaternion q, int limite){
		
		this.a = Mayth.Redondear(q.a, limite);
		this.v = q.v.doRedondear(limite);
		this.Magnitude = Mayth.Redondear(q.Magnitude, limite);
		this.Phi = q.Phi.doRedondear(limite);
		this.Conjugated = q.Conjugated;
		
	}
	
	private Quaternion(Quaternion q, boolean value){
		
		this.Conjugated = value;
		
		this.a = q.a;
		this.v = q.v.doScalar(-1);
		this.Magnitude = q.Magnitude;
		this.Phi = q.Phi;
		
	}
	
	public vector getVector(){
		
		return this.v;
		
	}
	
	public double getScalar(){
		
		return this.a;
		
	}
	
	public double getMagnitude(){
		
		return this.Magnitude;
		
	}
	
	public degree getPhi(){
		
		return this.Phi;
		
	}
	
	public Quaternion getUnitary(){
		
		return this.doScalar(1/this.Magnitude);
		
	}
	
	public String toString(){
		
		if (this.Conjugated==false){
			
			return "{"+a+" + "+v+"}";
			
		}else{
			
			return "{"+a+" - "+v.doScalar(-1)+"}";
			
		}
		
	}
	
	public double doScalar(Quaternion p){
		
		return this.a*p.a+this.v.doScalar(p.v);
		
	}
	
	public Quaternion doScalar(double s){
		
		return new Quaternion(a*s, v.doScalar(s));
		
	}
	
	public Quaternion doSuma(Quaternion p){
		
		return new Quaternion(this.a+p.a, this.v.doSuma(p.v));
		
	}
	
	public Quaternion doResta(Quaternion p){
		
		return new Quaternion(this.a-p.a, this.v.doResta(p.v));
		
	}
	
	public vector doCross(Quaternion p){
		
		return (this.doHamilton(p).doResta(p.doHamilton(this))).doScalar(1.00/2.00).v;
		
	}
	
	public Quaternion doHamilton(Quaternion p){
		
		return new Quaternion(this.a*p.a-this.v.doScalar(p.v), p.v.doScalar(this.a).doSuma(this.v.doScalar(p.a)).doSuma(this.v.doCross(p.v)));
		
	}
	
	public Quaternion doConjugate(){
		
		return new Quaternion(this, true);
		
	}
	
	public Quaternion arcQuaternion(){
		
		return this.doConjugate().doScalar(1/Mayth.Potencia(this.Magnitude));
		
	}
	
	public Quaternion doPotencia(double x){
		
		double p = Mayth.Potencia(this.Magnitude, x);
		
		return new Quaternion(p*Phi.doScalar(x).getCos(), this.v.getUnitary().doScalar(p*Phi.doScalar(x).getSen()));
		
	}
	
	public Quaternion doRaiz(){
		
		return new Quaternion(Math.sqrt((this.Magnitude+a)/2), this.v.getUnitary().doScalar(Math.sqrt((this.Magnitude-a)/2)));
		
	}
	
	public Quaternion doEuler(){
		
		double e = Mayth.Euler(this.a); 
		
		return new Quaternion(e*Mayth.Cos(this.v.getMagnitude()), this.v.getUnitary().doScalar(e*Mayth.Sen(this.v.getMagnitude())));
		
	}
	
	public Matriz toMatriz(){
		
		return new Matriz(new double[][] {
			
			{a, -v.getComponentX(), -v.getComponentY(), -v.getComponentZ()},
			{v.getComponentX(), a, -v.getComponentZ(), v.getComponentY()},
			{v.getComponentY(), v.getComponentZ(), a, -v.getComponentX()},
			{v.getComponentZ(), -v.getComponentY(), v.getComponentX(), a}
			
		});
		
	}
	
	public Quaternion doRedondear(int limite){
		
		return new Quaternion(this, limite);
		
	}

}