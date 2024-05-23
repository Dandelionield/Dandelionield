package Taylor.Arithmetic;

/*
 *
 * @author Dandelion
 * 
 */

import Taylor.Math.Mayth;

public class mod{

	private long m;
	private long b;
	private long a;
	
	public mod(long m){
		
		this.m = m;
		this.b = 0;
		this.a = 0;
		
	}
	
	public mod(long a, long m){
		
		this.m = m;
		this.b = a%m<0 ? a%m + m : a%m;
		this.a = a;
		
	}
	
	public mod(long a, long b, long m){
		
		this.m = m;
		this.b = b;
		this.a = a;
		
	}
	
	public long getModulus(){
		
		return this.m;
		
	}
	
	public long getCongruent(){
		
		return this.a;
		
	}
	
	public void setModulus(long m){
		
		this.m = m;
		this.b = a%m<0 ? a%m + m : a%m;
		
	}
	
	public void setCongruent(long a){
		
		this.b = a%m<0 ? a%m + m : a%m;
		
	}
	
	public String toString(){
		
		return a+" === "+b+" mod "+m;
		
	}
	
	public long getArc(){
		
		Mayth p = new Mayth();
		
		if (p.getGCD(new long[] {this.a, this.m})!=1){return 0;}
		
		long n = 0;
		
		do{
			
			if ((this.a*n - 1)%this.m==0){
				
				return n;
				
			}
			
			n++;
			
		}while(n<=this.m);
		
		return 0;
		
	}

}