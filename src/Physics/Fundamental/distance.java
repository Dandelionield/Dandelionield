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
	private long nth;
	private BigDecimal equivalent;
	
	public distance(double Scalar){
		
		this.Scalar = Scalar;
		this.Unity = "u";
		this.nth = 1;
		this.equivalent = new BigDecimal(0);
		
	}
	
	public distance(double Scalar, String Unity){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new BigDecimal(0);
		
	}
	
	public distance(double Scalar, String Unity, double MeterEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new BigDecimal(MeterEquivalent);
		
	}
	
	public distance(double Scalar, String Unity, BigDecimal MeterEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = MeterEquivalent;
		
	}
	
	private distance(double Scalar, String Unity, long nth, BigDecimal MeterEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = Unity;
		this.nth = nth;
		this.equivalent = MeterEquivalent;
		
	}
	
	private distance(distance x, int limite){
		
		this.Scalar = Mayth.Redondear(x.Scalar, limite);
		this.Unity = x.Unity;
		this.nth = x.nth;
		this.equivalent = x.equivalent;
		
	}
	
	public double getScalar(){
		
		return this.Scalar;
		
	}
	
	public double getMagnitude(){
		
		return Mayth.abs(this.Scalar);
		
	}
	
	public String getUnity(){
		
		return this.Unity;
		
	}
	
	public void setUnity(String Unity){
		
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new BigDecimal(0);
		
	}
	
	public BigDecimal getMeterEquivalent(){
		
		return this.equivalent;
		
	}
	
	public void setMeterEquivalent(double equivalent){
		
		this.equivalent = new BigDecimal(equivalent);
		
	}
	
	public void setMeterEquivalent(BigDecimal equivalent){
		
		this.equivalent = equivalent;
		
	}
	
	public String toString(){
		
		return Scalar+" "+Unity;
		
	}
	
	public distance toMeter(){
		
		String bup = "";
		
		if (this.nth!=1){
			
			bup = "^"+this.nth;
			
		}
		
		return new distance(new BigDecimal(this.Scalar).multiply(this.equivalent).setScale(15, RoundingMode.HALF_UP).doubleValue(), "m"+bup, this.nth, new BigDecimal(1));
		
	}
	
	public distance doScalar(double s){
		
		return new distance(this.Scalar*s, this.Unity, this.nth, this.equivalent);
		
	}
	
	public distance doProduct(distance x){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0 || x.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply distances due to variable has no equivalency in meters");
			
		}
		
		int s = 1;
		
		if (x.Scalar<0){
			
			s = -s;
			
		}
		
		double nthMeterValue;
		BigDecimal Equivalencia;
		BigDecimal newEquivalence;
		BigDecimal MeterValue;
		BigDecimal powNonMeterValue;
		BigDecimal backUpEquivalencia;
		
		if (this.nth==x.nth){
			
			nthMeterValue = x.toMeter().getScalar();
			
			return new distance(new BigDecimal(this.Scalar).multiply(new BigDecimal(nthMeterValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, x.nth), this.nth+x.nth, this.equivalent.multiply(this.equivalent));
			
			
		}else if (this.nth>x.nth){
			
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
		
			newEquivalence = this.equivalent;
			
			nthMeterValue = x.toMeter().getMagnitude();
			
			MeterValue = new BigDecimal(Math.pow(nthMeterValue, 1.00/x.nth));
			
			powNonMeterValue = MeterValue.divide(Equivalencia, MathContext.DECIMAL128);
			
			for (long i=2; i<=Mayth.abs(x.nth); i++){
				
				newEquivalence = newEquivalence.multiply(Equivalencia);
				
				powNonMeterValue = powNonMeterValue.multiply(powNonMeterValue);
				
			}
			
			newEquivalence = newEquivalence.multiply(Equivalencia);
			
			return new distance(new BigDecimal(s*this.Scalar).multiply(powNonMeterValue).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, x.nth), this.nth+x.nth, newEquivalence);
			
		}else{
		
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
			
			backUpEquivalencia = Equivalencia;
			
			newEquivalence = this.equivalent;
			
			nthMeterValue = x.toMeter().getMagnitude();
			
			for(long i=2; i<=Mayth.abs(x.nth); i++){
				
				newEquivalence = newEquivalence.multiply(Equivalencia);
				
				Equivalencia = Equivalencia.multiply(backUpEquivalencia);
				
			}
			
			newEquivalence = newEquivalence.multiply(Equivalencia);
			
			return new distance(new BigDecimal(s*nthMeterValue).divide(Equivalencia, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, x.nth), this.nth+x.nth, newEquivalence);
			
		}
		
	}
	
	public distance doRedondear(int limite){
		
		return new distance(this, limite);
		
	}
	
	private String getNewUnity(long a, long b){
		
		
		try{
			
			if (this.Unity.contains("^")){
				
				return this.Unity.substring(0, this.Unity.indexOf('^'))+"^"+(a+b);
				
			}else{
				
				return this.Unity+"^"+(a+b);
				
			}
			
		}catch(Exception e){
			
			return "u^"+(a+b);
			
		}
		
	}
	
	private String fixUnity(String U){
		
		if (U==null){
			
			return "u";
			
		}else if (U.equals("")){
			
			return "u";
			
		}else if (("^").equals(U.charAt(0)+"")){
			
			return "u"+U;
			
		}else{
			
			return U;
			
		}
		
	}
	
	private long getnthPower(String U){
		
		if (U.contains("^")){
			
			return Long.parseLong(U.substring(U.indexOf('^')+1));
			
		}else{
			
			return 1;
			
		}
		
	}
	
	private void throwError(String ErrorMessage){
		
		try {

            throw new RuntimeException(ErrorMessage);
			
        } catch (RuntimeException e) {

            e.printStackTrace();
			
			System.exit(0);
			
        }
		
	}
	
}
