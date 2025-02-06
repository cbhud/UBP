package me.cbhud.entiteti;

import java.time.LocalDate;

public class medicinskiPregled {

	int id;
	String nalaz;
	LocalDate datumPregleda;
	Vozac vozac;
	
	public medicinskiPregled(int id, String nalaz, LocalDate datumPregleda, Vozac vozac) {
		super();
		this.id = id;
		this.nalaz = nalaz;
		this.datumPregleda = datumPregleda;
		this.vozac = vozac;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNalaz() {
		return nalaz;
	}
	public void setNalaz(String nalaz) {
		this.nalaz = nalaz;
	}
	public LocalDate getDatumPregleda() {
		return datumPregleda;
	}
	public void setDatumPregleda(LocalDate datumPregleda) {
		this.datumPregleda = datumPregleda;
	}
	public Vozac getVozac() {
		return vozac;
	}
	public void setVozac(Vozac vozac) {
		this.vozac = vozac;
	}
	
	
	
	

	
}
