package me.cbhud.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import me.cbhud.DbConnector;

public class vozCRUD {
    private DbConnector dbc;
    private Scanner scanner = new Scanner(System.in);

    public vozCRUD(DbConnector dbc) {
        this.dbc = dbc;
    }

    public boolean createVoz() {
        System.out.println("Unesite oznaku voza (maksimalna duzina je 5): ");
        String oznaka = scanner.nextLine();
        
        if(oznaka.length() > 5 || oznaka.isBlank()) {
        	System.out.println("Oznaka nije validna");
        	return false;
        }

        System.out.println("Unesite broj vagona:");
        int brojVagona = Integer.parseInt(scanner.nextLine());

        System.out.println("Unesite godinu proizvodnje:");
        int godProizvodnje = Integer.parseInt(scanner.nextLine());

        //Prikaz svih dostupnih tipova voza kako bi menadzment mogao lakse da vidi koji voz pripada kom id-u
        // i na osnovu toga sa olaksicom odabere kojem tipu voza voz pripada
        System.out.println("Dostupni tipovi voza:");
        Connection conn = dbc.open();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, naziv_tipa FROM tip_voza;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nazivTipa = rs.getString("naziv_tipa");
                System.out.println("ID: " + id + ", Naziv: " + nazivTipa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            dbc.close(conn);
            return false;
        }

        System.out.println("Unesite ID tipa voza:");
        int tip = Integer.parseInt(scanner.nextLine());;

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO voz (oznaka, broj_vagona, god_proizvodnje, tip_voza_id) " +
                         "VALUES ('" + oznaka + "', " + brojVagona + ", " + godProizvodnje + ", " + tip + ");";
            stmt.executeUpdate(sql);
            System.out.println("Voz '" + oznaka + "' uspješno dodat.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbc.close(conn);
        }
    }


    public void readAllVoz() {
        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();

            //Umjesto ID-A stavili smo prikaz naziva tipa kako bi mogli lakse vidjeti kojem tipu pripada
            String sql = "SELECT v.id, v.oznaka, v.broj_vagona, v.god_proizvodnje, t.naziv_tipa " +
                         "FROM voz v " +
                         "JOIN tip_voza t ON v.tip_voza_id = t.id;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("ID | Oznaka | Broj Vagona | Godina Proizvodnje | Tip Voza");
            System.out.println("------------------------------------------------------------");

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;

                int id = rs.getInt("id");
                String oznaka = rs.getString("oznaka");
                int brojVagona = rs.getInt("broj_vagona");
                int godProizvodnje = rs.getInt("god_proizvodnje");
                String nazivTipa = rs.getString("naziv_tipa");

                System.out.println(id + " | " + oznaka + " | " + brojVagona + " | " + godProizvodnje + " | " + nazivTipa);
            }

            if (!hasData) {
                System.out.println("Nema unesenih vozova.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }


    
    //Voz se brise na osnovu oznake!
    public boolean deleteVoz() {
        System.out.println("Unesite Oznaku voza za brisanje:");
        String oznaka = scanner.nextLine();

        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM voz WHERE oznaka = " + oznaka + ";";
            int rows = stmt.executeUpdate(sql);

            if (rows > 0) {
                System.out.println("Voz sa oznakom " + oznaka + " uspješno obrisan.");
                return true;
            } else {
                System.out.println("Voz sa oznakom " + oznaka + " nije pronađen.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbc.close(conn);
        }
    }
    
    public boolean updateVozBrojVagona() {
        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();

            //prikaz dostupnih vozova prije izmjene
            System.out.println("Dostupni vozovi za izmjenu broja vagona:");
            String sql1 = "SELECT id, oznaka, broj_vagona FROM voz;";
            ResultSet rs = stmt.executeQuery(sql1);

            while (rs.next()) {
                int id = rs.getInt("id");
                String oznaka = rs.getString("oznaka");
                int brojVagona = rs.getInt("broj_vagona");
                System.out.println("ID: " + id + ", Oznaka: " + oznaka + ", Broj vagona: " + brojVagona);
            }

            System.out.println("Unesite ID voza koji želite da ažurirate:");
            int vozId = Integer.parseInt(scanner.nextLine());

            //provjera da li voz postoji
            String sql2 = "SELECT id FROM voz WHERE id = " + vozId + ";";
            rs = stmt.executeQuery(sql2);

            if (!rs.next()) {
                System.out.println("Voz sa ID " + vozId + " nije pronađen.");
                return false;
            }

            System.out.println("Unesite novi broj vagona za voz sa ID " + vozId + ":");
            int noviBrojVagona = Integer.parseInt(scanner.nextLine());

            String sql3 = "UPDATE voz SET broj_vagona = " + noviBrojVagona + " WHERE id = " + vozId + ";";
            int rowsAffected = stmt.executeUpdate(sql3);

            if (rowsAffected > 0) {
                System.out.println("Voz sa ID " + vozId + " uspešno ažuriran. Novi broj vagona: " + noviBrojVagona);
                return true;
            } else {
                System.out.println("Ažuriranje nije uspelo.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbc.close(conn);
        }
    }

    
}
