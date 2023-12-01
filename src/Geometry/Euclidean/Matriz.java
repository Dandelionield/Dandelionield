package Geometry.Euclidean;

/*
 *
 * @author Dandelion
 * 
 */

import java.util.ArrayList;
import Taylor.Math.Mayth;

public class Matriz{
	
	private vector[] m;
	public final int length;
	private boolean State = false;
	private final boolean Normal;
	
	public Matriz(vector[] m){
		
		length = m.length;
		this.m = m;
		Normal = getRegularState(this.m);
		
	}
	
	public Matriz(ArrayList<vector> mz){
		
		m = new vector[mz.size()];
		length = m.length;
		int i = 0;
		
		for (vector p : mz){
			
			m[i] = p;i++;
			
		}
		
		Normal = getRegularState(this.m);
		
	}
	
	public Matriz(double[][] mz){
		
		m = new vector[mz.length];
		length = m.length;
		int i = 0;
		
		for (double[] p : mz){
			
			m[i] = new vector(p);i++;
			
		}
		
		Normal = getRegularState(this.m);
		
	}
	
	private Matriz(Matriz mz, int limite){
		
		vector[] v = new vector[mz.length];
		
		for (int i=0; i<v.length; i++){
			
			v[i] = mz.m[i].doRedondear(limite);
			
		}
		
		m = v;
		length = mz.length;
		Normal = mz.Normal;
		
	}
	
