package Main;

/*
 *
 * @author Dandelion
 * 
 */

import Taylor.Math.*;
import Taylor.Arithmetic.*;
import Geometry.Euclidean.*;
import Geometry.Algebra.*;
import Physics.Fundamental.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {

    public static void main(String[] args){
        
		String bup = "(1235)";
		
		double n = 2;
		
		System.out.print("\n\n");
		
		/*vector a = new vector(new double[] {2, -5, 3, -1});
		vector b = new vector(new double[] {-1, 0, 7, -8});
		vector c = new vector(new double[] {9, 2, -2, -3});
		vector d = new vector(new double[] {-4, 0, -7, 4});
		
		bivector B = new bivector(c, d);
		
		Matriz mz = new Matriz(new vector[] {
			
			a, b, c, d
			
		});
		
		Quaternion q = new Quaternion(2, a);
		Quaternion p = new Quaternion(2, b);//*
		
		distance x = distance.getMetreValueOf(5);
		time t = time.getSecondValueOf(2);
		mass m = mass.getGramValueOf(3);
		
		Parser pr = new Parser("0");
		
		/*
		
			ln(((Sen(30))√2)!)+0.001*0.001*0.001+10E9
			(5+8)/(2*7)
			
		//*
		
		System.out.print(B+"\n\n");
		
		System.out.print(q+"\n\n");
		System.out.print(p+"\n\n");
		
		System.out.print(mz+"\n\n");//*
		
		System.out.print(x+"\n\n");
		System.out.print(t+"\n\n");
		System.out.print(m+"\n\n");//
		
		System.out.print(pr+"\n\n");//*/
		
		function f = new function('x', "x+2");
		
		f.set(2);
		
		System.out.print(f+"");
		
		System.out.print("\n\n");
		
    }
    
}
