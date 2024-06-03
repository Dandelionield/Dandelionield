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

public class Force implements Comparable<Force>{
	
	private final vector F;
	private mass m;
	private distance x;
	private time t;
	
	private long nth;
	private BigDecimal equivalent;
	
	private final Mayth Mth = new Mayth();

	public Force(double v){
		
		this.F = new vector(v, new degree(0));
		this.m = new mass(0, "um");
		this.x = new distance(0, "ud");
		this.t = new time(0, "ut^2");
		
		this.nth = 1;
		this.equivalent = new BigDecimal(0);
		
	}
	
	public Force(vector v){
		
		this.F = v;
		this.m = new mass(0, "um");
		this.x = new distance(0, "ud");
		this.t = new time(0, "ut^2");
		
		this.nth = 1;
		this.equivalent = new BigDecimal(0);
		
	}
	
	public Force(mass m, distance x, time t){
		
		if (m.getDegree()!=x.getDegree() || t.getDegree()!=2*m.getDegree()){
			
			throwError("mass and distance degree must be "+m.getDegree()+" and time degree must be "+(2*m.getDegree()));
			
		}
		
		this.F = new vector((m.getMagnitude()*x.getMagnitude())/t.getMagnitude(), new degree(0));
		this.m = m;
		this.x = x;
		this.t = t;
		
		this.nth = m.getDegree();
		
		try{
			
			this.equivalent = (m.getGramEquivalent().multiply(x.getMetreEquivalent())).divide(t.getSecondEquivalent(), MathContext.DECIMAL128);
			
		}catch(Exception e){this.equivalent = new BigDecimal(0);}
		
	}
	
	public Force(mass m, distance x, time t, degree Theta){
		
		if (m.getDegree()!=x.getDegree() || t.getDegree()!=2*m.getDegree()){
			
			throwError("mass and distance degree must be "+m.getDegree()+" and time degree must be "+(2*m.getDegree()));
			
		}
		
		this.F = new vector((m.getMagnitude()*x.getMagnitude())/t.getMagnitude(), Theta);
		this.m = m;
		this.x = x;
		this.t = t;
		
		this.nth = m.getDegree();
		
		try{
			
			this.equivalent = (m.getGramEquivalent().multiply(x.getMetreEquivalent())).divide(t.getSecondEquivalent(), MathContext.DECIMAL128);
			
		}catch(Exception e){this.equivalent = new BigDecimal(0);}
		
	}
	
	public Force(mass m, distance x, time t, degree Alpha, degree Beta, degree Gamma){
		
		if (m.getDegree()!=x.getDegree() || t.getDegree()!=2*m.getDegree()){
			
			throwError("mass and distance degree must be "+m.getDegree()+" and time degree must be "+(2*m.getDegree()));
			
		}
		
		this.F = new vector((m.getMagnitude()*x.getMagnitude())/t.getMagnitude(), Alpha, Beta, Gamma);
		this.m = m;
		this.x = x;
		this.t = t;
		
		this.nth = m.getDegree();
		
		try{
			
			this.equivalent = (m.getGramEquivalent().multiply(x.getMetreEquivalent())).divide(t.getSecondEquivalent(), MathContext.DECIMAL128);
			
		}catch(Exception e){this.equivalent = new BigDecimal(0);}
		
	}
	
	private Force(double Mag, mass m, distance x, time t, BigDecimal equivalent, vector v, long nth){
		
		this.F = new vector(Mag, v.getDirectionX(), v.getDirectionY(), v.getDirectionZ());
		this.m = m;
		this.x = x;
		this.t = t;
		
		this.nth = nth;
		this.equivalent = equivalent;
		
	}
	
	private Force(Force F, int limite){
		
		this.F = F.F.doRedondear(limite);
		this.m = F.m.doRedondear(limite);
		this.x = F.x.doRedondear(limite);
		this.t = F.t.doRedondear(limite);
		
		this.nth = F.nth;
		this.equivalent = F.equivalent;
		
	}
	
