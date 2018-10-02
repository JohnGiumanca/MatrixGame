package com.ParadiseIslandSPA;

import java.util.*;

public class ZoneStats 
{
	public static int nrPersoaneZonaA = 0;
	public static int nrPersoaneZonaB = 0;
	public static int nrPersoaneZonaC = 0;
	public static int nrPersoaneZonaD = 0;
	
	public static List<Integer> getZoneStats()
	{
		List<Integer> zoneListTmp = new LinkedList<Integer>();
		zoneListTmp.add(nrPersoaneZonaA);
		zoneListTmp.add(nrPersoaneZonaB);
		zoneListTmp.add(nrPersoaneZonaC);
		zoneListTmp.add(nrPersoaneZonaD);
		
		return zoneListTmp;
	}

}
