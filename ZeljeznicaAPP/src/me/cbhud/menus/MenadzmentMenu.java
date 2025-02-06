package me.cbhud.menus;

import java.util.Scanner;
import me.cbhud.DbConnector;
import me.cbhud.crud.vozacCRUD;
import me.cbhud.crud.zaposleniCRUD;
import me.cbhud.entiteti.tipVoza;
import me.cbhud.crud.Statistika;
import me.cbhud.crud.medicinskiPregledCRUD;
import me.cbhud.crud.tipVozaCRUD;
import me.cbhud.crud.vozCRUD;

public class MenadzmentMenu {

    Scanner scanner = new Scanner(System.in);
    private DbConnector dbc;

    public MenadzmentMenu(DbConnector dbc) {
        this.dbc = dbc;
    }

    public void menadzment() {
        while (true) {
            System.out.println("================");
            System.out.println("STRANICA MENADŽMENTA");
            System.out.println("1 - Upravljanje radnicima");
            System.out.println("2 - Upravljanje vozovima");
            System.out.println("3 - Prikaz statistika poslovanja");
            System.out.println("0 - Povratak");
            System.out.print("Unesite izbor: ");

            int izbor = Integer.parseInt(scanner.nextLine());

            switch (izbor) {
                case 1:
                    menadzmentRadnika();
                    break;
                case 2:
                	menadzmentVozova();
                	break;
                case 3:
                	menadzmentStatistika();
                	break;
                case 0:
                    return;
                default:
                    System.out.println("Pogrešan unos, pokušajte ponovo.");
            }
        }
    }

    public void menadzmentRadnika() {
        zaposleniCRUD zcr = new zaposleniCRUD(dbc);
        vozacCRUD vzcr = new vozacCRUD(dbc);
        medicinskiPregledCRUD mcr = new medicinskiPregledCRUD(dbc);

        while (true) {
            System.out.println("================");
            System.out.println("Upravljajte radnicima");
            System.out.println("1 - Dodaj radnika");
            System.out.println("2 - Obrisi radnika");
            System.out.println("3 - Izmijeni radnika");
            System.out.println("4 - Prikaz radnika");
            System.out.println("5 - Dodaj vozaca");
            System.out.println("6 - Obrisi vozaca");
            System.out.println("7 - Izmijeni vozaca");
            System.out.println("8 - Prikaz vozaca");
            System.out.println("9 - Dodaj medicinski pregled vozacu (dodaje se iz fajla)");
            System.out.println("10 - Prikaz pregleda svih vozaca");
            System.out.println("0 - Povratak");
            System.out.print("Unesite izbor: ");

            int izbor = Integer.parseInt(scanner.nextLine());
            
            switch (izbor) {
                case 1:
                    zcr.createZaposleni();
                    break;
                case 2:
                    zcr.deleteZaposleni();
                    break;
                case 3:
                    zcr.updateZaposleni();
                    break;
                case 4:
                    zcr.readZaposleni();
                    break;
                case 5:
                    vzcr.createVozac();
                    break;
                case 6:
                    vzcr.deleteVozac();
                    break;
                case 7:
                    vzcr.updateVozac();
                    break;
                case 8:
                    vzcr.readVozaci();
                    break;
                case 9:
                	mcr.createPregled();
                	break;
                case 10:
                	mcr.readPregledi();
                	break;
                case 0:
                    return;
                default:
                    System.out.println("Pogrešan unos, pokušajte ponovo.");
            }
        }
    }
    
    public void menadzmentVozova() {
    	tipVozaCRUD tvc = new tipVozaCRUD(dbc);
    	vozCRUD vcr = new vozCRUD(dbc);
    	

        while (true) {
            System.out.println("================");
            System.out.println("Upravljajte radnicima");
            System.out.println("1 - Dodaj tip voza");
            System.out.println("2 - Obrisi tip voza");
            System.out.println("3 - Izmijeni tip voza");
            System.out.println("4 - Prikaz tipova");
            System.out.println("5 - Dodaj voz");
            System.out.println("6 - Obrisi voz");
            System.out.println("7 - Izmijeni voz");
            System.out.println("8 - Prikaz vozova");
            System.out.println("9 - Dodijeli voz vozacu");
            System.out.println("0 - Povratak");
            System.out.print("Unesite izbor: ");

            int izbor = Integer.parseInt(scanner.nextLine());

            switch (izbor) {
                case 1:
                	tvc.createTipVoza();
                	break;
                case 2:
                    tvc.deleteTipVoza();
                    break;
                case 3:
                	tvc.updateTipVoza();
                	break;
                case 4:
                    tvc.readAllTipVoza();
                    break;
                case 5:
                    vcr.createVoz();
                    break;
                case 6:
                    vcr.deleteVoz();
                    break;
                case 7:
                	vcr.updateVozBrojVagona();
                	break;
                case 8:
                    vcr.readAllVoz();
                    break;
                case 9:
                	tvc.dodijeliVozVozacu();
                	break;
                case 0:
                    return;
                default:
                    System.out.println("Pogrešan unos, pokušajte ponovo.");
            }
        }
    }
    
    
    public void menadzmentStatistika() {
        Statistika statistika = new Statistika(dbc);

        while (true) {
            System.out.println("================");
            System.out.println("STATISTIKA POSLOVANJA");
            System.out.println("1 - Zarada po destinaciji");
            System.out.println("2 - Broj prodatih karata po destinaciji");
            System.out.println("3 - Prosecna starost vozova");
            System.out.println("4 - Broj redova voznje po zaposlenom");
            System.out.println("5 - Broj voznji po vozacu");
            System.out.println("6 - Vozaci sa satima iznad prosjeka");
            System.out.println("0 - Povratak");
            System.out.print("Unesite izbor: ");

            int izbor = Integer.parseInt(scanner.nextLine());

            switch (izbor) {
                case 1:
                	statistika.zaradaPoDestinaciji();
                    break;
                case 2:
                	statistika.brojProdatihKarataPoDestinaciji();
                    break;
                case 3:
                	statistika.prosecnaStarostVozova();
                    break;
                case 4:
                	statistika.brojRedovaVoznjePoZaposlenom();
                    break;
                case 5:
                	statistika.brojVoznjiPoVozacu();
                    break;
                case 6:
                    statistika.vozaciIznadProsekaSati();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Pogrešan unos, pokušajte ponovo.");
            }
        }
    }

    
    
}
