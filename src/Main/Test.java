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
		
		double n = 0;
		
		for (int i=0; i<=360; i++){
			
			n = Trigonometry.Cos(i);
			
			bup+=i+"° --- "+Trigonometry.Arccos(n)+" --- "+Math.acos(n)*180/Math.PI+"\n";
			
			System.out.print(i+" --- "+Trigonometry.Arccos(n)+"\n");
			
		}//*/
		
		/*n = Trigonometry.Tan(91);//135
		
		//-572.957213354061 -- -1.0000000000000013
		//270.1 -- 315
		
		System.out.print(n+"\n\n");
		
		System.out.print(Trigonometry.Arctan(n));
		
		System.out.print("\n\n");
		
		System.out.print(Trigonometry.Arctan(Trigonometry.Tan(269)));//*/
		
		System.out.print(bup+"\n\n");
		
    }
    
}
