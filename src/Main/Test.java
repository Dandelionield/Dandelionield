package Main;

/*
 *
 * @author Dandelion
 * 
 */

import Taylor.Math.*;
import Geometry.Euclidean.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {

    public static void main(String[] args){
        
		String bup = "";
		int limite = 2;
		
		System.out.print("\n\n");
		
		/*Matriz m = new Matriz(new double[][]{
			{-2, 8.133221, 5, 4},
			{5, -6, 7, -9},
			{2, 1, 3, 8},
			{5, -6, 2, 3}
			
		});//*/
		
		/*for (int x=0; x<=10; x++){
			
			for (int y=0; y<=10; y++){
				
				for (int z=0; z<=10; z++){
					
					System.out.print(new vector(x, y, z)+"\n");
					
				}
				
			}
			
		}//*/
		
		vector a = new vector(new vector(3.6548, 4.1234, -1.2487), new coordinate(4.47824, -5.43548, 2.45871)).doRedondear(limite);
		
		System.out.print(" Datos vector a: "
        +"\n Magnitud: "+a.getMagnitude()
        +"\n Componente x: "+a.getComponentX()
        +"\n Componente y: "+a.getComponentY()
		+"\n Componente z: "+a.getComponentZ()
		+"\n Direccion   : "+a.getDirection()
        +"\n Direccion x : "+a.getDirectionX()
		+"\n Direccion y : "+a.getDirectionY()
		+"\n Direccion z : "+a.getDirectionZ()
        +"\n Octante: "+a.getOctant()
		+"\n Unidad: "+a.getUnity()
		+"\n Cola: "+a.getTail());//*/
		
		System.out.print("\n\n");//*/
		
		System.out.print(a);
		
		System.out.print("\n\n");
		
    }
    
}
