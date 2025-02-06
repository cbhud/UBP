package me.cbhud.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

import me.cbhud.DbConnector;

public class RedVoznjeCRUD {
    private DbConnector dbc;
    private Scanner scanner = new Scanner(System.in);

    public RedVoznjeCRUD(DbConnector dbc) {
        this.dbc = dbc;
    }

    public boolean createRedVoznje() {
        System.out.println("Unesite destinaciju:");
        String destinacija = scanner.nextLine();

        LocalDateTime datumVrijemePolaska = parseDatumiVrijeme();
        if (datumVrijemePolaska.isBefore(LocalDateTime.now())) {
			System.out.println("Datum i vrijeme polaska ne mogu biti prije kreiranja reda voznje!!!");
			return false;
		}

        System.out.println("Unesite broj mjesta:");
        int brMjesta = Integer.parseInt(scanner.nextLine());

        System.out.println("Unesite broj VIP mjesta:");
        int brVipMjesta = Integer.parseInt(scanner.nextLine());

        System.out.println("Unesite ime i prezime zaposlenog koji kreira red voznje (Format: ime prezime):");
        String ip = scanner.nextLine();
        String zaposleniIme = ip.split(" ")[0];
        String zaposleniPrezime = ip.split(" ")[1];

        int zaposleniId = findZaposleniId(zaposleniIme, zaposleniPrezime);
        if (zaposleniId == -1) {
            System.out.println("Zaposleni sa unesenim imenom i prezimenom nije pronađen. Operacija prekinuta.");
            return false;
        }

        System.out.println("Unesite ime i prezime vozaca (Format: ime prezime):");
        String ip2 = scanner.nextLine();
        String vozacIme = ip2.split(" ")[0];
        String vozacPrezime = ip2.split(" ")[1];

        int vozacId = findVozacId(vozacIme, vozacPrezime);
        if (vozacId == -1) {
            System.out.println("Vozač sa unesenim imenom i prezimenom nije pronađen. Operacija prekinuta.");
            return false;
        }

        displayAvailableTrainsForDriver(vozacId);

        System.out.println("Unesite ID voza:");
        int vozId = Integer.parseInt(scanner.nextLine());

        // 🔹 **Provera da li vozač ima dozvolu za ovaj voz**
        if (!provjeriDozvoluZaVoz(vozacId, vozId)) {
            System.out.println("Vozač nema dozvolu za ovaj tip voza, KREIRANJE PREKINUTO.");
            return false;
        }

        Connection conn = dbc.open();
        try {
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO red_voznje (datum_kreiranja, destinacija, datum_vrijeme_polaska, br_mjesta, br_vip_mjesta, zaposleni_id, vozac_id, voz_id) " +
                         "VALUES (NOW(), '" + destinacija + "', '" + datumVrijemePolaska + "', " + brMjesta + ", " + brVipMjesta +
                         ", " + zaposleniId + ", " + vozacId + ", " + vozId + ");";
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int redVoznjeId = rs.getInt(1);

                System.out.println("Unesite cijenu za obična mjesta:");
                double cijena = Double.parseDouble(scanner.nextLine());

                System.out.println("Unesite cijenu za VIP mjesta:");
                double vipCijena = Double.parseDouble(scanner.nextLine());

                String sqlCjenovnik = "INSERT INTO cjenovnik (cijena, vip_cijena, red_voznje_id) " +
                                      "VALUES (" + cijena + ", " + vipCijena + ", " + redVoznjeId + ");";
                stmt.executeUpdate(sqlCjenovnik);
                System.out.println("Red voznje uspjesno kreiran id: " + redVoznjeId);
            } else {
                System.out.println("Greška prilikom kreiranja reda vožnje. Cjenovnik nije dodat.");
                return false;
            }
            
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbc.close(conn);
        }
    }



    
    //Parser koji mi olaksava unos datuma i vremena 
    // Poboljsava i pojednostavljuje unos datuma i vremena
    // umjesto YYYY-MM-DDTHH:MM
    // koji moze bit nerazumljiv za korisnika koji dodaje koristimo parser
    // koji ce nam sam spojiti nas posebno unijet datum i posebno unijeto vrijeme
    private LocalDateTime parseDatumiVrijeme() {
        while (true) {
            try {
                System.out.println("Unesite datum polaska u formatu YYYY-MM-DD:");
                String date = scanner.nextLine();

                if (date.length() != 10 || !date.contains("-")) {
                    System.out.println("Pogrešan format datuma! Datum mora biti u formatu YYYY-MM-DD.");
                    continue;
                }


                System.out.println("Unesite vrijeme polaska u formatu HH:MM:");
                String time = scanner.nextLine();

                if (time.length() != 5 || !time.contains(":")) {
                    System.out.println("Pogrešan format vremena! Vrijeme mora biti u formatu HH:MM.");
                    continue;
                }
                if(LocalDateTime.parse(date + "T" + time).isBefore(LocalDateTime.now())) {
                    System.out.println("Datum i vrijeme ne moze biti u proslosti!");
                    continue;
                }

                return LocalDateTime.parse(date + "T" + time);
                
            }catch(Exception E) {
            	
            }
            }
        }

	public void readAllRedVoznje() {
        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT rv.id, rv.destinacija, rv.datum_vrijeme_polaska, rv.br_mjesta, rv.br_vip_mjesta, " +
                         "CONCAT(z.ime, ' ', z.prezime) AS zaposleni_ime_prezime, " +
                         "CONCAT(zv.ime, ' ', zv.prezime) AS vozac_ime_prezime, rv.voz_id " +
                         "FROM red_voznje rv " +
                         "JOIN zaposleni z ON rv.zaposleni_id = z.id " +
                         "JOIN vozac v ON rv.vozac_id = v.id " +
                         "JOIN zaposleni zv ON v.zaposleni_id = zv.id " +
                         "ORDER BY rv.datum_vrijeme_polaska DESC;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("ID | Destinacija | Datum i vrijeme polaska | Broj mjesta | Broj VIP mjesta | Zaposleni | Vozac | Voz ID");
            System.out.println("------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String destinacija = rs.getString("destinacija");
                String datumVrijemePolaska = rs.getString("datum_vrijeme_polaska");
                int brMjesta = rs.getInt("br_mjesta");
                int brVipMjesta = rs.getInt("br_vip_mjesta");
                String zaposleniImePrezime = rs.getString("zaposleni_ime_prezime");
                String vozacImePrezime = rs.getString("vozac_ime_prezime");
                int vozId = rs.getInt("voz_id");

                System.out.println(id + " | " + destinacija + " | " + datumVrijemePolaska + " | " +
                                   brMjesta + " | " + brVipMjesta + " | " + zaposleniImePrezime + " | " + vozacImePrezime + " | " + vozId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }

    
    public void updateRedVoznje() {
        System.out.println("Unesite ID reda vožnje kod kojed želite da ažurirate datum i vrijeme polaska:");
        int redVoznjeId = Integer.parseInt(scanner.nextLine());


        LocalDateTime noviDatumVrijemePolaskaInput = parseDatumiVrijeme();

        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();
            String sqlCheck = "SELECT id FROM red_voznje WHERE id = " + redVoznjeId + ";";
            ResultSet rs = stmt.executeQuery(sqlCheck);

            if (rs.next()) {
                String sqlUpdate = "UPDATE red_voznje SET datum_vrijeme_polaska = '" + noviDatumVrijemePolaskaInput + "' WHERE id = " + redVoznjeId + ";";
                stmt.executeUpdate(sqlUpdate);
                System.out.println("Datum i vrijeme polaska za red vožnje sa ID " + redVoznjeId + " uspješno ažurirani.");
            } else {
                System.out.println("Red vožnje sa ID " + redVoznjeId + " nije pronađen.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }

    
    public void deleteRedVoznje() {
        System.out.println("Unesite ID reda vožnje koji želite da obrišete:");
        int redVoznjeId = Integer.parseInt(scanner.nextLine());

        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();

            // Check if the red voznje exists
            String sqlCheck = "SELECT id FROM red_voznje WHERE id = " + redVoznjeId + ";";
            ResultSet rs = stmt.executeQuery(sqlCheck);

            if (rs.next()) {
                // Delete associated cjenovnik
                String sqlDeleteCjenovnik = "DELETE FROM cjenovnik WHERE red_voznje_id = " + redVoznjeId + ";";
                stmt.executeUpdate(sqlDeleteCjenovnik);

                // Delete the red voznje
                String sqlDeleteRedVoznje = "DELETE FROM red_voznje WHERE id = " + redVoznjeId + ";";
                stmt.executeUpdate(sqlDeleteRedVoznje);

                System.out.println("Red vožnje sa ID " + redVoznjeId + " i njegov cjenovnik su uspješno obrisani.");
            } else {
                System.out.println("Red vožnje sa ID " + redVoznjeId + " nije pronađen.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }

    
    public void readDestinacije() {
        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT DISTINCT id, destinacija FROM red_voznje;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("ID | Destinacija");
            System.out.println("----------------");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("id");
                String destinacija = rs.getString("destinacija");
                System.out.println(id + " | " + destinacija);
            }

            if (!hasData) {
                System.out.println("Nema dostupnih destinacija.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }

    private int findZaposleniId(String ime, String prezime) {
        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT id FROM zaposleni WHERE ime = '" + ime + "' AND prezime = '" + prezime + "';";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1; // Not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            dbc.close(conn);
        }
    }

    
    private int findVozacId(String ime, String prezime) {
        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT v.id FROM vozac v " +
                         "JOIN zaposleni z ON v.zaposleni_id = z.id " +
                         "WHERE z.ime = '" + ime + "' AND z.prezime = '" + prezime + "';";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1; // Not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            dbc.close(conn);
        }
    }
    
    
    
    
    
    private void displayAvailableTrainsForDriver(int vozacId) {
        Connection conn = dbc.open();

        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT v.id, v.oznaka, t.naziv_tipa " +
                         "FROM voz v " +
                         "JOIN vozac_tip_voza vt ON v.tip_voza_id = vt.tip_voza_id " +
                         "JOIN tip_voza t ON v.tip_voza_id = t.id " +
                         "WHERE vt.vozac_id = " + vozacId + ";";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Dostupni vozovi za vozaca:");
            System.out.println("ID | Tip Voza");
            System.out.println("----------------");

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;

                int vozId = rs.getInt("id");
                String nazivTipa = rs.getString("naziv_tipa");

                System.out.println(vozId + " | " + nazivTipa);
            }

            if (!hasData) {
                System.out.println("Nema dostupnih vozova za ovog vozača.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
    }

    
    public boolean provjeriDozvoluZaVoz(int vozacId, int vozId) {
        Connection conn = dbc.open();
        boolean dozvoljeno = false;
        try {
            Statement stmt = conn.createStatement();
            
            // Prvo uzimamo tip voza iz voz tabele
            String sqlTipVoza = "SELECT tip_voza_id FROM voz WHERE id = " + vozId;
            ResultSet rsTip = stmt.executeQuery(sqlTipVoza);

            if (rsTip.next()) {
                int tipVozaId = rsTip.getInt("tip_voza_id");

                // Sada proveravamo da li vozač ima dozvolu za ovaj tip voza
                String sqlCheck = "SELECT * FROM vozac_tip_voza " +
                                  "WHERE vozac_id = " + vozacId + 
                                  " AND tip_voza_id = " + tipVozaId;
                ResultSet rsCheck = stmt.executeQuery(sqlCheck);
                
                dozvoljeno = rsCheck.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.close(conn);
        }
        return dozvoljeno;
    }

    
    
    

}