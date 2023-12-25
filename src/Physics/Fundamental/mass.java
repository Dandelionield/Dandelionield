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

public class mass{
	
	private final double Scalar;
	private String Unity;
	private long nth;
	private BigDecimal equivalent;
	
	private static final Mayth Mth = new Mayth();
	
	public mass(double Scalar){
		
		this.Scalar = Scalar;
		this.Unity = "u";
		this.nth = 1;
		this.equivalent = new BigDecimal(0);
		
	}
	
	public mass(double Scalar, String Unity){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new BigDecimal(0);
		
	}
	
	public mass(double Scalar, String Unity, double GramEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new BigDecimal(GramEquivalent);
		
	}
	
	public mass(double Scalar, String Unity, BigDecimal GramEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = GramEquivalent;
		
	}
	
	private mass(double Scalar, String Unity, long nth, BigDecimal GramEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = nth;
		this.equivalent = GramEquivalent;
		
	}
	
	private mass(mass t, int limite){
		
		this.Scalar = Mayth.Redondear(t.Scalar, limite);
		this.Unity = t.Unity;
		this.nth = t.nth;
		this.equivalent = t.equivalent;
		
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
	
	public BigDecimal getGramEquivalent(){
		
		return this.equivalent;
		
	}
	
	public void setGramEquivalent(double equivalent){
		
		this.equivalent = new BigDecimal(equivalent);
		
	}
	
	public void setGramEquivalent(BigDecimal equivalent){
		
		this.equivalent = equivalent;
		
	}
	
	public long getDegree(){
		
		return this.nth;
		
	}
	
	public String toString(){
		
		return Scalar+" "+Unity;
		
	}
	
	public mass toGram(){
		
		String bup = "";
		
		if (this.nth!=1){
			
			bup = "^"+this.nth;
			
		}
		
		return new mass(new BigDecimal(this.Scalar).multiply(this.equivalent).setScale(15, RoundingMode.HALF_UP).doubleValue(), "g"+bup, this.nth, new BigDecimal(1));
		
	}
	
	public mass to(mass m){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError(this.toString()+" has no equivalency in grams");
			
		}else if (m.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError(m.toString()+" has no equivalency in grams");
			
		}
		
		double SecondValue = 0;
		BigDecimal newEquivalence = new BigDecimal(1);
		BigDecimal BackUpEquivalence = new BigDecimal(0);
		
		if (this.nth==m.nth){
			
			SecondValue = this.toGram().Scalar;
			
			return new mass(new BigDecimal(SecondValue).divide(m.equivalent, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), m.Unity, this.nth, m.equivalent);
			
		}else{
			
			if (m.nth!=1){
				
				BackUpEquivalence = new BigDecimal(Math.pow(m.equivalent.setScale(15, RoundingMode.HALF_UP).doubleValue(), 1.00/m.nth));
				
			}else{
				
				BackUpEquivalence = m.equivalent;
				
			}
			
			for (int i=0; i<Mth.abs(this.nth); i++){
				
				newEquivalence = newEquivalence.multiply(BackUpEquivalence);
				
			}
			
			return new mass(new BigDecimal(this.toGram().Scalar).divide(newEquivalence, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), m.getNewUnity(this.nth, 0, 1), this.nth, newEquivalence);
			
		}
		
	}
	
	public mass doScalar(double s){
		
		return new mass(this.Scalar*s, this.Unity, this.nth, this.equivalent);
		
	}
	
	public mass doSuma(mass m){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add masses due to variable "+this.toString()+" has no equivalency in grams");
			
		}else if (m.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add masses due to variable "+m.toString()+" has no equivalency in grams");
			
		}
		
		throwAdditionSubtractionError(this.nth, m.nth, (byte) 1);
		
		double nthGramValue = m.toGram().getScalar();
		
		return new mass(new BigDecimal(this.Scalar).add(new BigDecimal(nthGramValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), this.Unity, this.nth, this.equivalent);	
		
	}
	
