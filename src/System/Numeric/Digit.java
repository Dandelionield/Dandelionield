package System.Numeric;

/*
 *
 * @author Dandelion
 * 
 */

public class Digit{
	
	private final String DPN;//Decimal Point Notation
	private final String DDN;//Dot Decimal Notation
	private final String CN;//Computer Notation
	
	private final double n;
	private final long IntegerPart;
	private final long DecimalPart;
	
	private boolean Notation;
	
	public Digit(double n){
		
		this.n = n;
		this.Notation = true;
		
		this.DPN = !Double.isNaN(n) ? (!Double.isInfinite(n) ? getNotation(true) : "Infinity") : "NaN";
		this.DDN = DPN.replace(".", "%").replace(",", ".").replace("%", ",");
		this.CN = DPN.replace(",", "");
		
		this.IntegerPart = (long) this.n;
		this.DecimalPart = Long.parseLong(
			
			((this.DPN.contains(".")) ? this.DPN.substring(this.DPN.indexOf('.') + 1) : "0")
			
		);
		
	}
	
	public Digit(double n, boolean value){
		
		this.n = n;
		this.Notation = value;
		
		this.DPN = !Double.isNaN(n) ? (!Double.isInfinite(n) ? getNotation(true) : "Infinity") : "NaN";
		this.DDN = DPN.replace(".", "%").replace(",", ".").replace("%", ",");
		this.CN = DPN.replace(",", "");
		
		this.IntegerPart = (long) this.n;
		this.DecimalPart = Long.parseLong(
			
			((this.DPN.contains(".")) ? this.DPN.substring(this.DPN.indexOf('.') + 1) : "0")
			
		);
		
	}
	
	public long getIntegerPart(){
		
		return this.IntegerPart;
		
	}
	
	public double getDecimalPart(){
		
		return Double.parseDouble(
		
			"0."+this.DecimalPart
			
		);
		
	}
	
	public double getDecimalPartAsInteger(){
		
		return this.DecimalPart;
		
	}
	
	public boolean getNotation(){
		
		return this.Notation;
		
	}
	
	public void setDecimalPointNotation(){
		
		this.setNotation(true);
		
	}
	
	public void setDotDecimalNotation(){
		
		this.setNotation(false);
		
	}
	
	public void setNotation(boolean Notation){
		
		this.Notation = Notation;
		
	}
	
	public String toString(){
		
		return this.Notation ? this.DPN : this.DDN;
		
	}
	
	public String noNotation(){
		
		return this.CN;
		
	}
	
	public String toBase(int Base){
		
        return (Base>=2 && Base<=16) ? (this.IntegerPart<0 ? "-" : "") + toBase(this.IntegerPart<0 ? -this.IntegerPart : this.IntegerPart, Base) + (this.Notation ? "." : ",") + toBase(this.DecimalPart, Base) : null;
		
	}
	
	private String toBase(long n, int Base){
		
		int i = (int) (((double) n)%Base);
		
        String convert = String.valueOf("0123456789ABCDEF".charAt(i));
        
        return (n<Base) ? convert : toBase(n/Base, Base) + convert;
		
	}
	
	private String getNotation(boolean b){
		
		String value = n+"";
		String DecimalNotation = b ? "." : ",";
		String IntegerNotation = b ? "," : ".";
		String INTEGER = "";
		String DECIMAL = "";
		String DN = "";
		String bup = "";
		long nth = 0;
		
		if (value.contains("E")){
			
			INTEGER = value.substring(0, value.indexOf('.'));
			DECIMAL = value.substring(value.indexOf('.') + 1, value.indexOf('E'));
			nth = Long.parseLong(
			
				value.substring(value.indexOf('E') + 1)
			
			);
			
			if (nth>0){
				
				DN =  INTEGER;
				
				for (int i=0; i<nth; i++){
					
					try{
						
						DN+= DECIMAL.charAt(i)+"";
						
					}catch(Exception e){
						
						DN+= "0";
						
					}
					
				}
				
				try{
					
					bup = DECIMAL.substring(DN.substring(INTEGER.length()-1).length()-1);
					
				}catch(Exception e){
					
					bup = "";
					
				}
				
				DN+= bup.equals("") ? "" : DecimalNotation+bup;
				
			}else{
				
				DN =  DECIMAL;
				
				int f = INTEGER.length()-1;
				
				for (int i=-(int)(1+nth); i>=0; i--){
					
					try{
						
						DN = INTEGER.charAt(f)+DN;
						
					}catch(Exception e){
						
						DN = "0"+DN;
						
					}
					
					f--;
					
				}
				
				DN = "0"+DecimalNotation+DN;
				
			}
			
		}else if (n%1!=0){
			
			DN = value.replace(".", DecimalNotation);
			
		}else{
			
			DN = ((long) n)+"";
			
		}
		
		bup = DN;
		int c = -1;
		
		DN = "";
		
		if (bup.contains(DecimalNotation)){
		
			for (int i=bup.length()-1; i>=0; i--){
				
				if (bup.charAt(i)==DecimalNotation.charAt(0)){c = 2;}
				
				if (c==3){
					
					DN+= IntegerNotation;
					
					c = 0;
					
				}
				
				if (c!=-1){
					
					c++;
					
				}
				
				DN+= bup.charAt(i);
				
			}
			
		}else{
			
			for (int i=bup.length()-1; i>=0; i--){
				
				c++;
				
				if (c==3){
					
					DN+= IntegerNotation;
					
					c = 0;
					
				}
				
				DN+= bup.charAt(i);
				
			}
			
		}
		
		return new StringBuilder(DN.charAt(0)==IntegerNotation.charAt(0) ? DN.substring(1) : DN).reverse().toString().replace(IntegerNotation+DecimalNotation, DecimalNotation).replace("-"+IntegerNotation, "-");
		
	}
	
}