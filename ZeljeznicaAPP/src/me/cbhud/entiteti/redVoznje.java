package me.cbhud.entiteti;

import java.time.LocalDateTime;

public class redVoznje {
    private int id;
    private LocalDateTime datumKreiranja;
    private String destinacija;
    private LocalDateTime datumVrijemePolaska;
    private int brMjesta;
    private int brVipMjesta;
    private int zaposleniId;
    private int vozacId;
    private int vozId;

    // Constructor
    public redVoznje(LocalDateTime datumKreiranja, String destinacija, LocalDateTime datumVrijemePolaska,
                     int brMjesta, int brVipMjesta, int zaposleniId, int vozacId, int vozId) {
        this.datumKreiranja = datumKreiranja;
        this.destinacija = destinacija;
        this.datumVrijemePolaska = datumVrijemePolaska;
        this.brMjesta = brMjesta;
        this.brVipMjesta = brVipMjesta;
        this.zaposleniId = zaposleniId;
        this.vozacId = vozacId;
        this.vozId = vozId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(LocalDateTime datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public LocalDateTime getDatumVrijemePolaska() {
		return datumVrijemePolaska;
	}

	public void setDatumVrijemePolaska(LocalDateTime datumVrijemePolaska) {
		this.datumVrijemePolaska = datumVrijemePolaska;
	}

	public int getBrMjesta() {
		return brMjesta;
	}

	public void setBrMjesta(int brMjesta) {
		this.brMjesta = brMjesta;
	}

	public int getBrVipMjesta() {
		return brVipMjesta;
	}

	public void setBrVipMjesta(int brVipMjesta) {
		this.brVipMjesta = brVipMjesta;
	}

	public int getZaposleniId() {
		return zaposleniId;
	}

	public void setZaposleniId(int zaposleniId) {
		this.zaposleniId = zaposleniId;
	}

	public int getVozacId() {
		return vozacId;
	}

	public void setVozacId(int vozacId) {
		this.vozacId = vozacId;
	}

	public int getVozId() {
		return vozId;
	}

	public void setVozId(int vozId) {
		this.vozId = vozId;
	}

    
    
    
}
