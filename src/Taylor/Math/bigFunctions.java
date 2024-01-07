package Taylor.Math;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
 
public class bigFunctions{
	
	private static long indiceFactorial = -3;
	private static BigDecimal factorial = new BigDecimal(0);
	
	public bigFunctions(){}
	
	public BigDecimal bigPotencia(double base, double exponent){
		
		if (base==0){
			
			return new BigDecimal(0);
			
		}
		
		if (base==1 || exponent==0){
			
			return new BigDecimal(1);
			
		}
		
		if (exponent<0){
			
			return new BigDecimal(1).divide(bigPotencia(base, -exponent), MathContext.DECIMAL128);
			
		}
		
		return new BigDecimal(base).multiply(bigPotencia(base, exponent-1));
		
	}
	
	public BigDecimal bigPotencia(BigDecimal base, double exponent){
		
		if (base.compareTo(new BigDecimal(0))==0){
			
			return new BigDecimal(0);
			
		}
		
		if (base.compareTo(new BigDecimal(1))==0 || exponent==0){
			
			return new BigDecimal(1);
			
		}
		
		if (exponent<0){
			
			return new BigDecimal(1).divide(bigPotencia(base, -exponent), MathContext.DECIMAL128);
			
		}
		
		return base.multiply(bigPotencia(base, exponent-1));
		
	}//*/
	
	public BigDecimal bigFactorial(long n){
		
		if (n==0 || n==1){
			
			return new BigDecimal(1);
			
		}
		
		BigDecimal fact = new BigDecimal(n);
		
		if (indiceFactorial==n-1){
			
			factorial = factorial.multiply(new BigDecimal(n));
			indiceFactorial = n;
			
			return factorial;
			
		}else if (indiceFactorial==n-2){
			
			factorial = factorial.multiply(new BigDecimal(n-1));
			factorial = factorial.multiply(new BigDecimal(n));
			indiceFactorial = n;
			
			return factorial;
			
		}else{
			
			factorial = fact.multiply(bigFactorial(n-1));
			indiceFactorial = n;
			
			return factorial;
			
		}
		
	}
	
	public BigDecimal bigFactorial(BigDecimal n){
		
		if (n.compareTo(new BigDecimal(0))==0 || n.compareTo(new BigDecimal(1))==1){
			
			return new BigDecimal(1);
			
		}
		
		BigDecimal fact = n;
		
		fact = fact.multiply(bigFactorial(n.subtract(new BigDecimal(-1))));
		
		return fact;
		
	}

}