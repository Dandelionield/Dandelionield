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
 
public class Parser extends Mayth{

	private ArrayList<String> wdProcess;
	private BigDecimal n;
	private String op = "";
	
	private boolean functionType;

	public Parser(String wd){
		
		super(false);
		
		this.functionType = false;
		
		try{
			
			wdProcess = new ArrayList<>();
			
			n = Parse(wd);
			
			op = this.getProcess();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			op = "Error";
			
			n = null;
			
			wdProcess = new ArrayList<>();
			
			wdProcess.add(op);
			
		}//*/
		
	}
	
	public Parser(String wd, boolean RAD_DEG){
		
		super(RAD_DEG);
		
		this.functionType = false;
		
		try{
			
			wdProcess = new ArrayList<>();
			
			n = Parse(wd);
			
			op = this.getProcess();
			
		}catch(Exception e){
			
			//e.printStackTrace();
			
			op = "Error";
			
			n = null;
			
			wdProcess = new ArrayList<>();
			
			wdProcess.add(op);
			
		}//*/
		
	}
	
	public Parser(String wd, boolean RAD_DEG, boolean functionType){
		
		super(RAD_DEG);
		
		this.functionType = functionType;
		
		try{
			
			wdProcess = new ArrayList<>();
			
			n = Parse(wd);
			
			op = this.getProcess();
			
		}catch(Exception e){
			
			//e.printStackTrace();
			
			op = "Error";
			
			n = null;
			
			wdProcess = new ArrayList<>();
			
			wdProcess.add(op);
			
		}//*/
		
	}
	
	public Parser(boolean RAD_DEG, boolean functionType){
		
		super(RAD_DEG);
		
		this.functionType = functionType;
		
		wdProcess = new ArrayList<>();
		
		n = Parse("0.0");
		
		op = this.getProcess();
		
	}
	
	public Parser(boolean RAD_DEG){
		
		super(RAD_DEG);
		
		this.functionType = false;
		
		wdProcess = new ArrayList<>();
		
		n = Parse("0.0");
		
		op = this.getProcess();
		
	}
	
	public Parser(){
		
		super(false);
		
		this.functionType = false;
		
		wdProcess = new ArrayList<>();
		
		n = Parse("0.0");
		
		op = this.getProcess();
		
	}
	
	public BigDecimal get(){
		
		return n;
		
	}
	
	public String get(int i){
		
		return this.wdProcess.get(i);
		
	}
	
	public String getProcess(){
		
		if (op.equals("")==false){
			
			return op;
			
		}
		
		for (String p : wdProcess){
			
			op+=p+"\n";
			
		}
		
		return op;
		
	}
	
	public boolean getFunctionType(){
		
		return this.functionType;
		
	}
	
	public void setFunctionType(boolean value){
		
		this.functionType = value;
		
	}
	
	public String toString(){
		
		if (n!=null){
			
			return wdProcess.get(0)+" = "+n;
			
		}else{
			
			return wdProcess.get(0)+" = "+Double.NaN;
			
		}
		
	}
	
