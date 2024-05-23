package Taylor.Arithmetic;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
import java.util.ArrayList;
 
public class function{
	
	private final char variable;
	private String function;
	private String name;
	
	private ArrayList<Parser> p = new ArrayList<>();
	private ArrayList<Double> values = new ArrayList<>();
	private double n = Double.NaN;
	
	private boolean RAD_DEG = false;
	private boolean functionType = false;
	
	public function(double value){
		
		this.variable = 'x';
		this.function = value+"";
		this.name = "f";
		
	}

	public function(char variable){
		
		this.variable = variable;
		this.function = variable+"";
		this.name = "f";
		
	}
	
	public function(char variable, String function){
		
		this.variable = variable;
		this.function = fix(function);
		this.name = "f";
		
	}
	
	public function(char variable, String function, String name){
		
		this.variable = variable;
		this.function = fix(function);
		this.name = name;
		
	}
	
	public char getVariable(){
		
		return this.variable;
		
	}
	
	public String getFunction(){
		
		return this.function;
		
	}
	
	public ArrayList<Parser> getParsers(){
		
		return this.p;
		
	}
	
	public ArrayList<Double> getValues(){
		
		return this.values;
		
	}
	
	public Parser getOutput(int indice){
		
		return this.p.get(indice);
		
	}
	
	public double getInput(int indice){
		
		return this.values.get(indice);
		
	}
	
	public void setFuntion(String function){
		
		this.function = function;
		
		this.p.clear();
		this.n = Double.NaN;
		
	}
	
	public void setName(String name){
		
		this.name = name;
		
	}
	
	public void reset(){
		
		this.p = new ArrayList<>();
		this.values = new ArrayList<>();
		
	}
	
	public String getName(){
		
		return this.name;
		
	}
	
	public void setTrigonometryMode(boolean value){
		
		this.RAD_DEG = value;
		
	}
	
	public void setParserFunctionType(boolean value){
		
		this.functionType = value;
		
	}
	
	public boolean isConstant(){
		
		if (this.function.contains(this.variable+"")){
			
			return false;
			
		}
		
		return true;
		
	}
	
	public String toString(){
		
		String bup = "";
		
		Parser q;
		
		if (Double.isNaN(this.n)==true && this.p.size()==0){

			return this.name+"("+this.variable+") = "+function;
			
		}else if (Double.isNaN(this.n)==false && this.p.size()==1){
			
			return this.name+"("+this.n+") = "+p.get(0);
			
		}else{
			
			bup = this.name+"("+this.variable+") = "+function+"\n\n";
			
			for (int i=0; i<this.p.size(); i++){
				
				q = this.p.get(i);
				
				if (q!=null){
					
					if (q.get()!=null){
						
						bup+= name+"("+this.values.get(i)+") = "+this.p.get(i)+"\n";
						
					}else{
						
						bup+= name+"("+this.values.get(i)+") = "+Overwrite(this.values.get(i))+" = "+Double.NaN+"\n";
						
					}
					
				}else{
					
					bup+= name+"("+this.values.get(i)+") = "+Overwrite(this.values.get(i))+" = "+Double.NaN+"\n";
					
				}
				
			}
			
		}
		
		return bup;
		
	}
	
	public BigDecimal integralOf(double a, double b, int n){
		
		BigDecimal Delta = (new BigDecimal(b).subtract(new BigDecimal(a))).divide(new BigDecimal(n), MathContext.DECIMAL128);
		BigDecimal Zigma = new BigDecimal(0);
		
		this.intervalueOf(a, b, Delta.setScale(15, RoundingMode.HALF_UP).doubleValue());
		
		for(Parser q : this.getParsers()){
			
			if (q.get()==null){continue;}
			
			Zigma = Zigma.add(Delta.multiply(q.get()));
			
		}
		
		this.reset();
		
		return Zigma;
		
	}
	
	public BigDecimal productoryOf(double a, double b){
		
		BigDecimal Zigma = new BigDecimal(1);
		
		this.intervalueOf(a, b);
		
		for(Parser q : this.getParsers()){
			
			if (q.get()==null){continue;}
			
			Zigma = Zigma.multiply(q.get());
			
		}
		
		this.reset();
		
		return Zigma;
		
	}
	
