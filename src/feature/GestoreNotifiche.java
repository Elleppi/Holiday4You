package feature;

import java.util.List;

import attori.UtenteRegistrato;

public class GestoreNotifiche {
	
	/*Cerca nel DB un legale con il minimo numero di Prenotazioni (tipo == 0) o locali (tipo == 1)*/
	public static int assegnaLegale(int tipo) {
		GestoreDB gdb = GestoreDB.getInstance();
		
		int IDlegale = -1;
		List<UtenteRegistrato> l = gdb.caricaRuolo("Legale");
		
		if(tipo == 0) {
			int min = l.get(0).getnPrenotazioni();
			IDlegale = l.get(0).getID();
			
			for(int i=1; i<l.size(); i++) {
				if(l.get(i).getnPrenotazioni() < min) {
					min = l.get(i).getnPrenotazioni();
					IDlegale = l.get(i).getID();
				}
			}
		}
		if(tipo == 1) {
			int min = l.get(0).getnLocali();
			IDlegale = l.get(0).getID();
			
			for(int i=1; i<l.size(); i++) {
				if(l.get(i).getnLocali() < min) {
					min = l.get(i).getnLocali();
					IDlegale = l.get(i).getID();
				}
			}
		}
		
		return IDlegale;
	}
	
	
	/*Cerca nel DB uno scout con il minimo numero di locali */
	public static int assegnaScout() {
		GestoreDB gdb = GestoreDB.getInstance();
		
		int IDscout = -1;
		List<UtenteRegistrato> l = gdb.caricaRuolo("Scout");
	
		int min = l.get(0).getnLocali();
		IDscout = l.get(0).getID();
		
		for(int i=1; i<l.size(); i++) {
			if(l.get(i).getnLocali() < min) {
				min = l.get(i).getnLocali();
				IDscout = l.get(i).getID();
			}
		}
	
		return IDscout;
	}
	
	
	
	
}