	public BigDecimal Parse(String wd){
		
		if (isAllowed(wd)){
			
			if (this.op.equals("")==true){wdProcess.add(wd);}
			
			return new BigDecimal(wd);
			
		}
		
		wd = Overwrite(wd);
		
		if (this.op.equals("")==true){wdProcess.add(wd);}
		
		if (isAllowed(wd)){
			
			return new BigDecimal(wd);
			
		}
		
		BigDecimal n = new BigDecimal(0);
		String r = "";
		String l = "";
		
		if (wd.contains("(")){
			
			for (int a = wd.length()-1; a>=0; a--){
				
				if (wd.charAt(a)=='('){
					
					for (int b=a; b<wd.length(); b++){
						
						if (wd.charAt(b)==')'){
							
							return Parse(
							
								wd.substring(0, a)+Parse(
								
									wd.substring(a+1, b)
									
								)+wd.substring(b+1)
								
							);
							
						}
						
					}
					
				}
				
			}
			
		}else if (wd.contains("Sen") || wd.contains("Cos") || wd.contains("Tan") || wd.contains("Csc") || wd.contains("Sec") || wd.contains("Cot") || wd.contains("Arcsen") || wd.contains("Arccos") || wd.contains("Arctan") || wd.contains("Arccsc") || wd.contains("Arcsec") || wd.contains("Arccot")){
			
			for (int i=6; i<wd.length(); i++){
			
				if (wd.charAt(i-6)=='A' && wd.charAt(i-5)=='r' && wd.charAt(i-4)=='c' && wd.charAt(i-3)=='s' && wd.charAt(i-2)=='e' && wd.charAt(i-1)=='n' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					wd = DeleteChar(wd,i-6);
					
					r = getRight(wd, i-6);
					n = new BigDecimal(Arcsenh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, n, r, true));
					
				}
				
				if (wd.charAt(i-6)=='A' && wd.charAt(i-5)=='r' && wd.charAt(i-4)=='c' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='o' && wd.charAt(i-1)=='s' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					wd = DeleteChar(wd,i-6);
					
					r = getRight(wd, i-6);
					n = new BigDecimal(Arccosh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, n, r, true));
					
				}
				
				if (wd.charAt(i-6)=='A' && wd.charAt(i-5)=='r' && wd.charAt(i-4)=='c' && wd.charAt(i-3)=='t' && wd.charAt(i-2)=='a' && wd.charAt(i-1)=='n' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					wd = DeleteChar(wd,i-6);
					
					r = getRight(wd, i-6);
					n = new BigDecimal(Arctanh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, n, r, true));
					
				}
				
				if (wd.charAt(i-6)=='A' && wd.charAt(i-5)=='r' && wd.charAt(i-4)=='c' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='s' && wd.charAt(i-1)=='c' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					wd = DeleteChar(wd,i-6);
					
					r = getRight(wd, i-6);
					n = new BigDecimal(Arccsch(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, n, r, true));
					
				}
				
				if (wd.charAt(i-6)=='A' && wd.charAt(i-5)=='r' && wd.charAt(i-4)=='c' && wd.charAt(i-3)=='s' && wd.charAt(i-2)=='e' && wd.charAt(i-1)=='c' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					wd = DeleteChar(wd,i-6);
					
					r = getRight(wd, i-6);
					n = new BigDecimal(Arcsech(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, n, r, true));
					
				}
				
