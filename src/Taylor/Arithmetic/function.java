package Taylor.Arithmetic;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.util.ArrayList;
 
public class function{
	
	private final char variable;
	private String function;
	private String name;
	
	private ArrayList<Parser> p = new ArrayList<>();
	private ArrayList<Double> values = new ArrayList<>();
	private double n = Double.NaN;

	public function(char variable){
		
		this.variable = variable;
		this.function = variable+"";
		this.name = "f";
		
	}
	
	public function(char variable, String function){
		
		this.variable = variable;
		this.function = function;
		this.name = "f";
		
	}
	
	public function(char variable, String function, String name){
		
		this.variable = variable;
		this.function = function;
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
	
	public void setFuntion(String function){
		
		this.function = function;
		
		this.p.clear();
		this.n = Double.NaN;
		
	}
	
	public void setName(String name){
		
		this.name = name;
		
	}
	
	public boolean isConstant(){
		
		if (this.function.contains(this.variable+"")){
			
			return true;
			
		}
		
		return false;
		
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
	
	public Parser get(double n){
		
		this.p.add(new Parser(Overwrite(n)));
		this.n = n;
		
		return this.p.get(0);
		
	}
	
	public void set(double n){
		
		this.p.add(new Parser(Overwrite(n)));
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
					
					this.p.add(new Parser(Overwrite(b)));
					
				}catch(Exception e){
					
					this.p.add(null);
					
				}
				
				break;
				
			}
			
			values.add(a+(n*increment));
			
			try {
				
				this.p.add(new Parser(Overwrite(values.get(values.size()-1))));
				
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

}