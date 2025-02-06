package me.cbhud.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import me.cbhud.DbConnector;
import me.cbhud.entiteti.Zaposleni;

public class vozacCRUD {

	
	private DbConnector dbc;
	Scanner scanner = new Scanner(System.in);
	
	
	public vozacCRUD(DbConnector dbc) {
		this.dbc = dbc;
	}
	
	
	public void readVozaci() {
	    Connection conn = dbc.open();

	    try {
	        Statement state = conn.createStatement();
	        String sql = "SELECT ime, prezime, radni_staz, datum_zaposlenja, sati_voznje, jmbg " +
	                     "FROM zaposleni z, vozac v WHERE z.id = v.zaposleni_id;";
	        ResultSet rs = state.executeQuery(sql);

	        System.out.println("Ime | Prezime | Radni staž | Datum zaposlenja | Sati vožnje | JMBG");
	        System.out.println("-----------------------------------------------------------------");

	        boolean hasData = false;

	        while (rs.next()) {
	            hasData = true;

	            String ime = rs.getString("ime");
	            String prezime = rs.getString("prezime");
	            int radniStaz = rs.getInt("radni_staz");
	            String datumZaposlenja = rs.getDate("datum_zaposlenja").toString();
	            int satiVoznje = rs.getInt("sati_voznje");
	            String jmbg = rs.getString("jmbg");

	            System.out.println(ime + " | " + prezime + " | " + radniStaz + " | " + datumZaposlenja + " | " + satiVoznje + " | " + jmbg);
	        }

	        if (!hasData) {
	            System.out.println("Nema unesenih vozača.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        dbc.close(conn);
	    }
	}
	
	public boolean createVozac() {
        System.out.println("Unesite vase ime i prezime (Format ime prezime):");
        String ip = scanner.nextLine();

        String ime = ip.split(" ")[0];
        String prezime = ip.split(" ")[1];
        
        System.out.println("Unesite broj sati voznje");

        
        int brojSati = Integer.parseInt(scanner.nextLine());
    	
		int id = findZaposleniById(ime, prezime);
		
		if (id == -1) {
			System.out.println("Molimo vas kreirajte zaposlenog prije nego ga dodate kao vozaca!");
			return false;
		}
		
		Connection conn = dbc.open();

		try {
			Statement state = conn.createStatement();

			String sql = "INSERT INTO vozac (sati_voznje, zaposleni_id)"
					+ "VALUES(" + brojSati +", "+ id+");";
			state.executeUpdate(sql);
			System.out.println("Vozac uspjesno dodat.");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbc.close(conn);
		}
		
	}
	
	
	//Brisanje vozaca iz baze zaposlenih
	public boolean deleteVozac() {
    	System.out.println("Unesite ime vozaca");
    	String ime = scanner.next();
    	System.out.println("Unesite prezime vozaca");
    	String prezime = scanner.next();
    	
		int id = findZaposleniById(ime, prezime);
		
		if (id == -1) {
			System.out.println("Vozac nije pronadjen!");
			return false;
		}
		
		Connection conn = dbc.open();

		try {
			Statement state = conn.createStatement();
			//Potrebno je i da obrisemo sve stavke koje imaju veze
			//Sa njegovim stranim kljucem
			String sql1 = "DELETE FROM medic_pregled WHERE vozac_id = (SELECT id FROM vozac WHERE zaposleni_id = " + id + ");";
			String sql2 = "DELETE FROM vozac_tip_voza WHERE zaposleni_id = " + id +";";
			String sql3 = "DELETE FROM red_voznje WHERE vozac_id = " + id +";";
			String sql4 = "DELETE FROM vozac WHERE zaposleni_id = " + id +";";
			String sql5 = "DELETE FROM zaposleni WHERE id = " + id +";";
			state.executeUpdate(sql1);
			state.executeUpdate(sql2);
			state.executeUpdate(sql3);
			state.executeUpdate(sql4);
			state.executeUpdate(sql5);
			System.out.println("Vozac uspjesno obrisan NAPOMENA obrisan je potpuno iz zaposlenih!");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbc.close(conn);
		}

	}
	
	
	//izmjena podatka o broju sati voznje kod vozaca
	public boolean updateVozac() {
		
    	System.out.println("Unesite ime vozaca");
    	String ime = scanner.next();
    	System.out.println("Unesite prezime vozaca");
    	String prezime = scanner.next();
		
		int id = findZaposleniById(ime, prezime);
		if (id == -1) {
			System.out.println("Vozac nije pronadjen!");
			return false;
		}
    	
    	System.out.println("Unesite novi broj sati voznje");
    	int br = Integer.parseInt(scanner.nextLine());
    	
		Connection conn = dbc.open();
    	
		try {
			Statement state = conn.createStatement();
			String sql = "UPDATE vozac SET sati_voznje = " + br + " WHERE zaposleni_id = " + id +";";
			state.executeUpdate(sql);
			System.out.println("Uspjesno izmijenjen broj sati voznje!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbc.close(conn);
		}	
    	
	}
	
	
	
	//Pronalazak zaposlenog po imenu pomocna metoda
	public int findZaposleniById(String ime, String prezime) {
		
		Connection conn = dbc.open();

		try {
			Statement state = conn.createStatement();

			String sql = "SELECT id FROM zaposleni WHERE ime = '" + ime + "' AND prezime = '" + prezime+"';";
			ResultSet rs = state.executeQuery(sql);
	        if (rs.next()) { 
	            return rs.getInt("id");
	        } else {
	        	System.out.println("Zaposleni nije pronadjen!");
	            return -1;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			dbc.close(conn);
		}	
	}
	
	
}
