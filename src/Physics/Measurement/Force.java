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
	
	private long nth;
	private BigDecimal equivalent;

	public Force(double v){
		
		this.F = new vector(v, new degree(0));
		this.m = new mass(0, "um");
		this.x = new distance(0, "ud");
		this.t = new time(0, "ut^2");
		
		this.nth = 1;
		this.equivalent = new BigDecimal(0);
		
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
		
		this.m = m;
		
		if (this.x.getScalar()!=0){
			
			bup = (new BigDecimal(this.m.getScalar()).multiply(new BigDecimal(this.x.getScalar()))).divide(new BigDecimal(this.F.getMagnitude()), MathContext.DECIMAL128);
			
			this.t = new time(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), t.getUnity(), t.getSecondEquivalent());
			
		}else if (this.t.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.m.getScalar()), MathContext.DECIMAL128);
			
			this.x = new distance(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), x.getUnity(), x.getMetreEquivalent());
			
		}
		
	}
	
	public void setDistance(distance x){
		
		BigDecimal bup;
		
		this.x = x;
		
		if (this.m.getScalar()!=0){
			
			bup = (new BigDecimal(this.m.getScalar()).multiply(new BigDecimal(this.x.getScalar()))).divide(new BigDecimal(this.F.getMagnitude()), MathContext.DECIMAL128);
			
			this.t = new time(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), t.getUnity(), t.getSecondEquivalent());
			
		}else if (this.t.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.x.getScalar()), MathContext.DECIMAL128);
			
			this.m = new mass(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), m.getUnity(), m.getGramEquivalent());
			
		}
		
	}
	
	public void setTime(time t){
		
		BigDecimal bup;
		
		this.t = t;
		
		if (this.m.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.m.getScalar()), MathContext.DECIMAL128);
			
			this.x = new distance(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), x.getUnity(), x.getMetreEquivalent());
			
		}else if (this.x.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.x.getScalar()), MathContext.DECIMAL128);
			
			this.m = new mass(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), m.getUnity(), m.getGramEquivalent());
			
		}
		
	}

}