	public Parser get(BigDecimal n){
		
		this.p.add(new Parser(Overwrite(n), RAD_DEG, functionType));
		this.n = n.doubleValue();
		
		return this.p.get(this.p.size()-1);
		
	}
	
	public Parser get(double n){
		
		this.p.add(new Parser(Overwrite(n), RAD_DEG, functionType));
		this.n = n;
		
		return this.p.get(this.p.size()-1);
		
	}
	
	public void set(double n){
		
		this.p.add(new Parser(Overwrite(n), RAD_DEG, functionType));
		this.n = n;
		
	}
	
	public void intervalueOf(double a, double b){
		
		this.intervalueOf(a, b, 1);
		
	}
	
	public void intervalueOf(double a, double b, double increment){
		
		this.p.clear();
		this.values.clear();
		this.n = Double.NaN;
		
		long n = 0;
		
		do{
			
			if (a>b){
				
				if (increment>0){
					
					intervalueOf(b, a, increment);
					
				}else{
					
					intervalueOf(b, a, -increment);
					
				}
				
				break;
				
			}else if (increment<0){
				
				increment = -increment;
				
			}
			
			if (a+(n*increment)>=b){
				
				values.add(b);
				
				try {
					
					this.p.add(new Parser(Overwrite(b), RAD_DEG, functionType));
					
				}catch(Exception e){
					
					this.p.add(null);
					
				}
				
				break;
				
			}
			
			values.add(a+(n*increment));
			
			try {
				
				this.p.add(new Parser(Overwrite(values.get(values.size()-1)), RAD_DEG, functionType));
				
			}catch(Exception e){
				
				this.p.add(null);
				
			}
			
			n++;
			
		}while(a+(n*increment)<=b);
		
	}
	
	public String getProcess(){
		
		String bup = "";
		
		Parser q;
		
		if (Double.isNaN(this.n)==false && p.size()==1){
			
			return this.toString()+"\n\n"+p.get(0).getProcess();
			
		}else if (Double.isNaN(this.n)==true && this.p.size()>=1){
			
			for (int i=0; i<this.p.size(); i++){
				
				q = this.p.get(i);
				
				if (q!=null){
					
					if (q.get()!=null){
						
						bup+= name+"("+this.values.get(i)+") = "+this.p.get(i)+"\n\n"+p.get(i).getProcess()+"\n\n";
						
					}else{
						
						bup+= name+"("+this.values.get(i)+") = "+Overwrite(this.values.get(i))+" = "+Double.NaN+"\n\n";
						
					}
					
				}else{
					
					bup+= name+"("+this.values.get(i)+") = "+Overwrite(this.values.get(i))+" = "+Double.NaN+"\n\n";
					
				}
				
			}
			
		}
		
		return bup;
		
	}
	
	private String fix(String func){
		
		String wd = "";
		char p;

		for (int i=0; i<func.length(); i++) {
			
			p = func.charAt(i);

			if (p==this.variable){
				
				if (i>0 && (Character.isLetter(func.charAt(i - 1)) || Character.isDigit(func.charAt(i - 1)))){
					
					wd+= "*";
					
				}

				wd+= p+"";

				if (i<func.length()-1 && (Character.isLetter(func.charAt(i + 1)) || func.charAt(i + 1)=='(')){
					
					wd+= "*";
					
				}
				
			}else{
				
				wd+= p+"";
				
			}
			
		}

		return wd;
		
	}
	
	public String Overwrite(double value){
		
		String wd = "";
		
		for (char p : this.function.toCharArray()){
			
			if (p==this.variable){
				
				wd+= ""+value+"";
				
				continue;
				
			}
			
			wd+= p+"";
			
		}
		
		return wd;
		
	}
	
	public String Overwrite(BigDecimal value){
		
		String wd = "";
		
		for (char p : this.function.toCharArray()){
			
			if (p==this.variable){
				
				wd+= ""+value+"";
				
				continue;
				
			}
			
			wd+= p+"";
			
		}
		
		return wd;
		
	}

}