package Geometry.Euclidean;

/*
 *
 * @author Dandelion
 * 
 */

import java.util.ArrayList;
import Taylor.Math.Mayth;
import Geometry.Algebra.bivector;

public class vector{
	
	private final double[] v;
	public final int length;
	
	private final double Magnitude;
	private final degree Direction;
	private final degree DirectionX;
	private final degree DirectionY;
	private final degree DirectionZ;
	private final byte Octant;
	private final double cx;
	private final double cy;
	private final double cz;
	private String Unity;
	private coordinate Tail;
	
	private final Mayth Mth = new Mayth();
	
	public vector(double x, double y){
		
		cx = x;
		cy = y;
		cz = 0;
		Magnitude = getMagnitud();
		Direction = fixAngle(getAngulo(cx,true));
		DirectionX = getAngulo(cx,false);
		DirectionY = getAngulo(cy,false);
		DirectionZ = new degree(90);
		Octant = getOctante();
		Unity = "u";
		
		v = new double[] {cx, cy};
		Tail = new coordinate(0, 0);
		length = v.length;
		
	}
	
	public vector(double mag, degree Theta){
		
		Magnitude = mag;
		Direction = Theta;
		cx = Magnitude*Direction.getCos();
		cy = Magnitude*Direction.getSen();
		cz = 0;
		DirectionZ = new degree(90);
		DirectionY = getAngulo(cy,false);
		DirectionX = getAngulo(cx,false);
		Octant = getOctante();
		Unity = "u";
		
		v = new double[] {cx, cy};
		Tail = new coordinate(0, 0);
		length = v.length;
		
	}
	
	public vector(String Components){
		
		if (Match(Components)==true){
			
			String[] parts = Components.split("[^0-9.]+");
			
			Magnitude = Double.parseDouble(parts[0]);
			double bup = Double.parseDouble(parts[1]);
			
			parts = Components.split("[^a-zA-Z]+");
			
			Unity = parts[1];
			
			if (parts[2].equalsIgnoreCase("N")==true){
				
				if (parts[3].equalsIgnoreCase("E")==true){
					
					Direction = new degree(90-bup);
					
				}else if (parts[3].equalsIgnoreCase("O")==true || parts[3].equalsIgnoreCase("W")==true){
					
					Direction = new degree(90+bup);
					
				}else{
					
					Direction = new degree(90);
					
				}
				
			}else if (parts[2].equalsIgnoreCase("S")==true){
				
				if (parts[3].equalsIgnoreCase("E")==true){
					
					Direction = new degree(270+bup);
					
				}else if (parts[3].equalsIgnoreCase("O")==true || parts[3].equalsIgnoreCase("W")==true){
					
					Direction = new degree(270-bup);
					
				}else{
					
					Direction = new degree(270);
					
				}
				
			}else if (parts[2].equalsIgnoreCase("E")==true){
				
				if (parts[3].equalsIgnoreCase("N")==true){
					
					Direction = new degree(bup);
					
				}else if (parts[3].equalsIgnoreCase("S")==true){
					
					Direction = new degree(360-bup);
					
				}else{
					
					Direction = new degree(0);
					
				}
				
			}else{
				
				if (parts[3].equalsIgnoreCase("N")==true){
					
					Direction = new degree(180-bup);
					
				}else if (parts[3].equalsIgnoreCase("S")==true){
					
					Direction = new degree(180+bup);
					
				}else{
					
					Direction = new degree(180);
					
				}
				
			}
			
			cx = Magnitude*Direction.getCos();
			cy = Magnitude*Direction.getSen();
			cz = 0;
			DirectionZ = new degree(90);
			DirectionY = getAngulo(cy,false);
			DirectionX = getAngulo(cx,false);
			Octant = getOctante();
			
			v = new double[] {cx, cy};
			Tail = new coordinate(0, 0);
			length = v.length;
			
		}else{
			
			cx = 0;
			cy = 0;
			cz = 0;
			Magnitude = 0;
			Direction = new degree(Double.NaN);
			DirectionX = new degree(Double.NaN);
			DirectionY = new degree(Double.NaN);
			DirectionZ = new degree(Double.NaN);
			Octant = 1;
			Unity = "u";
			
			length = 0;
			v = null;
			Tail = null;
			
		}
		
	}
	
