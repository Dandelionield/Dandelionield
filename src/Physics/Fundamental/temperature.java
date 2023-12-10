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
import Taylor.Arithmetic.Parser;
import Taylor.Arithmetic.function;

public class temperature{

	/*private final double Scalar;
	private String Unity;
	private long nth;
	private function equivalent;
	
	public temperature(double Scalar){
		
		this.Scalar = Scalar;
		this.Unity = "u";
		this.nth = 1;
		this.equivalent = new function('u', "0");
		
	}
	
	public temperature(double Scalar, String Unity){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new function(this.Unity.charAt(0), "0");
		
	}
	
	public temperature(double Scalar, String Unity, double CelsiusEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new function(this.Unity.charAt(0), this.Unity.charAt(0)+"*"+CelsiusEquivalent);
		
	}
	
	public temperature(double Scalar, String Unity, BigDecimal CelsiusEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new function(this.Unity.charAt(0), this.Unity.charAt(0)+"*"+CelsiusEquivalent);
		
	}
	
	public temperature(double Scalar, String Unity, function CelsiusEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = CelsiusEquivalent;
		
	}
	
	private temperature(double Scalar, String Unity, long nth, function CelsiusEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = nth;
		this.equivalent = CelsiusEquivalent;
		
	}
	
	private temperature(temperature t, int limite){
		
		this.Scalar = Mayth.Redondear(t.Scalar, limite);
		this.Unity = t.Unity;
		this.nth = t.nth;
		this.equivalent = t.equivalent;
		
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
		this.equivalent = new function(this.Unity.charAt(0), "0");
		
	}
	
	public function getCelsiusEquivalent(){
		
		return this.equivalent;
		
	}
	
	public void setCelsiusEquivalent(double equivalent){
		
		this.equivalent = new function(this.Unity.charAt(0), this.Unity.charAt(0)+"*"+equivalent);
		
	}
	
	public void setCelsiusEquivalent(BigDecimal equivalent){
		
		this.equivalent = new function(this.Unity.charAt(0), this.Unity.charAt(0)+"*"+equivalent);
		
	}
	
	public void setCelsiusEquivalent(function equivalent){
		
		this.equivalent = equivalent;
		
	}
	
	public String toString(){
		
		return Scalar+"°"+Unity;
		
	}
	
	public temperature toCelsius(){
		
		String bup = "";
		
		if (this.nth!=1){
			
			bup = "^"+this.nth;
			
		}
		
		return new temperature(this.equivalent.get(this.Scalar).setScale(15, RoundingMode.HALF_UP).doubleValue(), "C"+bup, this.nth, new BigDecimal(1));
		
	}
	
	public temperature doScalar(double s){
		
		return new mass(this.Scalar*s, this.Unity, this.nth, this.equivalent);
		
	}
	
	public temperature doSuma(temperature m){
		
		if (this.equivalent.isConstant()==true && this.equivalent.get(1).compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add degrees due to variable "+this.toString()+" has no equivalency in Celsius degree");
			
		}else if (m.equivalent.isConstant()==true && m.equivalent.get(1).compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add degrees due to variable "+m.toString()+" has no equivalency in Celsius degree");
			
		}
		
		throwAdditionSubtractionError(this.nth, m.nth, (byte) 1);
		
		double nthGramValue = m.toGram().getScalar();
		
		return new mass(new BigDecimal(this.Scalar).add(new BigDecimal(nthGramValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), this.Unity, this.nth, this.equivalent);	
		
	}
	
	public mass doResta(mass m){
		
		if (this.equivalent.isConstant()==true && this.equivalent.get(1).compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract degrees due to variable "+this.toString()+" has no equivalency in Celsius degree");
			
		}else if (m.equivalent.isConstant()==true && m.equivalent.get(1).compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract degrees due to variable "+m.toString()+" has no equivalency in Celsius degree");
			
		}
		
		throwAdditionSubtractionError(this.nth, m.nth, (byte) -1);
		
		double nthGramValue = m.toGram().getScalar();
		
		return new mass(new BigDecimal(this.Scalar).subtract(new BigDecimal(nthGramValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), this.Unity, this.nth, this.equivalent);	
		
	}
	
	public mass arcMass(){
		
		return new mass(1.00/this.Scalar, this.Unity, this.nth, this.equivalent);
		
	}
	
	public mass doPotencia(long n){
		
		if (n==0){
			
			return this.doScalar(1.00/this.Scalar);
			
		}else if (n==1){
			
			return this;
			
		}else if (n<0){
			
			return this.doPotencia(-n).arcMass();
			
		}
		
		return this.doProduct(this.doPotencia(n-1));
		
	}
	
	public mass doProduct(mass m){
		
		if (this.equivalent.isConstant()==true && this.equivalent.get(1).compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply degrees due to variable "+this.toString()+" has no equivalency in Celsius degree");
			
		}else if (m.equivalent.isConstant()==true && m.equivalent.get(1).compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply degrees due to variable "+m.toString()+" has no equivalency in Celsius degree");
			
		}
		
		int s = 1;
		
		if (m.Scalar<0){
			
			s = -s;
			
		}
		
		double nthMeterValue;
		BigDecimal Equivalencia;
		BigDecimal newEquivalence;
		BigDecimal MeterValue;
		BigDecimal powNonMeterValue;
		BigDecimal backUpEquivalencia;
		
		if (this.nth==m.nth){
			
			nthMeterValue = m.toGram().getScalar();
			
			return new mass(new BigDecimal(this.Scalar).multiply(new BigDecimal(nthMeterValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, 1), this.nth+m.nth, this.equivalent.multiply(this.equivalent));
			
			
		}else if (this.nth>m.nth){
			
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
		
			newEquivalence = this.equivalent;
			
			nthMeterValue = m.toGram().getMagnitude();
			
			MeterValue = new BigDecimal(Math.pow(nthMeterValue, 1.00/m.nth));
			
			powNonMeterValue = MeterValue.divide(Equivalencia, MathContext.DECIMAL128);
			
			for (long i=2; i<=Mayth.abs(m.nth); i++){
				
				newEquivalence = newEquivalence.multiply(Equivalencia);
				
				powNonMeterValue = powNonMeterValue.multiply(powNonMeterValue);
				
			}
			
			newEquivalence = newEquivalence.multiply(Equivalencia);
			
			return new mass(new BigDecimal(s*this.Scalar).multiply(powNonMeterValue).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, 1), this.nth+m.nth, newEquivalence);
			
		}else{
		
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
			
			backUpEquivalencia = Equivalencia;
			
			newEquivalence = this.equivalent;
			
			nthMeterValue = m.toGram().getScalar();
			
			for(long i=2; i<=Mayth.abs(m.nth); i++){
				
				newEquivalence = newEquivalence.multiply(backUpEquivalencia);
				
				Equivalencia = Equivalencia.multiply(backUpEquivalencia);
				
			}
			
			newEquivalence = newEquivalence.multiply(backUpEquivalencia);
			
			return new mass(new BigDecimal(this.Scalar).multiply(new BigDecimal(nthMeterValue).divide(Equivalencia, MathContext.DECIMAL128)).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, 1), this.nth+m.nth, newEquivalence);
			
		}
		
	}
	
	public mass doDivide(mass m){
		
		if (this.equivalent.isConstant()==true && this.equivalent.get(1).compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to divide degrees due to variable "+this.toString()+" has no equivalency in Celsius degree");
			
		}else if (m.equivalent.isConstant()==true && m.equivalent.get(1).compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to divide degrees due to variable "+m.toString()+" has no equivalency in Celsius degree");
			
		}
		
		int s = 1;
		
		if (m.Scalar<0){
			
			s = -s;
			
		}
		
		double nthSecondValue;
		BigDecimal Equivalencia;
		BigDecimal newEquivalence;
		BigDecimal SecondValue;
		BigDecimal powNonSecondValue;
		BigDecimal backUpEquivalencia;
		
		if (this.nth==m.nth){
			
			nthSecondValue = m.toGram().getScalar();
			
			return new mass(new BigDecimal(this.Scalar).divide(new BigDecimal(nthSecondValue).divide(this.equivalent, MathContext.DECIMAL128), MathContext.DECIMAL128).setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, -1), this.nth-m.nth, this.equivalent.multiply(this.equivalent));
			
			
		}else if (this.nth>m.nth){
			
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
		
			newEquivalence = this.equivalent;
			
			nthSecondValue = m.toGram().getMagnitude();
			
			SecondValue = new BigDecimal(Math.pow(nthSecondValue, 1.00/m.nth));
			
			powNonSecondValue = SecondValue.divide(Equivalencia, MathContext.DECIMAL128);
			
			for (long i=2; i<=Mayth.abs(m.nth); i++){
				
				newEquivalence = newEquivalence.multiply(Equivalencia);
				
				powNonSecondValue = powNonSecondValue.multiply(powNonSecondValue);
				
			}
			
			newEquivalence = newEquivalence.multiply(Equivalencia);
			
			return new mass(new BigDecimal(s*this.Scalar).divide(powNonSecondValue, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, -1), this.nth-m.nth, newEquivalence);
			
		}else{
		
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
			
			backUpEquivalencia = Equivalencia;
			
			newEquivalence = this.equivalent;
			
			nthSecondValue = m.toGram().getScalar();
			
			for(long i=2; i<=Mayth.abs(m.nth); i++){
				
				newEquivalence = newEquivalence.multiply(backUpEquivalencia);
				
				Equivalencia = Equivalencia.multiply(backUpEquivalencia);
				
			}
			
			newEquivalence = newEquivalence.multiply(backUpEquivalencia);
			
			return new mass(new BigDecimal(this.Scalar).divide(new BigDecimal(nthSecondValue).divide(Equivalencia, MathContext.DECIMAL128), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, -1), this.nth-m.nth, newEquivalence);
			
		}
		
	}
	
	public mass doRedondear(int limite){
		
		return new mass(this, limite);
		
	}
	
	private String getNewUnity(long a, long b, int s){
		
		
		try{
			
			if (this.Unity.contains("^")){
				
				return this.Unity.substring(0, this.Unity.indexOf('^'))+"^"+(a+s*b);
				
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
			
		}
		
		if (U.contains("^")){
		
			if (("^").equals(U.charAt(0)+"")){
				
				if (U.length()==1){
					
					return "u";
					
				}else{
					
					try{
						
						if (Long.parseLong(U.substring(U.indexOf('^')+1))==1){
							
							return "u";
							
						}else{
							
							return "u^"+Long.parseLong(U.substring(U.indexOf('^')+1));
							
						}
						
					}catch(Exception e){
						
						return "u";
						
					}
					
				}
				
			}else{
				
				try{
					
					if (Long.parseLong(U.substring(U.indexOf('^')+1))==1){
						
						return U.substring(0, U.indexOf('^'));
						
					}
					
				}catch(Exception e){
					
					return U.substring(0, U.indexOf('^'));
					
				}
				
			}
			
		}
		
		return U;
		
	}
	
	private long getnthPower(String U){
		
		if (U.contains("^")){
			
			return Long.parseLong(U.substring(U.indexOf('^')+1));
			
		}else{
			
			return 1;
			
		}
		
	}
	
	private void throwAdditionSubtractionError(long a, long b, byte n){
		
		String c = "", d = "", h= "";
		
		if (n<1){
			
			h = "subtract";
			
		}else{
			
			h = "add";
			
		}
		
		if (a!=b){
		
			if (a==0){
				
				c = " scalar";
				
			}else{
				
				c = " temperature";
				
			}
			
			if (b==0){
				
				d = " scalar";
				
			}else{
				
				d = " temperature";
				
			}
			
			throwError("unabled to "+h+" a"+d+" to a"+c);
			
		}
		
	}
	
	private void throwError(String ErrorMessage){
		
		try {

            throw new RuntimeException(ErrorMessage);
			
        } catch (RuntimeException e) {

            e.printStackTrace();
			
			System.exit(0);
			
        }
		
	}//*/
	
}