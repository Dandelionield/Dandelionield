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

public class temperature implements Comparable<temperature>{

	private final double Scalar;
	private String Unity;
	private long nth;
	private function toKelvin;
	private function fromKelvin;
	
	private final Mayth Mth = new Mayth();
	
	public temperature(double Scalar){
		
		this.Scalar = Scalar;
		this.Unity = "u";
		this.nth = 1;
		this.toKelvin = new function(0);
		this.fromKelvin = new function(0);
		
	}
	
	public temperature(double Scalar, String Unity){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.toKelvin = new function(0);
		this.fromKelvin = new function(0);
		
	}
	
	public temperature(double Scalar, String Unity, function toKelvin, function fromKelvin){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = getnthPower(this.Unity);
		this.toKelvin = toKelvin;
		this.fromKelvin = fromKelvin;
		
	}
	
	private temperature(double Scalar, String Unity, long nth, function toKelvin, function fromKelvin){
		
		this.Scalar = Scalar;
		this.Unity = fixUnity(Unity);
		this.nth = nth;
		this.toKelvin = toKelvin;
		this.fromKelvin = fromKelvin;
		
	}
	
	private temperature(temperature t, int limite){
		
		this.Scalar = Mayth.Redondear(t.Scalar, limite);
		this.Unity = t.Unity;
		this.nth = t.nth;
		this.toKelvin = t.toKelvin;
		this.fromKelvin = t.fromKelvin;
		
	}
	
	public int compareTo(temperature b){
		
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
		this.toKelvin = new function(0);
		this.fromKelvin = new function(0);
		
	}
	
	public function getToKelvin(){
		
		return this.toKelvin;
		
	}
	
	public function getFromKelvin(){
		
		return this.fromKelvin;
		
	}
	
	public void setToKelvin(function toKelvin){
		
		this.toKelvin = toKelvin;
		
	}
	
	public void setFromKelvin(function fromKelvin){
		
		this.fromKelvin = fromKelvin;
		
	}
	
	public long getDegree(){
		
		return this.nth;
		
	}
	
	public String toString(){
		
		return this.Scalar+"°"+this.Unity;
		
	}
	
	public temperature toKelvin(){
		
		String bup = "";
		
		if (this.nth!=1){
			
			bup = "^"+this.nth;
			
		}
		
		//System.out.print(this.toKelvin+"\n\n");
		
		return new temperature(this.toKelvin.get(this.Scalar).get().setScale(15, RoundingMode.HALF_UP).doubleValue(), "K"+bup, this.nth, new function('k', "k", "K"+bup), new function('k', "k", "K"+bup));
		
	}
	
	public temperature doScalar(double s){
		
		return new temperature(this.Scalar*s, this.Unity, this.nth, this.toKelvin, this.fromKelvin);
		
	}
	
	public temperature doSuma(temperature t){
		
		if (this.toKelvin.isConstant()==true || this.fromKelvin.isConstant()==true){
			
			throwError("unabled to add temperatures due to variable "+this.toString()+" has no equivalency in Kelvin degrees");
			
		}else if (t.toKelvin.isConstant()==true || t.fromKelvin.isConstant()==true){
			
			throwError("unabled to add temperatures due to variable "+t.toString()+" has no equivalency in Kelvin degrees");
			
		}
		
		throwAdditionSubtractionError(this.nth, t.nth, (byte) 1);
		
		double nthKelvinValue = t.toKelvin().getScalar();
		
		return new temperature(new BigDecimal(this.Scalar).add(this.fromKelvin.get(nthKelvinValue).get()).setScale(14, RoundingMode.HALF_UP).doubleValue(), this.Unity, this.nth, this.toKelvin, this.fromKelvin);	
		
	}
	
	public temperature doResta(temperature t){
		
		if (this.toKelvin.isConstant()==true || this.fromKelvin.isConstant()==true){
			
			throwError("unabled to subtract temperatures due to variable "+this.toString()+" has no equivalency in Kelvin degrees");
			
		}else if (t.toKelvin.isConstant()==true || t.fromKelvin.isConstant()==true){
			
			throwError("unabled to subtract temperatures due to variable "+t.toString()+" has no equivalency in Kelvin degrees");
			
		}
		
		throwAdditionSubtractionError(this.nth, t.nth, (byte) -1);
		
		double nthKelvinValue = t.toKelvin().getScalar();
		
		return new temperature(new BigDecimal(this.Scalar).subtract(this.fromKelvin.get(nthKelvinValue).get()).setScale(14, RoundingMode.HALF_UP).doubleValue(), this.Unity, this.nth, this.toKelvin, this.fromKelvin);	
		
	}
	
	public temperature arcTemperature(){
		
		return new temperature(1.00/this.Scalar, this.Unity, this.nth, this.toKelvin, this.fromKelvin);
		
	}
	
	public temperature doPotencia(long n){
		
		if (n==0){
			
			return this.doScalar(1.00/this.Scalar);
			
		}else if (n==1){
			
			return this;
			
		}else if (n<0){
			
			return this.doPotencia(-n).arcTemperature();
			
		}
		
		return this.doProduct(this.doPotencia(n-1));
		
	}//*/
	
