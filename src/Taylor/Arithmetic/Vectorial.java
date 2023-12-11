package Taylor.Arithmetic;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
 
public class Vectorial{

	private final Parametric[] v;
	private final int length;
	
	private final Parametric cx;
	private final Parametric cy;
	private final Parametric cz;
	
	private final char[] variable;
	private String name;
	
	public Vectorial(Parametric x, Parametric y){
		
		this.cx = x;
		this.cy = y;
		this.cz = new Parametric('t', "0", "h");
		
		this.name = "v";
		this.cx.setName("f");
		this.cy.setName("g");
		
		this.v = new Parametric[] {this.cx, this.cy};
		
		this.variable = getVar();
		this.length = v.length;
		
	}
	
	public Vectorial(Parametric x, Parametric y, Parametric z){
		
		this.cx = x;
		this.cy = y;
		this.cz = z;
		
		this.name = "v";
		this.cx.setName("f");
		this.cy.setName("g");
		this.cz.setName("h");
		
		this.v = new Parametric[] {this.cx, this.cy, this.cz};
		
		this.variable = getVar();
		this.length = v.length;
		
	}
	
	public Vectorial(Parametric[] cv){
		
		if (cv.length>=3){
			
			this.cx = cv[0];
			this.cy = cv[1];
			this.cz = cv[2];
			
		}else if (cv.length==2){
			
			this.cx = cv[0];
			this.cy = cv[1];
			this.cz = new Parametric('t', "0", "v_2");
			
		}else if (cv.length==1){
			
			this.cx = cv[0];
			this.cy = new Parametric('t', "0", "v_1");
			this.cz = new Parametric('t', "0", "v_2");
			
		}else{
			
			this.cx = new Parametric('t', "0", "v_0");
			this.cy = new Parametric('t', "0", "v_1");
			this.cz = new Parametric('t', "0", "v_2");
			
		}
		
		this.name = "v";
		
		this.v = cv;
		
		for (int i=3; i<this.v.length; i++){
			
			this.v[i].setName(this.name+"_"+i);
			
		}
		
		this.variable = getVar();
		this.length = v.length;
		
	}
	
	public Parametric[] get(){
		
		return this.v;
		
	}
	
	public Parametric get(int indice){
		
		return this.v[indice];
		
	}
	
	public char[] getVariable(){
		
		return this.variable;
		
	}
	
	public Parametric getComponentX(){
		
		return this.cx;
		
	}
	
	public Parametric getComponentY(){
		
		return this.cy;
		
	}
	
	public Parametric getComponentZ(){
		
		return this.cz;
		
	}
	
	public String toString(){
		
		String bup = this.name+"("+getInputs()+") = <";
		
		for (Parametric p : this.v){
			
			bup+= p.getFunction()+", ";
			
		}
		
		return bup.substring(0, bup.length()-2)+">";
		
	}
	
	private String getInputs(){
		
		String bup = "";
		
		for (char q : this.variable){
			
			bup+= q+", ";
			
		}
		
		return bup.substring(0, bup.length()-2);
		
	}
	
	private char[] getVar(){
		
		ArrayList<String> chars = new ArrayList<>();
		
		try {
			
			for (char q : this.v[0].getVariable()){
			
				chars.add(q+"");
				
			}
			
		}catch(Exception e){
			
			return new char[] {};
			
		}
		
		for (int i=1; i<this.v.length; i++){
			
			for (char q : this.v[i].getVariable()){
			
				if (chars.contains(q+"")==false){
					
					chars.add(q+"");
					
				}
				
			}
			
		}
		
		char[] varChar = new char[chars.size()];
		
		for (int i=0; i<varChar.length; i++){
			
			varChar[i] = chars.get(i).charAt(0);
			
		}
		
		return varChar;
		
	}

}