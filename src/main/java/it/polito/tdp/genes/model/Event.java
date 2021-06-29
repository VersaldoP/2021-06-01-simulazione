package it.polito.tdp.genes.model;

public class Event implements Comparable<Event> {
	
//	public enum EventType{
//		
//		
//	}
	
	private int mese;
	private Genes g;
	private int ingId;
	
	
	
	public Event(int mese, Genes gg, int ingId) {
		super();
		this.mese = mese;
		this.g = gg;
		this.ingId = ingId;
	}



	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.getMese()-o.getMese();
	}



	public int getMese() {
		return mese;
	}



	public void setMese(int mese) {
		this.mese = mese;
	}







	public int getIngId() {
		return ingId;
	}



	public void setIngId(int ingId) {
		this.ingId = ingId;
	}



	public Genes getG() {
		return g;
	}



	public void setG(Genes g) {
		this.g = g;
	}
	
	

}