	public vector(double x, double y, double z){
		
		cx = x;
		cy = y;
		cz = z;
		Magnitude = getMagnitud();
		DirectionX = getAngulo(cx,false);
		DirectionY = getAngulo(cy,false);
		DirectionZ = getAngulo(cz,false);
		
		if (DirectionZ.getDegree()!=90 && cx!=0 && cy!=0){
			
			Direction = fixAngle(new degree(cx, cy, 0));
			
		}else{Direction = fixAngle(getAngulo(cx,true));}
		
		Octant = getOctante();
		Unity = "u";
		
		v = new double[] {cx, cy, cz};
		Tail = new coordinate(0, 0, 0);
		length = v.length;
		
	}
	
	public vector(double mag, degree Alpha, degree Beta, degree Gamma){
		
		Magnitude = mag;
		DirectionX = Alpha;
		DirectionY = Beta;
		DirectionZ = Gamma;
		cx = mag*Alpha.getCos();
		cy = mag*Beta.getCos();
		cz = mag*Gamma.getCos();
		
		if (DirectionZ.getDegree()!=90 && cx!=0 && cy!=0){
			
			Direction = fixAngle(new degree(cx, cy, 0));
			
		}else{Direction = fixAngle(getAngulo(cx,true));}
		
		Octant = getOctante();
		Unity = "u";
		
		v = new double[] {cx, cy, cz};
		Tail = new coordinate(0, 0, 0);
		length = v.length;
		
	}
	
	public vector(double mag, degree Theta, degree Gamma){
		
		Magnitude = mag;
		Direction = Theta;
		cx = Magnitude*Direction.getCos()*Gamma.getCos();
		cy = Magnitude*Direction.getSen()*Gamma.getCos();
		
		Gamma = new degree(90).doResta(Gamma);
		
		cz = Magnitude*Gamma.getCos();
		DirectionZ = getAngulo(cz,false);
		DirectionY = getAngulo(cy,false);
		DirectionX = getAngulo(cx,false);
		Octant = getOctante();
		Unity = "u";
		
		v = new double[] {cx, cy, cz};
		Tail = new coordinate(0, 0, 0);
		length = v.length;
		
	}
	
	public vector(coordinate Zero, coordinate Final){
		
		coordinate delta = Final.doResta(Zero);
		
		v = delta.get();
		Tail = Zero;
		
		cx = delta.get(0);
		cy = delta.get(1);
		cz = delta.get(2);
		
		Magnitude = getMagnitud(v);
		DirectionX = getAngulo(cx,false);
		DirectionY = getAngulo(cy,false);
		DirectionZ = getAngulo(cz,false);
		
		if (DirectionZ.getDegree()!=90 && cx!=0 && cy!=0){
			
			Direction = fixAngle(new degree(cx, cy, 0));
			
		}else{Direction = fixAngle(getAngulo(cx,true));}
		
		Octant = getOctante();
		Unity = "u";
		
		length = v.length;
		
	}
	
	public vector(double[] cv){
		
		v = cv;
		length = v.length;
		Tail = new coordinate(0, 0, 0);
		
		cx = v[0];
		
		if (cv.length<2){
			
			cz = 0;
			cy = 0;
			
		}else if (cv.length==2){
			
			cy = v[1];
			cz = 0;
			
		}else{
			
			cy = v[1];
			cz = v[2];
			
		}
		
		Magnitude = getMagnitud(v);
		DirectionX = getAngulo(cx,false);
		DirectionY = getAngulo(cy,false);
		DirectionZ = getAngulo(cz,false);
		
		if (DirectionZ.getDegree()!=90 && cx!=0 && cy!=0){
			
			Direction = fixAngle(new degree(cx, cy, 0));
			
		}else{Direction = fixAngle(getAngulo(cx,true));}
		
		Octant = getOctante();
		Unity = "u";
		
	}
	
	public vector(ArrayList<Double> acv){
		
		double[] cv = new double[acv.size()];
		
		for (int i=0; i<acv.size(); i++){
			
			cv[i] = acv.get(i);
			
		}
		
		v = cv;
		length = v.length;
		Tail = new coordinate(0, 0, 0);
		
		cx = v[0];
		
		if (cv.length<2){
			
			cz = 0;
			cy = 0;
			
		}else if (cv.length==2){
			
			cy = v[1];
			cz = 0;
			
		}else{
			
			cy = v[1];
			cz = v[2];
			
		}
		
		Magnitude = getMagnitud(v);
		DirectionX = getAngulo(cx,false);
		DirectionY = getAngulo(cy,false);
		DirectionZ = getAngulo(cz,false);
		
		if (DirectionZ.getDegree()!=90 && cx!=0 && cy!=0){
			
			Direction = fixAngle(new degree(cx, cy, 0));
			
		}else{Direction = fixAngle(getAngulo(cx,true));}
		
		Octant = getOctante();
		Unity = "u";
		
	}
	
