package Geometry.Euclidean;

/*
 *
 * @author Dandelion
 * 
 */

import Taylor.Math.Mayth;

public class degree{
	
	private final double Pi = Mayth.PI;
	
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
		
		RAD.setMode(true);
		
		Degree = fixAngle(deg,360);
		Radian = (Degree*Pi)/180.00;
		Gradian = (Degree*20.00)/18.00;
		
		Sen = Mayth.Sen(Radian);
		Cos = Mayth.Cos(Radian);
		Tan = Mayth.Tan(Radian);
		Csc = 1/Sen;
		Sec = 1/Cos;
		Cot = 1/Tan;
		
		RAD.setMode(false);
		
	}
	
	public degree(double RadGrad, boolean bl){
		
		RAD.setMode(true);
		
		if (bl==true){
			
			Radian = fixAngle(RadGrad,2*Pi);
			Degree = (Radian*180)/Pi;
			Gradian = (Radian*200.00)/Pi;
			
		}else{
			
			Gradian = fixAngle(RadGrad,400);
			Degree = (Gradian*18.00)/20.00;
			Radian = (Gradian*Pi)/200.00;
			
		}
		
		Sen = Mayth.Sen(Radian);
		Cos = Mayth.Cos(Radian);
		Tan = Mayth.Tan(Radian);
		Csc = 1/Sen;
		Sec = 1/Cos;
		Cot = 1/Tan;
		
		RAD.setMode(false);
		
	}
	
	public degree(double ca, double co, double h){
		
		double bup = 0;
		
		ca = Math.abs(ca);
		co = Math.abs(co);
		h = Math.abs(h);
		
		if (ca!=0 && co!=0 && h!=0){
		
			Sen = co/h;
			Cos = ca/h;
			Tan = co/ca;
			Csc = h/co;
			Sec = h/ca;
			Cot = ca/co;
			
			bup = Mayth.Arcsen(Sen);
			
		}else if (ca!=0 && co!=0 && h==0){
			
			Tan = co/ca;
			
			bup = Mayth.Arctan(Tan);
			
			Sen = Mayth.Sen(bup);
			Cos = Mayth.Cos(bup);
			Csc = 1/Sen;
			Sec = 1/Cos;
			Cot = 1/Tan;
			
		}else if (ca!=0 && co==0 && h!=0){
			
			Cos = ca/h;
			
			bup = Mayth.Arccos(Cos);
			
			Sen = Mayth.Sen(bup);
			Tan = Mayth.Tan(bup);
			Csc = 1/Sen;
			Sec = 1/Cos;
			Cot = 1/Tan;
			
		}else if (ca==0 && co!=0 && h!=0){
			
			Sen = ca/h;
			
			bup = Mayth.Arcsen(Sen);
			
			Cos = Mayth.Cos(bup);
			Tan = Mayth.Tan(bup);
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
		
		return new degree(Mayth.Arccos(Math.sqrt(1-Mayth.Potencia(this.Cos)-Mayth.Potencia(Gamma.Cos))));
		
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