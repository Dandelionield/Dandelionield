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

public class Quaternion extends vector{

	private final double a;
	
	private final double Magnitude;
	private final degree Phi;
	private final boolean Conjugated;
	
	private final Mayth Mth = new Mayth();
	
	public Quaternion(double a, vector v){
		
		super(v.get());
		
		this.a = a;
		this.Magnitude = Math.sqrt(a*a+Mth.Potencia(super.getMagnitude()));
		this.Phi = new degree(Math.acos(a/Magnitude), true);
		this.Conjugated = false;
		
	}
	
	private Quaternion(Quaternion q, int limite){
		
		super(q, limite);
		
		this.a = Mayth.Redondear(q.a, limite);
		this.Magnitude = Mayth.Redondear(q.Magnitude, limite);
		this.Phi = q.Phi.doRedondear(limite);
		this.Conjugated = q.Conjugated;
		
	}
	
	private Quaternion(Quaternion q, boolean value){
		
		super(q.doScalar(-1).get());
		
		this.Conjugated = value;
		
		this.a = q.a;
		this.Magnitude = q.Magnitude;
		this.Phi = q.Phi;
		
	}
	
	public int compareTo(Quaternion b){
		
		if (this.Magnitude>b.Magnitude){
			
			return 1;
			
		}else if (this.Magnitude<b.Magnitude){
			
			return -1;
			
		}
		
		return 0;
		
	}
	
	public vector getVector(){
		
		return this;
		
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
			
			return "{"+a+" + "+super.toString()+"}";
			
		}else{
			
			return "{"+a+" - "+super.doScalar(-1).toString()+"}";
			
		}
		
	}
	
	public double doScalar(Quaternion p){
		
		return this.a*p.a + super.doScalar(p);
		
	}
	
	public Quaternion doScalar(double s){
		
		return new Quaternion(a*s, super.doScalar(s));
		
	}
	
	public Quaternion doSuma(Quaternion p){
		
		return new Quaternion(this.a+p.a, super.doSuma(p));
		
	}
	
	public Quaternion doResta(Quaternion p){
		
		return new Quaternion(this.a-p.a, super.doResta(p));
		
	}
	
	public vector doCross(Quaternion p){
		
		return (this.doHamilton(p).doResta(p.doHamilton(this))).doScalar(1.00/2.00);
		
	}
	
	public Quaternion doHamilton(Quaternion p){
		
		return new Quaternion(this.a*p.a - super.doScalar(p), p.getVector().doScalar(this.a).doSuma(super.doScalar(p.a)).doSuma(super.doCross(p)));
		
	}
	
	public Quaternion doConjugate(){
		
		return new Quaternion(this, true);
		
	}
	
	public Quaternion arcQuaternion(){
		
		return this.doConjugate().doScalar(1/Mth.Potencia(this.Magnitude));
		
	}
	
	public Quaternion doPotencia(double x){
		
		double p = Mth.Potencia(this.Magnitude, x);
		
		return new Quaternion(p*Phi.doScalar(x).getCos(), super.getUnitary().doScalar(p*Phi.doScalar(x).getSen()));
		
	}
	
	public Quaternion doRaiz(){
		
		return new Quaternion(Math.sqrt((this.Magnitude+a)/2), super.getUnitary().doScalar(Math.sqrt((this.Magnitude-a)/2)));
		
	}
	
	public Quaternion doEuler(){
		
		double e = Mth.Euler(this.a); 
		
		return new Quaternion(e*Mth.Cos(super.getMagnitude()), super.getUnitary().doScalar(e*Mth.Sen(super.getMagnitude())));
		
	}
	
	public Matriz toMatriz(){
		
		return new Matriz(new double[][] {
			
			{a, -this.getComponentX(), -this.getComponentY(), -this.getComponentZ()},
			{this.getComponentX(), a, -this.getComponentZ(), this.getComponentY()},
			{this.getComponentY(), this.getComponentZ(), a, -this.getComponentX()},
			{this.getComponentZ(), -this.getComponentY(), this.getComponentX(), a}
			
		});
		
	}
	
	public Quaternion doRedondear(int limite){
		
		return new Quaternion(this, limite);
		
	}

}