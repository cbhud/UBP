package me.cbhud.entiteti;

import java.time.LocalDate;

public class Karta {
    private int id;
    private String brKarte;
    private LocalDate datumProdaje;
    private String ime;
    private String prezime;
    private String tipMjesta; // "VIP" or "Obicno"
    private int redVoznjeId;

    // Constructor
    public Karta(String brKarte, LocalDate datumProdaje, String ime, String prezime, String tipMjesta, int redVoznjeId) {
        this.brKarte = brKarte;
        this.datumProdaje = datumProdaje;
        this.ime = ime;
        this.prezime = prezime;
        this.tipMjesta = tipMjesta;
        this.redVoznjeId = redVoznjeId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrKarte() {
		return brKarte;
	}

	public void setBrKarte(String brKarte) {
		this.brKarte = brKarte;
	}

	public LocalDate getDatumProdaje() {
		return datumProdaje;
	}

	public void setDatumProdaje(LocalDate datumProdaje) {
		this.datumProdaje = datumProdaje;
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

	public String getTipMjesta() {
		return tipMjesta;
	}

	public void setTipMjesta(String tipMjesta) {
		this.tipMjesta = tipMjesta;
	}

	public int getRedVoznjeId() {
		return redVoznjeId;
	}

	public void setRedVoznjeId(int redVoznjeId) {
		this.redVoznjeId = redVoznjeId;
	}

}
