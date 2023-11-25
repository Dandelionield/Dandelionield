package Taylor.Math;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
 
public class Mayth{

	public static double Potencia(double base, double exponent){
		
		if (base==1 || exponent==0){
			
			return 1;
			
		}else if (base==0){
			
			return 0;
			
		}else if (base==-1 && exponent%1==0){
			
			if (exponent%2==0){
				
				return 1;
				
			}else{
				
				return -1;
				
			}
			
		}else if (exponent%1==0){
			
			return bigPotencia(base, exponent).doubleValue();
			
		}else{
			
			return 1;
			
		}
		
	}
	
	public static BigDecimal bigPotencia(double base, double exponent){
		
		if (base==1 || exponent==0){
			
			return new BigDecimal(1);
			
		}
		
		return new BigDecimal(base).multiply(bigPotencia(base, exponent-1));
		
	}

}