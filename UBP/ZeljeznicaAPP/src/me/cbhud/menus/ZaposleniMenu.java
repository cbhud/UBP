package me.cbhud.menus;

import java.util.Scanner;

import me.cbhud.DbConnector;
import me.cbhud.crud.KartaCRUD;
import me.cbhud.crud.RedVoznjeCRUD;

public class ZaposleniMenu {
    private DbConnector dbc;
    private Scanner scanner = new Scanner(System.in);

    public ZaposleniMenu(DbConnector dbc) {
        this.dbc = dbc;
    }

    public void zaposleniMenu() {
        RedVoznjeCRUD redVoznjeCRUD = new RedVoznjeCRUD(dbc);
        KartaCRUD kartaCRUD = new KartaCRUD(dbc);

        while (true) {
            System.out.println("=========================");
            System.out.println("MENI ZA ZAPOSLENE");
            System.out.println("1 - Kreiraj red vožnje");
            System.out.println("2 - Prikaži sve redove vožnje");
            System.out.println("3 - Ažuriraj red vožnje");
            System.out.println("4 - Obriši red vožnje");
            System.out.println("5 - Kreiraj kartu");
            System.out.println("6 - Prikaži sve karte");
            System.out.println("7 - Obrisi kartu");
            System.out.println("0 - Povratak");
            System.out.print("Unesite izbor: ");

            int izbor = Integer.parseInt(scanner.nextLine());


            switch (izbor) {
                case 1:
                    redVoznjeCRUD.createRedVoznje();
                    break;
                case 2:
                    redVoznjeCRUD.readAllRedVoznje();
                    break;
                case 3:
                    redVoznjeCRUD.updateRedVoznje();
                    break;
                case 4:
                    redVoznjeCRUD.deleteRedVoznje();
                    break;
                case 5:
                    kartaCRUD.createKarta();
                    break;
                case 6:
                    kartaCRUD.readAllKarte();
                    break;
                case 7:
                    kartaCRUD.deleteKarta();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Pogrešan unos, pokušajte ponovo.");
            }
        }
    }
}
