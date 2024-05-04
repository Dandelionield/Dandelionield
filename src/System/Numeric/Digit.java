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
	
	public static byte CEILING = 1;
	public static byte HALF_UP = 0;
	public static byte FLOOR = -1;
	
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
		
		this.DPN = this.fix(!n.equals("NaN") ? (!n.equals("Infinity") ? this.getNotation(true) : "Infinity") : "NaN");
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
		
		this.DPN = this.fix(!n.equals("NaN") ? (!n.equals("Infinity") ? this.getNotation(true) : "Infinity") : "NaN");
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
	
	public byte compareTo(double n){
		
		return this.compareTo(new Digit(n));
		
	}
	
	public Digit add(double n){
		
		return this.add(new Digit(n));
		
	}
	
	public Digit subtract(double n){
		
		return this.subtract(new Digit(n));
		
	}
	
	public Digit multiply(double n){
		
		return this.multiply(new Digit(n));
		
	}
	
	public byte compareTo(Digit n){
		
		if (this.CN.equals(n.CN)){
			
			return 0;
			
		}
		
		if (this.CN.length()==n.CN.length()){
			
			for (int i=0; i<this.CN.length(); i++){
				
				if (this.CN.charAt(i)>n.CN.charAt(i)){
					
					return 1;
					
				}else if (this.CN.charAt(i)<n.CN.charAt(i)){
					
					return -1;
					
				}
				
			}
			
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
	
	public Digit add(Digit n){
		
		byte n1 = n.compareTo(0);
		byte m1 = this.compareTo(0);
		byte v1 = this.abs().compareTo(n.abs());
		
		if (n1==0){
			
			return this;
			
		}else if (m1==0){
			
			return n;
			
		}else if (m1<0 && n1<0){
			
			return this.abs().add(n.abs()).multiply(-1);
			
		}else if (v1<0 && (m1<0 || n1<0)){
			
			return n.multiply(-1).add(this.multiply(-1)).multiply(-1);
			
		}else if (v1<0 && (m1>0 && n1<0)){
			
			return n.multiply(-1).add(this.multiply(-1)).multiply(-1);
			
		}else if (v1>0 && m1<0){
			
			return this.multiply(-1).add(n.multiply(-1)).multiply(-1);
			
		}
		
		String a = this.CN;
		String b = n1<0 ? n.CN.substring(1) : n.CN;
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
		
		return indexComma==0 ? new Digit(c) : new Digit(c.substring(0, c.length()-indexComma)+"."+c.substring(c.length()-indexComma), this.Notation);
		
	}
	
	public Digit subtract(Digit n){
		
		if (this.CN.equals(n.CN)){
			
			return new Digit(0);
			
		}
		
		return this.add(n.multiply(-1));
		
	}
	
	public Digit multiply(Digit n){
		
		byte n1 = n.compareTo(0);
		byte m1 = this.compareTo(0);
		byte n2 = n.compareTo(-1);
		byte m2 = this.compareTo(-1);
		byte v1 = this.abs().compareTo(n.abs());
		
		if (this.compareTo(1)==0){
			
			return n;
			
		}else if (n.compareTo(1)==0){
			
			return this;
			
		}else if (m1==0 || n1==0){
			
			return new Digit(0);
			
		}else if (n2==0 && m1>0){
			
			return new Digit("-"+this.CN, this.Notation);
			
		}else if (n2==0 && m1<0){
			
			return this.abs();
			
		}else if (m2==0 && n1>0){
			
			return new Digit("-"+n.CN, n.Notation);
			
		}else if (m2==0 && n1<0){
			
			return n.abs();
			
		}
		
		String a = m1<0 ? this.CN.substring(1) : this.CN;
		String b = n1<0 ? n.CN.substring(1) : n.CN;
		String Zero = "";
		
		int ia = a.indexOf(".")!= -1 ? a.substring(a.indexOf(".")+1).length() : 0;
		int ib = b.indexOf(".")!= -1 ? b.substring(b.indexOf(".")+1).length() : 0;
		
		int indexComma = ia + ib;
		
		a = a.replace(".", "");
		b = b.replace(".", "");
		
		String[] v = new String[b.length()];
		int r = 0;
		
		for (int f=b.length()-1; f>=0; f--){
			
			v[f] = Zero;
			
			r = 0;
			
			for (int c=a.length()-1; c>=0; c--){
				
				r = (a.charAt(c) - '0')*(b.charAt(f) - '0') + r;
				
				if (c!=0){
					
					v[f] = ((r+"").charAt(r>9 ? 1 : 0))+v[f];
					
				}else{
					
					v[f] = r+""+v[f];
					
					break;
					
				}
				
				r = r<10 ? 0 : (r+"").charAt(0) - '0';
				
			}
			
			Zero+= "0";
			
		}
		
		Digit c = new Digit(0);
		
		for (String p : v){
			
			c = c.add(new Digit(p));
			
		}
		
		c = c.multiply(m1).multiply(n1);
		
		if (c.CN.length()-indexComma<=0){
			
			String bup = c.CN.charAt(0)=='-' ? c.CN.substring(1) : c.CN;
			
			int limite = -(c.CN.replace("-", "").length()-indexComma);
			Zero = "0.";
			
			for (int i=0; i<limite; i++){
				
				Zero+= "0";
				
			}
			
			return new Digit((c.CN.charAt(0)=='-' ? "-"+Zero : Zero)+bup, this.Notation);
			
		}else{
			
			return indexComma==0 ? c : new Digit(c.CN.substring(0, c.CN.length()-indexComma)+"."+c.CN.substring(c.CN.length()-indexComma), this.Notation);
			
		}
		
	}
	
	public Digit divide(Digit n, long presicion){
		
		if (n.compareTo(1)==0){
			
			return this;
			
		}else if (this.CN.equals(n.CN)){
			
			return new Digit(1);
			
		}
		
		String a = n.abs().CN;
		String s = "1";
		String c = "";
		
		int indexComma = a.indexOf(".")!= -1 ? a.substring(a.indexOf(".")+1).length() : 0;
		
		a = a.replace(".", "");
		
		if (indexComma!=0){
			
			for (int i=1; i<=indexComma; i++){
				
				s+= "0";
				
			}
			
		}
		
		Digit Divisor = new Digit(a);
		String m = "10";
		long r = 0;
		
		for (int i=0; i<presicion; i++){
			
			r = 0;
			
			Digit v = new Digit(m);
			int n1 = 0;
			
			do{
				
				v = v.subtract(Divisor);
				
				r++;
				
				n1 = v.compareTo(0);
				
				if (n1<0){r--;}
				
			}while(n1>0);
			
			if (n1!=0){
				
				c+= r+"";
				
				if (n1<0){
					
					v = v.add(Divisor);
					
				}
				
			}else{
				
				c+= r+"";
				
				break;
				
			}
			
			m = v.CN+"0";
			
		}
		
		return new Digit("0."+c, this.Notation).multiply(new Digit(n.compareTo(0))).multiply(this.multiply(new Digit(s))).setScale(c.length()-1, Digit.HALF_UP);
		
	}
	
	public Digit setScale(long limite, byte Mode){
		
		if (limite<0){
			
			return this;
			
		}
		
		int indexComma = 0;
		
		if (Mode==Digit.CEILING){
			
			indexComma = this.CN.indexOf(".");
			
			return indexComma==-1 ? this : new Digit(this.CN.substring(0, indexComma), this.Notation).add(1);
			
		}else if (Mode==Digit.HALF_UP){
			
			indexComma = this.CN.indexOf(".");
			
			String a = this.CN.substring(indexComma+1);
			int ia = indexComma!=-1 ? a.length() : 0;
			
			if (indexComma==-1 || ia<=limite){
				
				return this;
				
			}
			
			byte r = 0;
			ia--;
			
			do{
				
				int m = (a.charAt(ia) - '0') + r;
				
				r = m>=5 ? (m>9 ? (byte) 2 : (byte) 1) : 0;
				
				ia--;
				
			}while((ia+1-limite)>-1);
			
			r = r==2 ? 1 : r;
			
			if (ia==-1 && r>0){
				
				return this.setScale(0, Digit.CEILING);
				
			}
			
			String Zero = "0.";
			
			for (int i=0; i<ia-1; i++){
				
				Zero+= "0";
				
			}
			
			return new Digit(this.CN.substring(0, this.CN.length()-(a.length()-ia)), this.Notation).add(new Digit(Zero+""+r));
			
		}else if (Mode==Digit.FLOOR){
			
			indexComma = this.CN.indexOf(".");
			
			return indexComma==-1 ? this : new Digit(this.CN.substring(0, indexComma), this.Notation);
			
		}
		
		return this;
		
	}
	
	public Digit abs(){
		
		return this.CN.charAt(0)=='-' ? new Digit(this.CN.substring(1), this.Notation) : this;
		
	}
	
	private String toBase(long n, int Base){
		
		int i = (int) (((double) n)%Base);
		
        String convert = String.valueOf("0123456789ABCDEF".charAt(i));
        
        return (n<Base) ? convert : toBase(n/Base, Base) + convert;
		
	}
	
	public String fix(String Number){
		
		Number = Number.replace("-.", "-0.");
		
		String Minus = Number.indexOf("-")!=-1 ? "-" : "";
		
		Number = Number.substring(Number.indexOf("-")+1);
		
		for (int f=0; f<Number.length(); f++){
			
			if (Number.charAt(f)!='0' && Number.charAt(f)!=','){
				
				if (Number.contains(".")==false){
					
					return Minus+Number.substring(f);
					
				}
				
				for (int c=Number.length()-1; c>=0; c--){
					
					if (Number.charAt(c)!='0' && Number.charAt(c)!='.'){
						
						return c==Number.length()-1 ? Minus+Number.substring(f) : Minus+Number.substring(f, c + 1);
						
					}
					
				}
				
				return Minus+Number.substring(f);
				
			}else if (Number.indexOf(".")==(f+1)){
				
				for (int c=Number.length()-1; c>=0; c--){
					
					if (Number.charAt(c)!='0' && Number.charAt(c)!='.'){
						
						return c==Number.length()-1 ? Minus+Number.substring(f) : Minus+Number.substring(f, c + 1);
						
					}
					
				}
				
				return Minus+Number.substring(f);
				
			}
			
		}
		
		return Minus+Number;
		
	}
	
	private String getNotation(boolean b){
		
		String value = n+"";
		
		if (n.equals("0") || n.equals("-0")){return "0";}
		
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