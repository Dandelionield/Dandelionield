package Geometry.Euclidean;

/*
 *
 * @author Dandelion
 * 
 */

import java.util.ArrayList;
import Taylor.Math.Mayth;

public class coordinate{

	private double[] points;
	private double x;
	private double y;
	private double z;
	
	public coordinate(double x, double y){
		
		this.x = x;
		this.y = y;
		this.z = 0;
		
		this.points = new double[] {x, y};
		
	}
	
	public coordinate(double length, degree Theta){
		
		this.x = length*Theta.getCos();
		this.y = length*Theta.getSen();
		this.z = 0;
		
		this.points = new double[] {x, y};
		
	}
	
	/*public coordinate(double p, degree Theta){
		
		this.x = Math.exp(p)*Theta.getCos();
		this.y = Math.exp(p)*Theta.getSen();
		this.z = 0;
		
		this.v = new double[] {x, y, z};
		
	}//*/
	
	public coordinate(double x, double y, double z){
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.points = new double[] {x, y, z};
		
	}
	
	public coordinate(double length, double z, degree Theta){
		
		this.x = length*Theta.getCos();
		this.y = length*Theta.getSen();
		this.z = z;
		
		this.points = new double[] {x, y, z};
		
	}
	
	public coordinate(double length, degree Theta, degree Gamma){
		
		this.x = length*Gamma.getSen()*Theta.getCos();
		this.y = length*Gamma.getSen()*Theta.getSen();
		this.z = length*Gamma.getCos();
		
		this.points = new double[] {x, y, z};
		
	}
	
	public coordinate(double[] points){
		
		if (points.length==0){
			
			x = 0;
			y = 0;
			z = 0;
			
		}else if (points.length==1){
			
			x = points[0];
			y = 0;
			z = 0;
			
		}else if (points.length==2){
			
			x = points[0];
			y = points[1];
			z = 0;
			
		}else{
			
			x = points[0];
			y = points[1];
			z = points[2];
			
		}
		
		this.points = points;
		
	}
	
	public coordinate(ArrayList<Double> points){
		
		if (points.size()==0){
			
			x = 0;
			y = 0;
			z = 0;
			
		}else if (points.size()==1){
			
			x = points.get(0);
			y = 0;
			z = 0;
			
		}else if (points.size()==2){
			
			x = points.get(0);
			y = points.get(1);
			z = 0;
			
		}else{
			
			x = points.get(0);
			y = points.get(1);
			z = points.get(2);
			
		}
		
		double[] v = new double[points.size()];
		
		for (int i=0; i<v.length; i++){
			
			v[i] = points.get(i);
			
		}
		
		this.points = v;
		
	}
	
	private coordinate(coordinate a, int limite){
		
		this.x = Mayth.Redondear(a.x, limite);
		this.y = Mayth.Redondear(a.y, limite);
		this.z = Mayth.Redondear(a.z, limite);
		
		double[] v = new double[a.points.length];
		
		for (int i=0; i<v.length; i++){
			
			v[i] = Mayth.Redondear(a.points[i], limite);
			
		}
		
		this.points = v;
		
	}
	
	public double[] get(){
		
		return points;
		
	}
	
	public double get(int indice){
		
		try{
			
			return this.points[indice];
			
		}catch(Exception e){
			
			return 0;
			
		}
		
	}
	
	public double getX(){
		
		return this.x;
		
	}
	
	public double getY(){
		
		return this.y;
		
	}
	
	public double getZ(){
		
		return this.z;
		
	}
	
	public byte getOctant(){
		
		return new vector(this.points).getOctant();
		
	}
	
	public void set(double[] points){
		
		if (points.length==0){
			
			x = 0;
			y = 0;
			z = 0;
			
		}else if (points.length==1){
			
			x = points[0];
			y = 0;
			z = 0;
			
		}else if (points.length==2){
			
			x = points[0];
			y = points[1];
			z = 0;
			
		}else{
			
			x = points[0];
			y = points[1];
			z = points[2];
			
		}
		
		this.points = points;
		
	}
	
	public void set(ArrayList<Double> points){
		
		if (points.size()==0){
			
			x = 0;
			y = 0;
			z = 0;
			
		}else if (points.size()==1){
			
			x = points.get(0);
			y = 0;
			z = 0;
			
		}else if (points.size()==2){
			
			x = points.get(0);
			y = points.get(1);
			z = 0;
			
		}else{
			
			x = points.get(0);
			y = points.get(1);
			z = points.get(2);
			
		}
		
		double[] v = new double[points.size()];
		
		for (int i=0; i<v.length; i++){
			
			v[i] = points.get(i);
			
		}
		
		this.points = v;
		
	}
	
	public void set(double point, int indice){
		
		try{
			
			points[indice] = point;
			
			x = get(0);
			y = get(1);
			z = get(2);
			
		}catch(Exception e){}
		
	}
	
	public void setX(double x){
		
		this.x = x;
		
		this.set(x, 0);
		
	}
	
	public void setY(double y){
		
		this.y = y;
		
		this.set(y, 1);
		
	}
	
	public void setZ(double z){
		
		this.z = z;
		
		this.set(z, 2);
		
	}
	
	public String toString(){
		
		String bup = "(";
		
		for (double p : points){
			
			bup+= p+", ";
			
		}
		
		return bup.substring(0, bup.length()-2)+")";
		
	}
	
	public coordinate doSuma(coordinate b){
		
		return new coordinate(new vector(points).doSuma(new vector(b.points)).get());
		
	}
	
	public coordinate doResta(coordinate b){
		
		return new coordinate(new vector(points).doResta(new vector(b.points)).get());
		
	}
	
	public coordinate doScalar(double s){
		
		return new coordinate(new vector(points).doScalar(s).get());
		
	}
	
	public coordinate doRedondear(int limite){
		
		return new coordinate(this, limite);
		
	}

}