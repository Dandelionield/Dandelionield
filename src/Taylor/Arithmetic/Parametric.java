package Taylor.Arithmetic;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
 
public class Parametric{
	
	private final char[] variable;
	private String function;
	private String name;
	
	private ArrayList<ArrayList<Parser>> p = new ArrayList<>();
	private ArrayList<ArrayList<Double>> values = new ArrayList<>();
	private ArrayList<Double> n = new ArrayList<>();
	
	public Parametric(char x){
		
		name = "f";
		function = x+"";
		variable = new char[] {x};
		
	}
	
	public Parametric(char x, String function){
		
		name = "f";
		this.function = function;
		variable = new char[] {x};
		
	}
	
	public Parametric(char x, String function, String name){
		
		this.name = name;
		this.function = function;
		variable = new char[] {x};
		
	}
	
	public Parametric(char x, char y){
		
		name = "f";
		function = x+"*"+y;
		variable = new char[] {x, y};
		
	}
	
	public Parametric(char x, char y, String function){
		
		name = "f";
		this.function = function;
		variable = new char[] {x, y};
		
	}
	
	public Parametric(char x, char y, String function, String name){
		
		this.name = name;
		this.function = function;
		variable = new char[] {x, y};
		
	}

	public Parametric(char x, char y, char z){
		
		name = "f";
		function = x+"*"+y+"*"+z;
		variable = new char[] {x, y, z};
		
	}
	
	public Parametric(char x, char y, char z, String function){
		
		name = "f";
		this.function = function;
		variable = new char[] {x, y, z};
		
	}
	
	public Parametric(char x, char y, char z, String function, String name){
		
		this.name = name;
		this.function = function;
		variable = new char[] {x, y, z};
		
	}
	
	public Parametric(char[] variable){
		
		name = "f";
		this.variable = variable;
		
		for (char p : this.variable){
			
			function+= p+"*";
			
		}
		
		function+="1";
		
	}
	
	public Parametric(char[] variable, String function){
		
		name = "f";
		this.variable = variable;
		this.function = function;
		
	}
	
	public Parametric(char[] variable, String function, String name){
		
		this.name = name;
		this.variable = variable;
		this.function = function;
		
	}
	
	public char[] getVariable(){
		
		return this.variable;
		
	}
	
	public String getFunction(){
		
		return this.function;
		
	}
	
	public void setFuntion(String function){
		
		this.function = function;
		
		this.p.clear();
		this.n.clear();
		
	}
	
	public void setName(String name){
		
		this.name = name;
		
	}
	
	public boolean isConstant(){
		
		for (char p : this.variable){
			
			if (this.function.contains(p+"")){
				
				return false;
				
			}
			
		}
		
		return false;
		
	}
	
	public long countVar(){
		
		long cv = 0;
		
		for (int i=0; i<this.variable.length; i++){
			
			if (this.function.contains(this.variable[i]+"")){
				
				cv++;
				
			}
			
		}
		
		return cv;
		
	}
	
	public String toString(){
		
		String bup = "";
		
		int c = 0;
		
		if (this.n.size()==0 && this.p.size()==0){

			return this.name+"("+getVar()+") = "+function;
			
		}else if (this.n.size()!=1 && this.p.size()==1 && this.p.get(0).size()==1){
			
			return this.name+"("+getValues(this.n)+") = "+p.get(0).get(0);
			
		}else{
			
			bup = this.name+"("+getVar()+") = "+function+"\n\n";
		
			for	(ArrayList<Parser> q : this.p){
			
				for (Parser d : q){
					
					if (d!=null){
						
						if (d.get()!=null){
							
							bup+= this.name+"("+this.xyz.get(c)+") = "+d+"\n";
							
						}else{
							
							bup+= this.name+"("+this.xyz.get(c)+") = "+Overwrite(toArrayDouble(this.xyz.get(c)))+"\n";
							
						}
						
					}else{
						
						bup+= this.name+"("+this.xyz.get(c)+") = "+Overwrite(toArrayDouble(this.xyz.get(c)))+"\n";
						
					}
					
					c++;
					
				}
			
			}
			
		}
		
		return bup;
		
	}
	
	public BigDecimal get(double[] n){
		
		if (n.length>this.variable.length){
			
			throwError("more parameters antered than existed variables");
			
		}else if (n.length<this.variable.length){
			
			throwError("less parameters antered than existed variables");
			
		}
		
		Parser par = new Parser(Overwrite(n));
		
		ArrayList<Parser> a = new ArrayList<>();
		
		a.add(par);
		
		this.p.add(a);
		
		for (double p : n){
			
			this.n.add(p);
			
		}
		
		return this.p.get(0).get(0).get();
		
	}
	
	public void set(double[] n){
		
		if (n.length>this.variable.length){
			
			throwError("more parameters antered than existed variables");
			
		}else if (n.length<this.variable.length){
			
			throwError("less parameters antered than existed variables");
			
		}
		
		Parser par = new Parser(Overwrite(n));
		
		ArrayList<Parser> a = new ArrayList<>();
		
		a.add(par);
		
		this.p.add(a);
		
		for (double p : n){
			
			this.n.add(p);
			
		}
		
	}
	
