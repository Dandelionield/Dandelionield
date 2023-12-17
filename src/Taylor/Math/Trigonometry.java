package Taylor.Math;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
 
public class Trigonometry extends Constants{
	
	private boolean RAD_DEG;
	public static BigDecimal PI = new Constants().Pi();
	
	public Trigonometry(){
		
		RAD_DEG = false;
		
	}
	
	public Trigonometry(boolean value){
		
		RAD_DEG = value;
		
	}
	
	public void setMode(boolean value){
		
		RAD_DEG = value;
		
	}

	public double Sen(double x){
		
		if (Double.isNaN(x) || Double.isInfinite(x)){
			
			return x;
			
		}
		
		BigDecimal Seno = new BigDecimal(0);
		
		if (RAD_DEG==false){
			
			x = (x*Trigonometry.PI.doubleValue())/180;
			
		}
		
		for (int n=0; n<=100; n++){
			
			Seno = Seno.add(bigPotencia(-1, n).multiply(bigPotencia(x,2*n+1).divide(bigFactorial(2*n+1), 100, RoundingMode.HALF_UP)));
			
		}
		
		return Seno.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public double Arcsen(double x){
		
		if (Double.isNaN(x) || Double.isInfinite(x)){
			
			return x;
			
		}
		
		BigDecimal Arcoseno = new BigDecimal(0);
		
		int limite = 0;
		
		if (x==1){
			
			if (RAD_DEG==false){
				
				return 90;
				
			}else{
				
				return Trigonometry.PI.doubleValue()/2;
				
			}
			
		}else if (x==-1){
			
			if (RAD_DEG==false){
				
				return -90;
				
			}else{
				
				return -Trigonometry.PI.doubleValue()/2;
				
			}
			
		}
		
		boolean bup = RAD_DEG;
		
		RAD_DEG = true;
		
		try{
			
			Arcoseno = new BigDecimal(Arctan(x/Math.sqrt(1-x*x)));
			
		}catch(Exception e){
			
			RAD_DEG = bup;
			
			return x;
			
		}
		
		RAD_DEG = bup;
		
		if (RAD_DEG==false){
			
			Arcoseno = Arcoseno.multiply(new BigDecimal(180)).divide(Trigonometry.PI, MathContext.DECIMAL128);
			
		}
		
		return Arcoseno.setScale(15, RoundingMode.HALF_UP).doubleValue(); 
		
	}
	
	public double Cos(double x){
		
		if (Double.isNaN(x) || Double.isInfinite(x)){
			
			return x;
			
		}
		
		BigDecimal Cos = new BigDecimal(0);
		
		if (RAD_DEG==false){
			
			x = (x*Trigonometry.PI.doubleValue())/180;
			
		}
		
		if (x==0){
			
			return 1;
			
		}
		
		for (int n=0; n<=100; n++){
			
			Cos = Cos.add(bigPotencia(-1, n).multiply(bigPotencia(x,2*n).divide(bigFactorial(2*n), 100, RoundingMode.HALF_UP)));
			
		}
		
		return Cos.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public double Arccos(double x){
		
		if (Double.isNaN(x) || Double.isInfinite(x)){
			
			return x;
			
		}
		
		boolean bup = RAD_DEG;
		
		RAD_DEG = true;
		
		BigDecimal Arcocoseno = ((Trigonometry.PI.divide(new BigDecimal(2), MathContext.DECIMAL128)).subtract(new BigDecimal(Arcsen(x))));
		
		RAD_DEG = bup;
		
		if (RAD_DEG==false){
			
			Arcocoseno = Arcocoseno.multiply(new BigDecimal(180)).divide(Trigonometry.PI, MathContext.DECIMAL128);
			
		}
		
		return Arcocoseno.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public double Tan(double x){
		
		if (Double.isNaN(x) || Double.isInfinite(x)){
			
			return x;
			
		}
		
		if (x!=90 && x!=270){
			
			return Sen(x)/Cos(x);
			
		}else{
			
			return Double.NaN;
			
		}
		
	}
	
	public double Arctan(double x){
		
		if (Double.isNaN(x) || Double.isInfinite(x)){
			
			return x;
			
		}
		
		BigDecimal Arcotangente = new BigDecimal(0);
		
		boolean bup = RAD_DEG;
		boolean condition1 = x<=-1.0000000000000013 && x>=-572.957213354061;
		boolean Break = false;
		
		if (Math.abs(x)>1){
			
			RAD_DEG = true;
			
			Arcotangente =  new BigDecimal(Trigonometry.PI.doubleValue()/2-Arctan(1/x));
			
			RAD_DEG = bup;
			
			Break = true;
			
		}
		
		if (condition1){
			
			Arcotangente = Arcotangente.subtract(Trigonometry.PI);
			
		}
		
		for (int n=0; n<=100; n++){
			
			if (Break){break;}
			
			Arcotangente = Arcotangente.add(new BigDecimal(new Mayth().Potencia(-1, n)).multiply(bigPotencia(x,2*n+1).divide(new BigDecimal(2*n+1), MathContext.DECIMAL128)));
			
		}
		
		if (RAD_DEG==false){
			
			Arcotangente = Arcotangente.multiply(new BigDecimal(180)).divide(Trigonometry.PI, MathContext.DECIMAL128);
			
		}
		
		return Arcotangente.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public double Csc(double x){
		
		if (x!=0 && x!=180 && x!=360){
			
			return 1d/Sen(x);
			
		}else{
			
			return Double.NaN;
			
		}
		
	}
	
	public double Arccsc(double x){
		
		return Arcsen(1/x);
		
	}
	
	public double Sec(double x){
		
		if (x!=90 && x!=270){
			
			return 1d/Cos(x);
			
		}else{
			
			return Double.NaN;
			
		}
		
	}
	
	public double Arcsec(double x){
		
		return Arccos(1/x);
		
	}
	
	public double Cot(double x){
		
		if (x!=0 && x!=180 && x!=360){
			
			return Cos(x)/Sen(x);
			
		}else{
			
			return Double.NaN;
			
		}
		
	}
	
	public double Arccot(double x){
		
		return Arctan(1/x);
		
	}

}