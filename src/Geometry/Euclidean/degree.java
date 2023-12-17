package Geometry.Euclidean;

/*
 *
 * @author Dandelion
 * 
 */

import Taylor.Math.Mayth;

public class degree{
	
	private final double Pi = Mayth.PI.doubleValue();
	
	private final double Degree;
	private final double Radian;
	private final double Gradian;
	
	private final double Sen;
	private final double Cos;
	private final double Tan;
	private final double Csc;
	private final double Sec;
	private final double Cot;
	
	private final Mayth RAD = new Mayth(false);

	public degree(double deg){
		
		Degree = fixAngle(deg,360);
		Radian = (Degree*Pi)/180.00;
		Gradian = (Degree*20.00)/18.00;
		
		Sen = Math.sin(Radian);
		Cos = Math.cos(Radian);
		Tan = Math.tan(Radian);
		Csc = 1/Sen;
		Sec = 1/Cos;
		Cot = 1/Tan;
		
	}
	
	public degree(double RadGrad, boolean bl){
		
		if (bl==true){
			
			Radian = fixAngle(RadGrad,2*Pi);
			Degree = (Radian*180)/Pi;
			Gradian = (Radian*200.00)/Pi;
			
		}else{
			
			Gradian = fixAngle(RadGrad,400);
			Degree = (Gradian*18.00)/20.00;
			Radian = (Gradian*Pi)/200.00;
			
		}
		
		Sen = Math.sin(Radian);
		Cos = Math.cos(Radian);
		Tan = Math.tan(Radian);
		Csc = 1/Sen;
		Sec = 1/Cos;
		Cot = 1/Tan;
		
	}
	
	public degree(double ca, double co, double h){
		
		double bup = 0;
		
		ca = RAD.abs(ca);
		co = RAD.abs(co);
		h = RAD.abs(h);
		
		if (ca!=0 && co!=0 && h!=0){
		
			Sen = co/h;
			Cos = ca/h;
			Tan = co/ca;
			Csc = h/co;
			Sec = h/ca;
			Cot = ca/co;
			
			bup = Math.toDegrees(Math.asin(Sen));
			
		}else if (ca!=0 && co!=0 && h==0){
			
			Tan = co/ca;
			
			bup = Math.toDegrees(Math.atan(Tan));
			
			Sen = Math.sin(Math.toRadians(bup));
			Cos = Math.cos(Math.toRadians(bup));
			Csc = 1/Sen;
			Sec = 1/Cos;
			Cot = 1/Tan;
			
		}else if (ca!=0 && co==0 && h!=0){
			
			Cos = ca/h;
			
			bup = Math.toDegrees(Math.acos(Cos));
			
			Sen = Math.sin(Math.toRadians(bup));
			Tan = Math.tan(Math.toRadians(bup));
			Csc = 1/Sen;
			Sec = 1/Cos;
			Cot = 1/Tan;
			
		}else if (ca==0 && co!=0 && h!=0){
			
			Sen = ca/h;
			
			bup = Math.toDegrees(Math.asin(Sen));
			
			Cos = Math.cos(Math.toRadians(bup));
			Tan = Math.tan(Math.toRadians(bup));
			Csc = 1/Sen;
			Sec = 1/Cos;
			Cot = 1/Tan;
			
		}else{
			
			Sen = Double.NaN;
			Cos = Double.NaN;
			Tan = Double.NaN;
			Csc = Double.NaN;
			Sec = Double.NaN;
			Cot = Double.NaN;
			
		}
		
		if (Double.isNaN(Cos)==false){
			
			Degree = bup;
			Radian = (Degree*Pi)/180.00;
			Gradian = (Degree*20.00)/18.00;
			
		}else{
			
			Degree = Double.NaN;
			Radian = Double.NaN;
			Gradian = Double.NaN;
			
		}
		
	}
	
	private degree(degree Alpha, int limite){
		
		Degree = Mayth.Redondear(Alpha.Degree, limite);
		Radian = Mayth.Redondear(Alpha.Radian, limite);
		Gradian = Mayth.Redondear(Alpha.Gradian, limite);
		
		Sen = Mayth.Redondear(Alpha.Sen, limite);
		Cos = Mayth.Redondear(Alpha.Cos, limite);
		Tan = Mayth.Redondear(Alpha.Tan, limite);
		Csc = Mayth.Redondear(Alpha.Csc, limite);
		Sec = Mayth.Redondear(Alpha.Sec, limite);
		Cot = Mayth.Redondear(Alpha.Cot, limite);
		
	}
	
	public double getDegree(){
		
		return Degree;
		
	}
	
	public double getRadian(){
		
		return Radian;
		
	}
	
	public double getGradian(){
		
		return Gradian;
		
	}
	
	public double getSen(){
		
		return Sen;
		
	}
	
	public double getCos(){
		
		return Cos;
		
	}
	
	public double getTan(){
		
		return Tan;
		
	}
	
	public double getCsc(){
		
		return Csc;
		
	}
	
	public double getSec(){
		
		return Sec;
		
	}
	
	public double getCot(){
		
		return Cot;
		
	}
	
	public String toString(){
		
		return Degree+"°";
		
	}
	
	public degree doSuma(degree Theta){
		
		return new degree(this.Degree + Theta.Degree);
		
	}
	
	public degree doResta(degree Theta){
		
		return new degree(this.Degree - Theta.Degree);
		
	}
	
	public degree doScalar(double s){
		
		return new degree(s*this.Degree);
		
	}
	
	public degree getDirectingAngle(degree Gamma){
		
		return new degree(Math.toDegrees(Math.acos(Math.sqrt(1-RAD.Potencia(this.Cos)-RAD.Potencia(Gamma.Cos)))));
		
	}
	
	public degree doRedondear(int limite){
		
		return new degree(this, limite);
		
	}
	
	private double fixAngle(double angulo, double limite){
		
		do{
			
			if (angulo<0){
				
				angulo = limite+angulo;
				
			}else if (angulo>limite){
				
				angulo = angulo-limite;
				
			}
			
		}while(angulo<0 || angulo>limite);
		
		return angulo;
		
	}

}