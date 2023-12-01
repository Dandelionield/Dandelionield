package Main;

/*
 *
 * @author Dandelion
 * 
 */

import Taylor.Math.*;
import Geometry.Euclidean.*;
import Geometry.Algebra.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {

    public static void main(String[] args){
        
		String bup = "";
		
		System.out.print("\n\n");
		
		vector a = new vector(new double[] {2, -5, 3, 1});
		vector b = new vector(new double[] {-1, 0, 7, -8});
		vector c = new vector(new double[] {9, 2, -2, -3});
		vector d = new vector(new double[] {4, 0, -7, 4});
		
		Quaternion q = new Quaternion(2, a);
		Quaternion p = new Quaternion(2, b);
		
		Matriz m = new Matriz(new vector[] {a, b, c, d});
		
		System.out.print(m.doEuler().doRedondear(2)+"\n\n");
		System.out.print(a.doEuler().doRedondear(2)+"\n\n");//*/
		
		System.out.print(q.doRedondear(2)+"\n\n");
		System.out.print(q.doRaiz().doRedondear(2)+"\n\n");
		System.out.print(q.doPotencia(2).doRedondear(2)+"\n\n");
		System.out.print(q.doRaiz().doPotencia(2).doRedondear(2)+"\n\n");//*/
		
		System.out.print(p.getVector()+"\n\n");
		System.out.print(q.getVector()+"\n\n");
		System.out.print(p.doScalar(q)+"\n\n");
		
		System.out.print("\n\n");
		
    }
    
}
