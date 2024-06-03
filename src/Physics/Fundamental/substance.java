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

public class substance implements Comparable<substance>{
	
	private final double Scalar;
	private String Unity;
	private long nth;
	private BigDecimal equivalent;
	
	private static final Mayth Mth = new Mayth();
	
	public substance(double Scalar){
		
		this.Scalar = Scalar;
		this.Unity = "u";
		this.nth = 1;
		this.equivalent = new BigDecimal(0);
		
	}
	
	public substance(double Scalar, String Unity){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new BigDecimal(0);
		
	}
	
	public substance(double Scalar, String Unity, double UnidEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new BigDecimal(UnidEquivalent);
		
	}
	
	public substance(double Scalar, String Unity, BigDecimal UnidEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = UnidEquivalent;
		
	}
	
	private substance(double Scalar, String Unity, long nth, BigDecimal UnidEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = nth;
		this.equivalent = UnidEquivalent;
		
	}
	
	private substance(substance s, int limite){
		
		this.Scalar = Mayth.Redondear(s.Scalar, limite);
		this.Unity = s.Unity;
		this.nth = s.nth;
		this.equivalent = s.equivalent;
		
	}
	
	public int compareTo(substance b){
		
		if (this.Scalar>b.Scalar){
			
			return 1;
			
		}else if (this.Scalar<b.Scalar){
			
			return -1;
			
		}
		
		return 0;
		
	}
	
	public double getScalar(){
		
		return this.Scalar;
		
	}
	
	public double getMagnitude(){
		
		return Mth.abs(this.Scalar);
		
	}
	
	public String getUnity(){
		
		return this.Unity;
		
	}
	
	public void setUnity(String Unity){
		
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new BigDecimal(0);
		
	}
	
	public BigDecimal getUnidEquivalent(){
		
		return this.equivalent;
		
	}
	
	public void setUnidEquivalent(double equivalent){
		
		this.equivalent = new BigDecimal(equivalent);
		
	}
	
	public void setUnidEquivalent(BigDecimal equivalent){
		
		this.equivalent = equivalent;
		
	}
	
	public long getDegree(){
		
		return this.nth;
		
	}
	
	public String toString(){
		
		return Scalar+" "+Unity;
		
	}
	
	public substance toUnid(){
		
		String bup = "";
		
		if (this.nth!=1){
			
			bup = "^"+this.nth;
			
		}
		
		return new substance(new BigDecimal(this.Scalar).multiply(this.equivalent).setScale(15, RoundingMode.HALF_UP).doubleValue(), "u"+bup, this.nth, new BigDecimal(1));
		
	}
	
	public substance to(substance m){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError(this.toString()+" has no equivalency in unids");
			
		}else if (m.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError(m.toString()+" has no equivalency in unids");
			
		}
		
		double SecondValue = 0;
		BigDecimal newEquivalence = new BigDecimal(1);
		BigDecimal BackUpEquivalence = new BigDecimal(0);
		
