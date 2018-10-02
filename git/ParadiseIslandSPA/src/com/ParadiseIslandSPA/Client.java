package com.ParadiseIslandSPA;

import java.util.*;

public class Client 
{
	private String email;
	private String prenume;
	private String nume;
	private PortofelVirtual portofelulMeu;
	private String zona = "";

	public Client(String e, String p, String n)
	{
		email = e;
		prenume = p;
		nume = n;
		
		portofelulMeu = new PortofelVirtual();		
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getNume()
	{
		return nume;
	}
	
	public String getPrenume()
	{
		return prenume;
	}
	
	public PortofelVirtual getPortofelulMeu()
	{
		return portofelulMeu;
	}

	public Boolean cumparareProdus(List<DetaliiProdus> dpList)
	{				
		return portofelulMeu.adaugaBilant(dpList);
	}
	
	public List<DetaliiProdus> getChitanta()
	{
		return portofelulMeu.getChitanta();
	}
	
	//To Do return boolean
	public String requestAcces(String idZona)
	{
		try		
		{
			portofelulMeu.getProdus(idZona);
			
			String zonaTmp = zona; 
			zona = portofelulMeu.getProdus(idZona).getNumeProdus();
			
			if(zona == zonaTmp)
				return "Sunteti deja in aceasta zona";
			
			if(zona == "ZonaA")
				ZoneStats.nrPersoaneZonaA++;
			else if(zona == "ZonaB")
				ZoneStats.nrPersoaneZonaB++;
			else if(zona == "ZonaC")
				ZoneStats.nrPersoaneZonaB++;
			else if(zona == "ZonaD")
				ZoneStats.nrPersoaneZonaB++;
			
			if(zonaTmp == "ZonaA")
				ZoneStats.nrPersoaneZonaA--;
			else if(zonaTmp == "ZonaB")
				ZoneStats.nrPersoaneZonaB--;
			else if(zonaTmp == "ZonaC")
				ZoneStats.nrPersoaneZonaB--;
			else if(zonaTmp == "ZonaD")
				ZoneStats.nrPersoaneZonaB--;
			
			return "Acces aprobat";
		}catch(Exception e)
		{
			return "Accesul nu va este permis in aceasta zona";
		}
	}

}
