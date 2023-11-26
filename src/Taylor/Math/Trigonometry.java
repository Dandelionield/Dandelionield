package Taylor.Math;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
 
public class Trigonometry extends Algebra{
	
	private static boolean RAD_DEG = false;
	private static double PI = Pi().doubleValue();
	
	public Trigonometry(){
		
		RAD_DEG = false;
		
	}
	
	public Trigonometry(boolean value){
		
		RAD_DEG = value;
		
	}
	
	public void setMode(boolean value){
		
		RAD_DEG = value;
		
	}

	public static double Sen(double x){
		
		BigDecimal Seno = new BigDecimal(0);
		
		if (RAD_DEG==false){
			
			x = (x*PI)/180;
			
		}
		
		for (int n=0; n<=100; n++){
			
			Seno = Seno.add(bigPotencia(-1, n).multiply(bigPotencia(x,2*n+1).divide(bigFactorial(2*n+1), 100, RoundingMode.HALF_UP)));
			
		}
		
		return Seno.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public static double Arcsen(double x){
		
		if (Double.isNaN(x) || Double.isInfinite(x)){
			
			return x;
			
		}
		
		BigDecimal Arcoseno = new BigDecimal(0);
		
		int limite = 0;
		
		if (x==1){
			
			if (RAD_DEG==false){
				
				return 90;
				
			}else{
				
				return PI/2;
				
			}
			
		}else if (x==-1){
			
			if (RAD_DEG==false){
				
				return -90;
				
			}else{
				
				return -PI/2;
				
			}
			
		}
		
		boolean bup = Trigonometry.RAD_DEG;
		
		Trigonometry.RAD_DEG = true;
		
		try{
			
			Arcoseno = new BigDecimal(Trigonometry.Arctan(x/Math.sqrt(1-x*x)));
			
		}catch(Exception e){
			
			Trigonometry.RAD_DEG = bup;
			
			return x;
			
		}
		
		Trigonometry.RAD_DEG = bup;
		
		if (RAD_DEG==false){
			
			Arcoseno = Arcoseno.multiply(new BigDecimal(180)).divide(new BigDecimal(PI), MathContext.DECIMAL128);
			
		}
		
		return Arcoseno.setScale(15, RoundingMode.HALF_UP).doubleValue(); 
		
	}
	
	public static double Cos(double x){
		
		BigDecimal Cos = new BigDecimal(0);
		
		if (RAD_DEG==false){
			
			x = (x*PI)/180;
			
		}
		
		for (int n=0; n<=100; n++){
			
			Cos = Cos.add(bigPotencia(-1, n).multiply(bigPotencia(x,2*n).divide(bigFactorial(2*n), 100, RoundingMode.HALF_UP)));
			
		}
		
		return Cos.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public static double Arccos(double x){
		
		if (Double.isNaN(x) || Double.isInfinite(x)){
			
			return x;
			
		}
		
		boolean bup = Trigonometry.RAD_DEG;
		
		Trigonometry.RAD_DEG = true;
		
		BigDecimal Arcocoseno = (new BigDecimal(PI/2).subtract(new BigDecimal(Arcsen(x))));
		
		Trigonometry.RAD_DEG = bup;
		
		if (RAD_DEG==false){
			
			Arcocoseno = Arcocoseno.multiply(new BigDecimal(180)).divide(new BigDecimal(PI), MathContext.DECIMAL128);
			
		}
		
		return Arcocoseno.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public static double Tan(double x){
		
		if (x!=90 && x!=270){
			
			return Sen(x)/Cos(x);
			
		}else{
			
			return Double.NaN;
			
		}
		
	}
	
	public static double Arctan(double x){
		
		if (Double.isNaN(x) || Double.isInfinite(x)){
			
			return x;
			
		}
		
		BigDecimal Arcotangente = new BigDecimal(0);
		
		boolean bup = Trigonometry.RAD_DEG;
		boolean condition1 = x<=-1.0000000000000013 && x>=-572.957213354061;
		boolean Break = false;
		
		if (Math.abs(x)>1){
			
			Trigonometry.RAD_DEG = true;
			
			Arcotangente =  new BigDecimal(PI/2-Arctan(1/x));
			
			Trigonometry.RAD_DEG = bup;
			
			Break = true;
			
		}
		
		if (condition1){
			
			Arcotangente = Arcotangente.subtract(new BigDecimal(PI));
			
		}
		
		for (int n=0; n<=100; n++){
			
			if (Break){break;}
			
			Arcotangente = Arcotangente.add(new BigDecimal(Mayth.Potencia(-1, n)).multiply(bigPotencia(x,2*n+1).divide(new BigDecimal(2*n+1), MathContext.DECIMAL128)));
			
		}
		
		if (RAD_DEG==false){
			
			Arcotangente = Arcotangente.multiply(new BigDecimal(180)).divide(new BigDecimal(PI), MathContext.DECIMAL128);
			
		}
		
		return Arcotangente.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public static double Csc(double x){
		
		if (x!=0 && x!=180 && x!=360){
			
			return 1d/Sen(x);
			
		}else{
			
			return Double.NaN;
			
		}
		
	}
	
	public static double Arccsc(double x){
		
		return Arcsen(1/x);
		
	}
	
	public static double Sec(double x){
		
		if (x!=90 && x!=270){
			
			return 1d/Cos(x);
			
		}else{
			
			return Double.NaN;
			
		}
		
	}
	
	public static double Arcsec(double x){
		
		return Arccos(1/x);
		
	}
	
	public static double Cot(double x){
		
		if (x!=0 && x!=180 && x!=360){
			
			return Cos(x)/Sen(x);
			
		}else{
			
			return Double.NaN;
			
		}
		
	}
	
	public static double Arccot(double x){
		
		return Arctan(1/x);
		
	}

}