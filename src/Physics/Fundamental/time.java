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

public class time{
	
	private final double Scalar;
	private String Unity;
	private long nth;
	private BigDecimal equivalent;
	
	private static final BigDecimal[] Year = {new BigDecimal(29_030_400), new BigDecimal(365).multiply(new BigDecimal(86400)).add(new BigDecimal(5).multiply(new BigDecimal(3600))).add(new BigDecimal(49).multiply(new BigDecimal(60))).add(new BigDecimal(12))};
	private static byte y = 0;
	
	private static final Mayth Mth = new Mayth();
	
	public time(double Scalar){
		
		this.Scalar = Scalar;
		this.Unity = "u";
		this.nth = 1;
		this.equivalent = new BigDecimal(0);
		
	}
	
	public time(double Scalar, String Unity){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new BigDecimal(0);
		
	}
	
	public time(double Scalar, String Unity, double SecondEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = new BigDecimal(SecondEquivalent);
		
	}
	
	public time(double Scalar, String Unity, BigDecimal SecondEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.equivalent = SecondEquivalent;
		
	}
	
	private time(double Scalar, String Unity, long nth, BigDecimal SecondEquivalent){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = nth;
		this.equivalent = SecondEquivalent;
		
	}
	
	private time(time t, int limite){
		
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
	
	public BigDecimal getSecondEquivalent(){
		
		return this.equivalent;
		
	}
	
	public void setSecondEquivalent(double equivalent){
		
		this.equivalent = new BigDecimal(equivalent);
		
	}
	
	public void setSecondEquivalent(BigDecimal equivalent){
		
		this.equivalent = equivalent;
		
	}
	
	public long getDegree(){
		
		return this.nth;
		
	}
	
	public String toString(){
		
		return Scalar+" "+Unity;
		
	}
	
	public time toSecond(){
		
		String bup = "";
		
		if (this.nth!=1){
			
			bup = "^"+this.nth;
			
		}
		
		return new time(new BigDecimal(this.Scalar).multiply(this.equivalent).setScale(15, RoundingMode.HALF_UP).doubleValue(), "s"+bup, this.nth, new BigDecimal(1));
		
	}
	
	public static void useNormalYear(boolean b){
		
		time.y = (byte) 1;
		
		if (b==true){
			
			time.y = (byte) 0;
			
		}
		
	}
	
	public static void useGregorianYearYear(boolean b){
		
		time.y = (byte) 0;
		
		if (b==true){
			
			time.y = (byte) 1;
			
		}
		
	}
	
	public time doScalar(double s){
		
		return new time(this.Scalar*s, this.Unity, this.nth, this.equivalent);
		
	}
	
	public time doSuma(time t){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add times due to variable "+this.toString()+" has no equivalency in seconds");
			
		}else if (t.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add times due to variable "+t.toString()+" has no equivalency in seconds");
			
		}
		
		throwAdditionSubtractionError(this.nth, t.nth, (byte) 1);
		
		double nthSecondValue = t.toSecond().getScalar();
		
		return new time(new BigDecimal(this.Scalar).add(new BigDecimal(nthSecondValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), this.Unity, this.nth, this.equivalent);	
		
	}
	
	public time doResta(time t){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract times due to variable "+this.toString()+" has no equivalency in seconds");
			
		}else if (t.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract times due to variable "+t.toString()+" has no equivalency in seconds");
			
		}
		
		throwAdditionSubtractionError(this.nth, t.nth, (byte) -1);
		
		double nthSecondValue = t.toSecond().getScalar();
		
		return new time(new BigDecimal(this.Scalar).subtract(new BigDecimal(nthSecondValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), this.Unity, this.nth, this.equivalent);	
		
	}
	
	public time arcTime(){
		
		return new time(1.00/this.Scalar, this.Unity, this.nth, this.equivalent);
		
	}
	
	public time doPotencia(long n){
		
		if (n==0){
			
			return this.doScalar(1.00/this.Scalar);
			
		}else if (n==1){
			
			return this;
			
		}else if (n<0){
			
			return this.doPotencia(-n).arcTime();
			
		}
		
		return this.doProduct(this.doPotencia(n-1));
		
	}
	
	public time doProduct(time t){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply times due to variable "+this.toString()+" has no equivalency in seconds");
			
		}else if (t.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply times due to variable "+t.toString()+" has no equivalency in seconds");
			
		}
		
		int s = 1;
		
		if (t.Scalar<0){
			
			s = -s;
			
		}
		
		double nthMeterValue;
		BigDecimal Equivalencia;
		BigDecimal newEquivalence;
		BigDecimal MeterValue;
		BigDecimal powNonMeterValue;
		BigDecimal backUpEquivalencia;
		
		if (this.nth==t.nth){
			
			nthMeterValue = t.toSecond().getScalar();
			
			return new time(new BigDecimal(this.Scalar).multiply(new BigDecimal(nthMeterValue).divide(this.equivalent, MathContext.DECIMAL128)).setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, t.nth, 1), this.nth+t.nth, this.equivalent.multiply(this.equivalent));
			
			
		}else if (this.nth>t.nth){
			
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
		
			newEquivalence = this.equivalent;
			
			nthMeterValue = t.toSecond().getMagnitude();
			
			MeterValue = new BigDecimal(Math.pow(nthMeterValue, 1.00/t.nth));
			
			powNonMeterValue = MeterValue.divide(Equivalencia, MathContext.DECIMAL128);
			
			for (long i=2; i<=Mth.abs(t.nth); i++){
				
				newEquivalence = newEquivalence.multiply(Equivalencia);
				
				powNonMeterValue = powNonMeterValue.multiply(powNonMeterValue);
				
			}
			
			newEquivalence = newEquivalence.multiply(Equivalencia);
			
			return new time(new BigDecimal(s*this.Scalar).multiply(powNonMeterValue).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, t.nth, 1), this.nth+t.nth, newEquivalence);
			
		}else{
		
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
			
			backUpEquivalencia = Equivalencia;
			
			newEquivalence = this.equivalent;
			
			nthMeterValue = t.toSecond().getScalar();
			
			for(long i=2; i<=Mth.abs(t.nth); i++){
				
				newEquivalence = newEquivalence.multiply(backUpEquivalencia);
				
				Equivalencia = Equivalencia.multiply(backUpEquivalencia);
				
			}
			
			newEquivalence = newEquivalence.multiply(backUpEquivalencia);
			
			return new time(new BigDecimal(this.Scalar).multiply(new BigDecimal(nthMeterValue).divide(Equivalencia, MathContext.DECIMAL128)).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, t.nth, 1), this.nth+t.nth, newEquivalence);
			
		}
		
	}
	
	public time doDivide(time t){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to divide times due to variable "+this.toString()+" has no equivalency in seconds");
			
		}else if (t.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to divide times due to variable "+t.toString()+" has no equivalency in seconds");
			
		}
		
		int s = 1;
		
		if (t.Scalar<0){
			
			s = -s;
			
		}
		
		double nthSecondValue;
		BigDecimal Equivalencia;
		BigDecimal newEquivalence;
		BigDecimal SecondValue;
		BigDecimal powNonSecondValue;
		BigDecimal backUpEquivalencia;
		
		if (this.nth==t.nth){
			
			nthSecondValue = t.toSecond().getScalar();
			
			return new time(new BigDecimal(this.Scalar).divide(new BigDecimal(nthSecondValue).divide(this.equivalent, MathContext.DECIMAL128), MathContext.DECIMAL128).setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, t.nth, -1), this.nth-t.nth, this.equivalent.multiply(this.equivalent));
			
			
		}else if (this.nth>t.nth){
			
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
		
			newEquivalence = this.equivalent;
			
			nthSecondValue = t.toSecond().getMagnitude();
			
			SecondValue = new BigDecimal(Math.pow(nthSecondValue, 1.00/t.nth));
			
			powNonSecondValue = SecondValue.divide(Equivalencia, MathContext.DECIMAL128);
			
			for (long i=2; i<=Mth.abs(t.nth); i++){
				
				newEquivalence = newEquivalence.multiply(Equivalencia);
				
				powNonSecondValue = powNonSecondValue.multiply(powNonSecondValue);
				
			}
			
			newEquivalence = newEquivalence.multiply(Equivalencia);
			
			return new time(new BigDecimal(s*this.Scalar).divide(powNonSecondValue, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, t.nth, -1), this.nth-t.nth, newEquivalence);
			
		}else{
		
			Equivalencia = new BigDecimal(Math.pow(this.equivalent.doubleValue(), 1.00/this.nth));
			
			backUpEquivalencia = Equivalencia;
			
			newEquivalence = this.equivalent;
			
			nthSecondValue = t.toSecond().getScalar();
			
			for(long i=2; i<=Mth.abs(t.nth); i++){
				
				newEquivalence = newEquivalence.multiply(backUpEquivalencia);
				
				Equivalencia = Equivalencia.multiply(backUpEquivalencia);
				
			}
			
			newEquivalence = newEquivalence.multiply(backUpEquivalencia);
			
			return new time(new BigDecimal(this.Scalar).divide(new BigDecimal(nthSecondValue).divide(Equivalencia, MathContext.DECIMAL128), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, t.nth, -1), this.nth-t.nth, newEquivalence);
			
		}
		
	}
	
	public static time getQuettasecondValueOf(double value){
		
		return new time(value, "Qs", Mth.bigPotencia(10, 30));
		
	}
	
	public static time getRonnasecondValueOf(double value){
		
		return new time(value, "Rs", Mth.bigPotencia(10, 27));
		
	}
	
	public static time getYottasecondValueOf(double value){
		
		return new time(value, "Ys", Mth.bigPotencia(10, 24));
		
	}
	
	public static time getZettasecondValueOf(double value){
		
		return new time(value, "Zs", Mth.bigPotencia(10, 21));
		
	}
	
	public static time getExasecondValueOf(double value){
		
		return new time(value, "Xs", Mth.bigPotencia(10, 18));
		
	}
	
	public static time getKalpaValueOf(double value){
		
		return new time(value, "kp", new BigDecimal(4.32).multiply(Mth.bigPotencia(10, 9)).multiply(Year[y]));
		
	}
	
	public static time getEonValueOf(double value){
		
		return new time(value, "eon", Mth.bigPotencia(10, 9).multiply(Year[y]));
		
	}
	
	public static time getGalacticYearValueOf(double value){
		
		return new time(value, "Gy", new BigDecimal(2.3).multiply(Mth.bigPotencia(10, 8)).multiply(Year[y]));
		
	}
	
	public static time getPetasecondValueOf(double value){
		
		return new time(value, "Ps", Mth.bigPotencia(10, 15));
		
	}
	
	public static time getMegayearValueOf(double value){
		
		return new time(value, "My", Mth.bigPotencia(10, 6).multiply(Year[y]));
		
	}
	
	public static time getTerasecondValueOf(double value){
		
		return new time(value, "Ts", Mth.bigPotencia(10, 12));
		
	}
	
	public static time getAgeValueOf(double value){
		
		return new time(value, "Age", new BigDecimal(2.148).multiply(Year[y]).add(new BigDecimal(2.00/3.00).multiply(Year[y])));
		
	}
	
	public static time getMillenniumValueOf(double value){
		
		return new time(value, "ky", Mth.bigPotencia(10, 3).multiply(Year[y]));
		
	}
	
	public static time getCenturyValueOf(double value){
		
		return new time(value, "c", Mth.bigPotencia(10, 2).multiply(Year[y]));
		
	}
	
	public static time getJubileeValueOf(double value){
		
		return new time(value, "jb", new BigDecimal(50).multiply(Year[y]));
		
	}
	
	public static time getGigasecondValueOf(double value){
		
		return new time(value, "Gs", Mth.bigPotencia(10, 9));
		
	}
	
	public static time getIndictionValueOf(double value){
		
		return new time(value, "id", new BigDecimal(15).multiply(Year[y]));
		
	}
	
	public static time getDecadeValueOf(double value){
		
		return new time(value, "dc", new BigDecimal(10).multiply(Year[y]));
		
	}
	
	public static time getLustrumValueOf(double value){
		
		return new time(value, "lt", new BigDecimal(5).multiply(Year[y]));
		
	}
	
	public static time getOlimpiadValueOf(double value){
		
		return new time(value, "Olmp", new BigDecimal(4).multiply(Year[y]));
		
	}
	
	public static time getLeapYearValueOf(double value){
		
		return new time(value, "Ly", new BigDecimal(366).multiply(new BigDecimal(86400)));
		
	}
	
	public static time getSecondValueOf(double value){
		
		return new time(value, "s", Mth.bigPotencia(10, 0));
		
	}
	
	public static time getMinuteValueOf(double value){
		
		return new time(value, "min", new BigDecimal(60));
		
	}
	
	public static time getHourValueOf(double value){
		
		return new time(value, "h", new BigDecimal(3600));
		
	}
	
	public static time getDayValueOf(double value){
		
		return new time(value, "d", new BigDecimal(86400));
		
	}
	
	public static time getWeekValueOf(double value){
		
		return new time(value, "w", new BigDecimal(604800));
		
	}
	
	public static time getMonthValueOf(double value){
		
		return new time(value, "m", new BigDecimal(24_192_00));
		
	}
	
	public static time getSideralYearValueOf(double value){
		
		return new time(value, "sy", new BigDecimal(366).multiply(new BigDecimal(86400)).add(new BigDecimal(6).multiply(new BigDecimal(3600))).add(new BigDecimal(9).multiply(new BigDecimal(60))).add(new BigDecimal(9.7635456)));
		
	}
	
	public static time getGregorianYearValueOf(double value){
		
		return new time(value, "yr", Year[1]);
		
	}
	
	public static time getYearValueOf(double value){
		
		return new time(value, "yr", Year[0]);
		
	}
	
	public static time getLunarYearValueOf(double value){
		
		return new time(value, "ly", new BigDecimal(354.37).multiply(new BigDecimal(86400)));
		
	}
	
	public static time getSemesterValueOf(double value){
		
		return new time(value, "sixmth", new BigDecimal(18).multiply(new BigDecimal(604800)));
		
	}
	
	public static time getQuarantineValueOf(double value){
		
		return new time(value, "qa", new BigDecimal(40).multiply(new BigDecimal(86400)));
		
	}
	
	public static time getDecasecondValueOf(double value){
		
		return new time(value, "das", Mth.bigPotencia(10, 1));
		
	}
	
	public static time getDecisecondValueOf(double value){
		
		return new time(value, "ds", Mth.bigPotencia(10, -1));
		
	}
	
	public static time getCentisecondValueOf(double value){
		
		return new time(value, "cs", Mth.bigPotencia(10, -2));
		
	}
	
	public static time getMillisecondValueOf(double value){
		
		return new time(value, "ms", Mth.bigPotencia(10, -3));
		
	}
	
	public static time getMicrosecondValueOf(double value){
		
		return new time(value, "µs", Mth.bigPotencia(10, -6));
		
	}
	
	public static time getNanosecondValueOf(double value){
		
		return new time(value, "ns", Mth.bigPotencia(10, -9));
		
	}
	
	public static distance getPlanckTimeValueOf(double value){
		
		return new distance(value, "pt", new BigDecimal(5.39).multiply(Mth.bigPotencia(10, -44)));
		
	}
	
	public time doRedondear(int limite){
		
		return new time(this, limite);
		
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
				
				c = " time";
				
			}
			
			if (b==0){
				
				d = " scalar";
				
			}else{
				
				d = " time";
				
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