	public Matriz add(Matriz mz){
		
		if (State){
			
			return null;
			
		}
		
		ArrayList<vector> v = new ArrayList<>();
		
		vector length = new vector(this.length, mz.length);
		
		int f = 0;
		
		for (int i=0; i<length.getSmallest(); i++){
			
			v.add(this.m[i].add(mz.m[i]));
			
			f = i;
			
		}
		
		if (this.length==mz.length){
			
			return new Matriz(v);
			
		}
		
		for (int i=f+1; i<length.getLargest(); i++){
			
			try{
				
				v.add(this.m[i]);
				
			}catch(Exception e){
				
				v.add(mz.m[i]);
				
			}
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz stack(Matriz mz){
		
		if (State){
			
			return null;
			
		}
		
		ArrayList<vector> v = new ArrayList<>();
		
		for (vector p : mz.m){
			
			v.add(p);
			
		}
		
		for (vector p : this.m){
			
			v.add(p);
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz addRow(vector vz){
		
		if (State){
			
			return null;
			
		}
		
		ArrayList<vector> v = new ArrayList<>();
		
		for (vector p : this.m){
			
			v.add(p);
			
		}
		
		v.add(vz);
		
		return new Matriz(v);
		
	}
	
	public Matriz addColumn(vector vz){
		
		if (State){
			
			return null;
			
		}
		
		return this.doTransposedMatriz().addRow(vz).doTransposedMatriz();
		
	}
	
	public Matriz setRow(int Row, vector newRow){
		
		if (State){
			
			return null;
			
		}
		
		ArrayList<vector> v = new ArrayList<>();
		
		for (vector p : this.m){
			
			v.add(p);
			
		}
		
		try{
			
			v.remove(Row);
			v.add(Row, newRow);
			
		}catch(Exception e){
			
			return null;
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz setColumn(int Column, vector newColumn){
		
		try{
			
			return this.doTransposedMatriz().setRow(Column, newColumn).doTransposedMatriz();
			
		}catch(Exception e){
			
			return null;
			
		}
		
	}
	
	public double[][] getMatriz(){
		
		double[][] mz = new double[m.length][m[0].length];
		
		for (int f=0; f<m.length; f++){
		
			for (int c=0; c<m[f].length; c++){
				
				mz[f][c] = this.get(f,c);
				
			}
			
		}
		
		return mz;
		
	}
	
	public vector[] get(){
		
		return m;
		
	}
	
	public double get(int f, int c){
		
		return m[f].get(c);
		
	}
	
	public vector getRow(int f){
		
		return m[f];
		
	}
	
	public vector getColumn(int c){
		
		double[] v = new double[m.length];
		
		for (int f=0; f<m.length; f++){
			
			try{
				
				v[f] = m[f].get(c);
				
			}catch(Exception e){
				
				v[f] = 0;
				
			}
			
		}
		
		return new vector(v);
		
	}
	
	public double getLargest(){
		
		final ArrayList<Double> v = new ArrayList<>();
		
		for (vector p : this.m){
			
			v.add(p.getLargest());
			
		}
		
		return new vector(v).getLargest();
		
	}
	
	public double getSmallest(){
		
		final ArrayList<Double> v = new ArrayList<>();
		
		for (vector p : this.m){
			
			v.add(p.getSmallest());
			
		}
		
		return new vector(v).getSmallest();
		
	}
	
	public Matriz getPairs(){
		
		if (State){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		vector bup;
		
		for (vector p : this.m){
			
			bup = p.getPairs();
			
			if (bup!=null){
				
				v.add(bup);
				
			}
			
		}
		
		if (v.size()==0){
			
			return null;
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz getOdds(){
		
		if (State){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		vector bup;
		
		for (vector p : this.m){
			
			bup = p.getOdds();
			
			if (bup!=null){
				
				v.add(bup);
				
			}
			
		}
		
		if (v.size()==0){
			
			return null;
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz getPrimes(){
		
		if (State){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		vector bup;
		
		for (vector p : this.m){
			
			bup = p.getPrimes();
			
			if (bup!=null){
				
				v.add(bup);
				
			}
			
		}
		
		if (v.size()==0){
			
			return null;
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz getNevatives(){
		
		if (State){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		vector bup;
		
		for (vector p : this.m){
			
			bup = p.getNevatives();
			
			if (bup!=null){
				
				v.add(bup);
				
			}
			
		}
		
		if (v.size()==0){
			
			return null;
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz getDividers(double n){
		
		if (n==0 || State==true){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		vector bup;
		
		for (vector p : this.m){
			
			bup = p.getDividers(n);
			
			if (bup!=null){
				
				v.add(bup);
				
			}
			
		}
		
		if (v.size()==0){
			
			return null;
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz throwDividers(double n){
		
		if (n==0 || State==true){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		vector bup;
		
		for (vector p : this.m){
			
			v.add(p.throwDividers(n));
			
		}
		
		if (v.size()==0){
			
			return this;
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz throwNevatives(){
		
		if (State){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		vector bup;
		
		for (vector p : this.m){
			
			v.add(p.throwNevatives());
			
		}
		
		if (v.size()==0){
			
			return this;
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz throwPrimes(){
		
		if (State){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		vector bup;
		
		for (vector p : this.m){
			
			v.add(p.throwPrimes());
			
		}
		
		if (v.size()==0){
			
			return this;
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz throwRow(int indice){
		
		try{
			
			vector bup = this.m[indice];
			
			if (this.length-1==0 || State==true){
			
				return null;
				
			}
			
		}catch(Exception e){
			
			return this;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		
		int c = -1;
		
		for (vector p : this.m){
			
			c++;
			
			if (c==indice){
				
				continue;
				
			}
			
			v.add(p);
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz throwColumn(int indice){
		
		try{
			
			return this.doTransposedMatriz().throwRow(indice).doTransposedMatriz();
			
		}catch(Exception e){
			
			return null;
			
		}
		
	}
	
	public Matriz throwComponent(int Row, int Column){
		
		double c = 0;
		
		try{
			
			c = this.m[Row].get(Column);
			
			if (this.m[Row].length-1==0 || State==true){
			
				return null;
				
			}
			
		}catch(Exception e){
			
			return this;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		
		c = -1;
		
		for (vector p : this.m){
			
			c++;
			
			if (c==Row){
				
				v.add(p.throwComponent(Column));
				
			}else{
				
				v.add(p);
				
			}
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz throwComponent(double n){
		
		if (State){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		
		for (vector p : this.m){
			
			v.add(p.throwComponent(n));
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz throwZeros(){
		
		if (State){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		
		for (vector p : this.m){
			
			v.add(p.throwZeros());
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz replaceComponent(double components, double replacement){
		
		if (State){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		
		for (vector p : this.m){
			
			v.add(p.replaceComponent(components, replacement));
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz replaceAllComponentsBut(double replacement, double exception){
		
		if (State){
			
			return null;
			
		}
		
		final ArrayList<vector> v = new ArrayList<>();
		
		for (vector p : this.m){
			
			v.add(p.replaceAllComponentsBut(replacement, exception));
			
		}
		
		return new Matriz(v);
		
	}
	
	public String toString(){
		
		String bup = "[", ln = "";
		Matriz mz = new Matriz(this.m);
		mz.State = true;
		int i = 0;
		
		if (State==false){
			
			for (vector p : m){
				
				if (p!=m[0]){bup+=",\n ";}
				
				bup+= p+"";
				
			}
			
		}else{
			
			mz.recoverMatriz();
			
			for (vector p: mz.m){
				
				if (p!=mz.m[0]){bup+=",\n ";}
				
				bup+= p+" | "+this.get(i, this.m[i].length-1);i++;
				
			}
			
		}
		
		return bup+"]";
		
	}
	
	public boolean isMatriz(){
		
		return !State;
		
	}
	
	public boolean isEcuationSystem(){
		
		return State;
		
	}
	
	public boolean isRegular(){
		
		return Normal;
		
	}
	
	public boolean isNotRegular(){
		
		return !Normal;
		
	}
	
	public boolean isSquare(){
		
		if (Normal==false || this.length!=m[0].length){
			
			return false;
			
		}else{
			
			return true;
			
		}
		
	}
	
	public void setEcuationSystemEquals(vector v){
		
		if (State==false){
			
			State = true;
			
			this.m = this.addColumn(v).m;
			
		}
		
	}
	
	public void recoverMatriz(){
		
		if (State==true){
			
			State = false;
			
			this.m = this.getCofactor(-1, m[0].length-1).m;
			
		}
		
	}
	
	public vector doSystem(){
		
		if(State==false || (m.length+1!=m[0].length)){
			
			return null;
			
		}
		
		double Delta = this.getCofactor(-1,m[0].length-1).getDetermine();
		double[] v = new double[m.length];
		vector[] mz = this.getCofactor(-1,m[0].length-1).doTransposedMatriz().m;
		vector bup = this.getColumn(m[0].length-1);
		
		for (int i=0; i<v.length; i++){
			
			mz[i] = bup;
			
			v[i] = new Matriz(mz).doTransposedMatriz().getDetermine()/Delta;
			
			mz = this.getCofactor(-1,m[0].length-1).doTransposedMatriz().m;
			
		}
		
		return new vector(v);
		
	}
	
	public vector doGauss(){
		
		if(State==false || (m.length+1!=m[0].length) || this.Normal==false){
			
			return null;
			
		}
	   	
		double[] v = new double[m.length];
		
		double[][] mz = doGauss(this.getMatriz());
		
		for (int f=0; f<mz.length; f++){
		
			v[f] = mz[f][mz[f].length-1];
			
		}
	
		return new vector(v);
		
	}
	
	public Matriz arcGauss(){
		
		if(State==true || (m.length!=m[0].length)){
			
			return null;
			
		}
	   	
		double[][] v = new double[this.m.length][this.m.length];
		double[][] m = new double[this.m.length][this.m.length*2];
		
		for (int f=0; f<this.m.length; f++){
			
			for (int c=0; c<this.m.length; c++){
				
				m[f][c] = this.get(f,c);
				
				if (f==c){
					
					m[f][c+this.m.length] = 1;
					
				}else{
					
					m[f][c+this.m.length] = 0;
					
				}
				
			}
			
		}
		
		m = doGauss(m);
		
		for (int f=0; f<m.length; f++){
			
			for (int c=0; c<(m[f].length/2); c++){
			
				v[f][c] = m[f][c+(m[f].length/2)];
				
			}
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz arcMatriz(){
		
		if(State==true || (m.length!=m[0].length)){
			
			return null;
			
		}
		
		if (m[0].length>2){
			
			return this.doTransposedMatriz().doAttachedMatriz().doScalar(1/this.getDetermine());
			
		}else{
			
			return this.doTransposedMatriz().doAttachedMatriz().doScalar(1/this.getDetermine()).throwColumn(2);
			
		}
		
	}
	
	public Matriz doTransposedMatriz(){
		
		if (State){
			
			return null;
			
		}
		
		ArrayList<Double> vz = new ArrayList<>();
		
		for (vector p : this.m){
			
			vz.add( (double) p.length);
			
		}
		
		ArrayList<vector> v = new ArrayList<>();
		
		for (int i=0; i<new vector(vz).getLargest(); i++){
			
			v.add(this.getColumn(i));
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz doAttachedMatriz(){
		
		if(State==true || (m.length!=m[0].length)){
			
			return null;
			
		}
		
		double[][] mz = new double[m.length][m[0].length];
		int s = 0;
		
		for (int f=0; f<m.length; f++){
			
			s = f;
			
			for (int c=0; c<m[f].length; c++){
				
				mz[f][c] = Math.pow(-1,s) * this.getCofactor(f,c).getDetermine();s++;
				
			}
			
		}
		
		return new Matriz(mz);
		
	}
	
	public Matriz doSuma(Matriz mz){
		
		if(State==true || mz.State==true){
			
			return null;
			
		}
		
		Matriz[] ab = Equalate(this, mz);
		
		ArrayList<vector> v = new ArrayList<>();
		
		for (int i=0; i<ab[0].m.length; i++){
			
			v.add(ab[0].m[i].doSuma(ab[1].m[i]));
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz doResta(Matriz mz){
		
		if(State==true || mz.State==true){
			
			return null;
			
		}
		
		Matriz[] ab = Equalate(this, mz);
		
		ArrayList<vector> v = new ArrayList<>();
		
		for (int i=0; i<ab[0].m.length; i++){
			
			v.add(ab[0].m[i].doResta(ab[1].m[i]));
			
		}
		
		return new Matriz(v);
		
	}
	
	public Matriz doProduct(Matriz mz){
		
		int length1 = this.doTransposedMatriz().doTransposedMatriz().m[0].length;
		int length2 = mz.length;
		
		if(length1!=length2 || State==true || this.Normal==false || mz.Normal==false){
			
			return null;
			
		}
		
		double[][] m = new double[this.length][mz.m[0].length];
		
		for (int f=0; f<m.length; f++){
			
			for (int c=0; c<m[f].length; c++){
				
				m[f][c] = (this.getRow(f).doHadamard(mz.getColumn(c))).doSumatory();
				
			}
			
		}
		
		return new Matriz(m);
		
	}
	
	public Matriz doPotencia(int n){
		
		if (State || this.Normal==false || this.isSquare()==false){
			
			return null;
			
		}
		
		if (n==1){
			
			return this;
			
		}else if (n==0){
			
			return Matriz.getIdentityMatriz(this.length);
			
		}else if (n<0){
			
			return this.doPotencia(-n).arcMatriz();
			
		}else{
			
			return this.doProduct(this.doPotencia(n-1));
			
		}
		
	}
	
	public Matriz doEuler(){
		
		if (State || this.Normal==false || this.isSquare()==false){
			
			return null;
			
		}
		
		Matriz mz = Matriz.getIdentityMatriz(this.length);
		
		for (int n=1; n<=17; n++){
			
			mz = mz.doSuma(this.doPotencia(n).doScalar(1.00/Mayth.Factorial(n)));
			
		}
		
		return mz;
		
	}
	
	public Matriz doScalar(double s){
		
		if (State){
			
			return null;
			
		}
		
		ArrayList<vector> v = new ArrayList<>();
		
		for (vector p : this.m){
			
			v.add(p.doScalar(s));
			
		}
		
		return new Matriz(v);
		
	}
	
	public static Matriz getIdentityMatriz(int RowsColumns){
		
		if (RowsColumns<2){
			
			return null;
			
		}
		
		double[][] mz = new double[RowsColumns][RowsColumns];
		
		for (int i=0; i<mz.length; i++){
			
			mz[i][i] = 1;
			
		}
		
		return new Matriz(mz);
		
	}
	
	public double getDetermine(){
		
		if (State==true || (m.length!=m[0].length)){
			
			return Double.NaN;
			
		}

		double r=0;

		if (m.length>1){
			
			for (int c=0; c<m.length; c++){
				
				r+= (int) Mayth.Potencia(-1, c) * this.get(0,c) * this.getCofactor(0,c).getDetermine();
				
			}
			
		}else{
			
			return this.get(0,0);
			
		}
		
		return r;
		
	}
	
	public double doSumatory(){
		
		if (State){
			
			return Double.NaN;
			
		}
		
		double Sumatory = 0;
		
		for (vector p : this.m){
			
			Sumatory+= p.doSumatory();
			
		}
		
		return Sumatory;
		
	}
	
	public double getAvarage(){
		
		if (State){
			
			return Double.NaN;
			
		}
		
		double length = 0;
		
		for (vector p : this.m){
			
			length+= p.length;
			
		}
		
		return this.doSumatory()/length;
		
	}
	
	public Matriz getCofactor(int Fskip, int Cskip){
		
		int lx = m.length-1, ly = m[0].length-1;
		
		if (Fskip==-1){lx++;}
		if (Cskip==-1){ly++;}
		
		double[][] v = new double[lx][ly];
		int columna = 0, fila = 0;
		
		for (int f=0; f<m.length; f++){
			
			if (f==Fskip){continue;}
			
			for (int c=0; c<m.length; c++){
				
				if (c==Cskip){continue;}
				
				v[fila][columna] = this.get(f,c);
				
				columna++;
				
			}
			
			fila++;
			columna=0;
		}
	
		return new Matriz(v);
		
	}
	
	public Matriz doRedondear(int limite){
		
		return new Matriz(this, limite);
		
	}
	
	private double[][] doGauss(double[][] m){
		
		double n=0;
		int z=0;
		
		if (m[0][0]==0){
			
			do{
			
				for (int c=0; c<m[0].length; c++){
					
					n = m[0][c];
					m[0][c] = m[z][c];
					m[z][c] = n;
					
				}
				
				z++;
				
				if (z>m.length){break;}
				
			}while(m[0][0]==0);
			
		}
		
		for (int i=0; i<m.length; i++){
			
			n = m[i][i];
			
			for (int c=0; c<m[i].length; c++){
				
				m[i][c]/= n;
				
			}
			
			for (int f=0; f<m.length; f++){
				
				if (f==i){continue;}
				
				for (int c=0; c<m[f].length; c++){
					
					if (c==0){n=-1*m[f][i];}
					
					m[f][c] = n*m[i][c]+m[f][c];
					
				}
				
			}
			
		}
		
		return m;
		
	}
	
	private Matriz[] Equalate(Matriz a, Matriz b){
		
		if (a.length>b.length){
			
			do{
				
				b = b.addRow(vector.getFullComponents(0, 3));
				
			}while(b.length!=a.length);
			
		}else if (a.length<b.length){
			
			do{
				
				a = a.addRow(vector.getFullComponents(0, 3));
				
			}while(a.length!=b.length);
			
		}
		
		return new Matriz[] {a, b};
		
	}
	
	private boolean getRegularState(vector[] v){
		
		if (v.length==1){
			
			return true;
			
		}
		
		for (int i=1; i<v.length; i++){
			
			if (v[i].length!=v[i-1].length){
				
				return false;
				
			}
			
		}
		
		return true;
		
	}

}