				if (wd.charAt(i-6)=='A' && wd.charAt(i-5)=='r' && wd.charAt(i-4)=='c' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='o' && wd.charAt(i-1)=='t' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					wd = DeleteChar(wd,i-6);
					
					r = getRight(wd, i-6);
					n = new BigDecimal(Arccoth(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, n, r, true));
					
				}
				
			}
			
			for (int i=3; i<wd.length(); i++){
			
				if (wd.charAt(i-3)=='S' && wd.charAt(i-2)=='e' && wd.charAt(i-1)=='n' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					r = getRight(wd, i-3);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Senh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						n = new BigDecimal(Math.sinh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}
					
					return Parse(ReplaceOperation(wd, i-3, n, r, true));
					
				}
				
				if (wd.charAt(i-3)=='C' && wd.charAt(i-2)=='o' && wd.charAt(i-1)=='s' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					r = getRight(wd, i-3);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Cosh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						n = new BigDecimal(Math.cosh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}
					
					return Parse(ReplaceOperation(wd, i-3, n, r, true));
					
				}
				
				if (wd.charAt(i-3)=='T' && wd.charAt(i-2)=='a' && wd.charAt(i-1)=='n' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					r = getRight(wd, i-3);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Tanh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						n = new BigDecimal(Math.tanh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}
					
					return Parse(ReplaceOperation(wd, i-3, n, r, true));
					
				}
				
				if (wd.charAt(i-3)=='C' && wd.charAt(i-2)=='s' && wd.charAt(i-1)=='c' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					r = getRight(wd, i-3);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Csch(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						n = new BigDecimal(1).divide(new BigDecimal(Math.sinh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())), MathContext.DECIMAL128);
						
					}
					
					return Parse(ReplaceOperation(wd, i-3, n, r, true));
					
				}
				
				if (wd.charAt(i-3)=='S' && wd.charAt(i-2)=='e' && wd.charAt(i-1)=='c' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					r = getRight(wd, i-3);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Sech(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						n = new BigDecimal(1).divide(new BigDecimal(Math.cosh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())), MathContext.DECIMAL128);
						
					}
					
					return Parse(ReplaceOperation(wd, i-3, n, r, true));
					
				}
				
				if (wd.charAt(i-3)=='C' && wd.charAt(i-2)=='o' && wd.charAt(i-1)=='t' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					r = getRight(wd, i-3);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Coth(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						n = new BigDecimal(1).divide(new BigDecimal(Math.tanh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())), MathContext.DECIMAL128);
					
					}
					
					return Parse(ReplaceOperation(wd, i-3, n, r, true));
					
				}
				
			}
			
			for (int i=5; i<wd.length(); i++){
			
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='s' && wd.charAt(i-1)=='e' && wd.charAt(i)=='n'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					r = getRight(wd, i-5);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Arcsen(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(Math.toDegrees(Math.asin(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())));
							
						}else{
							
							n = new BigDecimal(Math.asin(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}
						
					}
					
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='c' && wd.charAt(i-1)=='o' && wd.charAt(i)=='s'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					r = getRight(wd, i-5);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Arccos(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(Math.toDegrees(Math.acos(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())));
							
						}else{
							
							n = new BigDecimal(Math.acos(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}
						
					}
					
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='t' && wd.charAt(i-1)=='a' && wd.charAt(i)=='n'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					r = getRight(wd, i-5);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Arctan(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(Math.toDegrees(Math.atan(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())));
							
						}else{
							
							n = new BigDecimal(Math.atan(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}
						
					}
				
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='c' && wd.charAt(i-1)=='s' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					r = getRight(wd, i-5);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Arccsc(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(Math.toDegrees(Math.asin(new BigDecimal(1).divide(new BigDecimal(r), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue())));
							
						}else{
							
							n = new BigDecimal(Math.asin(new BigDecimal(1).divide(new BigDecimal(r), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}
						
					}
					
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='s' && wd.charAt(i-1)=='e' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					r = getRight(wd, i-5);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Arcsec(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(Math.toDegrees(Math.acos(new BigDecimal(1).divide(new BigDecimal(r), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue())));
							
						}else{
							
							n = new BigDecimal(Math.acos(new BigDecimal(1).divide(new BigDecimal(r), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}
						
					}
					
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='c' && wd.charAt(i-1)=='o' && wd.charAt(i)=='t'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					r = getRight(wd, i-5);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Arccot(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(Math.toDegrees(Math.atan(new BigDecimal(1).divide(new BigDecimal(r), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue())));
							
						}else{
							
							n = new BigDecimal(Math.atan(new BigDecimal(1).divide(new BigDecimal(r), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}
						
					}
					
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
			}
			
			for (int i=2; i<wd.length(); i++){
			
				if (wd.charAt(i-2)=='S' && wd.charAt(i-1)=='e' && wd.charAt(i)=='n'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Sen(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(Math.sin(Math.toRadians(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())));
							
						}else{
							
							n = new BigDecimal(Math.sin(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}
						
					}
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
				if (wd.charAt(i-2)=='C' && wd.charAt(i-1)=='o' && wd.charAt(i)=='s'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Cos(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(Math.cos(Math.toRadians(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())));
							
						}else{
							
							n = new BigDecimal(Math.cos(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}
						
					}
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
				if (wd.charAt(i-2)=='T' && wd.charAt(i-1)=='a' && wd.charAt(i)=='n'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Tan(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(Math.tan(Math.toRadians(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())));
							
						}else{
							
							n = new BigDecimal(Math.tan(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}
						
					}
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
				if (wd.charAt(i-2)=='C' && wd.charAt(i-1)=='s' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Csc(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(1).divide(new BigDecimal(Math.sin(Math.toRadians(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()))), MathContext.DECIMAL128);
							
						}else{
							
							n = new BigDecimal(1).divide(new BigDecimal(Math.sin(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())), MathContext.DECIMAL128);
							
						}
						
					}
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
				if (wd.charAt(i-2)=='S' && wd.charAt(i-1)=='e' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Sec(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(1).divide(new BigDecimal(Math.cos(Math.toRadians(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()))), MathContext.DECIMAL128);
							
						}else{
							
							n = new BigDecimal(1).divide(new BigDecimal(Math.cos(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())), MathContext.DECIMAL128);
							
						}
						
					}
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
				if (wd.charAt(i-2)=='C' && wd.charAt(i-1)=='o' && wd.charAt(i)=='t'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					
					if (this.functionType==false){
						
						n = new BigDecimal(Cot(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
					}else{
						
						if (this.getMode()==false){
							
							n = new BigDecimal(1).divide(new BigDecimal(Math.tan(Math.toRadians(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()))), MathContext.DECIMAL128);
							
						}else{
							
							n = new BigDecimal(1).divide(new BigDecimal(Math.tan(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())), MathContext.DECIMAL128);
							
						}
						
					}
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
			}
			
		}else if (wd.contains("√") || wd.contains("^") || wd.contains("!") || wd.contains("ln") || wd.contains("log") || wd.contains("|")){
			
			if (wd.contains("√")){
				
				for (int i=0; i<wd.length(); i++){
				
					if (wd.charAt(i)=='√'){
						
						r = getRight(wd, i);
						l = getLeft(wd, i);
						
						if (this.functionType==false){
							
							n = new BigDecimal(Raiz(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(l).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}else{
							
							n = new BigDecimal(Math.pow(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(1).divide(new BigDecimal(l), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}

						return Parse(ReplaceOperation(wd, i, n, l, r));
						
					}
					
				}
				
			}
			
			if (wd.contains("^")){
				
				for (int i=0; i<wd.length(); i++){
				
					if (wd.charAt(i)=='^'){
						
						r = getRight(wd, i);
						l = getLeft(wd, i);
						
						if (this.functionType==false){
							
							n = new BigDecimal(Potencia(new BigDecimal(l).setScale(15, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}else{
							
							n = new BigDecimal(Math.pow(new BigDecimal(l).setScale(15, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}
						
						return Parse(ReplaceOperation(wd, i, n, l, r));
						
					}
					
				}
				
			}
			
			if (wd.contains("!")){
				
				for (int i=0; i<wd.length(); i++){
				
					if (wd.charAt(i)=='!'){
						
						l = getLeft(wd, i);

						n = new BigDecimal(Factorial(new BigDecimal(l).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
						return Parse(ReplaceOperation(wd, i, n, l, false));
						
					}
					
				}
				
			}
			
			if (wd.contains("ln")){
				
				for (int i=1; i<wd.length(); i++){
					
					if (wd.charAt(i-1)=='l' && wd.charAt(i)=='n'){
						
						wd = DeleteChar(wd, i-1);
						
						r = getRight(wd, i-1);
						
						if (this.functionType==false){
							
							n = new BigDecimal(Ln(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}else{
							
							n = new BigDecimal(Math.log(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
							
						}
						
						return Parse(ReplaceOperation(wd, i-1, n, r, true));
						
					}
					
				}
				
			}
			
			if (wd.contains("|")){
				
				for (int f=0; f<wd.length(); f++){
				
					if (wd.charAt(f)=='|'){
						
						for (int c=f+1; c<wd.length(); c++){
							
							if (wd.charAt(c)=='|'){
							
								return Parse(
								
									wd.substring(0, f)+abs(
									
										new BigDecimal(
										
											wd.substring(f+1, c)
											
										).setScale(15, RoundingMode.HALF_UP).doubleValue()
										
									)+wd.substring(c+1)
									
								);
								
							}
							
						}
						
					}
					
				}
				
			}
			
			if (wd.contains("log")){
				
				for (int f=2; f<wd.length(); f++){
				
					if (wd.charAt(f-2)=='l' && wd.charAt(f-1)=='o' && wd.charAt(f)=='g'){
						
						if (isAllowed(wd.charAt(f+1)+"")==true){
							
							wd = DeleteChar(wd, f);
							f--;wd = DeleteChar(wd, f);
							
							r = getRight(wd, f-1);
						
							if (this.functionType==false){
								
								n = new BigDecimal(Log(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
								
							}else{
								
								n = new BigDecimal(Math.log10(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
								
							}
							
							return Parse(ReplaceOperation(wd, f-1, n, r, true));
							
						}
						
						wd = DeleteChar(wd, f);
						f--;wd = DeleteChar(wd, f);
						f--;wd = DeleteChar(wd, f);
						f--;
						
						for (int c=f+1; c<wd.length(); c++){
							
							if (wd.charAt(c)=='}'){
								
								wd = DeleteChar(wd, c);
								wd = DeleteChar(wd, f+1);
								
								for (int i=f+1; i<wd.length(); i++){
									
									if (wd.charAt(i)==','){
									
										r = getRight(wd, i);
										l = getLeft(wd, i);
										
										if (this.functionType==false){
											
											n = new BigDecimal(Log(new BigDecimal(l).setScale(15, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
											
										}else{
											
											n = new BigDecimal(Math.log(new BigDecimal(l).setScale(15, RoundingMode.HALF_UP).doubleValue())).divide(new BigDecimal(Math.log(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue())), MathContext.DECIMAL128);
											
										}
										
										return Parse(ReplaceOperation(wd, i, n, l, r));
										
									}
									
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}else{
			
			for (int i=0; i<wd.length(); i++){
				
				if (wd.charAt(i)=='%'){
					
					r = getRight(wd, i);
					l = getLeft(wd, i);

					n = new BigDecimal(l).remainder(new BigDecimal(r), MathContext.DECIMAL128);
					
					return Parse(ReplaceOperation(wd, i, n, l, r));
					
				}
				
			}
			
			for (int i=0; i<wd.length(); i++){
				
				if (wd.charAt(i)=='/'){
					
					r = getRight(wd, i);
					l = getLeft(wd, i);

					n = new BigDecimal(l).divide(new BigDecimal(r), MathContext.DECIMAL128);
					
					return Parse(ReplaceOperation(wd, i, n, l, r));
					
				}
				
			}
			
			for (int i=0; i<wd.length(); i++){
				
				if (wd.charAt(i)=='*'){
					
					r = getRight(wd, i);
					l = getLeft(wd, i);

					n = new BigDecimal(l).multiply(new BigDecimal(r));
					
					return Parse(ReplaceOperation(wd, i, n, l, r));
					
				}
				
			}
			
			for (int i=0; i<wd.length(); i++){
				
				if (wd.charAt(i)=='+'){
					
					r = getRight(wd, i);
					l = getLeft(wd, i);
					
					n = new BigDecimal(l).add(new BigDecimal(r));
					
					return Parse(ReplaceOperation(wd, i, n, l, r));
					
				}
				
			}
			
			for (int i=0; i<wd.length(); i++){
				
				if (i==0 && wd.charAt(i)=='-'){continue;}
				
				if (wd.charAt(i)=='-'){
					
					r = getRight(wd, i);
					l = getLeft(wd, i);

					n = new BigDecimal(l).subtract(new BigDecimal(r));
					
					return Parse(ReplaceOperation(wd, i, n, l, r));
					
				}
				
			}
			
		}

		return new BigDecimal(wd);
		
	}
	
	public static boolean Parse(int n){
		
		if (n==1){
			
			return true;
			
		}
		
		return false;
		
	}
	
	public static int Parse(boolean n){
		
		if (n==true){
			
			return 1;
			
		}
		
		return 0;
		
	}
	
	public static String DeleteChar(String cadena, int indice){
		
		String nw="";
		
		for (int i=0; i<cadena.length(); i++){
			
			if (i==indice){continue;}
			
			nw+=cadena.charAt(i)+"";
			
		}
		
		return nw;
		
	}
	
	public static String ReplaceChar(String cadena, int indice, String replacement){
		
		String nw="";
		
		for (int i=0; i<cadena.length(); i++){
			
			if (i==indice){nw+=replacement;continue;}
			
			nw+=cadena.charAt(i)+"";
			
		}
		
		return nw;
		
	}
	
	public static boolean isAllowed(String wd){
		
		try{
			
			if (wd.charAt(0)=='+'){
			
				return false;
				
			}
			
			new BigDecimal(wd);
			
			return true;
			
		}catch(Exception e){
			
			return false;
			
		}
		
	}
	
	private static String ReplaceOperation(String wd, int indice, BigDecimal Result, String value, boolean b){
		
		String[] parts;
		
		if (b==true){
			
			parts = Separar(wd, wd.charAt(indice)+""+value);
			
		}else{
			
			parts = Separar(wd, value+""+wd.charAt(indice));
			
		}
		
		if (parts.length==0){
			
			parts = new String[] {"",""};
			
		}else if (parts.length==1){
			
			if (parts[0].equals(wd.charAt(indice)+""+value)){
				
				parts = new String[] {"",""};
				
			}else{
				
				parts = new String[] {parts[0],""};
				
			}
			
		}
		
		return parts[0]+Result+parts[1];
		
	}
	
	private static String ReplaceOperation(String wd, int indice, BigDecimal Result, String valueLeft, String valueRight){
		
		String bup = valueLeft+""+wd.charAt(indice)+""+valueRight;
		
		String[] parts = Separar(wd, bup);
		
		if (parts.length==0){
			
			parts = new String[] {"",""};
			
		}else if (parts.length==1){
			
			if (parts[0].equals(valueLeft+""+wd.charAt(indice)+""+valueRight)){
				
				parts = new String[] {"",""};
				
			}else{
				
				parts = new String[] {parts[0],""};
				
			}
			
		}

		if (bup.charAt(0)=='-' && Result.compareTo(new BigDecimal(0))>=0 && parts[0].equals("")==false && isAllowed(parts[0].charAt(parts[0].length()-1)+"")==true){
			
			parts[0] = parts[0]+"+";
			
		}
		
		return parts[0]+Result+parts[1];
		
	}
	
	private static String getRight(String wd, int indice){
		
		boolean E = false;
		
		indice++;

		for (int i=indice; i<wd.length(); i++){
			
			if (E==true){
				
				E = false;
				
				if (wd.charAt(i)=='-'){
					
					continue;
					
				}
				
			}
		
			if (i==wd.length()-1){
				
				return wd.substring(indice);
				
			}
			
			if (i!=wd.length()-1){
				
				if (wd.charAt(i)=='E'){
					
					E = true;
					
					continue;
					
				}
				
			}
			
			if ((wd.charAt(i)=='-' && i==indice) || wd.charAt(i)=='.'){continue;}
			
			if (isAllowed(wd.substring(indice, i+1))==false){
				
				return wd.substring(indice, i);
				
			}
			
		}
		
		return "0";
		
	}
	
	private static String getLeft(String wd, int indice){
		
		boolean E = false;
		
		for (int i=indice-1; i>=0; i--){
			
			if (E==true){
				
				E = false;
				
				if (wd.charAt(i)=='E'){
					
					continue;
					
				}
				
			}
			
			if (i==0){return wd.substring(0, indice);}
			
			if (wd.charAt(i)=='.'){continue;}
			
			if (wd.charAt(i)=='-' && wd.charAt(i-1)=='E'){
				
				E = true;
				
				continue;
				
			}else if (wd.charAt(i)=='E'){
				
				continue;
				
			}else if (wd.charAt(i)=='-' && isAllowed(wd.charAt(i-1)+"")==true){
				
				return wd.substring(i, indice);
				
			}
			
			if (isAllowed(wd.substring(i, indice))==false){
				
				return wd.substring(i+1, indice);
				
			}
			
		}
		
		return "0";
		
	}
	
	private String Overwrite(String wd){
		
		int Counter = 0;
		String bup = "";
		
		String Constant = "";
		
		if (wd.equals("")==true){return "0";}
		
		for (char p : wd.toCharArray()){
			
			if (p==' '){
				
				continue;
				
			}
			
			bup+= p+"";
			
		}
		
		wd = bup;
		
		bup = "";
		
		for (int i=0; i<wd.length()-1; i++){
			
			if (isAllowed(wd.charAt(i)+"")==true && isAllowed(wd.charAt(i+1)+"")==false){
				
				if (wd.charAt(i+1)=='e' || wd.charAt(i+1)=='P' || wd.charAt(i+1)=='A' 
				|| wd.charAt(i+1)=='G' || wd.charAt(i+1)=='(' || wd.charAt(i+1)=='l' 
				|| wd.charAt(i+1)=='S' || wd.charAt(i+1)=='C' || wd.charAt(i+1)=='T'){
				
					bup+= wd.charAt(i)+"*";
					continue;
					
				}
				
			}
			
			bup+= wd.charAt(i)+"";
			
		}
		
		wd = bup+wd.charAt(wd.length()-1);//*/
		
		for (int i=0; i<wd.length(); i++){
			
			if (wd.charAt(i)=='-' && wd.charAt(i+1)=='('){
				
				wd = Parser.ReplaceChar(wd,i,"-1*");
				i=0;
				
			}
			
			if (wd.charAt(i)=='e'){
				
				wd+=" ";
				
				if (wd.length()==1){
					
					if (functionType==false){
			
						Constant = Parser.e+"";
						
					}else{
						
						Constant = Math.E+"";
						
					}
					
					wd = Parser.ReplaceChar(wd,i,Constant);
					i=0;
					
				}else if (wd.charAt(i+1)!='n' && wd.charAt(i+1)!='c'){
					
					if (functionType==false){
			
						Constant = Parser.e+"";
						
					}else{
						
						Constant = Math.E+"";
						
					}
					
					wd = Parser.ReplaceChar(wd,i,Constant);
					i=0;
					
				}
				
				wd = Parser.DeleteChar(wd,wd.length()-1);
				
			}
			
			if (wd.charAt(i)=='P'){
				
				wd+=" ";
				
				if (wd.charAt(i+1)!='('){
					
					if (functionType==false){
			
						Constant = Parser.PI+"";
						
					}else{
						
						Constant = Math.PI+"";
						
					}
					
					wd = Parser.ReplaceChar(wd,i,Constant);
					i=0;
					
				}
				
				wd = Parser.DeleteChar(wd,wd.length()-1);
				
			}
			
			if (wd.charAt(i)=='A'){
				
				wd+=" ";
				
				if (wd.length()==1){
					
					wd = Parser.ReplaceChar(wd,i,A+"");
					i=0;
					
				}else if (wd.charAt(i+1)!='r'){
					
					wd = Parser.ReplaceChar(wd,i,A+"");
					i=0;
					
				}
				
				wd = Parser.DeleteChar(wd,wd.length()-1);
				
			}
			
			if (wd.charAt(i)=='G'){
				
				wd = Parser.ReplaceChar(wd,i,G+"");
				i=0;
				
			}
			
		}
		
		if (wd.contains("(") || wd.contains(")")){
			
			for (int i=0; i<wd.length(); i++){
				
				if (wd.charAt(i)=='('){Counter++;}else if (wd.charAt(i)==')'){Counter--;}
				
			}
			
		}
		
		if (Counter!=0){
			
			if (Counter>0){
				
				do{
					
					wd = "("+wd;
					Counter--;
					
				}while(Counter>0);
				
			}else if (Counter<0){
				
				do{
					
					wd = wd+")";
					Counter++;
					
				}while(Counter<0);
				
			}
			
		}
		
		bup = "";
		
		for (int i=0; i<wd.length(); i++){
			
			if (wd.charAt(i)=='-' && wd.charAt(i+1)=='-'){
				
				bup+= "+";
				
				i+=2;
				
			}
			
			if (wd.charAt(i)=='+' && wd.charAt(i+1)=='+'){
				
				bup+= "+";
				
				i+=2;
				
			}
			
			if (wd.charAt(i)=='-' && wd.charAt(i+1)=='+'){
				
				bup+= "-";
				
				i+=2;
				
			}
			
			if (wd.charAt(i)=='+' && wd.charAt(i+1)=='-'){
				
				bup+= "-";
				
				i+=2;
				
			}
			
			bup+= wd.charAt(i);
			
		}
		
		wd = bup;
		
		return wd;
		
	}//*/
	
	private static String[] Separar(String wd, String chain){
		
		if (chain.length()>wd.length()){
			
			return new String[] {wd};
			
		}
		
		int c = 0;
		
		for (int i=chain.length()-1; i<wd.length(); i++){
			
			if (i==wd.length()-1){
				
				if (wd.substring(c).equals(chain)){
					
					return new String[] {wd.substring(0, c), ""};
					
				}else{
					
					break;
					
				}
				
			}
			
			if (wd.substring(c, i+1).equals(chain)){
				
				return new String[] {wd.substring(0, c), wd.substring(i+1)};
				
			}
			
			c++;
			
		}
		
		return new String[] {wd};
		
	}

}