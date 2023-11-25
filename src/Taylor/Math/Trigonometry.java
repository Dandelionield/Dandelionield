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
			
			x = (x*Math.PI)/180;
			
		}
		
		for (int n=0; n<=100; n++){
			
			Seno = Seno.add(Mayth.bigPotencia(-1, n).multiply(Mayth.bigPotencia(x,2*n+1).divide(bigFactorial(2*n+1), 100, RoundingMode.HALF_UP)));
			
		}
		
		return Seno.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public static double Arcsen(double x){
		
		BigDecimal Arcoseno = new BigDecimal(0);
		
		int limite = 0;
		
		if (x==1){
			
			if (RAD_DEG==false){
				
				return 90;
				
			}else{
				
				return Math.PI/2;
				
			}
			
		}else if (x==-1){
			
			if (RAD_DEG==false){
				
				return 270;
				
			}else{
				
				return 3*Math.PI/2;
				
			}
			
		}else if (x==0){
			
			return 0;
			
		}
		
		boolean bup = Trigonometry.RAD_DEG;
		
		Trigonometry.RAD_DEG = true;
		
		Arcoseno = new BigDecimal(Trigonometry.Arctan(x/Math.sqrt(1-x*x)));
		
		Trigonometry.RAD_DEG = bup;
		
		if (RAD_DEG==false){
			
			Arcoseno = Arcoseno.multiply(new BigDecimal(180)).divide(new BigDecimal(Math.PI), MathContext.DECIMAL128);
			
		}
		
		return Arcoseno.setScale(15, RoundingMode.HALF_UP).doubleValue(); 
		
	}
	
	public static double Cos(double x){
		
		BigDecimal Cos = new BigDecimal(0);
		
		if (RAD_DEG==false){
			
			x = (x*Math.PI)/180;
			
		}
		
		for (int n=0; n<=100; n++){
			
			Cos = Cos.add(Mayth.bigPotencia(-1, n).multiply(Mayth.bigPotencia(x,2*n).divide(bigFactorial(2*n), 100, RoundingMode.HALF_UP)));
			
		}
		
		return Cos.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public static double Arccos(double x){
		
		if (x==-1){
			
			if (RAD_DEG==false){
				
				return 180;
				
			}else{
				
				return Math.PI;
				
			}
			
		}
		
		boolean bup = Trigonometry.RAD_DEG;
		boolean condition = Math.abs(x)>0.984807753012208 && Math.abs(x)<1;
		
		Trigonometry.RAD_DEG = true;
		
		BigDecimal Arcocoseno = (new BigDecimal(Math.PI/2).subtract(new BigDecimal(Arcsen(x))));
		
		Trigonometry.RAD_DEG = bup;
		
		if (RAD_DEG==false){
			
			Arcocoseno = Arcocoseno.multiply(new BigDecimal(180)).divide(new BigDecimal(Math.PI), MathContext.DECIMAL128);
			
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
	
	private static final double Tan136 = Tan(136);
	private static final double Tan269 = Tan(269.000000000000001);
	private static final double Tan270 = Tan(270.0000000000001);
	private static final double Tan360 = Tan(359.000000000000001);
	
	public static double Arctan(double x){
		
		BigDecimal Arcotangente = new BigDecimal(0);
		
		boolean bup = Trigonometry.RAD_DEG;
		boolean condition1 = condition1 = Tan136<=Math.abs(x) && Math.abs(x)<=Tan269;
		boolean condition2 = condition2 = Tan270>=x && x<=Tan360;
		boolean Break = false;
		double acrtan = 0;
		
		if (Math.abs(x)>1){
			
			Trigonometry.RAD_DEG = true;
			
			Arcotangente =  new BigDecimal(Math.PI/2-Arctan(1/x));
			
			Trigonometry.RAD_DEG = bup;
			
			Break = true;
			
		}
		
		/*System.out.print(Tan(136)+" | "+x+" | "+Tan(269.000000000000001)+" a\n\n");	
		System.out.print(condition1+"  "+condition2+" b\n\n");//*/
		
		if (condition1){
			
			//System.out.print(Arcotangente+" c\n\n");
		
			Arcotangente = new BigDecimal(Math.PI).add(Arcotangente);
			
			//System.out.print(Arcotangente+" d\n\n");
			
		}else if (condition2){
			
			Arcotangente = new BigDecimal(2*Math.PI).subtract(Arcotangente);
			
		}//*/
		
		for (int n=0; n<=100; n++){
			
			if (Break){break;}
			
			Arcotangente = Arcotangente.add(Mayth.bigPotencia(-1, n).multiply(Mayth.bigPotencia(x,2*n+1).divide(new BigDecimal(2*n+1), MathContext.DECIMAL128)));
			
		}
		
		if (RAD_DEG==false){
			
			Arcotangente = Arcotangente.multiply(new BigDecimal(180)).divide(new BigDecimal(Math.PI), MathContext.DECIMAL128);
			
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