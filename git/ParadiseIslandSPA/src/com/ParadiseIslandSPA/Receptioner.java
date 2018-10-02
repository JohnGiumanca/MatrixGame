package com.ParadiseIslandSPA;

import java.util.*;

public class Receptioner 
{
	static Map<String, Client> clientMapTmp;
	
	
	private Receptioner() 
	{
		
	}
	
	public static boolean setNewClient(String email, String password, String nume, String prenume)
	{	
		Client clientTmp = new Client(email, nume, prenume);
		
		BazaDeDate.addNewClient(clientTmp, password);	
		
		//System.out.println("email " + email + "passwrod " + password + "nume " + nume + "prenume " + prenume) ;
		
		if(BazaDeDate.addNewClient(clientTmp, password))
		{
			clientMapTmp.put(clientTmp.getEmail(), clientTmp);
			
			return true;
		}
		else
			return false;
	}
	
	
	public static boolean loginClient(String email, String password)
	{
		//System.out.println("email " + email + "  " + "password " + password);
		return BazaDeDate.loginValidation(email, password);
	}
	
	public static Boolean cumparareProdus(List<DetaliiProdus> dpList, String email)
	{				
		return getClient(email).cumparareProdus(dpList);
	}
	
	//TO DO with JOHN
	public static List<DetaliiProdus> afisareProduse(String tipProdus)
	{
		return BazaDeDate.getListaProduse(tipProdus);		
	}
	
	//TO DO with JOHN hardcoded atm
	public static List<String> afisareCategorii()
	{
		List<String> typeList = new LinkedList<String>();
		typeList.add("Zone");
		typeList.add("Bar");
		typeList.add("Restaurant");
		typeList.add("Servicii");
		
		return typeList;
	}
	
	//TO DO with JOHN
	public static Client getClient(String email)
	{
		return clientMapTmp.get(email);
	}
	
	public static String requestAcces(String idZona, String email)
	{
		return getClient(email).requestAcces(idZona);
	}
	
	public static double getBilantClient(String email)
	{
		return getClient(email).getPortofelulMeu().getBilant();
	}

	public static List<DetaliiProdus> getChitanta(String email)
	{
		List<DetaliiProdus> chitantaTmp =  getClient(email).getChitanta();
				
		
		getClient(email).getPortofelulMeu().reset();	
		
		clientMapTmp.remove(email);
		
		return chitantaTmp;
	}
	

}
