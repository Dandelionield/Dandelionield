package Physics.Fundamental;

/*
 *
 * @author Dandelion
 * 
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

import Taylor.Math.Mayth;

public class distance{
	
	private final double Scalar;
	private String Unity;
	private BigDecimal equivalent; 
	
	public distance(double Scalar){
		
		this.Scalar = Scalar;
		this.Unity = "u";
		this.equivalent = new BigDecimal(0);
		
	}
	
	public distance(double Scalar, String Unity){
		
		this.Scalar = Scalar;
		this.Unity = Unity.toLowerCase();
		this.equivalent = new BigDecimal(0);
		
	}
	
	public distance(double Scalar, String Unity, double MeterEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = Unity.toLowerCase();
		this.equivalent = new BigDecimal(MeterEquivalent);
		
	}
	
	public distance(double Scalar, String Unity, BigDecimal MeterEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = Unity.toLowerCase();
		this.equivalent = MeterEquivalent;
		
	}
	
	private distance(distance x, int limite){
		
		this.Scalar = Mayth.Redondear(x.Scalar, limite);
		this.Unity = x.Unity;
		this.equivalent = x.equivalent;
		
	}
	
	public double getScalar(){
		
		return this.Scalar;
		
	}
	
	public String getUnity(){
		
		return this.Unity;
		
	}
	
	public void setUnity(String Unity){
		
		this.Unity = Unity;
		this.equivalent = new BigDecimal(0);
		
	}
	
	public String toString(){
		
		return Scalar+" "+Unity;
		
	}
	
	public distance doScalar(double s){
		
		return new distance(this.Scalar*s, this.Unity);
		
	}
	
	public distance doRedondear(int limite){
		
		return new distance(this, limite);
		
	}

}