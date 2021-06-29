package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph<Genes,DefaultWeightedEdge> grafo;
	private Map<String,Genes> idMap;
	private List<Adiacenza> archi;
	private List<GenesAdiacenti> geniAdiacenti;
	
	
	
	public Model() {
		dao = new GenesDao();
	}
	
	
	public void CreaGrafo() {
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		 idMap = new HashMap<>();
		 dao.getAllGenes(idMap);
		 Graphs.addAllVertices(this.grafo, idMap.values());
		 
		 archi = new ArrayList<>();
		 dao.getAllArchi(idMap,archi);
		 
		 for(Adiacenza a :archi) {
			 DefaultWeightedEdge e =this.grafo.getEdge(a.getG1(), a.getG2());
			 if(e ==null) {
				 if(a.getG1().getChromosome()==a.getG2().getChromosome())
				 Graphs.addEdge(this.grafo, a.getG1(), a.getG2(), a.getPeso()*2);
				 else
					 Graphs.addEdge(this.grafo, a.getG1(), a.getG2(), a.getPeso());
			 }
		 }
	}
	public int getNvertici() {
		return this.grafo.vertexSet().size();
	}
	public int getNarchi() {
		return this.grafo.edgeSet().size();
	}
	public Set<Genes> getVertici() {
		return this.grafo.vertexSet();
	}
	public List<GenesAdiacenti> getGeniAdiacenti(Genes ricercato){
		geniAdiacenti = new ArrayList<>();
		for(Genes g: Graphs.neighborListOf(this.grafo, ricercato)) {
			DefaultWeightedEdge e= this.grafo.getEdge(ricercato, g);
			geniAdiacenti.add(new GenesAdiacenti(g,this.grafo.getEdgeWeight(e)));
		}
		Collections.sort(geniAdiacenti);
		return geniAdiacenti;
		
	}


	public String checkGenes(Genes g) {
		String result = null;
		if(idMap.containsKey(g.getGeneId())) {
			this.getGeniAdiacenti(g);
			result= "\nI geni Adicenti a "+g.toString()+"\n";
			for(GenesAdiacenti ga: this.geniAdiacenti) {
				result+=ga.toString()+"\n";
			}
		}
		else {
			result = "\nErrore, Devi selezionare prima il gene";
		}
		return result;
	}


	public Graph<Genes, DefaultWeightedEdge> getGrafo() {
		// TODO Auto-generated method stub
		return this.grafo;
	}
	
}
