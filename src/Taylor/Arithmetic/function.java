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

import Taylor.Math.Mayth;
 
public class function extends Mayth{
	
	private final char variable;
	private String function;
	private String name;
	
	private Parser p = null;
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
	
	public void setFuntion(String function){
		
		this.function = function;
		
		this.p = null;
		this.n = Double.NaN;
		
	}
	
	public void setName(String name){
		
		this.name = name;
		
	}
	
	public String toString(){
		
		if (Double.isNaN(this.n)){

			return this.name+"("+this.variable+") = "+function;
			
		}else{
			
			return this.name+"("+this.n+") = "+p;
			
		}
		
	}
	
	public BigDecimal get(double n){
		
		this.p = new Parser(Overwrite(n));
		this.n = n;
		
		return this.p.get();
		
	}
	
	public void set(double n){
		
		this.p = new Parser(Overwrite(n));
		this.n = n;
		
	}
	
	public String getProcess(){
		
		return this.toString()+"\n\n"+p.getProcess();
		
	}
	
	private String Overwrite(double value){
		
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