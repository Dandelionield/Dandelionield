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
	
	public BigDecimal getResult(){
		
		return n;
		
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
		BigDecimal r = new BigDecimal(0);
		BigDecimal l = new BigDecimal(0);
		
		if (wd.contains("(")){
			
			for (int i=wd.length()-1; i>=0 ; i--){
				
				if (wd.charAt(i)=='('){
					
					return Parse(
					
						wd.substring(0, i)+Parse(
						
							wd.substring(i+1, wd.indexOf(')'))
							
						)+wd.substring(wd.indexOf(')')+1)
						
					);
					
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
					
					n = getRight(wd, i-6);
					r = new BigDecimal(Arcsenh(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, r, n, true));
					
				}
				
				if (wd.charAt(i-6)=='A' && wd.charAt(i-5)=='r' && wd.charAt(i-4)=='c' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='o' && wd.charAt(i-1)=='s' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					wd = DeleteChar(wd,i-6);
					
					n = getRight(wd, i-6);
					r = new BigDecimal(Arccosh(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, r, n, true));
					
				}
				
				if (wd.charAt(i-6)=='A' && wd.charAt(i-5)=='r' && wd.charAt(i-4)=='c' && wd.charAt(i-3)=='t' && wd.charAt(i-2)=='a' && wd.charAt(i-1)=='n' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					wd = DeleteChar(wd,i-6);
					
					n = getRight(wd, i-6);
					r = new BigDecimal(Arctanh(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, r, n, true));
					
				}
				
				if (wd.charAt(i-6)=='A' && wd.charAt(i-5)=='r' && wd.charAt(i-4)=='c' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='s' && wd.charAt(i-1)=='c' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					wd = DeleteChar(wd,i-6);
					
					n = getRight(wd, i-6);
					r = new BigDecimal(Arccsch(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, r, n, true));
					
				}
				
				if (wd.charAt(i-6)=='A' && wd.charAt(i-5)=='r' && wd.charAt(i-4)=='c' && wd.charAt(i-3)=='s' && wd.charAt(i-2)=='e' && wd.charAt(i-1)=='c' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					wd = DeleteChar(wd,i-6);
					
					n = getRight(wd, i-6);
					r = new BigDecimal(Arcsech(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, r, n, true));
					
				}
				
				if (wd.charAt(i-6)=='A' && wd.charAt(i-5)=='r' && wd.charAt(i-4)=='c' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='o' && wd.charAt(i-1)=='t' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					wd = DeleteChar(wd,i-6);
					
					n = getRight(wd, i-6);
					r = new BigDecimal(Arccoth(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-6, r, n, true));
					
				}
				
			}
			
			for (int i=3; i<wd.length(); i++){
			
				if (wd.charAt(i-3)=='S' && wd.charAt(i-2)=='e' && wd.charAt(i-1)=='n' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					n = getRight(wd, i-3);
					r = new BigDecimal(Senh(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-3, r, n, true));
					
				}
				
				if (wd.charAt(i-3)=='C' && wd.charAt(i-2)=='o' && wd.charAt(i-1)=='s' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					n = getRight(wd, i-3);
					r = new BigDecimal(Cosh(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-3, r, n, true));
					
				}
				
				if (wd.charAt(i-3)=='T' && wd.charAt(i-2)=='a' && wd.charAt(i-1)=='n' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					n = getRight(wd, i-3);
					r = new BigDecimal(Tanh(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-3, r, n, true));
					
				}
				
				if (wd.charAt(i-3)=='C' && wd.charAt(i-2)=='s' && wd.charAt(i-1)=='c' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					n = getRight(wd, i-3);
					r = new BigDecimal(Csch(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-3, r, n, true));
					
				}
				
				if (wd.charAt(i-3)=='S' && wd.charAt(i-2)=='e' && wd.charAt(i-1)=='c' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					n = getRight(wd, i-3);
					r = new BigDecimal(Sech(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-3, r, n, true));
					
				}
				
				if (wd.charAt(i-3)=='C' && wd.charAt(i-2)=='o' && wd.charAt(i-1)=='t' && wd.charAt(i)=='h'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					
					n = getRight(wd, i-3);
					r = new BigDecimal(Coth(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-3, r, n, true));
					
				}
				
			}
			
			for (int i=5; i<wd.length(); i++){
			
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='s' && wd.charAt(i-1)=='e' && wd.charAt(i)=='n'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					n = getRight(wd, i-5);
					r = new BigDecimal(Arcsen(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, r, n, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='c' && wd.charAt(i-1)=='o' && wd.charAt(i)=='s'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					n = getRight(wd, i-5);
					r = new BigDecimal(Arccos(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, r, n, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='t' && wd.charAt(i-1)=='a' && wd.charAt(i)=='n'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					n = getRight(wd, i-5);
					r = new BigDecimal(Arctan(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, r, n, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='c' && wd.charAt(i-1)=='s' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					n = getRight(wd, i-5);
					r = new BigDecimal(Arccsc(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, r, n, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='s' && wd.charAt(i-1)=='e' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					n = getRight(wd, i-5);
					r = new BigDecimal(Arcsec(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, r, n, true));
					
				}
				
				if (wd.charAt(i-5)=='A' && wd.charAt(i-4)=='r' && wd.charAt(i-3)=='c' && wd.charAt(i-2)=='c' && wd.charAt(i-1)=='o' && wd.charAt(i)=='t'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					wd = DeleteChar(wd,i-3);
					wd = DeleteChar(wd,i-4);
					wd = DeleteChar(wd,i-5);
					
					n = getRight(wd, i-5);
					r = new BigDecimal(Arccot(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-5, r, n, true));
					
				}
				
			}
			
			for (int i=2; i<wd.length(); i++){
			
				if (wd.charAt(i-2)=='S' && wd.charAt(i-1)=='e' && wd.charAt(i)=='n'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					n = getRight(wd, i-2);
					r = new BigDecimal(Sen(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, r, n, true));
					
				}
				
				if (wd.charAt(i-2)=='C' && wd.charAt(i-1)=='o' && wd.charAt(i)=='s'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					n = getRight(wd, i-2);
					r = new BigDecimal(Cos(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, r, n, true));
					
				}
				
				if (wd.charAt(i-2)=='T' && wd.charAt(i-1)=='a' && wd.charAt(i)=='n'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					n = getRight(wd, i-2);
					r = new BigDecimal(Tan(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, r, n, true));
					
				}
				
				if (wd.charAt(i-2)=='C' && wd.charAt(i-1)=='s' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					n = getRight(wd, i-2);
					r = new BigDecimal(Csc(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, r, n, true));
					
				}
				
				if (wd.charAt(i-2)=='S' && wd.charAt(i-1)=='e' && wd.charAt(i)=='c'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					n = getRight(wd, i-2);
					r = new BigDecimal(Sec(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, r, n, true));
					
				}
				
				if (wd.charAt(i-2)=='C' && wd.charAt(i-1)=='o' && wd.charAt(i)=='t'){
					
					wd = DeleteChar(wd,i-1);
					wd = DeleteChar(wd,i-2);
					
					n = getRight(wd, i-2);
					r = new BigDecimal(Cot(n.setScale(15, RoundingMode.HALF_UP).doubleValue()));
					
					return Parse(ReplaceOperation(wd, i-2, r, n, true));
					
				}
				
			}
			
		}else if (wd.contains("√") || wd.contains("^") || wd.contains("!") || wd.contains("ln") || wd.contains("log") || wd.contains("|")){
			
			if (wd.contains("√")){
				
				for (int i=0; i<wd.length(); i++){
				
					if (wd.charAt(i)=='√'){
						
						r = getRight(wd, i);
						l = getLeft(wd, i);

						n = new BigDecimal(Raiz(r.setScale(15, RoundingMode.HALF_UP).doubleValue(), l.setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
						return Parse(ReplaceOperation(wd, i, n, l, r));
						
					}
					
				}
				
			}
			
			if (wd.contains("^")){
				
				for (int i=0; i<wd.length(); i++){
				
					if (wd.charAt(i)=='^'){
						
						r = getRight(wd, i);
						l = getLeft(wd, i);

						n = bigPotencia(l, r.setScale(15, RoundingMode.HALF_UP).doubleValue());
						
						return Parse(ReplaceOperation(wd, i, n, l, r));
						
					}
					
				}
				
			}
			
			if (wd.contains("!")){
				
				for (int i=0; i<wd.length(); i++){
				
					if (wd.charAt(i)=='!'){
						
						l = getLeft(wd, i);

						n = new BigDecimal(Factorial(l.setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
						return Parse(ReplaceOperation(wd, i, n, l, false));
						
					}
					
				}
				
			}
			
			if (wd.contains("ln")){
				
				for (int i=1; i<wd.length(); i++){
					
					if (wd.charAt(i-1)=='l' && wd.charAt(i)=='n'){
						
						wd = DeleteChar(wd, i-1);
						
						r = getRight(wd, i-1);
						n = new BigDecimal(Ln(r.setScale(15, RoundingMode.HALF_UP).doubleValue()));
						
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

										n = new BigDecimal(Log(l.setScale(15, RoundingMode.HALF_UP).doubleValue(), r.setScale(15, RoundingMode.HALF_UP).doubleValue()));
										
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

					n = l.remainder(r, MathContext.DECIMAL128);
					
					return Parse(ReplaceOperation(wd, i, n, l, r));
					
				}
				
			}
			
			for (int i=0; i<wd.length(); i++){
				
				if (wd.charAt(i)=='/'){
					
					r = getRight(wd, i);
					l = getLeft(wd, i);

					n = l.divide(r, MathContext.DECIMAL128);
					
					return Parse(ReplaceOperation(wd, i, n, l, r));
					
				}
				
			}
			
			for (int i=0; i<wd.length(); i++){
				
				if (wd.charAt(i)=='*'){
					
					r = getRight(wd, i);
					l = getLeft(wd, i);

					n = l.multiply(r);
					
					return Parse(ReplaceOperation(wd, i, n, l, r));
					
				}
				
			}
			
			for (int i=0; i<wd.length(); i++){
				
				if (wd.charAt(i)=='+'){
					
					r = getRight(wd, i);
					l = getLeft(wd, i);

					n = l.add(r);
					
					return Parse(ReplaceOperation(wd, i, n, l, r));
					
				}
				
			}
			
			for (int i=0; i<wd.length(); i++){
				
				if (i==0 && wd.charAt(i)=='-'){continue;}
				
				if (wd.charAt(i)=='-'){
					
					r = getRight(wd, i);
					l = getLeft(wd, i);

					n = l.subtract(r);
					
					return Parse(ReplaceOperation(wd, i, n, l, r));
					
				}
				
			}
			
		}
		
		return new BigDecimal(wd);
		
	}
	
	private static String ReplaceOperation(String wd, int indice, BigDecimal Result, BigDecimal value, boolean b){
		
		String[] parts;
		
		if (b==true){
			
			parts = wd.split(wd.charAt(indice)+""+value);
			
		}else{
			
			parts = wd.split(value+""+wd.charAt(indice));
			
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
	
	private static String ReplaceOperation(String wd, int indice, BigDecimal Result, BigDecimal valueLeft, BigDecimal valueRight){
		
		String[] parts = wd.split(valueLeft+""+wd.charAt(indice)+""+valueRight);
		
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
	
	private static BigDecimal getRight(String wd, int indice){
		
		indice++;
		
		for (int i=indice; i<wd.length(); i++){
		
			if (i==wd.length()-1){return new BigDecimal(wd.substring(indice));}
			
			if ((wd.charAt(i)=='-' && i==indice) || wd.charAt(i)=='.'){continue;}
			
			if (isAllowed(wd.substring(indice, i+1))==false){
				
				return new BigDecimal(wd.substring(indice, i));
				
			}
			
		}
		
		return new BigDecimal(0);
		
	}
	
	private static BigDecimal getLeft(String wd, int indice){
		
		for (int i=indice-1; i>=0; i--){
		
			if (i==0){return new BigDecimal(wd.substring(0, indice));}
			
			if (wd.charAt(i)=='.'){continue;}
			
			if (isAllowed(wd.substring(i, indice))==false){
				
				return new BigDecimal(wd.substring(i+1, indice));
				
			}
			
		}
		
		return new BigDecimal(0);
		
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
			
			new BigDecimal(wd);
			
			return true;
			
		}catch(Exception e){
			
			return false;
			
		}
		
	}

}