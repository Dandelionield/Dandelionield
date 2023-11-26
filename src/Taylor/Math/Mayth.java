package Taylor.Math;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
 
public class Mayth extends Hyperbolic{
	
	public Mayth(){
		
		super(false);
		
	}
	
	public Mayth(boolean value){
		
		super(value);
		
	}
	
	public static double Raiz(double x, double n){
		
		int signo=1;
		
		if (x>=0){
		
			if (n!=1 && x!=0){
			
				if (n<0){
					
					signo*=-1;
					n*=-1;
					
				}
				
				if (signo>0){
				
					return Euler(new BigDecimal(Ln(x)).divide(new BigDecimal(n), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue());
					
				}else{
					
					if (n==1){
						
						return 1/x;
						
					}else{
						
						return 1/Euler(new BigDecimal(Ln(x)).divide(new BigDecimal(n), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue());
						
					}
				}
				
			}else{return x;}
			
		}else{return Double.NaN;}
		
	}
	
	public static double Raiz(double x){
		
		return Raiz(x, 2);
		
	}

	public static double Potencia(double base, double exponent){
		
		if (exponent<0){
			
			return 1/Potencia(base, -exponent);
			
		}
		
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
			
			if (base<0){
				
				if (exponent%2==0){
					
					return Euler(new BigDecimal(Ln(base)).multiply(new BigDecimal(exponent)).setScale(15, RoundingMode.HALF_UP).doubleValue());
					
				}else{
					
					return -Euler(new BigDecimal(Ln(base)).multiply(new BigDecimal(exponent)).setScale(15, RoundingMode.HALF_UP).doubleValue());
					
				}
				
			}else{
				
				return Euler(new BigDecimal(Ln(base)).multiply(new BigDecimal(exponent)).setScale(15, RoundingMode.HALF_UP).doubleValue());
				
			}
			
		}
		
	}
	
	public static double Euler(double x){
		
		if (x==0){
			
			return 1;
			
		}
		
		BigDecimal exp = new BigDecimal(0);
		BigDecimal factorial = new BigDecimal(1);
		int signo=1;
		
		if (x<0){
			
			signo*=-1;
			x*=-1;
			
		}
		
		for (int n=0; n<=400; n++){
			
			if (n!=0){factorial = factorial.multiply(new BigDecimal(n));}
			
			exp = exp.add(bigPotencia(x,n).divide(factorial, MathContext.DECIMAL128));
			
		}
		
		if (signo==-1){
		
			exp = new BigDecimal(1).divide(exp, MathContext.DECIMAL128);
			
		}
		
		return exp.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public static double Ln(double x){
		
		BigDecimal ln = new BigDecimal(0);
		BigDecimal x2 = new BigDecimal(1);
		BigDecimal UNO = new BigDecimal(-1);
		boolean b = false;

		if (x>0 && x<1){x=1/x;b=true;}

		if (x>1){
		
			for (int n=1; n<=300; n++){
				
				x2 = x2.multiply(new BigDecimal(1/x - 1));
				UNO = UNO.multiply(new BigDecimal(-1));
				
				ln = ln.add(UNO.multiply(x2).divide(new BigDecimal(n), MathContext.DECIMAL128));
				
			}
			
		}else if (x==1){
			
			return 0;
			
		}else if (x<=0){return Double.NaN;}

		if (b==true){ln = ln.multiply(new BigDecimal(-1));}
		
		return ln.multiply(new BigDecimal(-1)).setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
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

}