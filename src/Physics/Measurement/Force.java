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
	
	private String Unity;
	private long nth;
	private BigDecimal equivalent;
	
	private final Mayth Mth = new Mayth();

	public Force(double v){
		
		this.F = new vector(v, new degree(0));
		this.m = new mass(0, "um");
		this.x = new distance(0, "ud");
		this.t = new time(0, "ut^2");
		
		this.Unity = "";
		this.nth = 1;
		this.equivalent = new BigDecimal(0);
		
	}
	
	public Force(vector v){
		
		this.F = v;
		this.m = new mass(0, "um");
		this.x = new distance(0, "ud");
		this.t = new time(0, "ut^2");
		
		this.Unity = "";
		this.nth = 1;
		this.equivalent = new BigDecimal(0);
		
	}
	
	public Force(mass m, distance x, time t){
		
		if (m.getDegree()!=1){
			
			throwError("mass "+m+" must have a degree of 1");
			
		}else if (x.getDegree()!=1){
			
			throwError("distance "+x+" must have a degree of 1");
			
		}else if (t.getDegree()!=2){
			
			throwError("time "+t+" must have a degree of 2");
			
		}
		
		this.F = new vector((m.getMagnitude()*x.getMagnitude())/t.getMagnitude(), new degree(0));
		this.m = m;
		this.x = x;
		this.t = t;
		
		this.Unity = "";
		this.nth = 1;
		this.equivalent = (m.getGramEquivalent().multiply(x.getMetreEquivalent())).divide(t.getSecondEquivalent(), MathContext.DECIMAL128);
		
	}
	
	public Force(mass m, distance x, time t, degree Theta){
		
		if (m.getDegree()!=x.getDegree() || t.getDegree()!=2*m.getDegree()){
			
			throwError("mass and distance degree must be "+m.getDegree()+" and time degree must be "+(2*m.getDegree()));
			
		}
		
		this.F = new vector((m.getMagnitude()*x.getMagnitude())/t.getMagnitude(), Theta);
		this.m = m;
		this.x = x;
		this.t = t;
		
		this.Unity = "";
		this.nth = m.getDegree();
		this.equivalent = (m.getGramEquivalent().multiply(x.getMetreEquivalent())).divide(t.getSecondEquivalent(), MathContext.DECIMAL128);
		
	}
	
	public Force(mass m, distance x, time t, degree Alpha, degree Beta, degree Gamma){
		
		if (m.getDegree()!=x.getDegree() || t.getDegree()!=2*m.getDegree()){
			
			throwError("mass and distance degree must be "+m.getDegree()+" and time degree must be "+(2*m.getDegree()));
			
		}
		
		this.F = new vector((m.getMagnitude()*x.getMagnitude())/t.getMagnitude(), Alpha, Beta, Gamma);
		this.m = m;
		this.x = x;
		this.t = t;
		
		this.Unity = "";
		this.nth = m.getDegree();
		this.equivalent = (m.getGramEquivalent().multiply(x.getMetreEquivalent())).divide(t.getSecondEquivalent(), MathContext.DECIMAL128);
		
	}
	
	private Force(double Mag, mass m, distance x, time t, BigDecimal equivalent, vector v, String Unity, long nth){
		
		this.F = new vector(Mag, v.getDirectionX(), v.getDirectionY(), v.getDirectionZ());
		this.m = m;
		this.x = x;
		this.t = t;
		
		this.Unity = Unity;
		this.nth = nth;
		this.equivalent = equivalent;
		
	}
	
	private Force(Force F, int limite){
		
		this.F = F.F.doRedondear(limite);
		this.m = F.m.doRedondear(limite);
		this.x = F.x.doRedondear(limite);
		this.t = F.t.doRedondear(limite);
		
		this.Unity = F.Unity;
		this.nth = F.nth;
		this.equivalent = F.equivalent;
		
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
	
	public String getUnity(){
		
		if (this.Unity.equals("")==false){
			
			return this.Unity;
			
		}
		
		return m.getUnity()+"*"+x.getUnity()+"/"+t.getUnity();
		
	}
	
	public BigDecimal getBaseEquivalent(){
		
		return this.equivalent;
		
	}
	
	public String toString(){
		
		if (this.Unity.equals("")==false){
			
			return this.F.getMagnitude()+" "+this.Unity+"\n"+this.nth;
			
		}
		
		return this.F.getMagnitude()+" "+m.getUnity()+"*"+x.getUnity()+"/"+t.getUnity();
		
	}
	
	public Force toBase(){
		
		return new Force(new BigDecimal(this.F.getMagnitude()).multiply(this.equivalent).setScale(15, RoundingMode.HALF_UP).doubleValue(), this.m.toGram(), this.x.toMetre(), this.t.toSecond(), new BigDecimal(1), this.F, this.Unity,this.nth);
		
	}
	
	public void setUnity(String Unity){
		
		this.Unity = Unity;
		
	}
	
	public void setMass(mass m){
		
		if (m.getDegree()!=1*nth){
			
			throwError("mass "+m+" must have a degree of "+nth);
			
		}
		
		BigDecimal bup;
		
		this.m = m;
		
		if (this.x.getScalar()!=0){
			
			bup = (new BigDecimal(this.m.getScalar()).multiply(new BigDecimal(this.x.getScalar()))).divide(new BigDecimal(this.F.getMagnitude()), MathContext.DECIMAL128);
			
			this.t = new time(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), t.getUnity(), t.getSecondEquivalent());
			
		}else if (this.t.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.m.getScalar()), MathContext.DECIMAL128);
			
			this.x = new distance(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), x.getUnity(), x.getMetreEquivalent());
			
		}
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			if (this.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0){
				
				this.equivalent = (this.m.getGramEquivalent().multiply(this.x.getMetreEquivalent())).divide(this.t.getSecondEquivalent(), MathContext.DECIMAL128);
				
			}
			
		}
		
	}
	
	public void setMass(double m){
		
		BigDecimal bup;
		
		this.m = new mass(m, this.m.getUnity(), this.m.getGramEquivalent());
		
		if (this.x.getScalar()!=0){
			
			bup = (new BigDecimal(this.m.getScalar()).multiply(new BigDecimal(this.x.getScalar()))).divide(new BigDecimal(this.F.getMagnitude()), MathContext.DECIMAL128);
			
			this.t = new time(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), t.getUnity(), t.getSecondEquivalent());
			
		}else if (this.t.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.m.getScalar()), MathContext.DECIMAL128);
			
			this.x = new distance(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), x.getUnity(), x.getMetreEquivalent());
			
		}
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			if (this.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0){
				
				this.equivalent = (this.m.getGramEquivalent().multiply(this.x.getMetreEquivalent())).divide(this.t.getSecondEquivalent(), MathContext.DECIMAL128);
				
			}
			
		}
		
	}
	
	public void setDistance(distance x){
		
		if (x.getDegree()!=1*nth){
			
			throwError("distance "+x+" must have a degree of "+nth);
			
		}
		
		BigDecimal bup;
		
		this.x = x;
		
		if (this.m.getScalar()!=0){
			
			bup = (new BigDecimal(this.m.getScalar()).multiply(new BigDecimal(this.x.getScalar()))).divide(new BigDecimal(this.F.getMagnitude()), MathContext.DECIMAL128);
			
			this.t = new time(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), t.getUnity(), t.getSecondEquivalent());
			
		}else if (this.t.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.x.getScalar()), MathContext.DECIMAL128);
			
			this.m = new mass(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), m.getUnity(), m.getGramEquivalent());
			
		}
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			if (this.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0){
				
				this.equivalent = (this.m.getGramEquivalent().multiply(this.x.getMetreEquivalent())).divide(this.t.getSecondEquivalent(), MathContext.DECIMAL128);
				
			}
			
		}
		
	}
	
	
	public void setDistance(double x){
		
		BigDecimal bup;
		
		this.x = new distance(x, this.x.getUnity(), this.x.getMetreEquivalent());
		
		if (this.m.getScalar()!=0){
			
			bup = (new BigDecimal(this.m.getScalar()).multiply(new BigDecimal(this.x.getScalar()))).divide(new BigDecimal(this.F.getMagnitude()), MathContext.DECIMAL128);
			
			this.t = new time(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), t.getUnity(), t.getSecondEquivalent());
			
		}else if (this.t.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.x.getScalar()), MathContext.DECIMAL128);
			
			this.m = new mass(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), m.getUnity(), m.getGramEquivalent());
			
		}
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			if (this.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0){
				
				this.equivalent = (this.m.getGramEquivalent().multiply(this.x.getMetreEquivalent())).divide(this.t.getSecondEquivalent(), MathContext.DECIMAL128);
				
			}
			
		}
		
	}
	
	public void setTime(time t){
		
		if (t.getDegree()!=2*nth){
			
			throwError("time "+t+" must have a degree of "+(2*nth));
			
		}
		
		BigDecimal bup;
		
		this.t = t;
		
		if (this.m.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.m.getScalar()), MathContext.DECIMAL128);
			
			this.x = new distance(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), x.getUnity(), x.getMetreEquivalent());
			
		}else if (this.x.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.x.getScalar()), MathContext.DECIMAL128);
			
			this.m = new mass(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), m.getUnity(), m.getGramEquivalent());
			
		}
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			if (this.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0){
				
				this.equivalent = (this.m.getGramEquivalent().multiply(this.x.getMetreEquivalent())).divide(this.t.getSecondEquivalent(), MathContext.DECIMAL128);
				
			}
			
		}
		
	}
	
	public void setTime(double t){
		
		BigDecimal bup;
		
		this.t = new time(t, this.t.getUnity(), this.t.getSecondEquivalent());
		
		if (this.m.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.m.getScalar()), MathContext.DECIMAL128);
			
			this.x = new distance(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), x.getUnity(), x.getMetreEquivalent());
			
		}else if (this.x.getScalar()!=0){
			
			bup = (new BigDecimal(this.F.getMagnitude()).multiply(new BigDecimal(this.t.getScalar()))).divide(new BigDecimal(this.x.getScalar()), MathContext.DECIMAL128);
			
			this.m = new mass(bup.setScale(15, RoundingMode.HALF_UP).doubleValue(), m.getUnity(), m.getGramEquivalent());
			
		}
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			if (this.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0){
				
				this.equivalent = (this.m.getGramEquivalent().multiply(this.x.getMetreEquivalent())).divide(this.t.getSecondEquivalent(), MathContext.DECIMAL128);
				
			}
			
		}
		
	}
	
	public Force doSuma(Force F){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add forces due to variable "+this.toString()+" has no equivalency in g*m/s^2");
			
		}else if (F.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to add forces due to variable "+F.toString()+" has no equivalency in g*m/s^2");
			
		}
		
		throwAdditionSubtractionError(this.nth, F.nth, (byte) 1);
		
		Force nthBaseValue1 = this.toBase();
		Force nthBaseValue2 = F.toBase();
		vector v = nthBaseValue1.F.doSuma(nthBaseValue2.F);
		BigDecimal s = new BigDecimal(nthBaseValue2.t.getScalar()*(nthBaseValue1.m.getScalar()*nthBaseValue1.x.getScalar()) + nthBaseValue1.t.getScalar()*(nthBaseValue2.m.getScalar()*nthBaseValue2.x.getScalar()));
		
		if (this.m.getGramEquivalent().compareTo(new BigDecimal(0))!=0 && this.x.getMetreEquivalent().compareTo(new BigDecimal(0))!=0 && this.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0){
			
			s = s.divide(this.m.getGramEquivalent().multiply(this.x.getMetreEquivalent()), MathContext.DECIMAL128);
			
			return new Force(
			
				new mass(Math.sqrt(s.setScale(15, RoundingMode.HALF_UP).doubleValue()), this.m.getUnity(), this.m.getGramEquivalent()),
				new distance(Math.sqrt(s.setScale(15, RoundingMode.HALF_UP).doubleValue()), this.x.getUnity(), this.x.getMetreEquivalent()),
				this.t.doScalar(
				
					new BigDecimal(nthBaseValue2.t.getScalar()).divide(this.t.getSecondEquivalent(), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue()
					
				),
				
				v.getDirectionX(), v.getDirectionY(), v.getDirectionZ()
				
			).doRedondear(14);
			
		}else{
			
			return new Force(
			
				new BigDecimal(v.getMagnitude()).divide(this.equivalent, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(),
				this.m.doScalar(0),
				this.x.doScalar(0),
				this.t.doScalar(0),
				this.equivalent,
				v,
				this.Unity,
				this.nth
			
			);
			
		}
		
	}
	
	public Force doResta(Force F){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract forces due to variable "+this.toString()+" has no equivalency in g*m/s^2");
			
		}else if (F.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to subtract forces due to variable "+F.toString()+" has no equivalency in g*m/s^2");
			
		}
		
		throwAdditionSubtractionError(this.nth, F.nth, (byte) -1);
		
		Force nthBaseValue1 = this.toBase();
		Force nthBaseValue2 = F.toBase();
		vector v = nthBaseValue1.F.doResta(nthBaseValue2.F);
		BigDecimal s = new BigDecimal(nthBaseValue2.t.getScalar()*(nthBaseValue1.m.getScalar()*nthBaseValue1.x.getScalar()) - nthBaseValue1.t.getScalar()*(nthBaseValue2.m.getScalar()*nthBaseValue2.x.getScalar()));
		
		if (this.m.getGramEquivalent().compareTo(new BigDecimal(0))!=0 && this.x.getMetreEquivalent().compareTo(new BigDecimal(0))!=0 && this.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0){
			
			s = s.divide(this.m.getGramEquivalent().multiply(this.x.getMetreEquivalent()), MathContext.DECIMAL128);
			
			return new Force(
			
				new mass(Math.sqrt(s.setScale(15, RoundingMode.HALF_UP).doubleValue()), this.m.getUnity(), this.m.getGramEquivalent()),
				new distance(Math.sqrt(s.setScale(15, RoundingMode.HALF_UP).doubleValue()), this.x.getUnity(), this.x.getMetreEquivalent()),
				this.t.doScalar(
				
					new BigDecimal(nthBaseValue2.t.getScalar()).divide(this.t.getSecondEquivalent(), MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue()
					
				),
				
				v.getDirectionX(), v.getDirectionY(), v.getDirectionZ()
				
			).doRedondear(14);
			
		}else{
			
			return new Force(
			
				new BigDecimal(v.getMagnitude()).divide(this.equivalent, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue(),
				this.m.doScalar(0),
				this.x.doScalar(0),
				this.t.doScalar(0),
				this.equivalent,
				v,
				this.Unity,
				this.nth
			
			);
			
		}
		
	}
	
	public Force doRedondear(int limite){
		
		return new Force(this, limite);
		
	}
	
	private void throwAdditionSubtractionError(long a, long b, byte n){
		
		String c = "", d = "", h= "";
		
		if (n<1){
			
			h = "subtract";
			
		}else{
			
			h = "add";
			
		}
		
		if (a!=b){
		
			if (a==0){
				
				c = " magnitude";
				
			}else{
				
				c = " force";
				
			}
			
			if (b==0){
				
				d = " magnitude";
				
			}else{
				
				d = " force";
				
			}
			
			throwError("unabled to "+h+" a"+d+" to a"+c);
			
		}
		
	}
	
	private void throwError(String ErrorMessage){
		
		try {

            throw new RuntimeException(ErrorMessage);
			
        } catch (RuntimeException e) {

            e.printStackTrace();
			
			System.exit(0);
			
        }
		
	}

}