	public int compareTo(Force b){
		
		return this.F.compareTo(b.F);
		
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
		
		return m.getUnity()+"*"+x.getUnity()+"/"+t.getUnity();
		
	}
	
	public BigDecimal getBaseEquivalent(){
		
		return this.equivalent;
		
	}
	
	public String toString(){
		
		return this.F.getMagnitude()+" "+m.getUnity()+"*"+x.getUnity()+"/"+t.getUnity();
		
	}
	
	public Force toBase(){
		
		return new Force(new BigDecimal(this.F.getMagnitude()).multiply(this.equivalent).setScale(15, RoundingMode.HALF_UP).doubleValue(), this.m.toGram(), this.x.toMetre(), this.t.toSecond(), new BigDecimal(1), this.F, this.nth);
		
	}
	
	public Force to(Force F){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("variable "+this.toString()+" has no equivalency in g*m/s^2");
			
		}else if (F.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("variable "+F.toString()+" has no equivalency in g*m/s^2");
			
		}
		
		mass m1;
		distance x1;
		time t1;
		boolean pass = true;
		String bup = "";
		
		double ForceValue = 0;
		BigDecimal newEquivalence = new BigDecimal(1);
		BigDecimal BackUpEquivalence = new BigDecimal(0);
		
		if (this.m.getGramEquivalent().compareTo(new BigDecimal(0))!=0 && F.m.getGramEquivalent().compareTo(new BigDecimal(0))!=0){

            m1 = this.m.to(F.m);
			
        }else{
			
			bup = F.m.getUnity();
			
			if (bup.indexOf('^')!=-1){
				
				bup = bup.substring(0, bup.indexOf('^'));
				
			}
			
			if (this.m.getDegree()!=1){
				
				bup+= "^"+this.m.getDegree();
				
			}
			
			m1 = new mass(0, bup, 0);
			
			pass = false;
			
        }
		
		if (this.x.getMetreEquivalent().compareTo(new BigDecimal(0))!=0 && F.x.getMetreEquivalent().compareTo(new BigDecimal(0))!=0){

            x1 = this.x.to(F.x);
			
        }else{
			
			bup = F.x.getUnity();
			
			if (bup.indexOf('^')!=-1){
				
				bup = bup.substring(0, bup.indexOf('^'));
				
			}
			
			if (this.x.getDegree()!=1){
				
				bup+= "^"+this.x.getDegree();
				
			}
			
			x1 = new distance(0, bup, 0);
			
			pass = false;
			
        }
		
		if (this.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0 && F.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0){

            t1 = this.t.to(F.t);
			
        }else{
			
			bup = F.t.getUnity();
			
			if (bup.indexOf('^')!=-1){
				
				bup = bup.substring(0, bup.indexOf('^'));
				
			}
			
			if (this.t.getDegree()!=1){
				
				bup+= "^"+this.t.getDegree();
				
			}
			
			t1 = new time(0, bup, 0);
			
			pass = false;
			
        }
		
		if (pass==true){
			
			return new Force(m1, x1, t1);
			
		}else{
			
			if (this.nth==F.nth){
			
				ForceValue = new BigDecimal(this.toBase().F.getMagnitude()).divide(F.equivalent, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue();
				
				return new Force(ForceValue, m1, x1, t1, F.equivalent, new vector(ForceValue, this.F.getDirectionX(), this.F.getDirectionY(), this.F.getDirectionZ()), this.nth);
				
			}else{
				
				if (F.nth!=1){
					
					BackUpEquivalence = new BigDecimal(Math.pow(F.equivalent.setScale(15, RoundingMode.HALF_UP).doubleValue(), 1.00/F.nth));
					
				}else{
					
					BackUpEquivalence = F.equivalent;
					
				}
				
				for (int i=0; i<Mth.abs(this.nth); i++){
					
					newEquivalence = newEquivalence.multiply(BackUpEquivalence);
					
				}
				
				ForceValue = new BigDecimal(this.toBase().F.getMagnitude()).divide(newEquivalence, MathContext.DECIMAL128).setScale(15, RoundingMode.HALF_UP).doubleValue();
				
				return new Force(ForceValue, m1, x1, t1, newEquivalence, new vector(ForceValue, this.F.getDirectionX(), this.F.getDirectionY(), this.F.getDirectionZ()), this.nth);
				
			}
			
		}
		
	}//*/
	
