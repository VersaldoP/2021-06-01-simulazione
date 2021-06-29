package it.polito.tdp.genes.model;

public class GenesAdiacenti implements Comparable<GenesAdiacenti> {
 private Genes g;
 private double peso;
public GenesAdiacenti(Genes g, double peso) {
	super();
	this.g = g;
	this.peso = peso;
}
public Genes getG() {
	return g;
}
public void setG(Genes g) {
	this.g = g;
}
public double getPeso() {
	return peso;
}
public void setPeso(double peso) {
	this.peso = peso;
}
@Override
public String toString() {
	return g + " -> "+peso;
}
@Override
public int compareTo(GenesAdiacenti o) {
	// TODO Auto-generated method stub
	if(this.getPeso()==o.getPeso())
		return 0;
	if(this.getPeso()>o.getPeso())
		return -1;
	else
		return 1;
	
}
 
}
