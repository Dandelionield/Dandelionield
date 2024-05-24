package System.Cryptosystem;

/*
 *
 * @author Dandelion
 * 
 */

import java.util.HashMap;

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
	
	private long[] error = {0};
	
	private String encrypt = "";
	
	public RSA(long p, long q){
		
		this.p = p;
		this.q = q;
		
		this.n = p*q;
		this.m = (p - 1)*(q - 1);
		this.l = this.getLambda();
		this.d = new mod(this.l, this.m).arc();
		
		
		this.s = 0;
		/*this.bankWord = null;
		this.bankWordOut = null;//*/
		
	}
	
	public RSA(long p, long q, int s){//HashMap<Character, Long> bankWord){
		
		this.p = p;
		this.q = q;
		
		this.n = p*q;
		this.m = (p - 1)*(q - 1);
		this.l = this.getLambda();
		this.d = new mod(this.l, this.m).arc();
		
		this.s = s;
		
		/*this.bankWord = bankWord;
		this.bankWordOut = new HashMap<>();
		
		for (char b : this.bankWord.keySet()){
			
			this.bankWordOut.put(this.bankWord.get(b), b);
			
		}//*/
		
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
	
	public String toString(){
		
		return this.encrypt.equals("") ? "Public Key: ("+this.n+", "+this.l+")" : this.encrypt;
		
	}
	
	public void encrypt(String wd){
		
		Mayth p = new Mayth();
		
		char[] bank = wd.toCharArray();
		this.error = new long[bank.length];
		long bup = 0;
		
		String encrypted = "";
		
		long b = 0;
		
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
			
			b = new mod(a, this.n).getBase() + this.error[i];
			
			encrypted+= ((char) (
			
				(
				
					(int) b) + this.s
					
				)
				
			)+"";
			
		}
		
		return encrypted;
		
	}//*/
	
	private long getLambda(){
		
		Mayth p = new Mayth();
		
		long r = 2;
		
		do{
			
			if (p.getGCD(new long[] {r, this.m})==1){
				
				return r;
				
			}
			
			r++;
			
		}while(r<this.m);
		
		return 0;
		
	}

}