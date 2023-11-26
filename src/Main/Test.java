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
		
		double n = Mayth.Senh(36);
		
		System.out.print("\n\n");
		
		/*for (int i=0; i<=360; i++){
			
			n = Mayth.Senh(i);
			
			bup+=i+" | "+n+" --- "+Math.sinh(i)+"\n";
			
			System.out.print(i+" | "+n+"\n\n");
			
		}//*/
		
		System.out.print(Mayth.Ln(6)/Mayth.Ln(10));
		
		/*Mayth m = new Mayth(false);
		
		System.out.print(Mayth.Sen(60));*/
		
		System.out.print("\n\n");
		
    }
    
}
