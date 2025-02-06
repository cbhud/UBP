package me.cbhud.entiteti;

public class Cjenovnik {
    private int id;
    private double cijena;
    private double vipCijena;
    private int redVoznjeId;

    // Constructor
    public Cjenovnik(double cijena, double vipCijena, int redVoznjeId) {
        this.cijena = cijena;
        this.vipCijena = vipCijena;
        this.redVoznjeId = redVoznjeId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

	public double getVipCijena() {
		return vipCijena;
	}

	public void setVipCijena(double vipCijena) {
		this.vipCijena = vipCijena;
	}

	public int getRedVoznjeId() {
		return redVoznjeId;
	}

	public void setRedVoznjeId(int redVoznjeId) {
		this.redVoznjeId = redVoznjeId;
	}

    
    
    
}
