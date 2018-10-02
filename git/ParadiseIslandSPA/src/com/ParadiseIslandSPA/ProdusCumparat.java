package com.ParadiseIslandSPA;

import java.util.*;

public class ProdusCumparat 
{
	int cantitateProdus;
	double pretTotalProdus;
	String numeProdus;
	String tipProdus;
	DetaliiProdus dpTmp;
	
	public ProdusCumparat(String idProdus, int cantitate)
	{
		dpTmp = BazaDeDate.getProdusById(idProdus);
		numeProdus = dpTmp.getNumeProdus();
		//tipProdus = dpTmp.getTipProdus();
		pretTotalProdus = cantitate * dpTmp.getPretProdus();
		cantitateProdus = cantitate;		
	}

	public void setCantitateProdus(int cantitate)
	{
		cantitateProdus += cantitate; 
	}
	
	public String getNumeProdus()
	{
		return numeProdus;
	}
}
