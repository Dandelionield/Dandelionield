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
		
		//double n = Mayth.Sen(180);
		
		System.out.print("\n\n");
		
		/*for (int i=0; i<=360; i++){
			
			n = Mayth.Sen(i);
			
			bup+=i+" | "+n+" --- "+Math.sin(i*Math.PI/180)+"\n";
			
			System.out.print(i+" | "+n+"\n\n");
			
		}//*/
		
		System.out.print(Mayth.Auero());
		
		/*Mayth m = new Mayth(true);
		
		System.out.print(Mayth.Arcsen(0.5)*6);//*/
		
		System.out.print(bup+"\n\n");
		
    }
    
}
