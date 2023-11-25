package Taylor.Math;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
 
public class Algebra{
    public static double Factorial(double n) {
        return (n < 0) ? Double.NaN :
			   (n <= 1) ? 1 : n * Factorial(n-1);
    }

	public static BigDecimal bigFactorial(BigDecimal n) {
        return (n.compareTo(BigDecimal.ZERO) < 0) ? BigDecimal.ZERO :
               ((n.compareTo(BigDecimal.ONE) < 1) ? BigDecimal.ONE :
               n.multiply(bigFactorial(n.subtract(BigDecimal.ONE))));
    }

    public static BigDecimal bigFactorial(long n) {
        BigDecimal number = new BigDecimal(n);
        
        return (number.compareTo(BigDecimal.ZERO) < 0) ? BigDecimal.ZERO :
               ((number.compareTo(BigDecimal.ONE) < 1) ? BigDecimal.ONE :
               number.multiply(bigFactorial(number.longValue()-1)));
    }
}