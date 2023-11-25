package Main;

/*
 *
 * @author Dandelion
 * 
 */

import Taylor.Math.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {

    public static void main(String[] args){
        
		String bup = "";
		
		double n = 81;
		
		System.out.print("\n\n");
		
		/*for (int i=0; i<=360; i++){
			
			n = Mayth.Raiz(i, 2);
			
			bup+=i+" | "+n+" --- "+Math.sqrt(i)+"\n";
			
			System.out.print(i+" | "+n+"\n\n");
			
		}//*/
		
		System.out.print(Mayth.Sen(n)+" --- "+Math.sin(n*Math.PI/180));
		
		System.out.print(bup+"\n\n");
		
    }
    
}
