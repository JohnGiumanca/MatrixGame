package com.ParadiseIslandSPA;

import java.util.List;

public class TestBazaDeDate {

	public static void main(String[] args) {
		
//		DetaliiProdus produs = BazaDeDate.getProdusById("5");
//		
//		System.out.println(produs.getNumeProdus());
//		System.out.println(produs.getPretProdus());
//		System.out.println(produs.getTipProdus());
		
//		Client client = new Client("Zaraza", "Carlos", "carlitozzz@corunia.com");
//		
//		if(BazaDeDate.addNewClient(client, "123456")) {
//			System.out.println("Succes");
//		}
		
		if(BazaDeDate.loginValidation("carlitozzz@corunia.com", "123456")) {
			System.out.println("Valid login info");
		}else {
			System.out.println("NOT Valid login info");
		}
//		
//		List<DetaliiProdus> lista = BazaDeDate.getListaProduse("Bar");
//		
//		for (DetaliiProdus pr : lista) {
//			System.out.println(pr.getNumeProdus());
//			
//		}
		
	}
}
