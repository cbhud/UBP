package me.cbhud.entiteti;

public class Vozac {
	
	private Zaposleni zaposleni;
	private int satiVoznje;
	
	Vozac(Zaposleni z, int satiVoznje){
		this.zaposleni = z;
		this.satiVoznje = satiVoznje;
	}

	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	public int getSatiVoznje() {
		return satiVoznje;
	}

	public void setSatiVoznje(int satiVoznje) {
		this.satiVoznje = satiVoznje;
	}

	@Override
	public String toString() {
		return "Vozac [zaposleni=" + zaposleni + ", satiVoznje=" + satiVoznje + "]";
	}
	
	
	

}
