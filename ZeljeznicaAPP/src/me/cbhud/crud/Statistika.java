package me.cbhud.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.cbhud.DbConnector;

public class Statistika {
    private DbConnector dbc;

    public Statistika(DbConnector dbc) {
        this.dbc = dbc;
    }

    // 1 Zarada po destinaciji
    public void zaradaPoDestinaciji() {
        Connection conn = dbc.open();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT rv.destinacija, " +
                         "SUM(c.cijena * (k.tip_mjesta = 'Obicno')) + " +
                         "SUM(c.vip_cijena * (k.tip_mjesta = 'VIP')) AS ukupna_zarada " +
                         "FROM karta k " +
                         "JOIN red_voznje rv ON k.red_voznje_id = rv.id " +
                         "JOIN cjenovnik c ON k.red_voznje_id = c.red_voznje_id " +
                         "GROUP BY rv.destinacija " +
                         "ORDER BY ukupna_zarada DESC;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Destinacija | Ukupna zarada");
            while (rs.next()) {
                System.out.println(rs.getString("destinacija") + " | " + rs.getDouble("ukupna_zarada") + " EUR");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }

    // 2 Prosečan broj prodatih karata po destinaciji
    public void brojProdatihKarataPoDestinaciji() {
        Connection conn = dbc.open();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT Destinacija, COUNT(destinacija) as broj "
            		+ " FROM karta k"
            		+ " JOIN red_voznje rv ON k.red_voznje_id = rv.id"
            		+ " group by destinacija"
            		+ " order by broj DESC;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Destinacija | Broj karata");
            while (rs.next()) {
                System.out.println(rs.getString("destinacija") + " | " + rs.getDouble("broj"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }


    // 3 Prosečna starost vozova
    public void prosecnaStarostVozova() {
        Connection conn = dbc.open();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT AVG(YEAR(NOW()) - v.god_proizvodnje) AS prosjecna_starost_vozova " +
                         "FROM voz v;";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Prosečna starost vozova: " + rs.getDouble("prosjecna_starost_vozova") + " godina");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }

    // 4 Broj kreiranih redova vožnje po zaposlenom
    public void brojRedovaVoznjePoZaposlenom() {
        Connection conn = dbc.open();
        try {
            Statement stmt = conn.createStatement();
            //Prikazuje samo zaposlene koji imaju makar 1 red voznje
            //LEFT JOIN bi prikazivao sve zaposlene
            String sql = "SELECT z.ime, z.prezime, COUNT(rv.id) AS broj_redova_voznje " +
                         "FROM zaposleni z " +
                         "JOIN red_voznje rv ON z.id = rv.zaposleni_id " +
                         "GROUP BY z.id, z.ime, z.prezime " +
                         "ORDER BY broj_redova_voznje DESC;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Ime | Prezime | Broj redova voznji");
            while (rs.next()) {
                System.out.println(rs.getString("ime") + " | " + rs.getString("prezime") + " | " + rs.getInt("broj_redova_voznje"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }

    // 5 Broj vožnji po vozaču
    public void brojVoznjiPoVozacu() {
        Connection conn = dbc.open();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT z.ime, z.prezime, COUNT(rv.id) AS broj_voznji " +
                         "FROM vozac v " +
                         "JOIN zaposleni z ON v.zaposleni_id = z.id " +
                         "JOIN red_voznje rv ON v.id = rv.vozac_id " +
                         "GROUP BY v.id, z.ime, z.prezime " +
                         "ORDER BY broj_voznji DESC;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Ime | Prezime | Broj voznji");
            while (rs.next()) {
                System.out.println(rs.getString("ime") + " | " + rs.getString("prezime") + " | " + rs.getInt("broj_voznji"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }

    // 6 Vozači sa iznadprosečnim brojem sati vožnje
    public void vozaciIznadProsekaSati() {
        Connection conn = dbc.open();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT z.ime, z.prezime, v.sati_voznje " +
                         "FROM vozac v " +
                         "JOIN zaposleni z ON v.zaposleni_id = z.id " +
                         "WHERE v.sati_voznje > (SELECT AVG(sati_voznje) FROM vozac) " +
                         "ORDER BY v.sati_voznje DESC;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Ime | Prezime | Sati voznje");
            while (rs.next()) {
                System.out.println(rs.getString("ime") + " | " + rs.getString("prezime") + " | " + rs.getInt("sati_voznje"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }
}