	public vector(double[] cv, coordinate Tail){
		
		v = cv;
		length = v.length;
		this.Tail = Tail;
		
		cx = v[0];
		
		if (cv.length<2){
			
			cz = 0;
			cy = 0;
			
		}else if (cv.length==2){
			
			cy = v[1];
			cz = 0;
			
		}else{
			
			cy = v[1];
			cz = v[2];
			
		}
		
		Magnitude = getMagnitud(v);
		DirectionX = getAngulo(cx,false);
		DirectionY = getAngulo(cy,false);
		DirectionZ = getAngulo(cz,false);
		
		if (DirectionZ.getDegree()!=90 && cx!=0 && cy!=0){
			
			Direction = fixAngle(new degree(cx, cy, 0));
			
		}else{Direction = fixAngle(getAngulo(cx,true));}
		
		Octant = getOctante();
		Unity = "u";
		
	}
	
	public vector(ArrayList<Double> acv, coordinate Tail){
		
		double[] cv = new double[acv.size()];
		
		for (int i=0; i<acv.size(); i++){
			
			cv[i] = acv.get(i);
			
		}
		
		v = cv;
		length = v.length;
		this.Tail = Tail;
		
		cx = v[0];
		
		if (cv.length<2){
			
			cz = 0;
			cy = 0;
			
		}else if (cv.length==2){
			
			cy = v[1];
			cz = 0;
			
		}else{
			
			cy = v[1];
			cz = v[2];
			
		}
		
		Magnitude = getMagnitud(v);
		DirectionX = getAngulo(cx,false);
		DirectionY = getAngulo(cy,false);
		DirectionZ = getAngulo(cz,false);
		
		if (DirectionZ.getDegree()!=90 && cx!=0 && cy!=0){
			
			Direction = fixAngle(new degree(cx, cy, 0));
			
		}else{Direction = fixAngle(getAngulo(cx,true));}
		
		Octant = getOctante();
		Unity = "u";
		
	}
	
	public vector(vector a, coordinate Tail){
		
		cx = a.cx;
		cy = a.cy;
		cz = a.cz;
		Magnitude = a.Magnitude;
		Direction = a.Direction;
		DirectionX = a.DirectionX;
		DirectionY = a.DirectionY;
		DirectionZ = a.DirectionZ;
		Octant = a.Octant;
		Unity = a.Unity;
		
		v = a.v;
		this.Tail = Tail;
		length = v.length;
		
	}
	
	public vector(){
		
		cx = 0;
		cy = 0;
		cz = 0;
		Magnitude = 0;
		Direction = new degree(0);
		DirectionX = new degree(0);
		DirectionY = new degree(90);
		DirectionZ = new degree(90);
		Octant = 1;
		Unity = "u";
		
		v = new double[] {};
		Tail = new coordinate(0, 0, 0);
		length = 0;
		
	}
	
	private vector(vector a, int limite){
		
		cx = Mayth.Redondear(a.cx, limite);
		cy = Mayth.Redondear(a.cy, limite);
		cz = Mayth.Redondear(a.cz, limite);
		Magnitude = Mayth.Redondear(a.Magnitude, limite);
		Direction = a.Direction.doRedondear(limite);
		DirectionX = a.DirectionX.doRedondear(limite);
		DirectionY = a.DirectionY.doRedondear(limite);
		DirectionZ = a.DirectionZ.doRedondear(limite);
		Octant = a.Octant;
		Unity = a.Unity;
		
		double[] bup = new double[a.length];
		
		for (int i=0; i<bup.length; i++){
			
			bup[i] = Mayth.Redondear(a.v[i], limite);
			
		}
		
		v = bup;
		Tail = a.Tail.doRedondear(limite);
		length = v.length;
		
	}
	
	public vector add(vector b){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			v.add(p);
			
		}
		
		for (double p : b.v){
			
			v.add(p);
			
		}
		
