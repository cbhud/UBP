package me.cbhud.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import me.cbhud.DbConnector;
import me.cbhud.entiteti.Zaposleni;

public class zaposleniCRUD {

	
	private DbConnector dbc;
	Scanner scanner = new Scanner(System.in);
	
	
	public zaposleniCRUD(DbConnector dbc) {
		this.dbc = dbc;
	}
	
	
	
	//formatirana metoda za prikaz zaposlenih
	public void readZaposleni() {
	    Connection conn = dbc.open();

	    try {
	        Statement state = conn.createStatement();
	        String sql = "SELECT id, ime, prezime, jmbg, radni_staz, datum_zaposlenja FROM zaposleni";
	        ResultSet rs = state.executeQuery(sql);

	        System.out.println("ID | Ime | Prezime | JMBG | Radni staž | Datum zaposlenja");
	        System.out.println("-----------------------------------------------------------");

	        boolean hasData = false;

	        while (rs.next()) {
	            hasData = true;

	            int id = rs.getInt("id");
	            String ime = rs.getString("ime");
	            String prezime = rs.getString("prezime");
	            String jmbg = rs.getString("jmbg");
	            int radniStaz = rs.getInt("radni_staz");
	            String datumZaposlenja = rs.getDate("datum_zaposlenja").toString();

	            System.out.println(id + " | " + ime + " | " + prezime + " | " + jmbg + " | " + radniStaz + " | " + datumZaposlenja);
	        }

	        if (!hasData) {
	            System.out.println("Nema unesenih zaposlenih.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        dbc.close(conn);
	    }
	}

	
	public boolean createZaposleni() {
        System.out.println("Unesite ime i prezime (Format ime prezime):");
        String ip = scanner.nextLine();

        String ime = ip.split(" ")[0];
        String prezime = ip.split(" ")[1];
    	System.out.println("Unesite JMBG");
    	String jmbg = scanner.nextLine();
    	System.out.println("UNESITE broj godina radnog staza");
    	int radniStaz = Integer.parseInt(scanner.nextLine());
    	System.out.println("Unesite datum zaposlenja u formatu yyyy-mm-dd");
    	String datumZaposlenja = scanner.nextLine();

    	//Potrebna je validacija podataka prije unosa kako se ne bi desio error
		if (!provjeraPodataka(ime, prezime, jmbg, datumZaposlenja)) {
			System.out.println("Unijeti podaci ne zadovoljavaju format!");
			return false;
		}
		
		
		//Potrebno je prije toga datum prebaciti iz stringa u LocalDate tip podatka za datume
		LocalDate.parse(datumZaposlenja);


		Connection conn = dbc.open();

		try {
			Statement state = conn.createStatement();

			String sql = "INSERT INTO zaposleni(ime, prezime, jmbg, radni_staz, datum_zaposlenja) VALUES ('" + ime + "', '"
					+ prezime + "', '" + jmbg + "', '" + radniStaz + "', '" + datumZaposlenja + "');";
			state.executeUpdate(sql);
			System.out.println("\u001B[32mZaposleni uspješno kreiran.\u001B[0m");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbc.close(conn);
		}
	}

	
	public boolean deleteZaposleni() {
    	System.out.println("Unesite ime radnika");
    	String ime = scanner.next();
    	System.out.println("Unesite prezime");
    	String prezime = scanner.next();
		
		int id = findRadnikById(ime, prezime);
		if (id == -1) {
			System.out.println("Radnik nije pronadjen!");
			return false;
		}
		
		//Ukoliko je zaposleni vozac bice potrebno ukloniti ga preko opcije za vozace,
		//Ovo smo mogli i ovdje da rijesimo
		if (checkIfVozac(id)) {
			System.out.println("Zaposleni je vozac molimo obriste ga preko opcije za vozace!");
			return false;
		}
		
		Connection conn = dbc.open();
		
		try {
			Statement state = conn.createStatement();
			String sql1 = "DELETE FROM red_voznje WHERE zaposleni_id = " + id + ";";
			String sql2 = "DELETE FROM zaposleni WHERE id = "+ id +";";
			state.executeUpdate(sql1);
			state.executeUpdate(sql2);
			System.out.println("Radnik uspjesno obrisan");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbc.close(conn);
		}	
	}
	
	//Metoda za update
	public boolean updateZaposleni() {
		
        System.out.println("Unesite vase ime i prezime (Format ime prezime):");
        String ip = scanner.nextLine();

        String ime = ip.split(" ")[0];
        String prezime = ip.split(" ")[1];
		
		int id = findRadnikById(ime, prezime);
		if (id == -1) {
			System.out.println("Radnik nije pronadjen!");
			return false;
		}
    	
    	System.out.println("Unesite novi broj godina radnog staza radniku");
    	int br = Integer.parseInt(scanner.nextLine());
    	
		Connection conn = dbc.open();
    	
		try {
			Statement state = conn.createStatement();
			String sql = "UPDATE zaposleni SET radni_staz = " + br + " WHERE id = " + id +";";
			state.executeUpdate(sql);
			System.out.println("Uspjesno izmijenjen broj godina radnog staza");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbc.close(conn);
		}	
    	
	}
	
	public boolean checkIfVozac(int id) {
		
		Connection conn = dbc.open();

		try {
			Statement state = conn.createStatement();

			String sql = "SELECT zaposleni_id FROM vozac WHERE id = " + id + ";";
			ResultSet rs = state.executeQuery(sql);
	        if (!rs.next()) { 
	            return false;
	        } else {
	            return true;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbc.close(conn);
		}	
	}
	
	//Metoda koja pomaze pri brisanju radnika iz baze kako bismo provjerili da li postoji
	public int findRadnikById(String ime, String prezime) {
		
		Connection conn = dbc.open();

		try {
			Statement state = conn.createStatement();

			String sql = "SELECT id FROM zaposleni WHERE ime = '" + ime + "' AND prezime = '" + prezime+"';";
			ResultSet rs = state.executeQuery(sql);
	        if (rs.next()) { 
	            return rs.getInt("id");
	        } else {
	            return -1;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			dbc.close(conn);
		}	
	}


	private boolean provjeraPodataka(String ime, String prezime, String jmbg, String datumZaposlenja) {

		if(ime.isEmpty() || prezime.isEmpty() ||jmbg.length() < 13) {
		
		return false;
		}
		
		//potrebno je i provjeriti datum formatiranja
		try {
			LocalDate.parse(datumZaposlenja);
		} catch (Exception e) {
			System.out.println("Pogresan format datuma, unesite datum u formatu YYYY-MM-DD");
			return false;
		}
		
		return true;
	}
	
	
	
	
	
}
