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
		
		Parser pr = new Parser("ln(((Sen(30))√2)!)+0.001*0.001*0.001+10E9");
		function f = new function('x', "x^2-(1/x)+x");
		
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
		
		System.out.print(pr+"\n\n");//
		
		f.intervalueOf(9, 10, 1);
		
		System.out.print(f+"\n\n");//*/
		
		Parametric par = new Parametric(new char[] {'x', 'y', 'z', 't'}, "(x*y^2)/z+t");
		
		par.intervalueOf(new double[] {3, 4, 6, 9}, new double[] {10, 10, 10, 10}, 0.5);
		
		System.out.print(par+"\n\n");
		
		System.out.print("\n\n");
		
    }
    
}
