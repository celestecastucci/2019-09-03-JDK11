package it.polito.tdp.food.model;

public class PorzionePeso {
	
	private String name_p;
	private double peso;
	
	public PorzionePeso(String name_p, double peso) {
		super();
		this.name_p = name_p;
		this.peso = peso;
	}

	public String getName_p() {
		return name_p;
	}

	public void setName_p(String name_p) {
		this.name_p = name_p;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return name_p +" || "+ peso;
	}
	
	
	

}
