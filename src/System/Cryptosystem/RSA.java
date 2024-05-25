package System.Cryptosystem;

/*
 *
 * @author Dandelion
 * 
 */

import Taylor.Math.Mayth;
import Taylor.Arithmetic.mod;

public class RSA{

	private long p;
	private long q;
	
	private long n;
	private long m;
	private long l;
	private long d;
	
	private int s;
	
	private long[] error = {};
	private long[] encryptValues = {};
	
	private String encrypt = "";
	
	public RSA(long p, long q){
		
		this.p = p;
		this.q = q;
		
		this.n = p*q;
		this.m = (p - 1)*(q - 1);
		this.l = this.getLambda();
		this.d = new mod(this.l, this.m).arc();
		
		
		this.s = 0;
		
	}
	
	public RSA(long p, long q, int s){
		
		this.p = p;
		this.q = q;
		
		this.n = p*q;
		this.m = (p - 1)*(q - 1);
		this.l = this.getLambda();
		this.d = new mod(this.l, this.m).arc();
		
		this.s = s;
		
	}
	
	public String get(){
		
		return this.encrypt;
		
	}
	
	public long[] getPublicKey(){
		
		return new long[] {this.n, this.l};
		
	}
	
	public long[] getPrivateKey(){
		
		return new long[] {this.n, this.d};
		
	}
	
	public long[] getEncryptedValues(){
		
		return this.encryptValues;
		
	}
	
	public long[] getError(){
		
		return this.error;
		
	}
	
	public String toString(){
		
		return this.encrypt.equals("") ? "Public Key: ("+this.n+", "+this.l+")" : this.encrypt;
		
	}
	
	public void encrypt(String wd){
		
		Mayth p = new Mayth();
		
		char[] bank = wd.toCharArray();
		
		this.error = new long[bank.length];
		this.encryptValues = new long[bank.length];
		
		String encrypted = "";
		
		long b = 0;
		long bup = 0;
		
		for (int i=0; i<bank.length; i++){
			
			error[i] = 0;
			
			long value = ((int) bank[i]) - this.s;
			
			bup = value;
			
			while (bup>=0 ? bup>=this.n : bup<=this.n){
				
				error[i] = error[i] + (bup>=0 ? (bup>=this.n ? this.n : 0) : (bup<=this.n ? -this.n : 0));
				
				bup+= bup>=0 ? -this.n : this.n;
				
			}
			
			long a = (long) p.Potencia(value, this.l);
			
			b = new mod(a, this.n).getBase();
			
			this.encryptValues[i] = b;
			
			encrypted+= ((char) (
			
				(
				
					(int) b) + this.s
					
				)
				
			)+"";
			
		}
		
		this.encrypt = encrypted;
		
	}
	
	public void encrypt(long[] bank){
		
		Mayth p = new Mayth();
		
		this.error = new long[bank.length];
		this.encryptValues = new long[bank.length];
		
		String encrypted = "";
		
		long b = 0;
		long bup = 0;
		
		for (int i=0; i<bank.length; i++){
			
			error[i] = 0;
			
			long value = ((int) bank[i]);
			
			bup = value;
			
			while (bup>=0 ? bup>=this.n : bup<=this.n){
				
				error[i] = error[i] + (bup>=0 ? (bup>=this.n ? this.n : 0) : (bup<=this.n ? -this.n : 0));
				
				bup+= bup>=0 ? -this.n : this.n;
				
			}
			
			long a = (long) p.Potencia(value, this.l);
			
			b = new mod(a, this.n).getBase();
			
			this.encryptValues[i] = b;
			
			encrypted+= ((char) (
			
				(
				
					(int) b) + this.s
					
				)
				
			)+"";
			
		}
		
		this.encrypt = encrypted;
		
	}
	
	public String unencrypt(String wd){
		
		Mayth p = new Mayth();
		
		char[] bank = wd.toCharArray();
		
		String encrypted = "";
		
		long b = 0;
		
		for (int i=0; i<bank.length; i++){
			
			long value = ((int) bank[i]) - this.s;
			
			long a = (long) p.Potencia(value, this.d);
			
			try{
				
				b = this.error[i];
				
			}catch(Exception e){b = 0;}
			
			b = new mod(a, this.n).getBase() + b;
			
			encrypted+= ((char) (
			
				(
				
					(int) b) + this.s
					
				)
				
			)+"";
			
		}
		
		this.error = new long[] {};
		
		return encrypted;
		
	}
	
	public String unencrypt(long[] bank){
		
		Mayth p = new Mayth();
		
		String encrypted = "";
		
		long b = 0;
		
		for (int i=0; i<bank.length; i++){
			
			long value = ((int) bank[i]);
			
			long a = (long) p.Potencia(value, this.d);
			
			try{
				
				b = this.error[i];
				
			}catch(Exception e){b = 0;}
			
			b = new mod(a, this.n).getBase() + b;
			
			encrypted+= ((char) (
			
				(
				
					(int) b) + this.s
					
				)
				
			)+""; 
			
		}
		
		this.error = new long[] {};
		
		return encrypted;
		
	}
	
	private long getLambda(){
		
		Mayth p = new Mayth();
		
		long r = 2;
		
		do{
			
			if (p.getGCD(new long[] {r, this.m})==1){
				
				return r;
				
			}
			
			r++;
			
		}while(r<this.m);
		
		return this.m-1;
		
	}

}