	public mass doResta(mass m){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract masses due to variable "+this.toString()+" has no equivalency in grams");
			
		}else if (m.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract masses due to variable "+m.toString()+" has no equivalency in grams");
			
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
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply masses due to variable "+this.toString()+" has no equivalency in grams");
			
		}else if (m.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply masses due to variable "+m.toString()+" has no equivalency in grams");
			
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
			
			for (long i=2; i<=Mth.abs(m.nth); i++){
				
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
			
			for(long i=2; i<=Mth.abs(m.nth); i++){
				
				newEquivalence = newEquivalence.multiply(backUpEquivalencia);
				
				Equivalencia = Equivalencia.multiply(backUpEquivalencia);
				
			}
			
			newEquivalence = newEquivalence.multiply(backUpEquivalencia);
			
			return new mass(new BigDecimal(this.Scalar).multiply(new BigDecimal(nthMeterValue).divide(Equivalencia, MathContext.DECIMAL128)).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, 1), this.nth+m.nth, newEquivalence);
			
		}
		
	}
	
	public mass doDivide(mass m){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to divide masses due to variable "+this.toString()+" has no equivalency in grams");
			
		}else if (m.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to divide masses due to variable "+m.toString()+" has no equivalency in grams");
			
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
			
			for (long i=2; i<=Mth.abs(m.nth); i++){
				
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
			
			for(long i=2; i<=Mth.abs(m.nth); i++){
				
				newEquivalence = newEquivalence.multiply(backUpEquivalencia);
				
				Equivalencia = Equivalencia.multiply(backUpEquivalencia);
				
			}
			
			newEquivalence = newEquivalence.multiply(backUpEquivalencia);
			
			return new mass(new BigDecimal(this.Scalar).divide(new BigDecimal(nthSecondValue).divide(Equivalencia, MathContext.DECIMAL128), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, m.nth, -1), this.nth-m.nth, newEquivalence);
			
		}
		
	}
	
	public static mass getTeragramValueOf(double value){
		
		return new mass(value, "Tg", Mth.bigPotencia(10, 12));
		
	}
	
	public static mass getGigagramValueOf(double value){
		
		return new mass(value, "Gg", Mth.bigPotencia(10, 9));
		
	}
	
	public static mass getTonValueOf(double value){
		
		return new mass(value, "T", Mth.bigPotencia(10, 6));
		
	}
	
	public static mass getQuintalValueOf(double value){
		
		return new mass(value, "q", Mth.bigPotencia(10, 5));
		
	}
	
	public static mass getKilogramValueOf(double value){
		
		return new mass(value, "kg", Mth.bigPotencia(10, 3));
		
	}
	
	public static mass getHectogramValueOf(double value){
		
		return new mass(value, "hg", Mth.bigPotencia(10, 2));
		
	}
	
	public static mass getDecagramValueOf(double value){
		
		return new mass(value, "dag", Mth.bigPotencia(10, 1));
		
	}
	
	public static mass getGramValueOf(double value){
		
		return new mass(value, "g", Mth.bigPotencia(10, 0));
		
	}
	
	public static mass getDecigramValueOf(double value){
		
		return new mass(value, "dg", Mth.bigPotencia(10, -1));
		
	}
	
	public static mass getCentigramValueOf(double value){
		
		return new mass(value, "cg", Mth.bigPotencia(10, -2));
		
	}
	
	public static mass getMilligramValueOf(double value){
		
		return new mass(value, "mg", Mth.bigPotencia(10, -3));
		
	}
	
	public static mass getMicrogramValueOf(double value){
		
		return new mass(value, "µg", Mth.bigPotencia(10, -6));
		
	}
	
	public static mass getNanogramValueOf(double value){
		
		return new mass(value, "ng", Mth.bigPotencia(10, -9));
		
	}
	
	public static mass getPicogramValueOf(double value){
		
		return new mass(value, "pg", Mth.bigPotencia(10, -12));
		
	}
	
	public static mass getFemtogramValueOf(double value){
		
		return new mass(value, "fg", Mth.bigPotencia(10, -15));
		
	}
	
	public static mass getAttogramValueOf(double value){
		
		return new mass(value, "ag", Mth.bigPotencia(10, -18));
		
	}
	
	public static mass getZeptogramValueOf(double value){
		
		return new mass(value, "zg", Mth.bigPotencia(10, -21));
		
	}
	
	public static mass getYoctogramValueOf(double value){
		
		return new mass(value, "yg", Mth.bigPotencia(10, -24));
		
	}
	
	public static mass getGrainValueOf(double value){
		
		return new mass(value, "gr", new BigDecimal(0.064799));
		
	}
	
	public static mass getOunceValueOf(double value){
		
		return new mass(value, "oz", new BigDecimal(28.34952));
		
	}
	
	public static mass getPoundValueOf(double value){
		
		return new mass(value, "lb", new BigDecimal(453.592338));
		
	}
	
	public static mass getStoneValueOf(double value){
		
		return new mass(value, "st", new BigDecimal(14).multiply(new BigDecimal(453.592338)));
		
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
				
				c = " mass";
				
			}
			
			if (b==0){
				
				d = " scalar";
				
			}else{
				
				d = " mass";
				
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