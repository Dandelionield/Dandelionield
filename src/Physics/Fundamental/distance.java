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
		this.Unity = fixUnity(Unity);
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
	
	public BigDecimal getMetreEquivalent(){
		
		return this.equivalent;
		
	}
	
	public void setMetreEquivalent(double equivalent){
		
		this.equivalent = new BigDecimal(equivalent);
		
	}
	
	public void setMetreEquivalent(BigDecimal equivalent){
		
		this.equivalent = equivalent;
		
	}
	
	public String toString(){
		
		return Scalar+" "+Unity;
		
	}
	
	public distance toMetre(){
		
		String bup = "";
		
		if (this.nth!=1){
			
			bup = "^"+this.nth;
			
		}
		
		return new distance(new BigDecimal(this.Scalar).multiply(this.equivalent).setScale(15, RoundingMode.HALF_UP).doubleValue(), "m"+bup, this.nth, new BigDecimal(1));
		
	}
	
	public distance doScalar(double s){
		
		return new distance(this.Scalar*s, this.Unity, this.nth, this.equivalent);
		
	}
	
	public distance doSuma(distance x){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add distances due to variable "+this.toString()+" has no equivalency in meters");
			
		}else if (x.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add distances due to variable "+x.toString()+" has no equivalency in meters");
			
		}
		
		throwAdditionSubtractionError(this.nth, x.nth, (byte) 1);
		
		double nthMeterValue = x.toMetre().getScalar();
		
		return new distance(new BigDecimal(this.Scalar).add(new BigDecimal(nthMeterValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), this.Unity, this.nth, this.equivalent);	
		
	}
	
	public distance doResta(distance x){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract distances due to variable "+this.toString()+" has no equivalency in meters");
			
		}else if (x.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract distances due to variable "+x.toString()+" has no equivalency in meters");
			
		}
		
		throwAdditionSubtractionError(this.nth, x.nth, (byte) -1);
		
		double nthMeterValue = x.toMetre().getScalar();
		
		return new distance(new BigDecimal(this.Scalar).subtract(new BigDecimal(nthMeterValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), this.Unity, this.nth, this.equivalent);	
		
	}
	
	public distance arcDistance(){
		
		return new distance(1.00/this.Scalar, this.Unity, this.nth, this.equivalent);
		
	}
	
	public distance doPotencia(long n){
		
		if (n==0){
			
			return this.doScalar(1.00/this.Scalar);
			
		}else if (n==1){
			
			return this;
			
		}else if (n<0){
			
			return this.doPotencia(-n).arcDistance();
			
		}
		
		return this.doProduct(this.doPotencia(n-1));
		
	}//*/
	
	public distance doProduct(distance x){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply distances due to variable "+this.toString()+" has no equivalency in meters");
			
		}else if (x.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply distances due to variable "+x.toString()+" has no equivalency in meters");
			
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
			
			nthMeterValue = x.toMetre().getScalar();
			
			return new distance(new BigDecimal(this.Scalar).multiply(new BigDecimal(nthMeterValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, x.nth, 1), this.nth+x.nth, this.equivalent.multiply(this.equivalent));
			
			
		}else if (this.nth>x.nth){
			
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
		
			newEquivalence = this.equivalent;
			
			nthMeterValue = x.toMetre().getMagnitude();
			
			MeterValue = new BigDecimal(Math.pow(nthMeterValue, 1.00/x.nth));
			
			powNonMeterValue = MeterValue.divide(Equivalencia, MathContext.DECIMAL128);
			
			for (long i=2; i<=Mayth.abs(x.nth); i++){
				
				newEquivalence = newEquivalence.multiply(Equivalencia);
				
				powNonMeterValue = powNonMeterValue.multiply(powNonMeterValue);
				
			}
			
			newEquivalence = newEquivalence.multiply(Equivalencia);
			
			return new distance(new BigDecimal(s*this.Scalar).multiply(powNonMeterValue).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, x.nth, 1), this.nth+x.nth, newEquivalence);
			
		}else{
		
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
			
			backUpEquivalencia = Equivalencia;
			
			newEquivalence = this.equivalent;
			
			nthMeterValue = x.toMetre().getScalar();
			
			for(long i=2; i<=Mayth.abs(x.nth); i++){
				
				newEquivalence = newEquivalence.multiply(backUpEquivalencia);
				
				Equivalencia = Equivalencia.multiply(backUpEquivalencia);
				
			}
			
			newEquivalence = newEquivalence.multiply(backUpEquivalencia);
			
			return new distance(new BigDecimal(this.Scalar).multiply(new BigDecimal(nthMeterValue).divide(Equivalencia, MathContext.DECIMAL128)).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, x.nth, 1), this.nth+x.nth, newEquivalence);
			
		}
		
	}
	
	public distance doDivide(distance x){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to divide distances due to variable "+this.toString()+" has no equivalency in meters");
			
		}else if (x.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to divide distances due to variable "+x.toString()+" has no equivalency in meters");
			
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
			
			nthMeterValue = x.toMetre().getScalar();
			
			return new distance(new BigDecimal(this.Scalar).divide(new BigDecimal(nthMeterValue).divide(this.equivalent, MathContext.DECIMAL128), MathContext.DECIMAL128).setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, x.nth, -1), this.nth+x.nth, this.equivalent.multiply(this.equivalent));
			
			
		}else if (this.nth>x.nth){
			
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
		
			newEquivalence = this.equivalent;
			
			nthMeterValue = x.toMetre().getMagnitude();
			
			MeterValue = new BigDecimal(Math.pow(nthMeterValue, 1.00/x.nth));
			
			powNonMeterValue = MeterValue.divide(Equivalencia, MathContext.DECIMAL128);
			
			for (long i=2; i<=Mayth.abs(x.nth); i++){
				
				newEquivalence = newEquivalence.multiply(Equivalencia);
				
				powNonMeterValue = powNonMeterValue.multiply(powNonMeterValue);
				
			}
			
			newEquivalence = newEquivalence.multiply(Equivalencia);
			
			return new distance(new BigDecimal(s*this.Scalar).divide(powNonMeterValue, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, x.nth, -1), this.nth+x.nth, newEquivalence);
			
		}else{
		
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
			
			backUpEquivalencia = Equivalencia;
			
			newEquivalence = this.equivalent;
			
			nthMeterValue = x.toMetre().getScalar();
			
			for(long i=2; i<=Mayth.abs(x.nth); i++){
				
				newEquivalence = newEquivalence.multiply(backUpEquivalencia);
				
				Equivalencia = Equivalencia.multiply(backUpEquivalencia);
				
			}
			
			newEquivalence = newEquivalence.multiply(backUpEquivalencia);
			
			return new distance(new BigDecimal(this.Scalar).divide(new BigDecimal(nthMeterValue).divide(Equivalencia, MathContext.DECIMAL128), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, x.nth, -1), this.nth+x.nth, newEquivalence);
			
		}
		
	}
	
	public static distance getAstronomicUnidValueOf(double value){
		
		return new distance(value, "UA", new BigDecimal(1.495979).multiply(Mayth.bigPotencia(10, 11)));
		
	}
	
	public static distance getLightYearValueOf(double value){
		
		return new distance(value, "ly", new BigDecimal("9460730472580800"));
		
	}
	
	public static distance getParsecValueOf(double value){
		
		return new distance(value, "pc", new BigDecimal(9.46052840488).multiply(Mayth.bigPotencia(3.08568, 16)));
		
	}
	
	public static distance getRonnametreValueOf(double value){
		
		return new distance(value, "Rm", Mayth.bigPotencia(10, 27));
		
	}
	
	public static distance getYottametreValueOf(double value){
		
		return new distance(value, "Ym", Mayth.bigPotencia(10, 24));
		
	}
	
	public static distance getZettametreValueOf(double value){
		
		return new distance(value, "Zm", Mayth.bigPotencia(10, 21));
		
	}
	
	public static distance getExametreValueOf(double value){
		
		return new distance(value, "Em", Mayth.bigPotencia(10, 18));
		
	}
	
	public static distance getPetametreValueOf(double value){
		
		return new distance(value, "Pm", Mayth.bigPotencia(10, 15));
		
	}
	
	public static distance getTerametreValueOf(double value){
		
		return new distance(value, "Tm", Mayth.bigPotencia(10, 12));
		
	}
	
	public static distance getGigametreValueOf(double value){
		
		return new distance(value, "Gm", Mayth.bigPotencia(10, 9));
		
	}
	
	public static distance getMegametreValueOf(double value){
		
		return new distance(value, "Mm", Mayth.bigPotencia(10, 6));
		
	}
	
	public static distance getMirametreValueOf(double value){
		
		return new distance(value, "mam", Mayth.bigPotencia(10, 4));
		
	}
	
	public static distance getKilometreValueOf(double value){
		
		return new distance(value, "km", Mayth.bigPotencia(10, 3));
		
	}
	
	public static distance getHectometreValueOf(double value){
		
		return new distance(value, "hm", Mayth.bigPotencia(10, 2));
		
	}
	
	public static distance getDecametreValueOf(double value){
		
		return new distance(value, "dam", Mayth.bigPotencia(10, 1));
		
	}
	
	public static distance getMetreValueOf(double value){
		
		return new distance(value, "m", Mayth.bigPotencia(10, 0));
		
	}
	
	public static distance getDecimetreValueOf(double value){
		
		return new distance(value, "dm", Mayth.bigPotencia(10, -1));
		
	}
	
	public static distance getCentimetreValueOf(double value){
		
		return new distance(value, "cm", Mayth.bigPotencia(10, -2));
		
	}
	
	public static distance getMilimetreValueOf(double value){
		
		return new distance(value, "mm", Mayth.bigPotencia(10, -3));
		
	}
	
	public static distance getMicrometreValueOf(double value){
		
		return new distance(value, "µm", Mayth.bigPotencia(10, -6));
		
	}
	
	public static distance getNanometreValueOf(double value){
		
		return new distance(value, "nm", Mayth.bigPotencia(10, -9));
		
	}
	
	public static distance getAngstromValueOf(double value){
		
		return new distance(value, "A", Mayth.bigPotencia(10, -10));
		
	}
	
	public static distance getPicometreValueOf(double value){
		
		return new distance(value, "pm", Mayth.bigPotencia(10, -12));
		
	}
	
	public static distance getFemtometreValueOf(double value){
		
		return new distance(value, "fm", Mayth.bigPotencia(10, -15));
		
	}
	
	public static distance getAttometreValueOf(double value){
		
		return new distance(value, "am", Mayth.bigPotencia(10, -18));
		
	}
	
	public static distance getZeptometreValueOf(double value){
		
		return new distance(value, "zm", Mayth.bigPotencia(10, -21));
		
	}
	
	public static distance getYoctometreValueOf(double value){
		
		return new distance(value, "ym", Mayth.bigPotencia(10, -24));
		
	}
	
	public static distance getPlanckLengthValueOf(double value){
		
		return new distance(value, "PL", Mayth.bigPotencia(10, -35));
		
	}
	
	public static distance getIncheValueOf(double value){
		
		return new distance(value, "inch", new BigDecimal(25.4).multiply(Mayth.bigPotencia(10, -3)));
		
	}
	
	public static distance getFeetValueOf(double value){
		
		return new distance(value, "ft", new BigDecimal(304.8).multiply(Mayth.bigPotencia(10, -3)));
		
	}
	
	public static distance getYardValueOf(double value){
		
		return new distance(value, "yd", new BigDecimal(0.9144));
		
	}
	
	public static distance getRodValueOf(double value){
		
		return new distance(value, "rd", new BigDecimal(5.0292));
		
	}
	
	public static distance getChainValueOf(double value){
		
		return new distance(value, "ch", new BigDecimal(20.1168));
		
	}
	
	public static distance getFurlongValueOf(double value){
		
		return new distance(value, "fur", new BigDecimal(201.168));
		
	}
	
	public static distance getLeagueValueOf(double value){
		
		return new distance(value, "lg", new BigDecimal(4.828032).multiply(Mayth.bigPotencia(10, 3)));
		
	}
	
	public static distance getMileValueOf(double value){
		
		return new distance(value, "mi", new BigDecimal(1.609344).multiply(Mayth.bigPotencia(10, 3)));
		
	}
	
	public static distance getLatitudeValueOf(double value){
		
		return new distance(value, "Phi", new BigDecimal(111.0996).multiply(Mayth.bigPotencia(10, 3)));
		
	}
	
	public static distance getNauticLeagueValueOf(double value){
		
		return new distance(value, "nlg", new BigDecimal(5558));
		
	}
	
	public static distance getNauticMileValueOf(double value){
		
		return new distance(value, "nmi", new BigDecimal(1852));
		
	}
	
	public static distance getCableValueOf(double value){
		
		return new distance(value, "cable", new BigDecimal(182.88));
		
	}
	
	public static distance getFathonValueOf(double value){
		
		return new distance(value, "fathom", new BigDecimal(1.8288));
		
	}
	
	public distance doRedondear(int limite){
		
		return new distance(this, limite);
		
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
				
			}else if (Mayth.abs(a)==1){
				
				c = " distance";
				
			}else if (Mayth.abs(a)==2){
				
				c = "n area";
				
			}else if (Mayth.abs(a)==3){
				
				c = " volume";
				
			}else if (Mayth.abs(a)>3){
				
				c = " hypervolume";
				
			}
			
			if (b==0){
				
				d = " scalar";
				
			}else if (Mayth.abs(b)==1){
				
				d = " distance";
				
			}else if (Mayth.abs(b)==2){
				
				d = "n area";
				
			}else if (Mayth.abs(b)==3){
				
				d = " volume";
				
			}else if (Mayth.abs(b)>3){
				
				d = " hypervolume";
				
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