		return new vector(v);
		
	}
	
	public vector add(double[] b){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			v.add(p);
			
		}
		
		for (double p : b){
			
			v.add(p);
			
		}
		
		return new vector(v);
		
	}
	
	public vector add(double cp){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			v.add(p);
			
		}
		
		v.add(cp);
		
		return new vector(v);
		
	}
	
	public vector set(int indice, double cp){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			v.add(p);
			
		}
		
		try{
			
			v.remove(indice);
			v.add(indice, cp);
			
		}catch(Exception e){
			
			return null;
			
		}
		
		return new vector(v, Tail);
		
	}
	
	public vector insert(int indice, double cp){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (int i=0; i<this.length; i++){
			
			if (i==indice){
				
				v.add(cp);
				
			}
			
			v.add(this.v[i]);
			
		}
		
		return new vector(v, Tail);
		
	}
	
	public void setTail(coordinate Tail){
		
		this.Tail = Tail;
		
	}
	
	public double[] get(){
		
		return v;
		
	}
	
	public double get(int i){
		
		return v[i];
		
	}
	
	public int get(double cp){
		
		for (int i=0; i<this.length; i++){
			
			if (v[i]==cp){
				
				return i;
				
			}
			
		}
		
		return -1;
		
	}
	
	public coordinate getTail(){
		
		return Tail;
		
	}
	
	public coordinate getHead(){
		
		return Tail.doSuma(new coordinate(this.v));
		
	}
	
	public double getLargest(){
		
		double bup = 0;
		double n = this.v[0];
		
		for (double p : this.v){
			
			bup = p;
			
			if (bup>n){
				
				n = bup;
				
			}
			
		}
		
		return n;
		
	}
	
	public double getSmallest(){
		
		double bup = 0;
		double n = this.v[0];
		
		for (double p : this.v){
			
			bup = p;
			
			if (bup<n){
				
				n = bup;
				
			}
			
		}
		
		return n;
		
	}
	
	public vector getPairs(){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p%2==0 && p!=0){
				
				v.add(p);
				
			}
			
		}
		
		if (v.size()==0){
			
			return null;
			
		}
		
		return new vector(v);
		
	}
	
	public vector getOdds(){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p%2!=0 && p!=0){
				
				v.add(p);
				
			}
			
		}
		
		if (v.size()==0){
			
			return null;
			
		}
		
		return new vector(v);
		
	}
	
	public vector getPrimes(){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p%1==0){
				
				if (Mth.isPrime((long) p)){
					
					v.add(p);
					
				}
				
			}
			
		}
		
		if (v.size()==0){
			
			return null;
			
		}
		
		return new vector(v);
		
	}
	
	public vector getNevatives(){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p<0){
				
				v.add(p);
				
			}
			
		}
		
		if (v.size()==0){
			
			return null;
			
		}
		
		return new vector(v);
		
	}
	
	public vector getDividers(double n){
		
		if (n==0){
			
			return null;
			
		}
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p%n==0 && p!=0){
				
				v.add(p);
				
			}
			
		}
		
		if (v.size()==0){
			
			return null;
			
		}
		
		return new vector(v);
		
	}
	
	public vector throwDividers(double n){
		
		if (n==0){
			
			return this;
			
		}
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p%n!=0 && p!=0){
				
				v.add(p);
				
			}
			
		}
		
		if (v.size()==0){
			
			return this;
			
		}
		
		return new vector(v);
		
	}
	
	public vector throwNevatives(){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p>0){
				
				v.add(p);
				
			}
			
		}
		
		if (v.size()==0){
			
			return this;
			
		}
		
		return new vector(v);
		
	}
	
	public vector throwPrimes(){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p%1==0){
				
				if (!Mth.isPrime((long) p)){
					
					v.add(p);
					
				}
				
			}else{
				
				v.add(p);
				
			}
			
		}
		
		if (v.size()==0){
			
			return this;
			
		}
		
		return new vector(v);
		
	}
	
	public vector throwComponent(int indice){
		
		double c = 0;
		
		try{
			
			c = this.v[indice];
			
			if (this.length-1==0){
			
				return null;
				
			}
			
		}catch(Exception e){
			
			return this;
			
		}
		
		ArrayList<Double> v = new ArrayList<>();
		
		c = -1;
		
		for (double p : this.v){
			
			c++;
			
			if (c==indice){
				
				continue;
				
			}
			
			v.add(p);
			
		}
		
		return new vector(v);
		
	}
	
	public vector throwComponent(double n){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p!=n){
				
				v.add(p);
				
			}
			
		}
		
		if (v.size()==0){
			
			return this;
			
		}
		
		return new vector(v);
		
	}
	
	public vector throwZeros(){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p!=0){
				
				v.add(p);
				
			}
			
		}
		
		if (v.size()==0){
			
			return this;
			
		}
		
		return new vector(v);
		
	}
	
	public vector replaceComponent(double components, double replacement){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p==components){
				
				v.add(replacement);
				
			}else{
				
				v.add(p);
				
			}
			
		}
		
		return new vector(v);
		
	}
	
	public vector replaceAllComponentsBut(double replacement, double exception){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			if (p!=exception){
				
				v.add(replacement);
				
			}else{
				
				v.add(p);
				
			}
			
		}
		
		return new vector(v);
		
	}
	
	public vector shortBy(boolean b){
		
		ArrayList<Double> v = new ArrayList<>();
		vector bup = this;
		double cp = 0;
		
		do{
			
			cp = b ? bup.getLargest() : bup.getSmallest();
			
			v.add(cp);
			
			bup = bup.throwComponent(bup.get(cp));
			
		}while(bup!=null);
		
		return new vector(v);
		
	}
	
	public double getComponentX(){
		
		return cx;
		
	}
	
	public double getComponentY(){
		
		return cy;
		
	}
	
	public double getComponentZ(){
		
		return cz;
		
	}
	
	public double getMagnitude(){
		
		return Magnitude;
		
	}
	
	public degree getDirection(){
		
		return Direction;
		
	}
	
	public degree getDirectionX(){
		
		return DirectionX;
		
	}
	
	public degree getDirectionY(){
		
		return DirectionY;
		
	}
	
	public degree getDirectionZ(){
		
		return DirectionZ;
		
	}
	
	public byte getOctant(){
		
		return Octant;
		
	}
	
	public String getUnity(){
		
		return Unity;
		
	}
	
	public void setUnity(String Unity){
		
		this.Unity = Unity;
		
	}
	
	public String toString(){
		
		String bup = "<";
		
		for (double p : v){
			
			bup+= p+", ";
			
		}
		
		return bup.substring(0, bup.length()-2)+">";
		
	}
	
	public vector getUnitary(){
		
		if (this.Magnitude!=0){
			
			return this.doScalar(1.00/this.Magnitude);
			
		}else{
			
			return null;
			
		}
		
	}
	
	public vector doSuma(vector b){
		
		if (this.length<=3 && b.length<=3){
		
			return new vector(this.cx+b.cx, this.cy+b.cy, this.cz+b.cz);
			
		}else{
			
			return new vector(doSuma(this.v, b.v));
			
		}
		
	}
	
	public vector doResta(vector b){
		
		if (this.length<=3 && b.length<=3){
		
			return new vector(this.cx-b.cx, this.cy-b.cy, this.cz-b.cz);
			
		}else{
			
			return new vector(doResta(this.v, b.v));
			
		}		
	}
	
	public vector doScalar(double s){
		
		if (this.length<=3){
			
			return new vector(s*this.cx,s*this.cy,s*this.cz);
			
		}else{
			
			return new vector(doScalar(this.v, s));
			
		}
		
	}
	
	public double doScalar(vector b){
		
        if (this.length<=3 && b.length<=3){
			
			return this.cx*b.cx + this.cy*b.cy + this.cz*b.cz;
			
		}else{
			
			return doScalar(this.v, b.v);
			
		}
		
    }
	
	public vector doCross(vector b){
		
		if ((this.length<2 || this.length>3) && this.length!=b.length){
			
			return null;
			
		}
		
		final Matriz mz;
		
		if (this.length==2){
			
			mz = new Matriz(new vector[] {this,b});
			
			return new vector(0, 0, mz.getDetermine());
			
		}else{

			mz = new Matriz(new vector[] {new vector(1, 1, 1),this,b});
			
			return new vector(mz.getCofactor(0, 0).getDetermine(), -mz.getCofactor(0, 1).getDetermine(), mz.getCofactor(0, 2).getDetermine());
			
		}
		
	}
	
	public bivector doWedge(vector b){
		
		return new bivector(this, b);
		
	}
	
	public vector doHadamard(vector b){
		
		if (this.length<=3 && b.length<=3){
			
			return new vector(this.cx*b.cx, this.cy*b.cy, this.cz*b.cz);
			
		}else{
			
			return new vector(doHadamard(this.v, b.v));
			
		}
		
	}
	
	public vector doEuler(){
		
		return vector.getFullComponents(1, this.length).doScalar(Mth.Cosh(this.Magnitude)).doSuma(this.getUnitary().doScalar(Mth.Senh(this.Magnitude)));
		
	}
	
	public vector doLn(){
		
		ArrayList<Double> v = new ArrayList<>();
		
		for (double p : this.v){
			
			v.add(Mth.Ln(Mth.abs(p)));
			
		}
		
		return new vector(v);
		
	}
	
	public degree doBetweenTwo(vector b){
		
		return	new degree(Math.acos(this.doScalar(b)/(this.Magnitude*b.Magnitude)), true);
		
	}
	
	public vector doOrthogonalProjection(vector b){
		
		return this.doScalar(this.doScalar(b)/Mth.Potencia(this.Magnitude));
		
	}
	
	public vector do2DRotation(degree Theta){
		
		try{
			
			return new Matriz (new vector[]
		
				{new vector (Theta.getCos(), -Theta.getSen())
				,new vector (Theta.getSen(), Theta.getCos())}
				
			).doProduct(new Matriz(new vector[] {this}).doTransposedMatriz()).getColumn(0);
			
		}catch(Exception e){
			
			return null;
			
		}
		
	}
	
	public vector do3DRotationX(degree Theta){
		
		try{
			
			return new Matriz (new vector[]
			
				{new vector (1, 0, 0)
				,new vector (0, Theta.getCos(), -Theta.getSen())
				,new vector (0, Theta.getSen(), Theta.getCos())}
				
			).doProduct(new Matriz(new vector[] {this}).doTransposedMatriz()).getColumn(0);
			
		}catch(Exception e){
			
			return null;
			
		}
		
	}
	
	public vector do3DRotationY(degree Theta){
		
		try{
			
			return new Matriz (new vector[]
			
				{new vector (Theta.getCos(), 0, Theta.getSen())
				,new vector (0, 1, 0)
				,new vector (-Theta.getSen(), 0, Theta.getCos())}
				
			).doProduct(new Matriz(new vector[] {this}).doTransposedMatriz()).getColumn(0);
			
		}catch(Exception e){
			
			return null;
			
		}
		
	}
	
	public vector do3DRotationZ(degree Theta){
		
		try{
			
			return new Matriz (new vector[]
			
				{new vector (Theta.getCos(), -Theta.getSen(), 0)
				,new vector (Theta.getSen(), Theta.getCos(), 0)
				,new vector (0, 0, 1)}
				
			).doProduct(new Matriz(new vector[] {this}).doTransposedMatriz()).getColumn(0);
			
		}catch(Exception e){
			
			return null;
			
		}
		
	}
	
	public double doSumatory(){
		
		double Sumatory = 0;
		
		if (length<=3){
			
			return this.cx + this.cy + this.cz;
			
		}else{
			
			for (double p : v){
				
				Sumatory+= p;
				
			}
			
		}
		
		return Sumatory;
		
	}
	
	
	public double getAvarage(){
		
		return this.doSumatory()/this.length;
		
	}
	
	public static vector getFullComponents(double c, int length){
		
		double[] vz = new double[length];
		
		for (int i=0; i<vz.length; i++){
			
			vz[i] = c;
			
		}
		
		return new vector(vz);
		
	}
	
	public vector doRedondear(int limite){
		
		return new vector(this, limite);
		
	}
	
	private double getMagnitud(){
		
		return Math.sqrt(Mth.Potencia(cx)+Mth.Potencia(cy)+Mth.Potencia(cz));
		
	}
	
	private double getMagnitud(double[] vz){
		
		double n = 0;
		
		for (double p : vz){
			
			n+= Mth.Potencia(p);
			
		}
		
		return Math.sqrt(n);
		
	}
	
	private degree getAngulo(double c, boolean a){
		
		if (a==true){
			
			return new degree(Math.acos(Mth.abs(c/Magnitude)), true);
			
		}else{
			
			return new degree(Math.acos(c/Magnitude), true);
			
		}
		
	}
	
	private degree fixAngle(degree angulo){
		
		if(cx<=0 && cy>=0){//-+
			
			angulo = new degree(180-angulo.getDegree());
			
		}else if(cx<=0 && cy<=0){//--
			
			angulo = new degree(180+angulo.getDegree());
			
		}else if(cx>=0 && cy<0){//+-
			
			angulo = new degree(360-angulo.getDegree());
			
		}else{
			
			return angulo;
			
		}
		
		return angulo;
		
	}
	
	private degree fixAngle(degree angulo, int limite){
		
		do{
			
			if (angulo.getDegree()<0){
				
				angulo = new degree(limite+angulo.getDegree());
				
			}else if (angulo.getDegree()>limite){
				
				angulo = new degree(angulo.getDegree()-limite);
				
			}
			
		}while(angulo.getDegree()<0 || angulo.getDegree()>limite);
		
		return angulo;
		
	}
	
	private byte getOctante(){
		
		byte octante = 0;
		
		if (cx>=0 && cy>=0 && cz>=0){
			
			octante = 1;
			
		}else if (cx<=0 && cy>=0 && cz>=0){
			
			octante = 2;
			
		}else if (cx<=0 && cy<=0 && cz>=0){
			
			octante = 3;
			
		}else if (cx>=0 && cy<=0 && cz>=0){
			
			octante = 4;
			
		}else if (cx>=0 && cy>=0 && cz<=0){
			
			octante = 5;
			
		}else if (cx>=0 && cy<=0 && cz<=0){
			
			octante = 6;
			
		}else if (cx<=0 && cy>=0 && cz<=0){
			
			octante = 7;
			
		}else if (cx<=0 && cy<=0 && cz<=0){
			
			octante = 8;
			
		}
		
		return octante;
		
	}
	
	private double[] doSuma(double[] a, double[] b){
		
		double[] c = new double[a.length];
		
		Matriz vectores = Equalate(a, b, c);
		
		a = vectores.getRow(0).get();
		b =	vectores.getRow(1).get();
		c =	vectores.getRow(2).get();
		
		for (int i=0; i<a.length; i++){
			
			c[i] = a[i]+b[i];
			
		}
		
		return c;
		
	}
	
	private double[] doResta(double[] a, double[] b){
		
		double[] c = null;
		
		Matriz vectores = Equalate(a, b, c);
		
		a = vectores.getRow(0).get();
		b =	vectores.getRow(1).get();
		c =	vectores.getRow(2).get();
		
		for (int i=0; i<a.length; i++){
			
			c[i] = a[i]-b[i];
			
		}
		
		return c;
		
	}
	
	private double[] doScalar(double[] v, double s){
		
		double[] vz = new double[this.length];
		
		for (int i=0; i<v.length; i++){
			
			vz[i] = v[i]*s;
			
		}
		
		return vz;
		
	}
	
	private double doScalar(double[] a, double[] b){
		
		double Sumatory = 0;
		
		for (int i=0; i<a.length; i++){
			
			Sumatory+= a[i]*b[i];
			
		}
		
		return Sumatory;
		
	}
	
	private double[] doCross(Matriz mz){
		
		double[] vz = new double[mz.length];
		
		for (int i=0; i<mz.length; i++){
			
			vz[i] = Mth.Potencia(-1, i)*mz.getCofactor(0, i).getDetermine();
			
		}
		
		return vz;
		
	}
	
	private double[] doHadamard(double[] a, double[] b){
		
		double[] Productory = new double[a.length];
		
		Matriz vectores = Equalate(a, b, Productory);
		
		a = vectores.getRow(0).get();
		b =	vectores.getRow(1).get();
		Productory = vectores.getRow(2).get();
		
		for (int i=0; i<a.length; i++){
			
			Productory[i] = a[i]*b[i];
			
		}
		
		return Productory;
		
	}
	
	private Matriz Equalate(double[] a, double[] b, double[] c){
		
		if (a.length>b.length){
			
			c = new double[a.length];
			
			for (int i=0; i<b.length; i++){
				
				c[i] = b[i];
				
			}
			
			b = c;
			
		}else if (a.length<b.length){
			
			c = new double[b.length];
			
			for (int i=0; i<a.length; i++){
				
				c[i] = a[i];
				
			}
			
			a = c;
			
		}
		
		return new Matriz(new double[][] {a, b, c});
		
	}
	
	public static boolean Match(String wd){
		
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("([0-9]+\\.*[0-9]*)\\s*([a-z]+)\\s*([NnSsEeOoWw])\\s*([0-9]+\\.*[0-9]*)°\\s*([NnSsEeOoWw])", java.util.regex.Pattern.CASE_INSENSITIVE);
        java.util.regex.Matcher matcher = pattern.matcher(wd);
		
        return matcher.matches();
		
    }
	
}