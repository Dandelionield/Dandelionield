package Taylor.Math;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
 
public class Constants{

	public static BigDecimal Pi(){
		
		BigDecimal pi = new BigDecimal(0);
		
		double x = 1/Mayth.Raiz(3);//0.5/Trigonometry.Sen(60);
		
		for (int n=0; n<=28; n++){
			
			pi = pi.add(new BigDecimal(Mayth.Potencia(-1, n)).multiply(Algebra.bigPotencia(x,2*n+1).divide(new BigDecimal(2*n+1), MathContext.DECIMAL128)));
			
		}
		
		return pi.multiply(new BigDecimal(6)).setScale(15, RoundingMode.HALF_UP);
		
	}
	
	public static BigDecimal Euler(){
		
		BigDecimal euler = new BigDecimal(0);
		BigDecimal factorial = new BigDecimal(1);
		
		for (int n=1; n<=100; n++){
			
			factorial = factorial.multiply(new BigDecimal(n));
			
			euler = euler.add(new BigDecimal(1).divide(factorial, MathContext.DECIMAL128));
			
		}
		
		return euler.add(new BigDecimal(1)).setScale(31, RoundingMode.HALF_UP);
		
	}
	
	public static BigDecimal Auero(){
		
		return new BigDecimal(Trigonometry.Cos(36)).multiply(new BigDecimal(2)).setScale(15, RoundingMode.HALF_UP);
		
	}

}