	public temperature doProduct(temperature t){
		
		if (this.toKelvin.isConstant()==true || this.fromKelvin.isConstant()==true){
			
			throwError("unabled to multiply temperatures due to variable "+this.toString()+" has no equivalency in Kelvin degrees");
			
		}else if (t.toKelvin.isConstant()==true || t.fromKelvin.isConstant()==true){
			
			throwError("unabled to multiply temperatures due to variable "+t.toString()+" has no equivalency in Kelvin degrees");
			
		}
		
		double nthKelvinValue;
		function from;
		BigDecimal KelvinValue1;
		BigDecimal KelvinValue2;
		BigDecimal newKelvinValue;
		
		if (this.nth==t.nth){
			
			nthKelvinValue = t.toKelvin().getScalar();
			
			return new temperature(new BigDecimal(this.Scalar).multiply(this.fromKelvin.get(nthKelvinValue).get()).setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, t.nth, 1), this.nth+t.nth, getNewConvertion(this.toKelvin, this.nth+t.nth), getNewConvertion(this.fromKelvin, this.nth+t.nth));	
			
			
		}else if (this.nth+t.nth==0){
			
			return null;
			
		}else{
			
			KelvinValue1 = this.toKelvin.get(this.Scalar).get();
			KelvinValue2 = t.toKelvin.get(t.Scalar).get();
			
			newKelvinValue = KelvinValue1.multiply(KelvinValue2);
			
			from = getNewConvertion(this.fromKelvin, this.nth+t.nth);
			
			return new temperature(from.get(newKelvinValue.setScale(15, RoundingMode.HALF_UP).doubleValue()).get().setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, t.nth, 1), this.nth+t.nth, getNewConvertion(this.toKelvin, this.nth+t.nth), from);
			
		}
		
	}
	
	public temperature doDivide(temperature t){
		
		if (this.toKelvin.isConstant()==true || this.fromKelvin.isConstant()==true){
			
			throwError("unabled to divide temperatures due to variable "+this.toString()+" has no equivalency in Kelvin degrees");
			
		}else if (t.toKelvin.isConstant()==true || t.fromKelvin.isConstant()==true){
			
			throwError("unabled to divide temperatures due to variable "+t.toString()+" has no equivalency in Kelvin degrees");
			
		}
		
		
		double nthKelvinValue;
		function from;
		BigDecimal KelvinValue1;
		BigDecimal KelvinValue2;
		BigDecimal newKelvinValue;
		
		if (this.nth==t.nth){
			
			nthKelvinValue = t.toKelvin().getScalar();
			
			return new temperature(new BigDecimal(this.Scalar).divide(this.fromKelvin.get(nthKelvinValue).get(), MathContext.DECIMAL128).setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, t.nth, -1), this.nth-t.nth, getNewConvertion(this.toKelvin, this.nth-t.nth), getNewConvertion(this.fromKelvin, this.nth-t.nth));	
			
			
		}else if (this.nth-t.nth==0){
			
			return null;
			
		}else{
			
			KelvinValue1 = this.toKelvin.get(this.Scalar).get();
			KelvinValue2 = t.toKelvin.get(t.Scalar).get();
			
			newKelvinValue = KelvinValue1.divide(KelvinValue2, MathContext.DECIMAL128);
			
			from = getNewConvertion(this.fromKelvin, this.nth-t.nth);
			
			return new temperature(from.get(newKelvinValue.setScale(15, RoundingMode.HALF_UP).doubleValue()).get().setScale(14, RoundingMode.HALF_UP).doubleValue(), getNewUnity(this.nth, t.nth, -1), this.nth-t.nth, getNewConvertion(this.toKelvin, this.nth-t.nth), from);
			
		}
		
	}
	
	public static temperature getKelvinValueOf(double value){
		
		return new temperature(value, "K", new function('k', "k", "K"), new function('k', "k", "K"));
		
	}
	
	public static temperature getCelsiusValueOf(double value){
		
		return new temperature(value, "C", new function('c', "c + 273.15", "K"), new function('k', "k - 273.15", "C"));
		
	}
	
	public static temperature getFahrenheitValueOf(double value){
		
		return new temperature(value, "F", new function('f', "5(f + 459.67)/9", "K"), new function('k', "9k/5 - 459.67", "F"));
		
	}
	
	public temperature doRedondear(int limite){
		
		return new temperature(this, limite);
		
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
	
	private function getNewConvertion(function f, long newNth){
		
		String wd = f.getFunction();
		String bup = "";
		long Counter = 0;
		boolean pass = true;
		char p;
		
		if (wd.charAt(0)=='('){
			
			for (int i=0; i<wd.length(); i++){
				
				p = wd.charAt(i);
				
				if (p=='('){
					
					Counter++;
					
				}else if (p==')'){
					
					Counter--;
					
				}
				
				if (Counter==0){
					
					if (i!=wd.length()-1){
						
						if (wd.charAt(i+1)=='^'){
							
							bup = wd.substring(i+2);
							
							if (bup.contains("(")==false && bup.contains(")")==false){
								
								if (Parser.isAllowed(bup)==true){
									
									return new function(f.getVariable(), wd.substring(0, i+2)+""+newNth, f.getName().charAt(0)+"^"+newNth);
									
								}
								
							}else{
								
								break;
								
							}
							
						}
						
					}else{
						
						break;
						
					}
					
				}
				
			}
			
		}
		
		return new function(f.getVariable(), "("+wd+")^"+newNth, f.getName().charAt(0)+"^"+newNth);
		
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
		
	}
	
}