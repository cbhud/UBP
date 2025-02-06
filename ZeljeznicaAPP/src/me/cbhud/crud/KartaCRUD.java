package me.cbhud.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import me.cbhud.DbConnector;

public class KartaCRUD {
    private DbConnector dbc;
    private Scanner scanner = new Scanner(System.in);

    public KartaCRUD(DbConnector dbc) {
        this.dbc = dbc;
    }

    //Metoda koja kreira kartu (koristi se u radnik i putnik meni)
    public boolean createKarta() {

        System.out.println("Unesite ime i prezime putnika (Format ime prezime):");
        String ip = scanner.nextLine();

        String ime = ip.split(" ")[0];
        String prezime = ip.split(" ")[1];

        //prije prodaje potrebno je da odabere destinaciju na koju ide
        //metoda displayDestinations nam prikazuje dostupne redove voznje kao i cijene
        displayDestinations();
        System.out.println("Unesite broj reda vožnje:");
        int redVoznjeId = Integer.parseInt(scanner.nextLine());

        //Odabir mjesta
        System.out.println("Unesite tip mesta (VIP ili Obicno):");
        String tipMjesta = scanner.nextLine();
        while (!tipMjesta.equalsIgnoreCase("VIP") && !tipMjesta.equalsIgnoreCase("Obicno")) {
            System.out.println("Pogrešan unos. Unesite tip mesta (VIP ili Obicno):");
            tipMjesta = scanner.nextLine();
        }
        
        Connection conn = dbc.open();

        //koristimo now() za upis datuma kako radnik ili putnik ne bi morali da brinu o datumu kreiranja
        // vec ce program sam to unijeti
        try {
            Statement stmt = conn.createStatement();

            // Insert the ticket into the database
            String sql = "INSERT INTO karta (datum_prodaje, ime, prezime, tip_mjesta, red_voznje_id) " +
                         "VALUES (NOW(), '" + ime + "', '" + prezime + "', '" + tipMjesta + "', " + redVoznjeId + ");";
            stmt.executeUpdate(sql);

            System.out.println("Karta uspjesno prodata.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            dbc.close(conn);
        }
    }


    
    //formatirana metoda za prikaz svih karata
    //dodatno prikazuje destinaciju i red_voznje_id smatrajuci da radniku prikaz destinacije 
    //pomaze pri obavljanju posla kao i id reda voznje
    //kako bi odmah mogao vidjeti koji je id tog reda voznje
    public void readAllKarte() {
        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT br_karte, datum_prodaje, ime, prezime, tip_mjesta, destinacija, rv.id as red_voznje_id FROM karta k JOIN red_voznje rv ON k.red_voznje_id = rv.id ORDER BY datum_prodaje ASC;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println(" Broj Karte | Datum Prodaje | Ime | Prezime | Tip Mjesta | Red Voznje ID");
            System.out.println("----------------------------------------------------------------------------");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String brKarte = rs.getString("br_karte");
                String datumProdaje = rs.getString("datum_prodaje");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String tipMjesta = rs.getString("tip_mjesta");
                int redVoznjeId = rs.getInt("red_voznje_id");

                System.out.println(brKarte + " | " + datumProdaje + " | " +
                                   ime + " | " + prezime + " | " + tipMjesta + " | " + redVoznjeId);
            }

            if (!hasData) {
                System.out.println("Nema dostupnih karata.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }

    
    //Metoda za brisanje karti iz baze
    public void deleteKarta() {
        System.out.println("Unesite ime i prezime putnika (Format ime prezime):");
        String ip = scanner.nextLine();

        String ime = ip.split(" ")[0];
        String prezime = ip.split(" ")[1];

        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();

            String sqlCheck = "SELECT id FROM karta WHERE ime = '" + ime + "' AND prezime = '" + prezime + "';";
            ResultSet rs = stmt.executeQuery(sqlCheck);

            if (rs.next()) {
                int kartaId = rs.getInt("id");
                String sqlDelete = "DELETE FROM karta WHERE id = " + kartaId + ";";
                stmt.executeUpdate(sqlDelete);
                System.out.println("Karta za kupca " + ime + " " + prezime + " uspešno obrisana.");
            } else {
                System.out.println("Karta za kupca " + ime + " " + prezime + " nije pronađena.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }
    
    
    
    //Metoda prikazuje sve dostupne destinacije
    //njihov id reda voznje
    //kao i obicnu i vip cijenu
    public void displayDestinations() {
        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();

            // Fetch available destinations along with their prices
            String sql = "SELECT rv.id, rv.destinacija, c.cijena, c.vip_cijena " +
                         "FROM red_voznje rv " +
                         "JOIN cjenovnik c ON rv.id = c.red_voznje_id;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Dostupne destinacije sa cijenama:");
            System.out.println("ID | Destinacija | Obična cijena | VIP cijena");
            System.out.println("-------------------------------------------------");

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("id");
                String destinacija = rs.getString("destinacija");
                double cijena = rs.getDouble("c.cijena");
                double vipCijena = rs.getDouble("c.vip_cijena");

                System.out.println(id + " | " + destinacija + " | " + cijena + " EUR | " + vipCijena + " EUR");
            }

            if (!hasData) {
                System.out.println("Nema dostupnih destinacija ili cijena.");
            }

            System.out.println("-------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }
    
    
    




    
    
}