	public void intervalueOf(double[] a, double[] b){
		
		this.intervalueOf(a, b, 1);
		
	}
	
	private ArrayList<String> xyz = new ArrayList<>();
	
	public void intervalueOf(double[] a, double[] b, double increment){
		
		if (a.length>this.variable.length || b.length>this.variable.length){
			
			throwError("more parameters antered than existed variables");
			
		}else if (a.length<this.variable.length || b.length<this.variable.length){
			
			throwError("less parameters antered than existed variables");
			
		}
		
		this.xyz.clear();
		this.p.clear();
		this.values.clear();
		this.n.clear();
		function f;
		Parametric par;
		double[] newA = new double[a.length-1];
		double[] newB = new double[b.length-1];
		char[] newVariable = new char[this.variable.length-1];
		ArrayList<Parser> bup;
		double input = 0;
		
		long n = 0;
		
		if (this.countVar()==1){
			
			f = new function(this.variable[0], this.function, this.name);
			
			f.intervalueOf(a[0], b[0], increment);
			
			this.p.add(f.getParsers());
			this.values.add(f.getValues());
			
			for (double q : f.getValues()){
				
				xyz.add(q+"");
				
			}
			
		}else{
			
			for (int i=0; i<newA.length; i++){
				
				newA[i] = a[i+1];
				
			}
			
			for (int i=0; i<newB.length; i++){
				
				newB[i] = b[i+1];
				
			}
			
			for (int i=0; i<newVariable.length; i++){
				
				newVariable[i] = this.variable[i+1];
				
			}
			
			do{
				
				input = a[0]+(n*increment);
				
				if (input>=b[0]){
					
					par = new Parametric(newVariable, Overwrite(this.variable[0], b[0]), this.name);
					
					input = b[0];
					
				}else{
					
					par = new Parametric(newVariable, Overwrite(this.variable[0], input), this.name);
					
				}
				
				par.intervalueOf(newA, newB, increment);
				
				for (ArrayList<Parser> q : par.p){
					
					this.p.add(q);
					
				}
				
				for (ArrayList<Double> q : par.values){
					
					this.values.add(q);
					
				}
				
				for (String q : par.xyz){
					
					this.xyz.add(input+", "+q);
					
				}
				
				n++;
				
			}while(input<b[0]);
			
		}
		
	}
	
	public String getProcess(){
		
		String bup = "";
		
		int c = 0;
		
		for	(ArrayList<Parser> q : this.p){
		
			for (Parser d : q){
				
				if (d!=null){
					
					if (d.get()!=null){
						
						bup+= this.name+"("+this.xyz.get(c)+") = "+d+"\n\n"+d.getProcess()+"\n\n";
						
					}else{
						
						bup+= this.name+"("+this.xyz.get(c)+") = "+Overwrite(toArrayDouble(this.xyz.get(c)))+"\n\n";
						
					}
					
				}else{
					
					bup+= this.name+"("+this.xyz.get(c)+") = "+Overwrite(toArrayDouble(this.xyz.get(c)))+"\n\n";
					
				}
				
				c++;
				
			}
		
		}
		
		return bup;
		
	}
	
	private String Overwrite(double[] value){
		
		boolean b = true;
		
		String wd = "";
		
		for (int f=0; f<this.function.length(); f++){
			
			for (int c=0; c<this.variable.length; c++){
				
				if (this.function.charAt(f)==this.variable[c]){
					
					wd+= value[c];
					
					b = false;
					
					break;
					
				}
				
				b = true;
				
			}
			
			if (b==true){
			 
				wd+= this.function.charAt(f);
				
			}
			
		}
		
		return wd;
		
	}
	
	private String Overwrite(char v, double value){
		
		String wd = "";
		
		for (char p : this.function.toCharArray()){
			
			if (p==v){
				
				wd+= value+"";
				
				continue;
				
			}
			
			wd+= p+"";
			
		}
		
		return wd;
		
	}
	
	private String getVar(){
		
		String bup = "";
		
		for(char p : this.variable){
			
			bup+= p+", ";
			
		}
		
		return bup.substring(0, bup.length()-2);
		
	}
	
	private String getValues(ArrayList<Double> val){
		
		String bup = "";
		
		for(double p : val){
			
			bup+= p+", ";
			
		}
		
		return bup.substring(0, bup.length()-2);
		
	}
	
	private double[] toArrayDouble(String wd){
		
		String[] parts = wd.split(", ");
		double[] db = new double[parts.length];
		
		int c = 0;
		
		for (String q : parts){
			
			db[c] = new BigDecimal(q).setScale(15, RoundingMode.HALF_UP).doubleValue();
			
			c++;
			
		}
		
		return db;
		
	}
	
	private void throwError(String ErrorMessage){
		
		try {

            throw new RuntimeException(ErrorMessage);
			
        } catch (RuntimeException e) {

            e.printStackTrace();
			
			System.exit(0);
			
        }
		
	}

}