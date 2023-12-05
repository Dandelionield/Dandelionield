package Main;

/*
 *
 * @author Dandelion
 * 
 */

import Taylor.Math.*;
import Geometry.Euclidean.*;
import Geometry.Algebra.*;
import Physics.Fundamental.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {

    public static void main(String[] args){
        
		String bup = "";
		
		System.out.print("\n\n");
		
		/*vector a = new vector(new double[] {2, -5, 3, -1});
		vector b = new vector(new double[] {-1, 0, 7, -8});
		vector c = new vector(new double[] {9, 2, -2, -3});
		vector d = new vector(new double[] {-4, 0, -7, 4});
		
		Quaternion q = new Quaternion(2, a);
		Quaternion p = new Quaternion(2, b);
		
		Matriz m = new Matriz(new vector[] {a, b, c, d});//*/
		
		time t = time.getGregorianYearValueOf(1);
		time t2 = time.getYearValueOf(1);
		
		System.out.print(t.getSecondEquivalent()+"\n\n");
		System.out.print(t2.getSecondEquivalent()+"\n\n");
		
		System.out.print("\n\n");
		
    }
    
}
