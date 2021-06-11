package it.polito.tdp.food.model;

public class Adiacenza {
	
	private String name_p1;
	private String name_p2;
	private double peso;
	
	public Adiacenza(String name_p1, String name_p2, double peso) {
		super();
		this.name_p1 = name_p1;
		this.name_p2 = name_p2;
		this.peso = peso;
	}
	public String getName_p1() {
		return name_p1;
	}
	public void setName_p1(String name_p1) {
		this.name_p1 = name_p1;
	}
	public String getName_p2() {
		return name_p2;
	}
	public void setName_p2(String name_p2) {
		this.name_p2 = name_p2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
	
	

}
