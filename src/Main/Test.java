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
		
		vector a = new vector(1, 0, 3);
		vector b = new vector(0, 1, 3);
		
		Quaternion q = new Quaternion(2, a);
		Quaternion p = new Quaternion(2, b);
		
		//System.out.print(new bivector(a, b)+"\n");
		
		System.out.print(q.arcQuaternion());
		
		System.out.print("\n\n");
		
    }
    
}
