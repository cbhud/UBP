package me.cbhud.menus;

import java.util.Scanner;

import me.cbhud.DbConnector;
import me.cbhud.crud.KartaCRUD;
import me.cbhud.crud.RedVoznjeCRUD;

public class PutnikMenu {
    private DbConnector dbc;
    private Scanner scanner = new Scanner(System.in);

    public PutnikMenu(DbConnector dbc) {
        this.dbc = dbc;
    }

    public void putnikMenu() {
        KartaCRUD kartaCRUD = new KartaCRUD(dbc);
        RedVoznjeCRUD redVoznjeCRUD = new RedVoznjeCRUD(dbc);

        while (true) {
            System.out.println("================");
            System.out.println("MENI ZA PUTNIKE");
            System.out.println("1 - Prikaži sve destinacije");
            System.out.println("2 - Kupi kartu");
            System.out.println("0 - Povratak");
            System.out.print("Unesite izbor: ");

            int izbor = Integer.parseInt(scanner.nextLine());


            switch (izbor) {
                case 1:
                    redVoznjeCRUD.readDestinacije();
                    break;
                case 2:
                    kartaCRUD.createKarta();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Pogrešan unos, pokušajte ponovo.");
            }
        }
    }
}
