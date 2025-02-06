package me.cbhud.entiteti;

import java.time.LocalDate;

import javax.sound.sampled.TargetDataLine;

public class Zaposleni {
	
	private String ime;
	private String prezime;
	private String JMBG;
	private int radniStaz;
	LocalDate datumZaposljenja;
	
	
	public Zaposleni(String ime, String prezime, String jMBG, int radniStaz, LocalDate datumZaposljenja) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.JMBG = jMBG;
		this.radniStaz = radniStaz;
		this.datumZaposljenja = datumZaposljenja;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getJMBG() {
		return JMBG;
	}


	public void setJMBG(String jMBG) {
		this.JMBG = jMBG;
	}


	public int getRadniStaz() {
		return radniStaz;
	}


	public void setRadniStaz(int radniStaz) {
		this.radniStaz = radniStaz;
	}


	public LocalDate getDatumZaposljenja() {
		return datumZaposljenja;
	}


	public void setDatumZaposljenja(LocalDate datumZaposljenja) {
		this.datumZaposljenja = datumZaposljenja;
	}


	
	@Override
	public String toString() {
		return "Zaposleni [ime=" + ime + ", prezime=" + prezime + ", JMBG=" + JMBG + ", radniStaz=" + radniStaz
				+ ", datumZaposljenja=" + datumZaposljenja + "]";
	}
	
	
	
	
	
	
	
	

}