		if (this.nth==m.nth){
			
			SecondValue = this.toUnid().Scalar;
			
			return new substance(new BigDecimal(SecondValue).divide(m.equivalent, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), m.Unity, this.nth, m.equivalent);
			
		}else{
			
			if (m.nth!=1){
				
				BackUpEquivalence = new BigDecimal(Math.pow(m.equivalent.setScale(15, RoundingMode.HALF_UP).doubleValue(), 1.00/m.nth));
				
			}else{
				
				BackUpEquivalence = m.equivalent;
				
			}
			
			for (int i=0; i<Mth.abs(this.nth); i++){
				
				newEquivalence = newEquivalence.multiply(BackUpEquivalence);
				
			}
			
			return new substance(new BigDecimal(this.toUnid().Scalar).divide(newEquivalence, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), m.getNewUnity(this.nth, 0, 1), this.nth, newEquivalence);
			
		}
		
	}
	
	public substance doScalar(double s){
		
		return new substance(this.Scalar*s, this.Unity, this.nth, this.equivalent);
		
	}
	
	public substance doSuma(substance m){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add substances due to variable "+this.toString()+" has no equivalency in unids");
			
		}else if (m.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add substances due to variable "+m.toString()+" has no equivalency in unids");
			
		}
		
		throwAdditionSubtractionError(this.nth, m.nth, (byte) 1);
		
		double nthUnidValue = m.toUnid().getScalar();
		
		return new substance(new BigDecimal(this.Scalar).add(new BigDecimal(nthUnidValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), this.Unity, this.nth, this.equivalent);	
		
	}
	
	public substance doResta(substance m){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract substances due to variable "+this.toString()+" has no equivalency in unids");
			
		}else if (m.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract substances due to variable "+m.toString()+" has no equivalency in unids");
			
		}
		
		throwAdditionSubtractionError(this.nth, m.nth, (byte) -1);
		
		double nthGramValue = m.toUnid().getScalar();
		
		return new substance(new BigDecimal(this.Scalar).subtract(new BigDecimal(nthGramValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), this.Unity, this.nth, this.equivalent);	
		
	}
	
	public substance arcSubstance(){
		
		return new substance(1.00/this.Scalar, this.Unity, this.nth, this.equivalent);
		
	}
	
	public substance doPotencia(long n){
		
		if (n==0){
			
			return this.doScalar(1.00/this.Scalar);
			
		}else if (n==1){
			
			return this;
			
		}else if (n<0){
			
			return this.doPotencia(-n).arcSubstance();
			
		}
		
		return this.doProduct(this.doPotencia(n-1));
		
	}
	
	public substance doProduct(substance m){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply substances due to variable "+this.toString()+" has no equivalency in unids");
			
		}else if (m.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply substances due to variable "+m.toString()+" has no equivalency in unids");
			
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
			
			nthMeterValue = m.toUnid().getScalar();
			
			return new substance(new BigDecimal(this.Scalar).multiply(new BigDecimal(nthMeterValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, 1), this.nth+m.nth, this.equivalent.multiply(this.equivalent));
			
			
		}else if (this.nth>m.nth){
			
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
		
			newEquivalence = this.equivalent;
			
			nthMeterValue = m.toUnid().getMagnitude();
			
			MeterValue = new BigDecimal(Math.pow(nthMeterValue, 1.00/m.nth));
			
			powNonMeterValue = MeterValue.divide(Equivalencia, MathContext.DECIMAL128);
			
			for (long i=2; i<=Mth.abs(m.nth); i++){
				
				newEquivalence = newEquivalence.multiply(Equivalencia);
				
				powNonMeterValue = powNonMeterValue.multiply(powNonMeterValue);
				
			}
			
			newEquivalence = newEquivalence.multiply(Equivalencia);
			
			return new substance(new BigDecimal(s*this.Scalar).multiply(powNonMeterValue).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, 1), this.nth+m.nth, newEquivalence);
			
		}else{
		
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
			
			backUpEquivalencia = Equivalencia;
			
			newEquivalence = this.equivalent;
			
			nthMeterValue = m.toUnid().getScalar();
			
			for(long i=2; i<=Mth.abs(m.nth); i++){
				
				newEquivalence = newEquivalence.multiply(backUpEquivalencia);
				
				Equivalencia = Equivalencia.multiply(backUpEquivalencia);
				
			}
			
			newEquivalence = newEquivalence.multiply(backUpEquivalencia);
			
			return new substance(new BigDecimal(this.Scalar).multiply(new BigDecimal(nthMeterValue).divide(Equivalencia, MathContext.DECIMAL128)).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, 1), this.nth+m.nth, newEquivalence);
			
		}
		
	}
	
	public substance doDivide(substance m){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to divide substances due to variable "+this.toString()+" has no equivalency in unids");
			
		}else if (m.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to divide substances due to variable "+m.toString()+" has no equivalency in unids");
			
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
			
			nthSecondValue = m.toUnid().getScalar();
			
			return new substance(new BigDecimal(this.Scalar).divide(new BigDecimal(nthSecondValue).divide(this.equivalent, MathContext.DECIMAL128), MathContext.DECIMAL128).setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, -1), this.nth-m.nth, this.equivalent.multiply(this.equivalent));
			
			
		}else if (this.nth>m.nth){
			
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
		
			newEquivalence = this.equivalent;
			
			nthSecondValue = m.toUnid().getMagnitude();
			
			SecondValue = new BigDecimal(Math.pow(nthSecondValue, 1.00/m.nth));
			
			powNonSecondValue = SecondValue.divide(Equivalencia, MathContext.DECIMAL128);
			
			for (long i=2; i<=Mth.abs(m.nth); i++){
				
				newEquivalence = newEquivalence.multiply(Equivalencia);
				
				powNonSecondValue = powNonSecondValue.multiply(powNonSecondValue);
				
			}
			
			newEquivalence = newEquivalence.multiply(Equivalencia);
			
			return new substance(new BigDecimal(s*this.Scalar).divide(powNonSecondValue, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, -1), this.nth-m.nth, newEquivalence);
			
		}else{
		
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
			
			backUpEquivalencia = Equivalencia;
			
			newEquivalence = this.equivalent;
			
			nthSecondValue = m.toUnid().getScalar();
			
			for(long i=2; i<=Mth.abs(m.nth); i++){
				
				newEquivalence = newEquivalence.multiply(backUpEquivalencia);
				
				Equivalencia = Equivalencia.multiply(backUpEquivalencia);
				
			}
			
			newEquivalence = newEquivalence.multiply(backUpEquivalencia);
			
			return new substance(new BigDecimal(this.Scalar).divide(new BigDecimal(nthSecondValue).divide(Equivalencia, MathContext.DECIMAL128), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, -1), this.nth-m.nth, newEquivalence);
			
		}
		
	}
	
	public static substance getMolValueOf(double value){
		
		return new substance(value, "mol", new BigDecimal(6.02214076).multiply(Mth.bigPotencia(10, 23)));
		
	}
	
	public static substance getPoundMolValueOf(double value){
		
		return new substance(value, "lb-mol", new BigDecimal(453.59237).multiply(new BigDecimal(6.02214076).multiply(Mth.bigPotencia(10, 23))));
		
	}
	
	public substance doRedondear(int limite){
		
		return new substance(this, limite);
		
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
				
				c = " substances";
				
			}
			
			if (b==0){
				
				d = " scalar";
				
			}else{
				
				d = " substances";
				
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
		
	}
	
}