package it.polito.tdp.genes.model;

public class GeniStudiati implements Comparable<GeniStudiati> {
	private Genes g;
	private int nIng;
	public GeniStudiati(Genes g, int nIng) {
		super();
		this.g = g;
		this.nIng = nIng;
	}
	public Genes getG() {
		return g;
	}
	public void setG(Genes g) {
		this.g = g;
	}
	public int getnIng() {
		return nIng;
	}
	public void setnIng(int nIng) {
		this.nIng = nIng;
	}
	
	public void increment() {
		nIng++;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((g == null) ? 0 : g.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeniStudiati other = (GeniStudiati) obj;
		if (g == null) {
			if (other.g != null)
				return false;
		} else if (!g.equals(other.g))
			return false;
		return true;
	}
	@Override
	public int compareTo(GeniStudiati o) {
		// TODO Auto-generated method stub
		return this.nIng- o.getnIng();
	}
	

}
