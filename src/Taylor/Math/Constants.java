package Taylor.Math;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
 
public class Constants extends bigFuntions{

	public static BigDecimal Pi(){
		
		BigDecimal a = new BigDecimal(0);
		BigDecimal b = new BigDecimal(0);
		
		for (int n=0; n<=10; n++){
			
			a = a.add(new BigDecimal(Mayth.Potencia(-1, n)).multiply(bigPotencia(1.00/5.00,2*n+1).divide(new BigDecimal(2*n+1), MathContext.DECIMAL128)));
			b = b.add(new BigDecimal(Mayth.Potencia(-1, n)).multiply(bigPotencia(1.00/239.00,2*n+1).divide(new BigDecimal(2*n+1), MathContext.DECIMAL128)));
			
		}
		
		return new BigDecimal(4).multiply(new BigDecimal(4).multiply(a).subtract(b)).setScale(15, RoundingMode.HALF_UP);
		
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