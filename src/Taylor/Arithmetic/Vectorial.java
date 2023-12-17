package Taylor.Arithmetic;

/*
 *
 * @author Dandelion
 * 
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import Geometry.Euclidean.vector;
 
public class Vectorial{

	private final Parametric[] v;
	private final int length;
	
	private final Parametric cx;
	private final Parametric cy;
	private final Parametric cz;
	
	private final char[] variable;
	private String name;
	
	ArrayList<ArrayList<Parser>> p = new ArrayList<>();
	ArrayList<vector> output = new ArrayList<>();
	ArrayList<String> input = new ArrayList<>();
	
	private boolean RAD_DEG = false;
	
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
	
	public Vectorial(ArrayList<Parametric> acv){
		
		Parametric[] cv = new Parametric[acv.size()];
		
		for (int i=0; i<cv.length; i++){
			
			cv[i] = acv.get(i);
			
		}
		
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
	
	public Parser[] getOutputAsParser(int indice){
		
		Parser[] out = new Parser[this.p.get(indice).size()];
		
		for (int i=0; i<out.length; i++){
			
			out[i] = this.p.get(indice).get(i);
			
		}
		
		return out;
		
	}
	
	public vector getOutput(int indice){
		
		return this.output.get(indice);
		
	}
	
	public double[] getInput(int indice){
		
		return toArrayDouble(this.input.get(indice));
		
	}
	
	public void setTrigonometryMode(boolean value){
		
		RAD_DEG = value;
		
		for (int i=0; i<this.v.length; i++){
			
			this.v[i].setTrigonometryMode(RAD_DEG);
			
		}
		
	}
	
	public long countVar(){
		
		long n = 0;
		
		for (char q : this.variable){
			
			for (Parametric d : this.v){
				
				if (d.getFunction().contains(q+"")){
					
					n++;
					
					break;
					
				}
				
			}
			
		}
		
		return n;
		
	}
	
	public String toString(){
		
		String bup = "";
		
		if (this.length==0){return this.name+"(0) = <>";}
		
		bup = this.name+"("+getInputs()+") = <";
		
		for (Parametric p : this.v){
			
			bup+= p.getFunction()+", ";
			
		}
		
		bup = bup.substring(0, bup.length()-2)+">";
		
		if (this.p.size()==0){
			
			return bup;
			
		}else{
			
			bup+="\n\n";
			
			for (int i=0; i<this.p.size(); i++){
				
				bup+= this.name+"("+this.input.get(i)+") = <";
				
				for (Parser q : this.p.get(i)){
					
					bup+= q.get(0)+", ";
					
				}
				
				bup = bup.substring(0, bup.length()-2)+"> = "+this.output.get(i)+"\n";
				
			}
			
			return bup;
			
		}
		
	}
	
	public vector get(double[] n){
		
		if (n.length>this.variable.length){
			
			throwError("more parameters antered than existed variables");
			
		}else if (n.length<this.variable.length){
			
			throwError("less parameters antered than existed variables");
			
		}
		
		ArrayList<Double> cv = new ArrayList<>();
		ArrayList<Parser> pa = new ArrayList<>();
		
		for (Parametric q : this.v){
			
			pa.add(q.get(get(q.getVariable(), n)));
			cv.add(pa.get(pa.size()-1).get().setScale(15, RoundingMode.HALF_UP).doubleValue());
			
		}
		
		String bup = "";
		vector a = new vector(cv);
		
		for (double q : n){
			
			bup+= q+", ";
			
		}
		
		bup = bup.substring(0, bup.length()-2)+"";
		
		this.p.add(pa);
		this.input.add(bup);
		this.output.add(a);
		
		return a;
		
	}
	
	public void set(double[] n){
		
		if (n.length>this.variable.length){
			
			throwError("more parameters antered than existed variables");
			
		}else if (n.length<this.variable.length){
			
			throwError("less parameters antered than existed variables");
			
		}
		
		ArrayList<Double> cv = new ArrayList<>();
		ArrayList<Parser> pa = new ArrayList<>();
		
		for (Parametric q : this.v){
			
			pa.add(q.get(get(q.getVariable(), n)));
			cv.add(pa.get(pa.size()-1).get().setScale(15, RoundingMode.HALF_UP).doubleValue());
			
		}
		
		String bup = "";
		vector a = new vector(cv);
		
		for (double q : n){
			
			bup+= q+", ";
			
		}
		
		bup = bup.substring(0, bup.length()-2)+"";
		
		this.p.add(pa);
		this.input.add(bup);
		this.output.add(a);
		
	}
	
	public void intervalueOf(double[] a, double[] b){
		
		this.intervalueOf(a, b, 1);
		
	}
	
	public void intervalueOf(double[] a, double[] b, double increment){
		
		if (a.length>this.variable.length || b.length>this.variable.length){
			
			throwError("more parameters antered than existed variables");
			
		}else if (a.length<this.variable.length || b.length<this.variable.length){
			
			throwError("less parameters antered than existed variables");
			
		}
		
		this.input.clear();
		this.p.clear();
		this.output.clear();
		function f;
		ArrayList<Parametric> par;
		ArrayList<Parser> pa;
		Vectorial cv;
		
		double[] newA;
		double[] newB;
		char[] newVariable;
		int m = 0;
		
		if (a.length>1){
			
			newA = new double[a.length-1];
			newB = new double[b.length-1];
			newVariable = new char[this.variable.length-1];
			
			m = 1;
			
		}else{
			
			newA = a;
			newB = b;
			newVariable = this.variable;
			
			m = 0;
			
		}
		
		ArrayList<Double> bup;
		double INPUT = 0;
		
		long n = 0;
		
		if (this.countVar()==1){
			
			do{
				
				pa = new ArrayList<>();
				bup = new ArrayList<>();
				
				INPUT = a[0]+(n*increment);
				cv = new Vectorial(this.v);
				
				if (INPUT>=b[0]){
					
					INPUT = b[0];
					
				}
				
				this.input.add(INPUT+"");
				
				for (Parametric q : cv.v){

					if (q.countVar()==0){
						
						pa.add(new Parser(q.getFunction()));
						
					}else{
						
						f = new function(q.getVariable()[0], q.getFunction(), q.getName());
						f.setTrigonometryMode(RAD_DEG);
						pa.add(f.get(INPUT));
						
					}
					
					if (pa.get(pa.size()-1).get()==null){
						
						bup.add(Double.NaN);
						
					}else{
						
						bup.add(pa.get(pa.size()-1).get().setScale(15, RoundingMode.HALF_UP).doubleValue());
						
					}
					
				}
				
				this.p.add(pa);
				this.output.add(new vector(bup));
				
				n++;
				
			}while(INPUT<b[0]);
			
		}else{
			
			for (int i=0; i<newA.length; i++){
				
				newA[i] = a[i+m];
				
			}
			
			for (int i=0; i<newB.length; i++){
				
				newB[i] = b[i+m];
				
			}
			
			for (int i=0; i<newVariable.length; i++){
				
				newVariable[i] = this.variable[i+m];
				
			}
			
			do{
				
				par = new ArrayList<>();
				INPUT = a[0]+(n*increment);
				
				if (INPUT>=b[0]){
					
					for (Parametric q : this.v){
						
						par.add(new Parametric(newVariable, Overwrite(q.getFunction(), this.variable[0], b[0]), q.getName()));
						
					}
					
					INPUT = b[0];
					
				}else{
					
					for (Parametric q : this.v){
						
						par.add(new Parametric(newVariable, Overwrite(q.getFunction(), this.variable[0], INPUT), q.getName()));
						
					}
					
				}
				
				cv = new Vectorial(par);
				
				cv.setTrigonometryMode(RAD_DEG);
				
				cv.intervalueOf(newA, newB, increment);
				
				for (ArrayList<Parser> q : cv.p){
					
					this.p.add(q);
					
				}
				
				for (vector q : cv.output){
					
					this.output.add(q);
					
				}
				
				for (String q : cv.input){
					
					this.input.add(INPUT+", "+q);
					
				}
				
				n++;
				
			}while(INPUT<b[0]);
			
		}
		
	}
	
	public String getProcess(){
		
		String bup = "";
		
		for (int i=0; i<this.p.size(); i++){
			
			bup+= this.name+"("+this.input.get(i)+") = <";
			
			for (Parser q : this.p.get(i)){
				
				bup+= q.get(0)+", ";
				
			}
			
			bup = bup.substring(0, bup.length()-2)+"> = "+this.output.get(i)+"\n\n";
			
			for (Parser q : this.p.get(i)){
				
				bup+= q.getProcess()+"\n";
				
			}
			
			bup+="\n";
			
		}
		
		return bup;
		
	}
	
	private String Overwrite(String Function, char a, double n){
		
		String wd = "";
		
		for (char p : Function.toCharArray()){
			
			if (p==a){
				
				wd+= n+"";
				
				continue;
				
			}
			
			wd+= p+"";
			
		}
		
		return wd;
		
	}
	
	private double[] get(char[] vars, double[] n){
		
		double[] values = new double[vars.length];
		
		for (int f=0; f<vars.length; f++){
			
			for (int c=0; c<this.variable.length; c++){
				
				if (vars[f]==variable[c]){
					
					values[f] = n[c];
					
				}
				
			}
			
		}
		
		return values;
		
	}
	
	private String getInputs(){
		
		String bup = "";
		
		for (char q : this.variable){
			
			bup+= q+", ";
			
		}
		
		try{
			
			return bup.substring(0, bup.length()-2);
			
		}catch(Exception e){
			
			return "0";
			
		}
		
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