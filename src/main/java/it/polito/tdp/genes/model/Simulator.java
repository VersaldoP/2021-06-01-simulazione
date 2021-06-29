package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;





public class Simulator {
	Model model;
	public Simulator(Model m) {
		this.model = m;
	}
	
	//Modello del mondo 
	
	private int nIng;
	private Genes partenza;
	private int mesi =36;
	private Map<Integer,Genes> mapIng;
	
	
	//Tipi di Evento -> coda Prioritaria
	
	private PriorityQueue<Event> queue;
	//parametri della Simulazione
	
	public void setParameter(int n,Genes g) {
		this.nIng=n;
		this.partenza=g;
		this.mapIng = new HashMap<>();
		
		for(int i=1;i<=this.mesi;i++) {
			mapIng.put(i, partenza);
		}
		
	}
	
	
	
	//valori di output
	private Map<String,GeniStudiati> soluzione;
	
	//imposto lo Stato iniziale
	public void init() {
		this.queue= new PriorityQueue<>();
		
		//inserisco gli eventi iniziali (Ingresso)
		
		for(int i:mapIng.keySet()) {
			queue.add(new Event(1,this.partenza,i));
		}
		
		//Processo gli eventi
		
    	while(!this.queue.isEmpty()) {
    		Event e = this.queue.poll();
    		ProcessEvent(e);
    	}
		
		
	}
	
	// Processo gli eventi

	private void ProcessEvent(Event e) {
		if(e.getMese()<=36) {
		double prob = Math.random();
		if (prob<=0.3) {
			//rimango sullo stesso gene
			this.queue.add(new Event(e.getMese()+1,e.getG(),e.getIngId()));
			
		}else {
			//Cambio Gene 
			int pesoT=0;
			List<Genes> vicini= Graphs.neighborListOf(this.model.getGrafo(), e.getG());
			for (Genes g :vicini) {
				DefaultWeightedEdge edge = this.model.getGrafo().getEdge(e.getG(), g);
				pesoT+= this.model.getGrafo().getEdgeWeight(edge);
			}
			double probg= Math.random()*pesoT;
			int peso=0;
			for (Genes g :vicini) {
				DefaultWeightedEdge edge = this.model.getGrafo().getEdge(e.getG(), g);
				peso+= this.model.getGrafo().getEdgeWeight(edge);
				if(peso<=probg) {
					this.queue.add(new Event(e.getMese()+1,g,e.getIngId()));
					mapIng.put(e.getIngId(), g);
					break;
				}
			}
		}
		}
		
	}
	public String Soluzione() {
		for(Integer i:mapIng.keySet() ) {
			if(soluzione.containsKey(mapIng.get(i).getGeneId())) {
				soluzione.get(mapIng.get(i).getGeneId()).increment();;
			}
			else {
				soluzione.put(mapIng.get(i).getGeneId(), new GeniStudiati(mapIng.get(i),1));
			}
		}
		List<GeniStudiati> soluzioneS= new ArrayList<>();
		for(GeniStudiati g:soluzione.values()) {
			soluzioneS.add(g);
			
		}
		Collections.sort(soluzioneS);
		String result = null;
		for(GeniStudiati g :soluzioneS) {
			result+= g+"\n";
		}
		return result;
		
	}

	

	
	

}
