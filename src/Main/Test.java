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
		double n = Trigonometry.Tan(225.5);
		
		System.out.print(n+"\n\n");
		
		System.out.print(Trigonometry.Arctan(n));
		
		System.out.print("\n\n");
		
		System.out.print(Trigonometry.Arctan(Trigonometry.Tan(269)));//*/
		
		//Seno: 90> x >=225.00000000000001: -
		
		//Tan: 135.00000000000001>= x <270: +
		//Tan: 27Pi/36 <2.356194490192345> >= x >3Pi/2 <4.71238898038469>
		//Tan: -0.9656887748070739 <= x < 57.28996163076109
		//Tan: 270.0000000000001>= x <=359.000000000000001: -
		
		System.out.print("\n\n");
		
    }
    
}
