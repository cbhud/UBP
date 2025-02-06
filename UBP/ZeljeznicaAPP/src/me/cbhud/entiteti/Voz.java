package me.cbhud.entiteti;

public class Voz {
	
	int id;
	String naziv_tipa;
	
	public Voz(int id, String naziv_tipa) {
		super();
		this.id = id;
		this.naziv_tipa = naziv_tipa;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv_tipa() {
		return naziv_tipa;
	}
	public void setNaziv_tipa(String naziv_tipa) {
		this.naziv_tipa = naziv_tipa;
	}
	
	

}
