package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private FoodDao dao;
	private SimpleWeightedGraph<String, DefaultWeightedEdge> grafo;
	private List<String> percorsoMigliore;
	private double pesoMigliore;


	public Model() {
		dao = new FoodDao();
		
	}

	
	public void creaGrafo(int calorie) {
		grafo = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		//vertici
		Graphs.addAllVertices(grafo, dao.getVertici(calorie));
		
		//archi
		for(Adiacenza a: dao.getAdiacenze()) {
			if(this.grafo.containsVertex(a.getName_p1()) && this.grafo.containsVertex(a.getName_p2())) {
				DefaultWeightedEdge e= this.grafo.getEdge(a.getName_p1(), a.getName_p2());
				if(e==null) {
					Graphs.addEdgeWithVertices(grafo, a.getName_p1(), a.getName_p2(), a.getPeso());
				}
			}
		}
	}
	
	// punto d: tutte gli adiacenti del vertice selezionato con il peso --> creo classe PorzionePeso
	
	public List<PorzionePeso> getPorzioniCorrelate(String tendina){
		List<PorzionePeso> result = new LinkedList<PorzionePeso>();
		
		for(String p: Graphs.neighborListOf(grafo, tendina)) {
			if(!p.equals(tendina)) {
				
				//mi prendo l'arco tra p e tendina ed estraggo il peso
				DefaultWeightedEdge e= this.grafo.getEdge(tendina,p );
				double pesoArco= this.grafo.getEdgeWeight(e);
				PorzionePeso porz = new PorzionePeso(p, pesoArco );
				result.add(porz);
			}
		}
		return result;
		
	}
	
	
	
	
	// RICORSIONE
	public List<String> trovaPercorso(String partenza, int N){
		this.percorsoMigliore=new ArrayList<String>();
		this.pesoMigliore=0.0;
		List<String> parziale = new ArrayList<String>();
		parziale.add(partenza);
		cerca(N,parziale);
		return this.percorsoMigliore;
	}
	
		
		
	private void cerca(int N, List<String> parziale) {
		
		String ultimo = parziale.get(parziale.size()-1);
		
		// CASO TERMINALE 
				 //se il percorso è di lunghezza pari a N
				 //&& il peso è massimo
		double peso=0.0;
		
		if(parziale.size()==N) {
			peso= this.calcolaPeso(parziale);
			if(peso > this.pesoMigliore) {
					this.pesoMigliore= peso;
					this.percorsoMigliore = new ArrayList<>(parziale);
					  }
			return;
		}
		//implemento
				for(String vicino: Graphs.neighborListOf(grafo, ultimo)){
					if(!parziale.contains(vicino)) {
						
						//calcolo il peso recuperando l'arco
						parziale.add(vicino);
						cerca(N,parziale);
						parziale.remove(parziale.size()-1);
					}
					
				}
	

	}


	private double calcolaPeso(List<String> parziale) {
		double peso=0.0;
		for(int i=1 ; i<parziale.size(); i++) {
			DefaultWeightedEdge e= this.grafo.getEdge(parziale.get(i-1), parziale.get(i));
			peso= peso+ this.grafo.getEdgeWeight(e);
			
		}
		return  peso;
	}
	
	


	public double getPesoMigliore() {
		return pesoMigliore;
	}


	public void setPesoMigliore(double pesoMigliore) {
		this.pesoMigliore = pesoMigliore;
	}


	//vertici tendina
	public Set<String> getVerticiTendina(){
		return this.grafo.vertexSet();
	}
	
	public int getNumVertici() {
		if(grafo!=null) {
			return this.grafo.vertexSet().size();
		}
		return 0;
	}
	
	public int getNumArchi() {
		if(grafo!=null) {
			return this.grafo.edgeSet().size();
		}
		return 0;
	}

	
	
	
	
}
