package com.ParadiseIslandSPA;

import java.util.*;

public class Manager 
{
	static Map<String,Integer> statsMapTmp;
	
	public static Map<String,Integer> getZoneStatus()
	{
		statsMapTmp.put("ZonaA", ZoneStats.nrPersoaneZonaA);
		statsMapTmp.put("ZonaB", ZoneStats.nrPersoaneZonaB);
		statsMapTmp.put("ZonaC", ZoneStats.nrPersoaneZonaC);
		statsMapTmp.put("ZonaD", ZoneStats.nrPersoaneZonaD);
		
		return statsMapTmp;
	}
}
