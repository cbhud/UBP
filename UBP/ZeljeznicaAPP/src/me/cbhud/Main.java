package me.cbhud;

import me.cbhud.menus.Menus;

public class Main {
	public static void main(String[] args) {
		
		DbConnector dbc = new DbConnector("root", "", "127.0.0.1", "3306", "zeljeznicka");
		
		Menus menu = new Menus(dbc);
		
	}
}