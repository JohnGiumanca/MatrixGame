package com.ParadiseIslandSPA;

public class DetaliiProdus 
{
	private String idProdus;
	private String numeProdus;
	private double pretProdus;
	private double pretTotalProdus;
	private Integer cantitateProdus;
	
	public DetaliiProdus(String idProdus, String numeProdus, double pretProdus, Integer cantitate) 
	{
		super();
		this.numeProdus = numeProdus;
		this.pretProdus = pretProdus;
		this.idProdus = idProdus;
		this.cantitateProdus = cantitate;
		
		pretTotalProdus = pretProdus*cantitate;
	}
	
	
	public String getNumeProdus() 
	{
		return numeProdus;
	}
	
	public Integer getCantitateProdus() 
	{
		return cantitateProdus;
	}

	public double getPretProdus() 
	{
		return pretProdus;
	}
	
	public String getIdProdus()
	{
		return idProdus;
	}
	
	public double getPretTotalProdus()
	{
		return pretTotalProdus;
	}
	
	public void updateProdus(int cantitate)
	{
		cantitateProdus += cantitate; 
		
		pretTotalProdus = cantitateProdus * pretProdus;
	}
	
	
}
