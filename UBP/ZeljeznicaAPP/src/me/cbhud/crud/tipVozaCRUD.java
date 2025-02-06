package me.cbhud.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import me.cbhud.DbConnector;

public class tipVozaCRUD {
    private DbConnector dbc;
    private Scanner scanner = new Scanner(System.in);

    public tipVozaCRUD(DbConnector dbc) {
        this.dbc = dbc;
    }

    public boolean createTipVoza() {
        System.out.println("Unesite naziv tipa voza:");
        String nazivTipa = scanner.nextLine();

        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO tip_voza (naziv_tipa) VALUES ('" + nazivTipa + "');";
            stmt.executeUpdate(sql);
            System.out.println("Tip voza '" + nazivTipa + "' uspješno dodat.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbc.close(conn);
        }
    }

    public void readAllTipVoza() {
        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, naziv_tipa FROM tip_voza;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("ID | Naziv Tipa");
            System.out.println("----------------------");

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;

                int id = rs.getInt("id");
                String nazivTipa = rs.getString("naziv_tipa");

                System.out.println(id + " | " + nazivTipa);
            }

            if (!hasData) {
                System.out.println("Nema unesenih tipova voza.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }


    public boolean deleteTipVoza() {
    	readAllTipVoza();
        System.out.println("Unesite naziv tipa voza za brisanje:");
        String naziv = scanner.next();

        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM tip_voza WHERE naziv LIKE " + naziv + ";";
            int rows = stmt.executeUpdate(sql);

            if (rows > 0) {
                System.out.println("Tip voza " + naziv + " uspješno obrisan.");
                return true;
            } else {
                System.out.println("Tip voza " + naziv + " nije pronađen.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbc.close(conn);
        }
    }
    
    
    //Metoda koja dodjeljuje vozacu tip voza koji mu je dozvoljen za voznju
    public boolean dodijeliVozVozacu() {
        System.out.println("Unesite ime vozaca:");
        String ime = scanner.nextLine();
        System.out.println("Unesite prezime vozaca:");
        String prezime = scanner.nextLine();

        Connection conn = dbc.open();
        int vozacId = -1;

        
        //Da provjerimo da li postoji taj vozac
        try {
            Statement stmt = conn.createStatement();

            String sql1 = "SELECT v.id AS vozac_id " +
                          "FROM zaposleni z " +
                          "JOIN vozac v ON z.id = v.zaposleni_id " +
                          "WHERE z.ime = '" + ime + "' AND z.prezime = '" + prezime + "';";
            ResultSet rs1 = stmt.executeQuery(sql1);

            if (rs1.next()) {
                vozacId = rs1.getInt("vozac_id");
                System.out.println("Vozac sa ID " + vozacId + " pronađen.");
            } else {
                System.out.println("Vozac nije pronađen za uneto ime i prezime.");
                dbc.close(conn);
                return false;
            }

            System.out.println("Dostupni tipovi voza:");
            readAllTipVoza();

            System.out.println("Unesite ID tipa voza koji želite da dodelite:");
            int tipVozaId = Integer.parseInt(scanner.nextLine());

            String sql2 = "SELECT id FROM tip_voza WHERE id = " + tipVozaId + ";";
            ResultSet rs3 = stmt.executeQuery(sql2);

            if (!rs3.next()) {
                System.out.println("Tip voza sa ID " + tipVozaId + " nije pronađen.");
                dbc.close(conn);
                return false;
            }

            String sql3 = "INSERT INTO vozac_tip_voza (vozac_id, tip_voza_id) " +
                          "VALUES (" + vozacId + ", " + tipVozaId + ");";
            stmt.executeUpdate(sql3);

            System.out.println("Tip voza uspešno dodeljen vozaču.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            dbc.close(conn);
        }
    }

    public boolean updateTipVoza() {
        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();

            System.out.println("Dostupni tipovi voza:");
            readAllTipVoza();
            

            System.out.println("Unesite ID tipa voza koji želite da ažurirate:");
            int tipVozaId = Integer.parseInt(scanner.nextLine());
            scanner.nextLine(); // Clear the buffer

            String sql1 = "SELECT id FROM tip_voza WHERE id = " + tipVozaId + ";";
            ResultSet rs = stmt.executeQuery(sql1);

            if (!rs.next()) {
                System.out.println("Tip voza sa ID " + tipVozaId + " nije pronađen.");
                return false;
            }

            System.out.println("Unesite novi naziv za tip voza:");
            String noviNaziv = scanner.nextLine();

            String sql2 = "UPDATE tip_voza SET naziv_tipa = '" + noviNaziv + "' WHERE id = " + tipVozaId + ";";
            int rowsAffected = stmt.executeUpdate(sql2);

            if (rowsAffected > 0) {
                System.out.println("Tip voza sa ID " + tipVozaId + " uspešno ažuriran na naziv: " + noviNaziv);
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