	public void setBaseEquivalent(double equivalent){
		
		this.equivalent = new BigDecimal(equivalent);
		
	}
	
	public void setBaseEquivalent(BigDecimal equivalent){
		
		this.equivalent = equivalent;
		
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
				this.nth
			
			);
			
		}
		
	}
	
	public Force arcForce(){
		
		return new Force(1.00/this.F.getMagnitude(), this.m.arcMass(), this.x.arcDistance(), this.t.arcTime(), this.equivalent, new vector(1, this.F.getDirectionX(), this.F.getDirectionY(), this.F.getDirectionZ()), this.nth);
		
	}
	
	public Force doPotencia(long n){
		
		if (n==0){
			
			return new Force(1.00, this.m.arcMass(), this.x.arcDistance(), this.t.arcTime(), this.equivalent, new vector(1, this.F.getDirectionX(), this.F.getDirectionY(), this.F.getDirectionZ()), this.nth);
			
		}else if (n==1){
			
			return this;
			
		}else if (n<0){
			
			return this.doPotencia(-n).arcForce();
			
		}
		
		return this.doProduct(this.doPotencia(n-1));
		
	}
	
	public Force doProduct(Force F){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply forces due to variable "+this.toString()+" has no equivalency in g*m/s^2");
			
		}else if (F.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply forces due to variable "+F.toString()+" has no equivalency in g*m/s^2");
			
		}
		
		mass m1;
		distance x1;
		time t1;
		Force F1;
		String bup;
		boolean pass = true;
		
		if (this.m.getGramEquivalent().compareTo(new BigDecimal(0))!=0 && F.m.getGramEquivalent().compareTo(new BigDecimal(0))!=0){

            m1 = this.m.doProduct(F.m);
			
        }else{
			
			bup = this.m.getUnity();
			
			if (bup.indexOf('^')!=-1){
				
				bup = bup.substring(0, bup.indexOf('^'))+"^"+(this.m.getDegree()+F.m.getDegree());
				
			}else{
				
				bup+= "^"+(this.m.getDegree()+F.m.getDegree());
				
			}
			
			m1 = new mass(0, bup, 0);
			
			pass = false;
			
        }
		
		if (this.x.getMetreEquivalent().compareTo(new BigDecimal(0))!=0 && F.x.getMetreEquivalent().compareTo(new BigDecimal(0))!=0){

            x1 = this.x.doProduct(F.x);
			
        }else{
			
			bup = this.x.getUnity();
			
			if (bup.indexOf('^')!=-1){
				
				bup = bup.substring(0, bup.indexOf('^'))+"^"+(this.x.getDegree()+F.x.getDegree());
				
			}else{
				
				bup+= "^"+(this.x.getDegree()+F.x.getDegree());
				
			}
			
			x1 = new distance(0, bup, 0);
			
			pass = false;
			
        }
		
		if (this.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0 && F.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0){

            t1 = this.t.doProduct(F.t);
			
        }else{
			
			bup = this.t.getUnity();
			
			if (bup.indexOf('^')!=-1){
				
				bup = bup.substring(0, bup.indexOf('^'))+"^"+(this.t.getDegree()+F.t.getDegree());
				
			}else{
				
				bup+= "^"+(this.t.getDegree()+F.t.getDegree());
				
			}
			
			t1 = new time(0, bup, 0);
			
			pass = false;
			
        }
		
		if (pass==true){
			
			return new Force(m1, x1, t1);
			
		}else{
			
			F1 = F.to(this);
			
			return new Force(this.F.getMagnitude()*F1.F.getMagnitude(), m1, x1, t1, this.equivalent.multiply(F1.equivalent), new vector(1, 0), this.nth+F.nth);
			
		}
		
	}
	
	public Force doDivide(Force F){
		
		if (this.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply forces due to variable "+this.toString()+" has no equivalency in g*m/s^2");
			
		}else if (F.equivalent.compareTo(new BigDecimal(0))==0){
			
			throwError("unabled to multiply forces due to variable "+F.toString()+" has no equivalency in g*m/s^2");
			
		}
		
		mass m1;
		distance x1;
		time t1;
		Force F1;
		String bup;
		boolean pass = true;
		
		if (this.m.getGramEquivalent().compareTo(new BigDecimal(0))!=0 && F.m.getGramEquivalent().compareTo(new BigDecimal(0))!=0){

            m1 = this.m.doDivide(F.m);
			
        }else{
			
			bup = this.m.getUnity();
			
			if (bup.indexOf('^')!=-1){
				
				bup = bup.substring(0, bup.indexOf('^'))+"^"+(this.m.getDegree()-F.m.getDegree());
				
			}else{
				
				bup+= "^"+(this.m.getDegree()-F.m.getDegree());
				
			}
			
			m1 = new mass(0, bup, 0);
			
			pass = false;
			
        }
		
		if (this.x.getMetreEquivalent().compareTo(new BigDecimal(0))!=0 && F.x.getMetreEquivalent().compareTo(new BigDecimal(0))!=0){

            x1 = this.x.doDivide(F.x);
			
        }else{
			
			bup = this.x.getUnity();
			
			if (bup.indexOf('^')!=-1){
				
				bup = bup.substring(0, bup.indexOf('^'))+"^"+(this.x.getDegree()-F.x.getDegree());
				
			}else{
				
				bup+= "^"+(this.x.getDegree()-F.x.getDegree());
				
			}
			
			x1 = new distance(0, bup, 0);
			
			pass = false;
			
        }
		
		if (this.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0 && F.t.getSecondEquivalent().compareTo(new BigDecimal(0))!=0){

            t1 = this.t.doDivide(F.t);
			
        }else{
			
			bup = this.t.getUnity();
			
			if (bup.indexOf('^')!=-1){
				
				bup = bup.substring(0, bup.indexOf('^'))+"^"+(this.t.getDegree()-F.t.getDegree());
				
			}else{
				
				bup+= "^"+(this.t.getDegree()-F.t.getDegree());
				
			}
			
			t1 = new time(0, bup, 0);
			
			pass = false;
			
        }
		
		if (pass==true){
			
			return new Force(m1, x1, t1);
			
		}else{
			
			F1 = F.to(this);
			
			return new Force(this.F.getMagnitude()/F1.F.getMagnitude(), m1, x1, t1, this.equivalent.divide(F1.equivalent, MathContext.DECIMAL128), new vector(1, 0), this.nth-F.nth);
			
		}
		
	}
	
	public static Force getNewtonValueOf(double value){
		
		return new Force(mass.getKilogramValueOf(value/2.00), distance.getMetreValueOf(2.00), time.getSecondValueOf(1).doPotencia(2));
		
	}
	
	public static Force getPoundalValueOf(double value){
		
		return new Force(mass.getPoundValueOf(value/2.00), distance.getFeetValueOf(2.00), time.getSecondValueOf(1).doPotencia(2));
		
	}
	
	public static Force getBaseValueOf(double value){
		
		return new Force(mass.getGramValueOf(value/2.00), distance.getMetreValueOf(2.00), time.getSecondValueOf(1).doPotencia(2));
		
	}
	
	public static Force getDyneValueOf(double value){
		
		return new Force(mass.getGramValueOf(value/2.00), distance.getCentimetreValueOf(2.00), time.getSecondValueOf(1).doPotencia(2));
		
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