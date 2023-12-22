package Physics.Measurement;

/*
 *
 * @author Dandelion
 * 
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

import Taylor.Math.Mayth;
import Geometry.Euclidean.vector;
import Geometry.Euclidean.degree;
import Physics.Fundamental.mass;
import Physics.Fundamental.distance;
import Physics.Fundamental.time;

public class Force{
	
	private final vector F;
	private mass m;
	private distance x;
	private time t;

	public Force(double v){
		
		this.F = new vector(v, new degree(0));
		this.m = null;
		this.x = null;
		this.t = null;
		
	}
	
	public vector get(){
		
		return this.F;
		
	}
	
	public mass getMass(){
		
		return this.m;
		
	}
	
	public distance getDistance(){
		
		return this.x;
		
	}
	
	public time getTime(){
		
		return this.t;
		
	}
	
	public void setMass(mass m){
		
		BigDecimal bup;
		
		if (this.x==null && this.t==null || ((this.x==null || this.t==null) && this.m==null)){
			
			this.m = m;
			
		}
		
		if (this.m!=null && this.x!=null && this.t==null){
			
			bup = (new BigDecimal(m.getScalar()).multiply(new BigDecimal(x.getScalar()))).divide(new BigDecimal(this.F.getMagnitude()), MathContext.DECIMAL128);
			
			this.t = new time(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), "ut^2");
			
		}else if (this.m!=null && this.t!=null && this.x==null){
			
			bup = (new BigDecimal(t.getScalar()).multiply(new BigDecimal(this.F.getMagnitude()))).divide(new BigDecimal(m.getScalar()), MathContext.DECIMAL128);
			
			this.x = new distance(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), "ud");
			
		}
		
	}

}