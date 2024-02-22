package Taylor.Math;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

import java.util.ArrayList;
import java.util.Arrays;
 
public class Mayth extends Hyperbolic{
	
	public static final BigDecimal e = new Constants().Euler();
	public static final BigDecimal A = new Constants().Aureo();
	public static final BigDecimal G = new Constants().Gamma();
	
	public Mayth(){
		
		super(false);
		
	}
	
	public Mayth(boolean value){
		
		super(value);
		
	}
	
	public double Raiz(double x, double n){
		
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
	
	public double Raiz(double x){
		
		return Raiz(x, 2);
		
	}

	public double Potencia(double base, double exponent){
		
		if (Double.isNaN(base)==true || Double.isInfinite(base)==true || Double.isNaN(exponent)==true || Double.isInfinite(exponent)==true){
			
			return Double.NaN;
			
		}
		
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
	
	public double Potencia(double x){
		
		return Potencia(x, 2);
		
	}
	
	public double Euler(double x){
		
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
		
		if (x%1!=0){
		
			for (int n=0; n<=400; n++){
				
				if (n!=0){factorial = factorial.multiply(new BigDecimal(n));}
				
				exp = exp.add(bigPotencia(x,n).divide(factorial, MathContext.DECIMAL128));
				
			}
			
		}else{
			
			exp = Mayth.e;
			
			for (int n=2; n<=x; n++){
			
				exp = exp.multiply(Mayth.e);
				
			}
			
		}
		
		if (signo==-1){
		
			exp = new BigDecimal(1).divide(exp, MathContext.DECIMAL128);
			
		}
		
		return exp.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public double Ln(double x){
		
		BigDecimal ln = new BigDecimal(0);
		BigDecimal x2 = new BigDecimal(1);
		BigDecimal UNO = new BigDecimal(-1);
		boolean b = false;

		if (x>0 && x<1){x=1/x;b=true;}

		if (x>1){
		
			for (int n=1; n<=500; n++){
				
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
	
	public double Log(double x, double base){//Se usa la serie Taylor para el logaritmo en base b 
		
		BigDecimal log = new BigDecimal(0);
		BigDecimal ln = new BigDecimal(0);
		boolean b = false;

		if (x>0 && x<1){x=1/x;b=true;}

		if (x>1 && base>0){
			
			ln = new BigDecimal(Ln(base));
			
			for (int n=1; n<=300; n++){
				
				log = log.add(new BigDecimal(Potencia(-1f,n-1)).multiply(bigPotencia(1/x - 1,n).divide(ln.multiply(new BigDecimal(n)), MathContext.DECIMAL128)));
				
			}
			
		}else if (x==1 && base>0){
			
			return 0;
			
		}else if (x<=0 && base<=0){return Double.NaN;}

		if (b==true){log = log.multiply(new BigDecimal(-1));}

		return log.multiply(new BigDecimal(-1)).setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public double Log(double x){
		
		return Log(x, 10);
		
	}
	
	public double Factorial(double n){

		if (n>0 && n%1==0){

			return bigFactorial( (long) n).doubleValue();
			
		}else if (n%1!=0 && n!=0){
			
			return GammaEuler(n).setScale(15, RoundingMode.HALF_UP).doubleValue();
			
		}else if (n==0){

			return 1;
			
		}else{
			
			return Double.NaN;
			
		}
		
	}
	
	public BigDecimal GammaEuler(double z){
		
		BigDecimal p = new BigDecimal(0.99999999999980993);
		BigDecimal t = new BigDecimal(z).add(new BigDecimal(7.5));
		
		p = p.add(new BigDecimal(676.5203681218851).divide(new BigDecimal(z).add(new BigDecimal(1)), MathContext.DECIMAL128));
		p = p.subtract(new BigDecimal(1259.1392167224028).divide(new BigDecimal(z).add(new BigDecimal(2)), MathContext.DECIMAL128));
		p = p.add(new BigDecimal(771.32342877765313).divide(new BigDecimal(z).add(new BigDecimal(3)), MathContext.DECIMAL128));
		p = p.subtract(new BigDecimal(176.61502916214059).divide(new BigDecimal(z).add(new BigDecimal(4)), MathContext.DECIMAL128));
		p = p.add(new BigDecimal(12.507343278686905).divide(new BigDecimal(z).add(new BigDecimal(5)), MathContext.DECIMAL128));
		p = p.subtract(new BigDecimal(0.13857109526572012).divide(new BigDecimal(z).add(new BigDecimal(6)), MathContext.DECIMAL128));
		p = p.add(new BigDecimal(9.9843695780195716e-6).divide(new BigDecimal(z).add(new BigDecimal(7)), MathContext.DECIMAL128));
		p = p.add(new BigDecimal(1.5056327351493116e-7).divide(new BigDecimal(z).add(new BigDecimal(8)), MathContext.DECIMAL128));
		
		return new BigDecimal(Raiz(2 * PI.doubleValue(), 2)).multiply(new BigDecimal(Potencia(t.setScale(15, RoundingMode.HALF_UP).doubleValue(), z + 0.5d))).multiply(new BigDecimal(Euler(-t.setScale(15, RoundingMode.HALF_UP).doubleValue()))).multiply(p);
		
	}
	
	public boolean isPrime(long n){
		
		if (n<=1){return false;}
		
		int c = 0;
		
		for (int i=1; i<=n; i++){
			
			if (n%i==0){
				
				c++;
				
			}
			
			if (c==3){
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
	public long nPrime(int indice){
		
		if (indice<=0){return 0;}
		
		long in = 0,co = 0;
		long primo = 1;
		boolean b = false;
		
		do{
			
			in++;
			
			do{
				
				co = 0;
				primo++;
				
				for (long i=1; i<=primo; i++){
					
					if (primo%i==0){co++;}
					
					if (co>2){
						
						b = false;
						
						break;
						
					}else if (co==2){b = true;}
					
				}
				
			}while(b==false);
			
		}while(in!=indice);
		
		return primo;
		
	}
	
	private ArrayList<Double> fibonacci = new ArrayList<>(Arrays.asList(0.00, 1.00));
	
	public double nFibonacci(int indice){
		
		if (indice<=fibonacci.size()-1){
			
			return fibonacci.get(indice);
			
		}else{
			
			fibonacci.add(nFibonacci(indice-1) + nFibonacci(indice-2));
			
			return fibonacci.get(fibonacci.size()-1);
			
		}
		
	}
	
	public BigDecimal nBernoulli(double indice){
		
		BigDecimal Bn = new BigDecimal(0);
		
		if (indice!=0 && indice>0){
			
			//Bn = ((Potencia(-1,indice+1) * 2 * Factorial(2*indice))/Potencia(2*Pi(),2*indice)) * ZetaReimann(2*indice);
			
			for (int n=0; n<=indice; n++){
				
				Bn = Bn.add(new BigDecimal(Potencia(-1,n)).multiply(nWorpitzky(indice,n)).divide(new BigDecimal(n+1), MathContext.DECIMAL128));
				
			}
			
		}else if (indice==0){Bn = new BigDecimal(1);}else {Bn = null;}
		
		return Bn;
		
	}
	
	public BigDecimal nWorpitzky(double n, int k){
		
		BigDecimal W = new BigDecimal(0);
		
		for (int v=0; v<=k; v++){
			
			W = W.add(new BigDecimal(Potencia(-1,v+k)).multiply(new BigDecimal(Potencia(v+1,n))).multiply(new BigDecimal(Combination(k,v))));
			
		}
		
		return W;
		
	}
	
	public double Combination(double n, double k){
		
		return Factorial(n) / (Factorial(k)*Factorial(n-k));
		
	}
	
	public BigDecimal ZetaReimann(double s){
		
		BigDecimal zeta = new BigDecimal(0);
		int iteracion=1000;
		
		if (s%1!=0){iteracion=10;}
		
		if (s>1){
		
			for (int n=1; n<=iteracion; n++){
				
				zeta = zeta.add(new BigDecimal(1).divide(new BigDecimal(Potencia(n,s)), MathContext.DECIMAL128));
				
			}
			
		}else{
			
			zeta = new BigDecimal(-1).multiply(nBernoulli(s+1)).divide(new BigDecimal(s+1), MathContext.DECIMAL128);
			
		}
		
		return zeta;
		
	}
	
	public double abs(double x){
		
		if (x==-0){return 0;}
		
		if (x<0){
			
			return -x;
			
		}
		
		return x;
		
	}
	
	public static double Redondear(double n, int limite){
		
		try{
		
			if (Double.isNaN(n)==false && Double.isInfinite(n)==false){
				
				return new BigDecimal(n).setScale(limite, RoundingMode.HALF_UP).doubleValue();
				
			}else{
				
				return n;
				
			}
			
		}catch(Exception e){
			
			return n;
			
		}
		
	}

}