package me.cbhud.menus;

import java.util.Scanner;
import me.cbhud.DbConnector;

public class Menus {

    private final Scanner scanner = new Scanner(System.in);
    private DbConnector dbc;
    
    public Menus(DbConnector dbc) {
        this.dbc = dbc;
        pocetna();
    }

    public void pocetna() {
        MenadzmentMenu m1 = new MenadzmentMenu(dbc);
        ZaposleniMenu m2 = new ZaposleniMenu(dbc);
        PutnikMenu m3 = new PutnikMenu(dbc);
        while (true) {
            System.out.println("================");
            System.out.println("Početni meni programa Željezničke stanice");
            System.out.println("1 - Za opcije menadžmenta");
            System.out.println("2 - Za opcije radnika");
            System.out.println("3 - Za opcije putnika");
            System.out.println("0 - Exit");
            System.out.print("Unesite izbor: ");

            int izbor = Integer.parseInt(scanner.nextLine());


            switch (izbor) {
                case 1:
                    m1.menadzment();
                    break;
                case 2:
                    m2.zaposleniMenu();
                    break;
                case 3:
                	m3.putnikMenu();
                	break;
                case 0:
                    System.out.println("Izlazak iz programa. Doviđenja!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pogrešan unos, pokušajte ponovo.");
            }
        }
    }
}
