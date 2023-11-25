package Taylor.Math;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
 
public class Algebra{

	public static double Factorial(double n){

		if (n>0 && n%1==0){

			return bigFactorial( (long) n).doubleValue();
			
		}else if (n%1!=0 && n!=0){
			
			return 0;
			
			//producto = GammaEuler(n);
			
		}else if (n==0){

			return 1;
			
		}else{
			
			return Double.NaN;
			
		}
		
	}
	
	private static long indiceFactorial = -3;
	private static BigDecimal factorial = new BigDecimal(0);
	
	public static BigDecimal bigFactorial(long n){
		
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
	
	public static BigDecimal bigFactorial(BigDecimal n){
		
		if (n.compareTo(new BigDecimal(0))==0 || n.compareTo(new BigDecimal(1))==1){
			
			return new BigDecimal(1);
			
		}
		
		BigDecimal fact = n;
		
		fact = fact.multiply(bigFactorial(n.subtract(new BigDecimal(-1))));
		
		return fact;
		
	}

}