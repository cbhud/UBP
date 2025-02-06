package me.cbhud.crud;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import me.cbhud.DbConnector;
import me.cbhud.entiteti.Zaposleni;

public class medicinskiPregledCRUD {
	
	private DbConnector dbc;
	Scanner scanner = new Scanner(System.in);
	
	
	public medicinskiPregledCRUD(DbConnector dbc) {
		this.dbc = dbc;
	}
	
	public boolean createPregled() {
	    System.out.println("Unesite ime vozaca:");
	    String ime = scanner.next();
	    System.out.println("Unesite prezime vozaca:");
	    String prezime = scanner.next();

	    int vozacId = findVozacId(ime, prezime);

	    if (vozacId == -1) {
	        System.out.println("Vozac nije pronadjen!");
	        return false;
	    }

	    System.out.println("Unesite naziv fajla nalaza (u formatu imefajla.txt):");
	    String fileName = scanner.next();
	    File file = new File(fileName);

	    try (Scanner sc = new Scanner(file)) {
	        int counter = 1;
	        Connection conn = dbc.open();
	        Statement state = conn.createStatement();

	        while (sc.hasNextLine()) {
	            String line = sc.nextLine();
	            String[] data = line.split(";");

	            try {
	                LocalDate datumPregleda = LocalDate.parse(data[0].trim());
	                String nalaz = data[1].trim();

	                if (nalaz.length() > 250) {
	                    System.out.println("Nalaz ima " + counter + " odbacuje se unos maksimalan je do 250 karaktera!");
	                    return false;
	                }

	                //zatim se unosi u bazu podataka
	                String sql = "INSERT INTO medic_pregled (datum_pregleda, nalaz, vozac_id) VALUES ('" 
	                             + datumPregleda + "', '" + nalaz + "', " + vozacId + ");";
	                state.executeUpdate(sql);

	                System.out.println("Pregled je uspjesno dodat.");
	            } catch (Exception e) {
	                System.err.println("Neispravni podaci preskacem unos.");
	            }
	            counter++;
	        }

	        dbc.close(conn);
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } catch (FileNotFoundException e) {
	        System.err.println("Fajl sa tim nazivom ne postoji!");
	        return false;
	    }
	}
	
	
	//Kako bi pogledali svi preglede koristili smo JOIN kako bi vidjeli i ime vozaca
	// jer menadzmentu nista ne znaci radnik id vec je bitno da vide ime i prezime
	public void readPregledi() {
	    Connection conn = dbc.open();

	    try {
	        Statement state = conn.createStatement();

	        String sql = "SELECT z.ime, z.prezime, mp.nalaz, mp.datum_pregleda " +
	                     "FROM medic_pregled mp " +
	                     "JOIN vozac v ON mp.vozac_id = v.id " +
	                     "JOIN zaposleni z ON v.zaposleni_id = z.id " +
	                     "ORDER BY mp.datum_pregleda DESC;";

	        ResultSet rs = state.executeQuery(sql);

	        System.out.println("Ime | Prezime | Nalaz | Datum Pregleda");
	        System.out.println("--------------------------------------");

	        boolean hasData = false;

	        while (rs.next()) {
	            hasData = true;

	            String ime = rs.getString("ime");
	            String prezime = rs.getString("prezime");
	            String nalaz = rs.getString("nalaz");
	            String datumPregleda = rs.getString("datum_pregleda");

	            System.out.println(ime + " | " + prezime + " | " + nalaz + " | " + datumPregleda);
	        }

	        if (!hasData) {
	            System.out.println("Nema unesenih medicinskih pregleda.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        dbc.close(conn);
	    }
	}
	
	
	
	//Metoda koja pronalazi vozaca prije unosa medicinskog pregleda
	public int findVozacId(String ime, String prezime) {
	    Connection conn = dbc.open();

	    try {
	        Statement state = conn.createStatement();

	        String sql1 = "SELECT id FROM zaposleni WHERE ime = '" + ime + "' AND prezime = '" + prezime + "';";
	        ResultSet rs = state.executeQuery(sql1);

	        if (rs.next()) {
	            int zaposleniId = rs.getInt("id");

	            String sql2 = "SELECT id FROM vozac WHERE zaposleni_id = " + zaposleniId + ";";
	            rs = state.executeQuery(sql2);

	            if (rs.next()) {
	                return rs.getInt("id");
	            } else {
	                System.out.println("Vozac nije pronadjen za unesenog zaposlenog.");
	                return -1;
	            }
	        } else {
	            System.out.println("Zaposleni nije pronadjen.");
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
