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

	private static ArrayList<String> Process = new ArrayList<>();
	private ArrayList<String> wdProcess;
	private BigDecimal n;
	private String op = "";

	public Parser(String wd){
		
		super(false);
		
		Parser.Process.clear();
		
		try{
			
			n = Parser.Parse(wd);
			
			wdProcess = Parser.Process;
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			op = "Error";
			
			n = null;
			
			wdProcess.add(op);
			
		}//*/
		
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
	
	public String toString(){
		
		return wdProcess.get(0)+" = "+n.setScale(15, RoundingMode.HALF_UP).doubleValue();
		
	}
	
	public static BigDecimal Parse(String wd){
		
		if (isAllowed(wd)){
			
			Process.add(wd);
			
			return new BigDecimal(wd);
			
		}
		
		wd = Overwrite(wd);
		
		Process.add(wd);
		
		if (isAllowed(wd)){return new BigDecimal(wd);}
		
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
					n = new BigDecimal(Senh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-3, n, r, true));
					
				}
				
				if (wd.charAt(i-3)=='C' && wd.charAt(i-2)=='o' && wd.charAt(i-1)=='s' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					r = getRight(wd, i-3);
					n = new BigDecimal(Cosh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-3, n, r, true));
					
				}
				
				if (wd.charAt(i-3)=='T' && wd.charAt(i-2)=='a' && wd.charAt(i-1)=='n' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					r = getRight(wd, i-3);
					n = new BigDecimal(Tanh(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-3, n, r, true));
					
				}
				
				if (wd.charAt(i-3)=='C' && wd.charAt(i-2)=='s' && wd.charAt(i-1)=='c' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					r = getRight(wd, i-3);
					n = new BigDecimal(Csch(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-3, n, r, true));
					
				}
				
				if (wd.charAt(i-3)=='S' && wd.charAt(i-2)=='e' && wd.charAt(i-1)=='c' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					r = getRight(wd, i-3);
					n = new BigDecimal(Sech(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-3, n, r, true));
					
				}
				
				if (wd.charAt(i-3)=='C' && wd.charAt(i-2)=='o' && wd.charAt(i-1)=='t' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					r = getRight(wd, i-3);
					n = new BigDecimal(Coth(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
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
					n = new BigDecimal(Arcsen(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='c' && wd.charAt(i-1)=='o' && wd.charAt(i)=='s'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					r = getRight(wd, i-5);
					n = new BigDecimal(Arccos(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='t' && wd.charAt(i-1)=='a' && wd.charAt(i)=='n'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					r = getRight(wd, i-5);
					n = new BigDecimal(Arctan(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='c' && wd.charAt(i-1)=='s' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					r = getRight(wd, i-5);
					n = new BigDecimal(Arccsc(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='s' && wd.charAt(i-1)=='e' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					r = getRight(wd, i-5);
					n = new BigDecimal(Arcsec(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='c' && wd.charAt(i-1)=='o' && wd.charAt(i)=='t'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					r = getRight(wd, i-5);
					n = new BigDecimal(Arccot(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, n, r, true));
					
				}
				
			}
			
			for (int i=2; i<wd.length(); i++){
			
				if (wd.charAt(i-2)=='S' && wd.charAt(i-1)=='e' && wd.charAt(i)=='n'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					n = new BigDecimal(Sen(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
				if (wd.charAt(i-2)=='C' && wd.charAt(i-1)=='o' && wd.charAt(i)=='s'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					n = new BigDecimal(Cos(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
				if (wd.charAt(i-2)=='T' && wd.charAt(i-1)=='a' && wd.charAt(i)=='n'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					n = new BigDecimal(Tan(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
				if (wd.charAt(i-2)=='C' && wd.charAt(i-1)=='s' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					n = new BigDecimal(Csc(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
				if (wd.charAt(i-2)=='S' && wd.charAt(i-1)=='e' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					n = new BigDecimal(Sec(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
				if (wd.charAt(i-2)=='C' && wd.charAt(i-1)=='o' && wd.charAt(i)=='t'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					r = getRight(wd, i-2);
					n = new BigDecimal(Cot(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, n, r, true));
					
				}
				
			}
			
		}else if (wd.contains("√") || wd.contains("^") || wd.contains("!") || wd.contains("ln") || wd.contains("log") || wd.contains("|")){
			
			if (wd.contains("√")){
				
				for (int i=0; i<wd.length(); i++){
				
					if (wd.charAt(i)=='√'){
						
						r = getRight(wd, i);
						l = getLeft(wd, i);

						n = new BigDecimal(Raiz(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(l).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
						return Parse(ReplaceOperation(wd, i, n, l, r));
						
					}
					
				}
				
			}
			
			if (wd.contains("^")){
				
				for (int i=0; i<wd.length(); i++){
				
					if (wd.charAt(i)=='^'){
						
						r = getRight(wd, i);
						l = getLeft(wd, i);

						n = bigPotencia(new BigDecimal(l), new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue());
						
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
						n = new BigDecimal(Ln(new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
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

										n = new BigDecimal(Log(new BigDecimal(l).setScale(15, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(r).setScale(15, RoundingMode.HALF_UP).doubleValue()));
										
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
		System.out.print(wd+"\n\n");
		return new BigDecimal(wd);
		
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
		
		if (wd.charAt(0)=='+'){
			
			return false;
			
		}
		
		try{
			
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
		
		String[] parts = Separar(wd, valueLeft+""+wd.charAt(indice)+""+valueRight);
		
		if (parts.length==0){
			
			parts = new String[] {"",""};
			
		}else if (parts.length==1){
			
			if (parts[0].equals(valueLeft+""+wd.charAt(indice)+""+valueRight)){
				
				parts = new String[] {"",""};
				
			}else{
				
				parts = new String[] {parts[0],""};
				
			}
			
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
		
			if (i==wd.length()-1){return wd.substring(indice);}
			
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
		
		for (int i=indice-1; i>=0; i--){
		
			if (i==0){return wd.substring(0, indice);}
			
			if (wd.charAt(i)=='.' || wd.charAt(i)=='E'){continue;}
			
			if (isAllowed(wd.substring(i, indice))==false){
				
				return wd.substring(i+1, indice);
				
			}
			
		}
		
		return "0";
		
	}
	
	private static String Overwrite(String wd){
		
		int Counter = 0;
		
		if (wd==""){wd="0";}
		
		for (int i=0; i<wd.length(); i++){
			
			if (wd.charAt(i)=='-' && wd.charAt(i+1)=='('){
				
				wd = Parser.ReplaceChar(wd,i,"-1x");
				i=0;
				
			}
			
			if (wd.charAt(i)=='e'){
				
				wd+=" ";
				
				if (wd.length()==1){
					
					wd = Parser.ReplaceChar(wd,i,Parser.Euler()+"");
					i=0;
					
				}else if (wd.charAt(i+1)!='n' && wd.charAt(i+1)!='c'){
					
					wd = Parser.ReplaceChar(wd,i,Parser.Euler()+"");
					i=0;
					
				}
				
				wd = Parser.DeleteChar(wd,wd.length()-1);
				
			}
			
			if (wd.charAt(i)=='P'){
				
				wd+=" ";
				
				if (wd.charAt(i+1)!='('){
					
					wd = Parser.ReplaceChar(wd,i,Parser.PI+"");
					i=0;
					
				}
				
				wd = Parser.DeleteChar(wd,wd.length()-1);
				
			}
			
			if (wd.charAt(i)=='A'){
				
				wd+=" ";
				
				if (wd.length()==1){
					
					wd = Parser.ReplaceChar(wd,i,Parser.Aureo()+"");
					i=0;
					
				}else if (wd.charAt(i+1)!='r'){
					
					wd = Parser.ReplaceChar(wd,i,Parser.Aureo()+"");
					i=0;
					
				}
				
				wd = Parser.DeleteChar(wd,wd.length()-1);
				
			}
			
			if (wd.charAt(i)=='G'){
				
				wd = Parser.ReplaceChar(wd,i,"0.5772156649");
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