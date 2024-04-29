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
	
	private final String n;
	private final String IntegerPart;
	private final String DecimalPart;
	
	private boolean Notation;
	
	public Digit(double n){
		
		this.n = n+"";
		this.Notation = true;
		
		this.DPN = !Double.isNaN(n) ? (!Double.isInfinite(n) ? getNotation(true) : "Infinity") : "NaN";
		this.DDN = DPN.replace(".", "%").replace(",", ".").replace("%", ",");
		this.CN = DPN.replace(",", "");
		
		String[] bup = this.CN.split("\\.");
		
		this.IntegerPart = bup.length>0 ? bup[0] : this.CN;
		this.DecimalPart = bup.length>1 ? bup[1] : "0";
		
	}
	
	public Digit(String n){
		
		this.n = n;
		this.Notation = true;
		
		this.DPN = !n.equals("NaN") ? (!n.equals("Infinity") ? getNotation(true) : "Infinity") : "NaN";
		this.DDN = DPN.replace(".", "%").replace(",", ".").replace("%", ",");
		this.CN = DPN.replace(",", "");
		
		String[] bup = this.CN.split("\\.");
		
		this.IntegerPart = bup.length>0 ? bup[0] : this.CN;
		this.DecimalPart = bup.length>1 ? bup[1] : "0";
		
	}
	
	public Digit(double n, boolean value){
		
		this.n = n+"";
		this.Notation = value;
		
		this.DPN = !Double.isNaN(n) ? (!Double.isInfinite(n) ? getNotation(true) : "Infinity") : "NaN";
		this.DDN = DPN.replace(".", "%").replace(",", ".").replace("%", ",");
		this.CN = DPN.replace(",", "");
		
		String[] bup = this.CN.split("\\.");
		
		this.IntegerPart = bup.length>0 ? bup[0] : this.CN;
		this.DecimalPart = bup.length>1 ? bup[1] : "0";
		
	}
	
	public Digit(String n, boolean value){
		
		this.n = n;
		this.Notation = value;
		
		this.DPN = !n.equals("NaN") ? (!n.equals("Infinity") ? getNotation(true) : "Infinity") : "NaN";
		this.DDN = DPN.replace(".", "%").replace(",", ".").replace("%", ",");
		this.CN = DPN.replace(",", "");
		
		String[] bup = this.CN.split("\\.");
		
		this.IntegerPart = bup.length>0 ? bup[0] : this.CN;
		this.DecimalPart = bup.length>1 ? bup[1] : "0";
		
	}
	
	public Digit(long IntegerPart, long DecimalPart){
		
		this.n = IntegerPart+"."+DecimalPart;
		this.Notation = true;
		
		this.DPN = getNotation(true);
		this.DDN = DPN.replace(".", "%").replace(",", ".").replace("%", ",");
		this.CN = DPN.replace(",", "");
		
		this.IntegerPart = IntegerPart+"";
		this.DecimalPart = DecimalPart+"";
		
	}
	
	public Digit(long IntegerPart, long DecimalPart, boolean value){
		
		this.n = IntegerPart+"."+DecimalPart;
		this.Notation = value;
		
		this.DPN = getNotation(true);
		this.DDN = DPN.replace(".", "%").replace(",", ".").replace("%", ",");
		this.CN = DPN.replace(",", "");
		
		this.IntegerPart = IntegerPart+"";
		this.DecimalPart = DecimalPart+"";
		
	}
	
	public Digit(String IntegerPart, String DecimalPart){
		
		this.n = IntegerPart+"."+DecimalPart;
		this.Notation = true;
		
		this.DPN = getNotation(true);
		this.DDN = DPN.replace(".", "%").replace(",", ".").replace("%", ",");
		this.CN = DPN.replace(",", "");
		
		this.IntegerPart = IntegerPart;
		this.DecimalPart = DecimalPart;
		
	}
	
	public Digit(String IntegerPart, String DecimalPart, boolean value){
		
		this.n = IntegerPart+"."+DecimalPart;
		this.Notation = value;
		
		this.DPN = getNotation(true);
		this.DDN = DPN.replace(".", "%").replace(",", ".").replace("%", ",");
		this.CN = DPN.replace(",", "");
		
		this.IntegerPart = IntegerPart;
		this.DecimalPart = DecimalPart;
		
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
	
	public String toNotation(String Period, String Decimal){
		
		return this.DPN.replace(".", "%").replace(",", Period).replace("%", Decimal);
		
	}
	
	public String toBase(int Base){
		
        return (Base>=2 && Base<=16) ? (this.IntegerPart.compareTo("0")<0 ? "-" : "") + toBase(Long.parseLong(this.IntegerPart.compareTo("0")<0 ? this.IntegerPart.substring(1) : this.IntegerPart), Base) + (this.Notation ? "." : ",") + toBase(Long.parseLong(this.DecimalPart), Base) : null;
		
	}
	
	public byte compareTo(Digit n){
		
		if (this.CN.equals(n.CN)){
			
			return 0;
			
		}
		
		long a = 0;
		long b = 0;
		long c = 0;
		long d = 0;
		
		for (char p : this.abs().IntegerPart.toCharArray()){
			
			a = p + a;
			
		}
		
		for (char p : this.DecimalPart.toCharArray()){
			
			b = p + b;
			
		}
		
		for (char p : n.abs().IntegerPart.toCharArray()){
			
			c = p + c;
			
		}
		
		for (char p : n.DecimalPart.toCharArray()){
			
			d = p + d;
			
		}
		
		byte Int;
		byte Dec;
		
		if (b<d){
			
			Dec = -1;
			
		}else if (b>d){
			
			Dec = 1;
			
		}else{
			
			Dec = 0;
			
		}
		
		if ((this.CN.charAt(0)!='-' ? a : -a)<(n.CN.charAt(0)!='-' ? c : -c)){
			
			Int = -1;
			
		}else if ((this.CN.charAt(0)!='-' ? a : -a)>(n.CN.charAt(0)!='-' ? c : -c)){
			
			Int = 1;
			
		}else{
			
			Int = 0;
			
		}
		
		if (Int==1){
			
			return 1;
			
		}else if (Int==-1){
			
			return -1;
			
		}else{
			
			if (Dec==1){
				
				return 1;
				
			}else if (Dec==-1){
				
				return -1;
				
			}
			
		}
		
		return 0;
		
	}
	
	public byte compareTo(double n){
		
		return this.compareTo(new Digit(n));
		
	}
	
	public Digit add(Digit n){
		
		if (this.compareTo(0)<0 && n.compareTo(0)<0){
			
			return this.abs().add(n.abs()).multiply(-1);
			
		}else if (this.abs().compareTo(n.abs())<0 && (this.compareTo(0)<0 || n.compareTo(0)<0)){
			
			return n.multiply(-1).add(this.multiply(-1)).multiply(-1);
			
		}else if (this.abs().compareTo(n.abs())>0 && this.compareTo(0)<0){
			
			return this.multiply(-1).add(n.multiply(-1)).multiply(-1);
			
		}
		
		String a = this.CN;
		String b = n.compareTo(0)<0 ? n.CN.substring(1) : n.CN;
		String c = "";
		
		int ia = a.indexOf(".")!= -1 ? a.substring(a.indexOf(".")+1).length() : 0;
		int ib = b.indexOf(".")!= -1 ? b.substring(b.indexOf(".")+1).length() : 0;
		
		int indexComma = ia>ib ? ia : ib;
		
		if (ia<ib){
			
			for (int i=1; i<=ib-ia; i++){
				
				a+= "0";
				
			}
			
		}else if (ib<ia){
			
			for (int i=1; i<=ia-ib; i++){
				
				b+= "0";
				
			}
			
		}
		
		a = a.replace(".", "");
		b = b.replace(".", "");
		
		if (a.length()<b.length()){
			
			int limite = b.length()-a.length();
			
			for (int i=1; i<=limite; i++){
				
				a = "0"+a;
				
			}
			
		}else if (b.length()<a.length()){
			
			int limite = a.length()-b.length();
			
			for (int i=1; i<=limite; i++){
			
				b = "0"+b;
				
			}
			
		}
		
		int r = 0;
		
		for (int i=a.length()-1; i>=0; i--){
			
			r = (n.compareTo(0)<0 ? (a.charAt(i) - b.charAt(i)) : (a.charAt(i) + b.charAt(i) - '0' - '0')) + r;
			
			c = n.compareTo(0)>0 ? ((r+"").charAt(r>9 ? 1 : 0))+c : (r<0 ? (10 + r)+"" : r+"")+c;
			
			r = n.compareTo(0)>0 ? (r<10 ? 0 : (r+"").charAt(0) - '0') : (r<0 ? -1 : 0);
			
		}
		
		if (r!=0){
			
			c = r+c;
			
		}
		
		return new Digit(c.substring(0, c.length()-indexComma) + (indexComma!=0 ? "." : "") + c.substring(c.length()-indexComma), this.Notation);
		
	}//*/
	
	public Digit multiply(Digit n){
		
		if (n.compareTo(-1)==0 && this.compareTo(0)>0){
			
			return new Digit("-"+this.CN, this.Notation);
			
		}else if (n.compareTo(-1)==0 && this.compareTo(0)<0){
			
			return this.abs();
			
		}
		
		return null;
		
	}
	
	public Digit multiply(double n){
		
		return this.multiply(new Digit(n));
		
	}
	
	public Digit abs(){
		
		return this.CN.charAt(0)=='-' ? new Digit(this.CN.substring(1), this.Notation) : this;
		
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
			
		}else if (n.substring(n.indexOf(".")+1).equals("0")==false){
			
			DN = value.replace(".", DecimalNotation);
			
		}else{
			
			DN = n.substring(0, n.indexOf("."));
			
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