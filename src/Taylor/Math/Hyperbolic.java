package Taylor.Math;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
 
public class Hyperbolic extends Trigonometry{
	
	public Hyperbolic(){
		
		super(false);
		
	}
	
	public Hyperbolic(boolean value){
		
		super(value);
		
	}

	public static double Senh(double x){
		
		BigDecimal Senoh = new BigDecimal(0);
		
		for (int n=0; n<=200; n++){
			
			Senoh = Senoh.add(bigPotencia(x, 2*n+1).divide(bigFactorial(2*n+1), MathContext.DECIMAL128));
			
		}
		
		return Senoh.setScale(15, RoundingMode.HALF_UP).doubleValue(); 
		
	}
	
	public static double Cosh(double x){
		
		BigDecimal Cosenoh = new BigDecimal(0);
		
		for (int n=0; n<=200; n++){
			
			Cosenoh = Cosenoh.add(bigPotencia(x, 2*n).divide(bigFactorial(2*n), MathContext.DECIMAL128));
			
		}
		
		return Cosenoh.setScale(15, RoundingMode.HALF_UP).doubleValue(); 
		
	}
	
	public static double Tanh(double x){
		
		return (new BigDecimal(Senh(x)).divide(new BigDecimal(Cosh(x)), MathContext.DECIMAL128)).setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public static double Csch(double x){
		
		try {
			
			return (new BigDecimal(1).divide(new BigDecimal(Senh(x)), MathContext.DECIMAL128)).setScale(15, RoundingMode.HALF_UP).doubleValue();
			
		}catch(Exception e){
			
			return Double.NaN;
			
		}
		
	}
	
	public static double Sech(double x){
		
		try {
			
			return (new BigDecimal(1).divide(new BigDecimal(Cosh(x)), MathContext.DECIMAL128)).setScale(15, RoundingMode.HALF_UP).doubleValue();
			
		}catch(Exception e){
			
			return Double.NaN;
			
		}
		
	}
	
	public static double Coth(double x){
		
		try {
			
			return (new BigDecimal(Cosh(x)).divide(new BigDecimal(Senh(x)), MathContext.DECIMAL128)).setScale(15, RoundingMode.HALF_UP).doubleValue();
			
		}catch(Exception e){
			
			return Double.NaN;
			
		}
		
	}

}